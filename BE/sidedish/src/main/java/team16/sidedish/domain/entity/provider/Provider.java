package team16.sidedish.domain.entity.provider;

import org.springframework.data.annotation.Id;

public class Provider {
    @Id
    private Long id;

    private String name;

    private Integer deliveryFee;

    private Integer deliveryFreePrice;

    public Provider(String name, Integer deliveryFee, Integer deliveryFreePrice) {
        this.name = name;
        this.deliveryFee = deliveryFee;
        this.deliveryFreePrice = deliveryFreePrice;
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

    public Integer getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Integer deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Integer getDeliveryFreePrice() {
        return deliveryFreePrice;
    }

    public void setDeliveryFreePrice(Integer deliveryFreePrice) {
        this.deliveryFreePrice = deliveryFreePrice;
    }
}
