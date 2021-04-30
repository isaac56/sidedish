package team16.sidedish.domain.entity.aggregate.product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImage {
    @Id
    private Long id;

    private String url;

    private Long productId;

    private boolean isDetail;

    public ProductImage(String url, boolean isDetail) {
        this.url = url;
        this.isDetail = isDetail;
    }
}
