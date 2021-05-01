package team16.sidedish.domain.entity.lookUp;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"name"})
public class Category {
    @Id
    private Integer id;

    private String name;

    private boolean isBest;

    public Category(String name, boolean isBest) {
        this.name = name;
        this.isBest = isBest;
    }
}
