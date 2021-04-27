package team16.sidedish.repository;

import org.springframework.data.repository.CrudRepository;
import team16.sidedish.domain.entity.aggregate.provider.Provider;

import java.util.List;
import java.util.Optional;

public interface ProviderRepository extends CrudRepository<Provider, Long> {
    Optional<Provider> findByName(String name);

    List<Provider> findAllById(Iterable<Long> ids);
}
