package team16.sidedish.service;

import org.springframework.stereotype.Service;
import team16.sidedish.domain.entity.aggregate.product.Product;
import team16.sidedish.domain.entity.aggregate.provider.Provider;
import team16.sidedish.domain.entity.lookUp.Category;
import team16.sidedish.domain.enums.CategoryEnum;
import team16.sidedish.dto.response.CategoryResponseDto;
import team16.sidedish.dto.response.DetailInfoResponseDto;
import team16.sidedish.dto.response.MenuResponseDto;
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

    public List<MenuResponseDto> getMenuListAll() {
        List<Product> products = productRepository.findAll();

        List<MenuResponseDto> menuResponseDtoList = getMenuResponseDtoList(products);
        return menuResponseDtoList;
    }

    public MenuResponseDto getMenu(String hash) {
        Product product = productRepository.findByHash(hash)
                .orElseThrow(IllegalStateException::new);
        Provider provider = providerRepository.findById(product.getProviderId())
                .orElseThrow(IllegalStateException::new);

        return MenuResponseDto.of(product, provider);
    }

    public List<CategoryResponseDto> getBestCategories() {
        List<Category> categories = categoryRepository.findAllByIsBestIsTrue();

        List<CategoryResponseDto> categoryResponseDtoList = new ArrayList<>();
        for (Category category : categories) {
            List<Product> products = productRepository.findAllByCategoryId(category.getId());

            List<MenuResponseDto> menuResponseDtos = getMenuResponseDtoList(products);
            categoryResponseDtoList.add(CategoryResponseDto.of(category.getId(), menuResponseDtos));
        }

        return categoryResponseDtoList;
    }

    public boolean isCategoryBest(Integer categoryId) {
        return LookUpTableUtils.getCategoryById(categoryId).isBest();
    }

    public CategoryResponseDto getCategory(Integer categoryId) {
        List<Product> products = productRepository.findAllByCategoryId(categoryId);

        return CategoryResponseDto.of(categoryId, getMenuResponseDtoList(products));
    }

    public MenuResponseDto getMenu(CategoryEnum categoryEnum, String hash) {
        Product product = productRepository.findByCategoryIdAndHash(categoryEnum.getId(), hash).
                orElseThrow(() -> new NotFoundException(hash + " 제품이 " + categoryEnum + "에 존재하지 않습니다."));
        Provider provider = getProvider(product.getProviderId());

        return MenuResponseDto.of(product, provider);
    }

    public DetailInfoResponseDto getDetail(String hash) {
        Product product = productRepository.findByHash(hash).
                orElseThrow(() -> new NotFoundException(hash + " 제품이 존재하지 않습니다."));
        Provider provider = getProvider(product.getProviderId());

        return DetailInfoResponseDto.of(product, provider);
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
