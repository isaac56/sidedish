package team16.sidedish.domain.entity.product;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public class ProductImage {
    @Id
    private Long id;

    private String url;

    private Long productId;

    private boolean isDetail;

    public ProductImage(String url, Long productId, boolean isDetail) {
        this.url = url;
        this.productId = productId;
        this.isDetail = isDetail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public boolean isDetail() {
        return isDetail;
    }

    public void setDetail(boolean detail) {
        isDetail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductImage)) return false;
        ProductImage that = (ProductImage) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
