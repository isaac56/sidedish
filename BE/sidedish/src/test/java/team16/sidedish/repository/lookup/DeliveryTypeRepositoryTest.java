package team16.sidedish.repository.lookup;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team16.sidedish.domain.entity.lookUp.DeliveryType;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class DeliveryTypeRepositoryTest {
    private DeliveryTypeRepository deliveryTypeRepository;

    @Autowired
    public DeliveryTypeRepositoryTest(DeliveryTypeRepository deliveryTypeRepository) {
        this.deliveryTypeRepository = deliveryTypeRepository;
    }

    @Test
    @DisplayName("DeliveryType save 테스트")
    public void save(){
        deliveryTypeRepository.deleteAll();

        DeliveryType deliveryType = new DeliveryType("퀵배송");
        deliveryType = deliveryTypeRepository.save(deliveryType);
        Assertions.assertThat(deliveryType.getId()).isNotNull();
    }
}
