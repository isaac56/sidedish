package team16.sidedish.domain.entity.aggregate.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"productId"})
public class Cart {
    private Long productId;

    private Integer count;

    public Cart(Long productId, Integer count) {
        this.productId = productId;
        this.count = count;
    }
}
