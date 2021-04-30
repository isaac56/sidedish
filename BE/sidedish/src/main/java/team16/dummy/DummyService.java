package team16.dummy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team16.dummy.requestDTO.DetailData;
import team16.dummy.requestDTO.DetailDataWrapper;
import team16.dummy.requestDTO.SimpleData;
import team16.sidedish.domain.entity.aggregate.product.BadgeRef;
import team16.sidedish.domain.entity.aggregate.product.CategoryRef;
import team16.sidedish.domain.entity.aggregate.product.Product;
import team16.sidedish.domain.entity.aggregate.product.ProductImage;
import team16.sidedish.domain.entity.aggregate.provider.DeliveryTypeRef;
import team16.sidedish.domain.entity.aggregate.provider.Provider;
import team16.sidedish.domain.entity.lookUp.Badge;
import team16.sidedish.domain.entity.lookUp.Category;
import team16.sidedish.domain.entity.lookUp.DeliveryType;
import team16.sidedish.repository.ProductRepository;
import team16.sidedish.repository.ProviderRepository;
import team16.sidedish.repository.lookup.BadgeRepository;
import team16.sidedish.repository.lookup.CategoryRepository;
import team16.sidedish.repository.lookup.DeliveryTypeRepository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DummyService {
    private BadgeRepository badgeRepository;
    private CategoryRepository categoryRepository;
    private DeliveryTypeRepository deliveryTypeRepository;
    private ProductRepository productRepository;
    private ProviderRepository providerRepository;

    @Autowired
    public DummyService(BadgeRepository badgeRepository, CategoryRepository categoryRepository,
                        DeliveryTypeRepository deliveryTypeRepository, ProductRepository productRepository,
                        ProviderRepository providerRepository) {
        this.badgeRepository = badgeRepository;
        this.categoryRepository = categoryRepository;
        this.deliveryTypeRepository = deliveryTypeRepository;
        this.productRepository = productRepository;
        this.providerRepository = providerRepository;
    }

    private Map<String, Badge> getStringBadgeMap() {
        return badgeRepository.findAll().stream().collect(Collectors.toMap(Badge::getName, Function.identity()));
    }

    private Map<String, Category> getStringCategoryMap() {
        return categoryRepository.findAll().stream().collect(Collectors.toMap(Category::getName, Function.identity()));
    }

    private Map<String, DeliveryType> getStringDeliveryTypeMap() {
        return deliveryTypeRepository.findAll().stream().collect(Collectors.toMap(DeliveryType::getName, Function.identity()));
    }

    private Badge addBadge(String name) {
        Badge existBadge = badgeRepository.findByName(name).orElse(null);
        if (existBadge != null) {
            return existBadge;
        }

        Badge badge = badgeRepository.save(new Badge(name));
        return badge;
    }

    private Category addCategory(String name, boolean isBest) {
        Category existCategory = categoryRepository.findByName(name).orElse(null);
        if (existCategory != null) {
            return existCategory;
        }

        Category category = categoryRepository.save(new Category(name, isBest));
        return category;
    }

    private DeliveryType addDeliveryType(String name, String message) {
        DeliveryType existDeliveryType = deliveryTypeRepository.findByName(name).orElse(null);
        if (existDeliveryType != null) {
            return existDeliveryType;
        }

        DeliveryType deliveryType = deliveryTypeRepository.save(new DeliveryType(name, message));
        return deliveryType;
    }

    private Provider addProvider(Provider provider) {
        Provider existProvider = providerRepository.findByName(provider.getName()).orElse(null);
        if (existProvider != null) {
            return existProvider;
        }

        return providerRepository.save(provider);
    }

    private Product addProduct(Product product) {
        Product existProduct = productRepository.findByHash(product.getHash()).orElse(null);
        if (existProduct != null) {
            return existProduct;
        }

        return productRepository.save(product);
    }

    public void importSimpleData(List<SimpleData> simpleDataList, String categoryName, boolean isBest) {
        for (SimpleData simpleData : simpleDataList) {
            //provider 삽입
            Provider provider = addProvider(new Provider(simpleData.getProvider(), 2500, 40000));
            if (simpleData.getDelivery_type() != null) {
                for (String deliveryTypeName : simpleData.getDelivery_type()) {
                    DeliveryType deliveryType = addDeliveryType(deliveryTypeName, "");

                    provider.addDeliveryTypeRef(new DeliveryTypeRef(deliveryType.getId()));
                }
            }
            providerRepository.save(provider);

            //product 삽입
            Product product = new Product(provider.getId(), simpleData.getDetail_hash(), simpleData.getName(), simpleData.getDescription(),
                    simpleData.getOriginalPrice(), simpleData.getDiscountPrice(), 0, simpleData.getImage(), 10);
            product = addProduct(product);
            if (simpleData.getBadge() != null) {
                for (String badgeName : simpleData.getBadge()) {
                    Badge badge = addBadge(badgeName);
                    product.addBadgeRef(new BadgeRef(badge.getId()));
                }
            }
            Category category = addCategory(categoryName, isBest);
            product.addCategoryRef(new CategoryRef(category.getId()));
            productRepository.save(product);
        }
    }

    public void updateDetailData(List<DetailDataWrapper> detailDataWrappers) {
        for (DetailDataWrapper detailDataWrapper : detailDataWrappers) {
            String hash = detailDataWrapper.getHash();
            DetailData detailData = detailDataWrapper.getData();

            Product product = productRepository.findByHash(hash).orElse(null);
            if (product != null) {
                if (detailData.getThumb_images() != null) {
                    for (String imageUrl : detailData.getThumb_images()) {
                        product.addImage(new ProductImage(imageUrl, false));
                    }
                }
                if (detailData.getDetail_section() != null) {
                    for (String imageUrl : detailData.getDetail_section()) {
                        product.addImage(new ProductImage(imageUrl, true));
                    }
                }
                product.setPoint(detailData.getPoint());
                productRepository.save(product);
            }
        }
    }
}
