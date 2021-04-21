package team16.sidedish.repository.lookup;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team16.sidedish.domain.entity.lookUp.Category;

@Transactional
@SpringBootTest
class CategoryRepositoryTest {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryRepositoryTest(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Test
    @DisplayName("Category save 테스트")
    public void save(){
        categoryRepository.deleteAll();

        Category category = new Category("우리아이 건강반찬",true);
        category = categoryRepository.save(category);
        Assertions.assertThat(category.getId()).isNotNull();
    }
}
