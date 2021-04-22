package team16.sidedish.domain.entity.aggregate.user;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"userId", "productId"})
public class Cart {
    @Id
    private Long id;

    private Long userId;

    private Long productId;

    private Integer count;

    public Cart(Long productId, Integer count) {
        this.productId = productId;
        this.count = count;
    }
}
