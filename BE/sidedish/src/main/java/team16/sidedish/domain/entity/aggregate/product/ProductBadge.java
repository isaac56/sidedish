package team16.sidedish.domain.entity.aggregate.product;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"productId", "badgeId"})
@Table("product_has_badge")
public class ProductBadge {
    @Id
    private Long id;

    private Long productId;

    private Integer badgeId;

    public ProductBadge(Integer badgeId) {
        this.badgeId = badgeId;
    }
}
