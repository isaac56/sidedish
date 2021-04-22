package team16.sidedish.service;

import org.springframework.stereotype.Service;
import team16.sidedish.domain.entity.aggregate.product.Product;
import team16.sidedish.domain.entity.aggregate.provider.Provider;
import team16.sidedish.dto.response.MenuResponseDto;
import team16.sidedish.repository.ProductRepository;
import team16.sidedish.repository.ProviderRepository;
import team16.sidedish.repository.lookup.BadgeRepository;
import team16.sidedish.repository.lookup.DeliveryTypeRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProviderRepository providerRepository;
    private final BadgeRepository badgeRepository;
    private final DeliveryTypeRepository deliveryTypeRepository;

    public ProductService(
            ProductRepository productRepository,
            ProviderRepository providerRepository,
            BadgeRepository badgeRepository,
            DeliveryTypeRepository deliveryTypeRepository) {
        this.productRepository = productRepository;
        this.providerRepository = providerRepository;
        this.badgeRepository = badgeRepository;
        this.deliveryTypeRepository = deliveryTypeRepository;
    }

    public void best() {
        Product product = productRepository.findById(1L).orElseThrow(() -> new IllegalStateException());
        Provider provider = providerRepository.findById(product.getId()).orElseThrow(() -> new IllegalStateException());
        MenuResponseDto.of(product, provider);
    }

}
