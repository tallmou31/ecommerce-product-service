package sn.esmt.mp2isi.ecommerce.productservice.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import sn.esmt.mp2isi.ecommerce.productservice.domain.Category;

/**
 * Spring Data JPA repository for the Category entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    Optional<Category> findByNameIgnoreCase(String name);
}
