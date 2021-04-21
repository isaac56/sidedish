package team16.sidedish.domain.entity.aggregate.provider;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("provider_has_delivery_type")
public class ProviderDeliveryType {
    @Id
    private Long id;

    private Long providerId;

    private Integer deliveryTypeId;

    public ProviderDeliveryType(Integer deliveryTypeId) {
        this.deliveryTypeId = deliveryTypeId;
    }

    public Long getId() {
        return id;
    }

    public Long getProviderId() {
        return providerId;
    }

    public Integer getDeliveryTypeId() {
        return deliveryTypeId;
    }

    public void setDeliveryTypeId(Integer deliveryTypeId) {
        this.deliveryTypeId = deliveryTypeId;
    }
}
