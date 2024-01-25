package sn.esmt.mp2isi.ecommerce.productservice.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import sn.esmt.mp2isi.ecommerce.productservice.domain.Brand;

/**
 * Spring Data JPA repository for the Brand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>, JpaSpecificationExecutor<Brand> {
    Optional<Brand> findByNameIgnoreCase(String name);
}
