package team16.sidedish.repository;

import org.springframework.data.repository.CrudRepository;
import team16.sidedish.domain.entity.aggregate.user.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}
