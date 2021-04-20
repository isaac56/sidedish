package team16.sidedish.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team16.sidedish.domain.product.Product;
import team16.sidedish.domain.product.ProductBadge;
import team16.sidedish.domain.product.ProductCategory;
import team16.sidedish.domain.product.ProductImage;

@Transactional
@SpringBootTest
class ProductRepositoryTest {
    private ProductRepository productRepository;

    @Autowired
    public ProductRepositoryTest(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Test
    @DisplayName("저장 테스트")
    public void saveTest(){
        Product test = new Product(1L,"사계절 건강식당 작은상", "마포엄마들이 사랑해온 건강반찬"
        ,10000,9000,10,"https://cdn.bmf.kr/_data/product/201410/13/b8b0a0aba3386f030155b6cb0c1c75fa.jpg", 10);

        test = productRepository.save(test);

        test.addImage(new ProductImage("asdf",1L,false));
        test.addBadgeRef(new ProductBadge(1L,1));
        test.addCategoryRef(new ProductCategory(1,1L));
        test = productRepository.save(test);

        Assertions.assertThat(productRepository.count()).isEqualTo(1L);
        Assertions.assertThat(test.getProductBadges().size()).isEqualTo(1);
        Assertions.assertThat(test.getImages().size()).isEqualTo(1);
        Assertions.assertThat(test.getProductCategories().size()).isEqualTo(1);
    }
}
