package team16.sidedish.service;

import org.springframework.stereotype.Service;
import team16.sidedish.domain.entity.aggregate.product.Product;
import team16.sidedish.domain.entity.aggregate.provider.Provider;
import team16.sidedish.dto.response.MenuResponseDto;
import team16.sidedish.dto.response.list.MenuListResponseDto;
import team16.sidedish.repository.ProductRepository;
import team16.sidedish.repository.ProviderRepository;
import team16.sidedish.repository.lookup.BadgeRepository;
import team16.sidedish.repository.lookup.DeliveryTypeRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProviderRepository providerRepository;

    public ProductService(
            ProductRepository productRepository,
            ProviderRepository providerRepository) {
        this.productRepository = productRepository;
        this.providerRepository = providerRepository;
    }

    public MenuListResponseDto menu() {
        MenuListResponseDto menuListResponseDto = new MenuListResponseDto();
        List<Product> products = (List<Product>) productRepository.findAll();
        Set<MenuResponseDto> menuResponseDtoSet = new HashSet<>();
        for (Product product : products) {
            Provider provider = getProviderByProduct(product);
            menuResponseDtoSet.add(MenuResponseDto.of(product, provider));
        }
        menuListResponseDto.setBody(menuResponseDtoSet);
        return menuListResponseDto;
    }

    private Provider getProviderByProduct(Product product) {
        return providerRepository.findById(product.getProviderId())
                .orElseThrow(IllegalStateException::new);
    }
}
