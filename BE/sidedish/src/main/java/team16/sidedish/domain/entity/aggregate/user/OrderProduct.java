package team16.sidedish.domain.entity.aggregate.user;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"productId"})
@Table("order_has_product")
public class OrderProduct {
    private Long productId;

    private Integer count;

    public OrderProduct(Long productId, Integer count) {
        this.productId = productId;
        this.count = count;
    }
}
