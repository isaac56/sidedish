package team16.sidedish.repository.lookup;

import org.springframework.data.repository.CrudRepository;
import team16.sidedish.domain.entity.lookUp.Badge;

import java.util.List;
import java.util.Optional;

public interface BadgeRepository extends CrudRepository<Badge, Integer> {
    List<Badge> findAll();

    Optional<Badge> findByName(String name);
}
