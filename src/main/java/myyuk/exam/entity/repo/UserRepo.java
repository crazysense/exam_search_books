package myyuk.exam.entity.repo;

import myyuk.exam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
    User findByUserId(String userId);

    User save(User user);
}
