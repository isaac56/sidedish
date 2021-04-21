package team16.sidedish.domain.entity.aggregate.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("product_has_badge")
public class ProductBadge {
    @Id
    private Long id;

    private Long productId;

    private Integer badgeId;

    public ProductBadge(Long productId, Integer badgeId) {
        this.productId = productId;
        this.badgeId = badgeId;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Integer badgeId) {
        this.badgeId = badgeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductBadge)) return false;
        ProductBadge productBadge = (ProductBadge) o;
        return Objects.equals(getProductId(), productBadge.getProductId()) && Objects.equals(getBadgeId(), productBadge.getBadgeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getBadgeId());
    }
}
