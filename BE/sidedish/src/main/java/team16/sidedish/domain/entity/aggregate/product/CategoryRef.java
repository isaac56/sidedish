package team16.sidedish.domain.entity.aggregate.product;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"categoryId"})
@Table("product_has_category")
public class CategoryRef {
    private Integer categoryId;

    public CategoryRef(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
