package team16.sidedish.domain.entity.lookUp;

import org.springframework.data.annotation.Id;

public class DeliveryType {
    @Id
    private Long id;

    private String name;

    public DeliveryType(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
