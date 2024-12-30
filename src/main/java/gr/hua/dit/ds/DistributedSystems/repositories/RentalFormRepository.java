package gr.hua.dit.ds.DistributedSystems.repositories;

import gr.hua.dit.ds.DistributedSystems.entities.RentalForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalFormRepository extends JpaRepository<RentalForm, Integer> {
}
