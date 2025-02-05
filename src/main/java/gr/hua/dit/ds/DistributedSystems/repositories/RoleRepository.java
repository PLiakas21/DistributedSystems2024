package gr.hua.dit.ds.DistributedSystems.repositories;

import gr.hua.dit.ds.DistributedSystems.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String roleName);

    default void updateOrInsert(Role role) {
        findByName(role.getName()).orElseGet(() -> save(role));
    }
}