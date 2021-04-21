package team16.sidedish.repository.lookup;

import org.springframework.data.repository.CrudRepository;
import team16.sidedish.domain.entity.lookUp.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
