package team16.sidedish.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import team16.sidedish.domain.entity.aggregate.product.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findByHash(String hash);
}
