package sn.esmt.mp2isi.ecommerce.productservice.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import sn.esmt.mp2isi.ecommerce.productservice.domain.Product;

/**
 * Spring Data JPA repository for the Product entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Optional<Product> findByReferenceIgnoreCase(String reference);
}
