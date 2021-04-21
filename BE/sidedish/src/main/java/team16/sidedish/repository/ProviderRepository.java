package team16.sidedish.repository;

import org.springframework.data.repository.CrudRepository;
import team16.sidedish.domain.entity.provider.Provider;

public interface ProviderRepository extends CrudRepository<Provider, Long> {
}
