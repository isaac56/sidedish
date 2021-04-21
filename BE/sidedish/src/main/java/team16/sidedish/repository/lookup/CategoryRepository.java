package team16.sidedish.repository.lookup;

import org.springframework.data.repository.CrudRepository;
import team16.sidedish.domain.entity.lookUp.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    List<Category> findAll();

    Optional<Category> findByName(String name);
}
