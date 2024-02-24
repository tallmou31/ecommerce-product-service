package sn.esmt.mp2isi.ecommerce.productservice.service.impl;

import java.util.ArrayList;
import java.util.List;
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
import sn.esmt.mp2isi.ecommerce.productservice.service.dto.*;
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

    @Override
    @Transactional
    public OrderResponseDTO validateOrder(OrderRequestDTO order) {
        var response = new OrderResponseDTO();
        response.setStatus(OrderResponseStatus.OK);
        var products = new ArrayList<Product>();
        order
            .getItems()
            .forEach(item -> {
                var p = productRepository.findById(item.getProductId()).orElse(null);
                var itemResp = new OrderItemResponseDTO(
                    item.getQuantity(),
                    item.getQuantity(),
                    item.getProductId(),
                    p != null ? p.getPrice() : 0,
                    p.getReference(),
                    p.getName()
                );
                if (p == null || p.getQuantity() < item.getQuantity()) {
                    response.setStatus(OrderResponseStatus.PARTIAL_OK);
                    itemResp.setAvailable(p == null ? 0L : p.getQuantity());
                } else {
                    p.setQuantity(p.getQuantity() - item.getQuantity());
                }
                response.addItem(itemResp);
                products.add(p);
            });

        if (response.getStatus() == OrderResponseStatus.OK) {
            try {
                productRepository.saveAll(products);
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(OrderResponseStatus.NOT_OK);
            }
        }

        return response;
    }

    @Override
    public void cancelOrder(OrderRequestDTO order) {
        order
            .getItems()
            .forEach(item -> {
                productRepository
                    .findById(item.getProductId())
                    .ifPresent(product -> {
                        product.setQuantity(product.getQuantity() + item.getQuantity());
                        productRepository.save(product);
                    });
            });
    }
}
