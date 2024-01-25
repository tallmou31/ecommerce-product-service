package sn.esmt.mp2isi.ecommerce.productservice.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.esmt.mp2isi.ecommerce.productservice.domain.Product;
import sn.esmt.mp2isi.ecommerce.productservice.exception.CustomBadRequestException;
import sn.esmt.mp2isi.ecommerce.productservice.repository.ProductRepository;
import sn.esmt.mp2isi.ecommerce.productservice.service.ProductService;
import sn.esmt.mp2isi.ecommerce.productservice.service.dto.ProductDTO;
import sn.esmt.mp2isi.ecommerce.productservice.service.mapper.ProductMapper;

/**
 * Service Implementation for managing {@link Product}.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        log.debug("Request to save Product : {}", productDTO);
        if (productRepository.findByReferenceIgnoreCase(productDTO.getReference()).isPresent()) {
            throw new CustomBadRequestException("Cette référence de produit existe déjà");
        }
        Product product = productMapper.toEntity(productDTO);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) {
        log.debug("Request to update Product : {}", productDTO);
        productRepository
            .findByReferenceIgnoreCase(productDTO.getReference())
            .ifPresent(b -> {
                if (!b.getId().equals(productDTO.getId())) {
                    throw new CustomBadRequestException("Cette référence de produit existe déjà");
                }
            });
        Product product = productMapper.toEntity(productDTO);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public Optional<ProductDTO> partialUpdate(ProductDTO productDTO) {
        log.debug("Request to partially update Product : {}", productDTO);

        return productRepository
            .findById(productDTO.getId())
            .map(existingProduct -> {
                productMapper.partialUpdate(existingProduct, productDTO);

                return existingProduct;
            })
            .map(productRepository::save)
            .map(productMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Products");
        return productRepository.findAll(pageable).map(productMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductDTO> findOne(Long id) {
        log.debug("Request to get Product : {}", id);
        return productRepository.findById(id).map(productMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Product : {}", id);
        productRepository.deleteById(id);
    }
}
