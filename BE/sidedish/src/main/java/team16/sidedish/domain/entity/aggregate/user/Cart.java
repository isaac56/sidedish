package team16.sidedish.domain.entity.aggregate.user;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"productId"})
@Table("user_cart")
public class Cart {
    private Long productId;

    private Integer count;

    public Cart(Long productId, Integer count) {
        this.productId = productId;
        this.count = count;
    }
}
