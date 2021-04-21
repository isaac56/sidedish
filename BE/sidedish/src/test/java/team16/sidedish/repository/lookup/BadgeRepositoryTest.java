package team16.sidedish.repository.lookup;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team16.sidedish.domain.entity.lookUp.Badge;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class BadgeRepositoryTest {
    private BadgeRepository badgeRepository;

    @Autowired
    public BadgeRepositoryTest(BadgeRepository badgeRepository) {
        this.badgeRepository = badgeRepository;
    }

    @Test
    @DisplayName("Badge 저장 테스트")
    public void save(){
        badgeRepository.deleteAll();

        Badge badge = new Badge("이벤트 특가");
        badge = badgeRepository.save(badge);
        Assertions.assertThat(badge.getId()).isNotNull();
    }
}
