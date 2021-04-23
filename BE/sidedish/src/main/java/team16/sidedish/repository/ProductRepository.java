package team16.sidedish.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import team16.sidedish.domain.entity.aggregate.product.Product;
import team16.sidedish.repository.custom.CustomizedProductRepository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>, CustomizedProductRepository {
    Optional<Product> findByHash(String hash);
}
