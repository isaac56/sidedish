package team16.sidedish.repository.custom;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.stereotype.Repository;
import team16.sidedish.domain.entity.aggregate.product.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomizedProductRepository {

    @Query("select A.id, A.hash, A.provider_id, A.name, A.description, A.price_original, A.price_discount, A.point, A.top_image_url, A.stock from product_has_category AB " +
            "inner join product A on AB.product_id = A.id " +
            "inner join category B on B.id = AB.category_id " +
            "where B.id = :id")
    List<Product> findAllByCategoryId(Integer id);

    @Query("select A.id, A.hash, A.provider_id, A.name, A.description, A.price_original, A.price_discount, A.point, A.top_image_url, A.stock from product_has_category AB " +
            "inner join product A on AB.product_id = A.id " +
            "inner join category B on B.id = AB.category_id " +
            "where B.id = :id and A.hash = :hash")
    Optional<Product> findByCategoryIdAndHash(Integer id, String hash);
}
