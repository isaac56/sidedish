package team16.sidedish.domain.entity.aggregate.product;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"hash"})
public class Product {
    @Id
    private Long id;

    private String hash;

    private Long providerId;

    private String name;

    private String description;

    private Integer priceOriginal;

    private Integer priceDiscount;

    private int point;

    private String topImageUrl;

    private int stock;

    @MappedCollection(idColumn = "product_id")
    private Set<ProductBadge> productBadges = new HashSet<>();

    @MappedCollection(idColumn = "product_id")
    private Set<ProductImage> images = new HashSet<>();

    @MappedCollection(idColumn = "product_id")
    private Set<ProductCategory> productCategories = new HashSet<>();

    public Product(Long providerId, String hash, String name, String description, Integer priceOriginal, Integer priceDiscount, int point, String topImageUrl, int stock) {
        this.providerId = providerId;
        this.hash = hash;
        this.name = name;
        this.description = description;
        this.priceOriginal = priceOriginal;
        this.priceDiscount = priceDiscount;
        this.point = point;
        this.topImageUrl = topImageUrl;
        this.stock = stock;
    }

    public void addImage(ProductImage... images) {
        for (ProductImage image : images) {
            image.setProductId(this.id);
            this.images.add(image);
        }
    }

    public void removeImage(ProductImage image) {
        this.images.remove(image);
    }

    public void addBadgeRef(ProductBadge... productBadges) {
        for (ProductBadge productBadge : productBadges) {
            productBadge.setProductId(this.id);
            this.productBadges.add(productBadge);
        }
    }

    public void removeBadgeRef(ProductBadge productBadge) {
        this.productBadges.remove(productBadge);
    }

    public void addCategoryRef(ProductCategory... productCategories) {
        for (ProductCategory productCategory : productCategories) {
            productCategory.setProductId(this.id);
            this.productCategories.add(productCategory);
        }
    }

    public void removeCategoryRef(ProductCategory productCategory) {
        this.productCategories.remove(productCategory);
    }
}
