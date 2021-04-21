package team16.sidedish.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team16.sidedish.domain.entity.provider.Provider;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ProviderRepositoryTest {
    ProviderRepository providerRepository;

    @Autowired
    public ProviderRepositoryTest(ProviderRepository providerRepository){
        this.providerRepository = providerRepository;
    }

    @Test
    @DisplayName("Provider save 테스트")
    public void save(){
        providerRepository.deleteAll();
        Provider provider = new Provider("오늘의밥상",2000,40000);
        provider = providerRepository.save(provider);

        Assertions.assertThat(provider.getId()).isNotNull();
        Assertions.assertThat(providerRepository.count()).isEqualTo(1L);
    }

}
