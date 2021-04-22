package team16.sidedish.domain.entity.aggregate.product;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"categoryId", "productId"})
@Table("category_has_product")
public class ProductCategory {
    @Id
    private Long id;

    private Integer categoryId;

    private Long productId;

    public ProductCategory(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
