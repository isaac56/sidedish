package team16.sidedish.repository.lookup;

import org.springframework.data.repository.CrudRepository;
import team16.sidedish.domain.entity.lookUp.DeliveryType;

import java.util.List;
import java.util.Optional;

public interface DeliveryTypeRepository extends CrudRepository<DeliveryType, Integer> {
    List<DeliveryType> findAll();

    Optional<DeliveryType> findByName(String name);
}
