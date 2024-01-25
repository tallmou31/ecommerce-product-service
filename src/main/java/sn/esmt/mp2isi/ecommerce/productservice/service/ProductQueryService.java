package sn.esmt.mp2isi.ecommerce.productservice.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.esmt.mp2isi.ecommerce.productservice.domain.*; // for static metamodels
import sn.esmt.mp2isi.ecommerce.productservice.domain.Product;
import sn.esmt.mp2isi.ecommerce.productservice.repository.ProductRepository;
import sn.esmt.mp2isi.ecommerce.productservice.service.criteria.ProductCriteria;
import sn.esmt.mp2isi.ecommerce.productservice.service.dto.ProductDTO;
import sn.esmt.mp2isi.ecommerce.productservice.service.mapper.ProductMapper;
import tech.jhipster.service.QueryService;
import tech.jhipster.service.filter.LongFilter;

/**
 * Service for executing complex queries for {@link Product} entities in the database.
 * The main input is a {@link ProductCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProductDTO} or a {@link Page} of {@link ProductDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProductQueryService extends QueryService<Product> {

    private final Logger log = LoggerFactory.getLogger(ProductQueryService.class);

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductQueryService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    /**
     * Return a {@link List} of {@link ProductDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProductDTO> findByCriteria(ProductCriteria criteria, String filter) {
        log.debug("find by criteria : {}", criteria);
        Specification<Product> specification = createSpecification(criteria);
        if (!StringUtils.isBlank(filter)) {
            specification = specification.and(createSpecificationOr(createFilterCriteria(filter)));
        }
        return productMapper.toDto(productRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProductDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProductDTO> findByCriteria(ProductCriteria criteria, String filter, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        Specification<Product> specification = createSpecification(criteria);
        if (!StringUtils.isBlank(filter)) {
            specification = specification.and(createSpecificationOr(createFilterCriteria(filter)));
        }
        return productRepository.findAll(specification, page).map(productMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ProductCriteria criteria, String filter) {
        log.debug("count by criteria : {}", criteria);
        Specification<Product> specification = createSpecification(criteria);
        if (!StringUtils.isBlank(filter)) {
            specification = specification.and(createSpecificationOr(createFilterCriteria(filter)));
        }
        return productRepository.count(specification);
    }

    /**
     * Function to convert {@link ProductCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Product> createSpecification(ProductCriteria criteria) {
        Specification<Product> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Product_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Product_.name));
            }
            if (criteria.getReference() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReference(), Product_.reference));
            }
            if (criteria.getImageSrc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageSrc(), Product_.imageSrc));
            }
            if (criteria.getQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQuantity(), Product_.quantity));
            }
            if (criteria.getPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrice(), Product_.price));
            }
            if (criteria.getActive() != null) {
                specification = specification.and(buildSpecification(criteria.getActive(), Product_.active));
            }
            if (criteria.getCategoryId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getCategoryId(), root -> root.join(Product_.category, JoinType.LEFT).get(Category_.id))
                    );
            }
            if (criteria.getBrandId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getBrandId(), root -> root.join(Product_.brand, JoinType.LEFT).get(Brand_.id))
                    );
            }
        }
        return specification;
    }

    protected Specification<Product> createSpecificationOr(ProductCriteria criteria) {
        if (criteria == null) {
            return Specification.where(null);
        }

        var alwaysFalseFilter = new LongFilter();
        alwaysFalseFilter.setEquals(0L);
        Specification<Product> specification = Specification.where(buildSpecification(alwaysFalseFilter, Product_.id));

        // This has to be called first, because the distinct method returns null
        if (criteria.getDistinct() != null) {
            specification = specification.or(distinct(criteria.getDistinct()));
        }
        if (criteria.getId() != null) {
            specification = specification.or(buildRangeSpecification(criteria.getId(), Product_.id));
        }
        if (criteria.getName() != null) {
            specification = specification.or(buildStringSpecification(criteria.getName(), Product_.name));
        }
        if (criteria.getReference() != null) {
            specification = specification.or(buildStringSpecification(criteria.getReference(), Product_.reference));
        }
        if (criteria.getImageSrc() != null) {
            specification = specification.or(buildStringSpecification(criteria.getImageSrc(), Product_.imageSrc));
        }
        if (criteria.getQuantity() != null) {
            specification = specification.or(buildRangeSpecification(criteria.getQuantity(), Product_.quantity));
        }
        if (criteria.getPrice() != null) {
            specification = specification.or(buildRangeSpecification(criteria.getPrice(), Product_.price));
        }
        if (criteria.getActive() != null) {
            specification = specification.or(buildSpecification(criteria.getActive(), Product_.active));
        }
        if (criteria.getCategoryId() != null) {
            specification =
                specification.or(
                    buildSpecification(criteria.getCategoryId(), root -> root.join(Product_.category, JoinType.LEFT).get(Category_.id))
                );
        }
        if (criteria.getBrandId() != null) {
            specification =
                specification.or(
                    buildSpecification(criteria.getBrandId(), root -> root.join(Product_.brand, JoinType.LEFT).get(Brand_.id))
                );
        }

        return specification;
    }

    private ProductCriteria createFilterCriteria(String value) {
        var result = new ProductCriteria();
        var nameCriteria = result.name();
        nameCriteria.setContains(value);
        result.setName(nameCriteria);

        var descCriteria = result.description();
        descCriteria.setContains(value);
        result.setDescription(descCriteria);

        return result;
    }
}
