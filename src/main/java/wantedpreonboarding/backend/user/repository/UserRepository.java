package wantedpreonboarding.backend.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wantedpreonboarding.backend.user.domain.User;

import java.util.Optional;
import java.util.stream.DoubleStream;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
