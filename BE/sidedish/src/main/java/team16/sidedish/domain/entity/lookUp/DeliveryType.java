package team16.sidedish.domain.entity.lookUp;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"name"})
public class DeliveryType {
    @Id
    private Integer id;

    private String name;

    public DeliveryType(String name) {
        this.name = name;
    }
}
