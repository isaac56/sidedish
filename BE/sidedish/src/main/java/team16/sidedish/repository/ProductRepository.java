package team16.sidedish.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import team16.sidedish.domain.product.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
