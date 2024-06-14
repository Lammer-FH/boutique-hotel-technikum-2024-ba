package at.technikum.boutique.hotel.apiservice.customers;

import at.technikum.boutique.hotel.apiservice.customers.domain.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>, JpaSpecificationExecutor<CustomerEntity> {

    Optional<CustomerEntity> findByEmail(String email);
}