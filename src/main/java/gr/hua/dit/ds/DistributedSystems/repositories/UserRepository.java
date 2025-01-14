package gr.hua.dit.ds.DistributedSystems.repositories;


import gr.hua.dit.ds.DistributedSystems.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    default User updateOrInsert(User user) {
        User existing_user = findByUsername(user.getUsername()).orElse(null);
        if (existing_user != null) {
            return existing_user;
        }
        else {
            return save(user);
        }
    }
}
