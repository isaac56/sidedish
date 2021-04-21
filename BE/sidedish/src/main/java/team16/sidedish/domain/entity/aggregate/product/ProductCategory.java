package team16.sidedish.domain.entity.aggregate.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("category_has_product")
public class ProductCategory {
    @Id
    private Long id;

    private Integer categoryId;

    private Long productId;

    public ProductCategory(Integer categoryId, Long productId) {
        this.categoryId = categoryId;
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductCategory)) return false;
        ProductCategory that = (ProductCategory) o;
        return Objects.equals(getCategoryId(), that.getCategoryId()) && Objects.equals(getProductId(), that.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoryId(), getProductId());
    }
}
