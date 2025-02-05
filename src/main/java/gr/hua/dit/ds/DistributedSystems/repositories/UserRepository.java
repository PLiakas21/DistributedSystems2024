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

    default void updateOrSave(User user){
        Optional<User> opt = findByUsername(user.getUsername());
        if (opt.isEmpty()) {
            save(user);
        }
        else {
            User currentUser = opt.get();
            currentUser.updateUser(user);
        }
    }
}
