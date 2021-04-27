package team16.sidedish.service;

import org.springframework.stereotype.Service;
import team16.sidedish.domain.entity.aggregate.product.Product;
import team16.sidedish.domain.entity.aggregate.provider.Provider;
import team16.sidedish.domain.entity.lookUp.Category;
import team16.sidedish.domain.enums.CategoryEnum;
import team16.sidedish.dto.response.BestMenuResponseDto;
import team16.sidedish.dto.response.MenuResponseDto;
import team16.sidedish.dto.response.detail.DetailResponseDto;
import team16.sidedish.dto.response.list.BestMenuListResponseDto;
import team16.sidedish.dto.response.list.MenuListResponseDto;
import team16.sidedish.exception.NotFoundException;
import team16.sidedish.repository.ProductRepository;
import team16.sidedish.repository.ProviderRepository;
import team16.sidedish.repository.lookup.CategoryRepository;
import team16.sidedish.utils.LookUpTableUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProviderRepository providerRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(
            ProductRepository productRepository,
            ProviderRepository providerRepository,
            CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.providerRepository = providerRepository;
        this.categoryRepository = categoryRepository;
    }

    public MenuListResponseDto getAllMenuList() {
        List<Product> products = productRepository.findAll();

        List<MenuResponseDto> menuResponseDtoList = getMenuResponseDtoList(products);
        return MenuListResponseDto.of(menuResponseDtoList);
    }

    public MenuResponseDto getMenu(String hash) {
        Product product = productRepository.findByHash(hash)
                .orElseThrow(IllegalStateException::new);
        Provider provider = providerRepository.findById(product.getProviderId())
                .orElseThrow(IllegalStateException::new);

        return MenuResponseDto.of(product, provider);
    }

    public BestMenuListResponseDto getBestAllMenuList() {
        List<Category> categories = categoryRepository.findAllByIsBestIsTrue();

        List<BestMenuResponseDto> bestMenuResponseDtoList = new ArrayList<>();
        for (Category category : categories) {
            List<Product> products = productRepository.findAllByCategoryId(category.getId());

            List<MenuResponseDto> menuResponseDtos = getMenuResponseDtoList(products);
            bestMenuResponseDtoList.add(BestMenuResponseDto.of(category.getId(), menuResponseDtos));
        }

        return BestMenuListResponseDto.of(bestMenuResponseDtoList);
    }

    public BestMenuResponseDto getBestCategoryMenuList(Integer categoryId) {
        if (!LookUpTableUtils.getCategoryById(categoryId).isBest()) {
            throw new NotFoundException(categoryId + "는 베스트 메뉴 카테고리가 아닙니다.");
        }

        return BestMenuResponseDto.of(categoryId, getCategoryMenuList(categoryId));
    }

    private List<MenuResponseDto> getCategoryMenuList(Integer categoryId) {
        List<Product> products = productRepository.findAllByCategoryId(categoryId);

        return getMenuResponseDtoList(products);
    }

    public MenuListResponseDto getMenuList(CategoryEnum categoryEnum) {
        return MenuListResponseDto.of(getCategoryMenuList(categoryEnum.getId()));
    }

    public MenuResponseDto getMenu(CategoryEnum categoryEnum, String hash) {
        Product product = productRepository.findByCategoryIdAndHash(categoryEnum.getId(), hash).
                orElseThrow(() -> new NotFoundException(hash + " 제품이 " + categoryEnum + "에 존재하지 않습니다."));
        Provider provider = getProvider(product.getProviderId());

        return MenuResponseDto.of(product, provider);
    }

    public DetailResponseDto getDetail(String hash) {
        Product product = productRepository.findByHash(hash).
                orElseThrow(() -> new NotFoundException(hash + " 제품이 존재하지 않습니다."));
        Provider provider = getProvider(product.getProviderId());

        return DetailResponseDto.of(product, provider);
    }

    private Provider getProvider(Long providerId) {
        return providerRepository.findById(providerId)
                .orElseThrow(() -> new NotFoundException(providerId + "번 업체는 존재하지 않습니다."));
    }

    private List<MenuResponseDto> getMenuResponseDtoList(List<Product> products) {
        Map<Long, Provider> providers = getProviders(products);

        List<MenuResponseDto> menuResponseDtoList = new ArrayList<>();
        for (Product product : products) {
            Provider provider = providers.getOrDefault(product.getProviderId(), null);
            if (provider == null) {
                throw new NotFoundException(product.getProviderId() + "번 업체는 존재하지 않습니다.");
            }

            menuResponseDtoList.add(MenuResponseDto.of(product, provider));
        }
        return menuResponseDtoList;
    }

    private Map<Long, Provider> getProviders(List<Product> products) {
        List<Long> providerIds = products.stream().map(Product::getProviderId).collect(Collectors.toList());

        return providerRepository.findAllById(providerIds).
                stream().collect(Collectors.toMap(Provider::getId, Function.identity()));
    }
}
