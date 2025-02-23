package gr.hua.dit.ds.DistributedSystems.repositories;

import gr.hua.dit.ds.DistributedSystems.entities.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends JpaRepository<Form, Integer> {
}
