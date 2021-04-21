package team16.sidedish.repository;

import org.springframework.data.repository.CrudRepository;
import team16.sidedish.domain.entity.aggregate.user.User;

public interface UserRepository extends CrudRepository<User,Long> {
}
