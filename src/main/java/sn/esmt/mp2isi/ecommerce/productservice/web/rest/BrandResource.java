package sn.esmt.mp2isi.ecommerce.productservice.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sn.esmt.mp2isi.ecommerce.productservice.domain.Brand;
import sn.esmt.mp2isi.ecommerce.productservice.repository.BrandRepository;
import sn.esmt.mp2isi.ecommerce.productservice.service.BrandQueryService;
import sn.esmt.mp2isi.ecommerce.productservice.service.BrandService;
import sn.esmt.mp2isi.ecommerce.productservice.service.criteria.BrandCriteria;
import sn.esmt.mp2isi.ecommerce.productservice.service.dto.BrandDTO;
import sn.esmt.mp2isi.ecommerce.productservice.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link Brand}.
 */
@RestController
@RequestMapping("/api")
public class BrandResource {

    private final Logger log = LoggerFactory.getLogger(BrandResource.class);

    private static final String ENTITY_NAME = "productserviceBrand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BrandService brandService;

    private final BrandRepository brandRepository;

    private final BrandQueryService brandQueryService;

    public BrandResource(BrandService brandService, BrandRepository brandRepository, BrandQueryService brandQueryService) {
        this.brandService = brandService;
        this.brandRepository = brandRepository;
        this.brandQueryService = brandQueryService;
    }

    /**
     * {@code POST  /brands} : Create a new brand.
     *
     * @param brandDTO the brandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new brandDTO, or with status {@code 400 (Bad Request)} if the brand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/brands")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BrandDTO> createBrand(@Valid @RequestBody BrandDTO brandDTO) throws URISyntaxException {
        log.debug("REST request to save Brand : {}", brandDTO);
        if (brandDTO.getId() != null) {
            throw new BadRequestAlertException("A new brand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BrandDTO result = brandService.save(brandDTO);
        return ResponseEntity
            .created(new URI("/api/brands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /brands/:id} : Updates an existing brand.
     *
     * @param id the id of the brandDTO to save.
     * @param brandDTO the brandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated brandDTO,
     * or with status {@code 400 (Bad Request)} if the brandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the brandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/brands/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BrandDTO> updateBrand(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody BrandDTO brandDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Brand : {}, {}", id, brandDTO);
        if (brandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, brandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!brandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BrandDTO result = brandService.update(brandDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, brandDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /brands/:id} : Partial updates given fields of an existing brand, field will ignore if it is null
     *
     * @param id the id of the brandDTO to save.
     * @param brandDTO the brandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated brandDTO,
     * or with status {@code 400 (Bad Request)} if the brandDTO is not valid,
     * or with status {@code 404 (Not Found)} if the brandDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the brandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/brands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BrandDTO> partialUpdateBrand(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody BrandDTO brandDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Brand partially : {}, {}", id, brandDTO);
        if (brandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, brandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!brandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BrandDTO> result = brandService.partialUpdate(brandDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, brandDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /brands} : get all the brands.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of brands in body.
     */
    @GetMapping("/brands")
    public ResponseEntity<List<BrandDTO>> getAllBrands(BrandCriteria criteria) {
        log.debug("REST request to get Brands by criteria: {}", criteria);
        List<BrandDTO> entityList = brandQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /brands/count} : count all the brands.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/brands/count")
    public ResponseEntity<Long> countBrands(BrandCriteria criteria) {
        log.debug("REST request to count Brands by criteria: {}", criteria);
        return ResponseEntity.ok().body(brandQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /brands/:id} : get the "id" brand.
     *
     * @param id the id of the brandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the brandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/brands/{id}")
    public ResponseEntity<BrandDTO> getBrand(@PathVariable Long id) {
        log.debug("REST request to get Brand : {}", id);
        Optional<BrandDTO> brandDTO = brandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(brandDTO);
    }

    /**
     * {@code DELETE  /brands/:id} : delete the "id" brand.
     *
     * @param id the id of the brandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/brands/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        log.debug("REST request to delete Brand : {}", id);
        brandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
