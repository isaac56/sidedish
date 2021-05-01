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

    private String message;

    public DeliveryType(String name, String message) {
        this.name = name;
        this.message = message;
    }


}
