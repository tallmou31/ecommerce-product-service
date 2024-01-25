package sn.esmt.mp2isi.ecommerce.productservice.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.esmt.mp2isi.ecommerce.productservice.domain.Brand;
import sn.esmt.mp2isi.ecommerce.productservice.exception.CustomBadRequestException;
import sn.esmt.mp2isi.ecommerce.productservice.repository.BrandRepository;
import sn.esmt.mp2isi.ecommerce.productservice.service.BrandService;
import sn.esmt.mp2isi.ecommerce.productservice.service.dto.BrandDTO;
import sn.esmt.mp2isi.ecommerce.productservice.service.mapper.BrandMapper;

/**
 * Service Implementation for managing {@link Brand}.
 */
@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    private final Logger log = LoggerFactory.getLogger(BrandServiceImpl.class);

    private final BrandRepository brandRepository;

    private final BrandMapper brandMapper;

    public BrandServiceImpl(BrandRepository brandRepository, BrandMapper brandMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
    }

    @Override
    public BrandDTO save(BrandDTO brandDTO) {
        log.debug("Request to save Brand : {}", brandDTO);
        if (brandRepository.findByNameIgnoreCase(brandDTO.getName()).isPresent()) {
            throw new CustomBadRequestException("Ce nom de marque existe déjà");
        }
        Brand brand = brandMapper.toEntity(brandDTO);
        brand = brandRepository.save(brand);
        return brandMapper.toDto(brand);
    }

    @Override
    public BrandDTO update(BrandDTO brandDTO) {
        log.debug("Request to update Brand : {}", brandDTO);
        brandRepository
            .findByNameIgnoreCase(brandDTO.getName())
            .ifPresent(b -> {
                if (!b.getId().equals(brandDTO.getId())) {
                    throw new CustomBadRequestException("Ce nom de marque existe déjà");
                }
            });
        Brand brand = brandMapper.toEntity(brandDTO);
        brand = brandRepository.save(brand);
        return brandMapper.toDto(brand);
    }

    @Override
    public Optional<BrandDTO> partialUpdate(BrandDTO brandDTO) {
        log.debug("Request to partially update Brand : {}", brandDTO);

        return brandRepository
            .findById(brandDTO.getId())
            .map(existingBrand -> {
                brandMapper.partialUpdate(existingBrand, brandDTO);

                return existingBrand;
            })
            .map(brandRepository::save)
            .map(brandMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BrandDTO> findAll() {
        log.debug("Request to get all Brands");
        return brandRepository.findAll().stream().map(brandMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BrandDTO> findOne(Long id) {
        log.debug("Request to get Brand : {}", id);
        return brandRepository.findById(id).map(brandMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Brand : {}", id);
        brandRepository.deleteById(id);
    }
}
