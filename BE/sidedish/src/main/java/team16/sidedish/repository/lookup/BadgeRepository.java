package team16.sidedish.repository.lookup;

import org.springframework.data.repository.CrudRepository;
import team16.sidedish.domain.entity.lookUp.Badge;

public interface BadgeRepository extends CrudRepository<Badge, Integer> {
}
