package team16.sidedish.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team16.sidedish.domain.entity.aggregate.provider.DeliveryTypeRef;
import team16.sidedish.domain.entity.aggregate.provider.Provider;

@Transactional
@SpringBootTest
class ProviderRepositoryTest {
    private ProviderRepository providerRepository;

    @Autowired
    public ProviderRepositoryTest(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Test
    @DisplayName("Provider save 테스트")
    public void save() {
        Provider provider = new Provider("오늘의밥상", 2000, 40000);
        provider.addDeliveryTypeRef(new DeliveryTypeRef(1));
        provider = providerRepository.save(provider);

        Assertions.assertThat(provider.getId()).isNotNull();
        Assertions.assertThat(provider.getDeliveryTypeRefs().size()).isEqualTo(1);
    }

}
