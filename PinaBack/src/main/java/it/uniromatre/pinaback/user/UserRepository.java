package it.uniromatre.pinaback.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    User findUserById(Integer id);

    @Query(value = "SELECT i FROM User i ORDER BY function('RAND')")
    User findRandom();

}
