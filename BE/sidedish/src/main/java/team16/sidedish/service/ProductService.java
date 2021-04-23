package team16.sidedish.service;

import org.springframework.stereotype.Service;
import team16.sidedish.domain.entity.aggregate.product.Product;
import team16.sidedish.domain.entity.aggregate.provider.Provider;
import team16.sidedish.domain.entity.lookUp.Category;
import team16.sidedish.dto.response.BestMenuResponseDto;
import team16.sidedish.dto.response.MenuResponseDto;
import team16.sidedish.dto.response.list.BestMenuListResponseDto;
import team16.sidedish.dto.response.list.MenuListResponseDto;
import team16.sidedish.repository.ProductRepository;
import team16.sidedish.repository.ProviderRepository;
import team16.sidedish.repository.lookup.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

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

    public MenuListResponseDto menuList() {
        List<Product> products = (List<Product>) productRepository.findAll();
        List<MenuResponseDto> menuResponseDtos = new ArrayList<>();
        for (Product product : products) {
            Provider provider = getProviderByProduct(product);
            menuResponseDtos.add(MenuResponseDto.of(product, provider));
        }
        return MenuListResponseDto.of(menuResponseDtos);
    }

    public MenuResponseDto menu(String detailhash) {
        Product product = productRepository.findByHash(detailhash)
                .orElseThrow(IllegalStateException::new);
        Provider provider = getProviderByProduct(product);
        return MenuResponseDto.of(product, provider);
    }

    private Provider getProviderByProduct(Product product) {
        return providerRepository.findById(product.getProviderId())
                .orElseThrow(IllegalStateException::new);
    }

    public BestMenuListResponseDto bestMenuList() {
        List<Category> categories = categoryRepository.findAll();
        List<MenuResponseDto> menuResponseDtos;
        List<BestMenuResponseDto> bestMenuResponseDtos = new ArrayList<>();

        for (Category category : categories) {
            if (category.isBest()) {
                List<Product> products = productRepository.findAllByCategoryId(category.getId());
                menuResponseDtos = getMenuResponseDtosByProducts(products);
                bestMenuResponseDtos.add(BestMenuResponseDto.of(category, menuResponseDtos));
            }
        }
        return BestMenuListResponseDto.of(bestMenuResponseDtos);
    }

    public BestMenuResponseDto bestMenu(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(IllegalStateException::new);
        List<MenuResponseDto> menuResponseDtos = null;

        if (category.isBest()) {
            List<Product> products = productRepository.findAllByCategoryId(category.getId());
            menuResponseDtos = getMenuResponseDtosByProducts(products);
        }
        return BestMenuResponseDto.of(category, menuResponseDtos);
    }

    private List<MenuResponseDto> getMenuResponseDtosByProducts(List<Product> products) {
        List<MenuResponseDto> menuResponseDtos = new ArrayList<>();
        for (Product product : products) {
            Provider provider = getProviderByProduct(product);
            menuResponseDtos.add(MenuResponseDto.of(product, provider));
        }
        return menuResponseDtos;
    }
}
