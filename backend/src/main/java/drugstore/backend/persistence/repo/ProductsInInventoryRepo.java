package drugstore.backend.persistence.repo;


import drugstore.backend.persistence.entities.ProductsInVendingMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsInInventoryRepo extends JpaRepository<ProductsInVendingMachine, Long> {

    Optional<ProductsInVendingMachine> findByProductEntityId(Long productId);
}
