package sn.esmt.mp2isi.ecommerce.productservice.service;

import java.util.List;
import java.util.Optional;
import sn.esmt.mp2isi.ecommerce.productservice.domain.Category;
import sn.esmt.mp2isi.ecommerce.productservice.service.dto.CategoryDTO;

/**
 * Service Interface for managing {@link Category}.
 */
public interface CategoryService {
    /**
     * Save a category.
     *
     * @param categoryDTO the entity to save.
     * @return the persisted entity.
     */
    CategoryDTO save(CategoryDTO categoryDTO);

    /**
     * Updates a category.
     *
     * @param categoryDTO the entity to update.
     * @return the persisted entity.
     */
    CategoryDTO update(CategoryDTO categoryDTO);

    /**
     * Partially updates a category.
     *
     * @param categoryDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CategoryDTO> partialUpdate(CategoryDTO categoryDTO);

    /**
     * Get all the categories.
     *
     * @return the list of entities.
     */
    List<CategoryDTO> findAll();

    /**
     * Get the "id" category.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CategoryDTO> findOne(Long id);

    /**
     * Delete the "id" category.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
