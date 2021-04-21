package team16.sidedish.repository.lookup;

import org.springframework.data.repository.CrudRepository;
import team16.sidedish.domain.entity.lookUp.DeliveryType;

public interface DeliveryTypeRepository extends CrudRepository<DeliveryType, Integer> {
}
