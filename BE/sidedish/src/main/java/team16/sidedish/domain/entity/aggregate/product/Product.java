package team16.sidedish.domain.entity.aggregate.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.HashSet;
import java.util.Set;

public class Product {
    @Id
    private Long id;

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

    public Product(Long providerId, String name, String description, Integer priceOriginal, Integer priceDiscount, int point, String topImageUrl, int stock) {
        this.providerId = providerId;
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
            this.images.add(image);
        }
    }

    public void removeImage(ProductImage image) {
        this.images.remove(image);
    }

    public void addBadgeRef(ProductBadge... productBadges) {
        for (ProductBadge productBadge : productBadges) {
            this.productBadges.add(productBadge);
        }
    }

    public void removeBadgeRef(ProductBadge productBadge) {
        this.productBadges.remove(productBadge);
    }

    public void addCategoryRef(ProductCategory... productCategories) {
        for (ProductCategory productCategory : productCategories) {
            this.productCategories.add(productCategory);
        }
    }

    public void removeCategoryRef(ProductCategory productCategory) {
        this.productCategories.remove(productCategory);
    }

    public Long getId() {
        return id;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriceOriginal() {
        return priceOriginal;
    }

    public void setPriceOriginal(Integer priceOriginal) {
        this.priceOriginal = priceOriginal;
    }

    public Integer getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(Integer priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getTopImageUrl() {
        return topImageUrl;
    }

    public void setTopImageUrl(String topImageUrl) {
        this.topImageUrl = topImageUrl;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Set<ProductBadge> getProductBadges() {
        return productBadges;
    }

    public void setProductBadges(Set<ProductBadge> productBadges) {
        this.productBadges = productBadges;
    }

    public Set<ProductImage> getImages() {
        return images;
    }

    public void setImages(Set<ProductImage> images) {
        this.images = images;
    }

    public Set<ProductCategory> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(Set<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }
}
