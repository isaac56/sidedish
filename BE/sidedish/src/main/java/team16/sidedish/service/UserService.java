package team16.sidedish.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team16.sidedish.domain.entity.aggregate.user.User;
import team16.sidedish.exception.NotFoundException;
import team16.sidedish.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(email + "로 가입한 사용자는 없습니다."));
    }

    public void createUser(String email) {
        User user = new User(email);
        this.userRepository.save(user);
    }

    public boolean emailExist(String email) {
        return userRepository.existsByEmail(email);
    }
}
