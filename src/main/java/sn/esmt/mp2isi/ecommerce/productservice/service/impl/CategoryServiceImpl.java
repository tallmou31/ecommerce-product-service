package sn.esmt.mp2isi.ecommerce.productservice.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.esmt.mp2isi.ecommerce.productservice.domain.Category;
import sn.esmt.mp2isi.ecommerce.productservice.exception.CustomBadRequestException;
import sn.esmt.mp2isi.ecommerce.productservice.repository.CategoryRepository;
import sn.esmt.mp2isi.ecommerce.productservice.service.CategoryService;
import sn.esmt.mp2isi.ecommerce.productservice.service.dto.CategoryDTO;
import sn.esmt.mp2isi.ecommerce.productservice.service.mapper.CategoryMapper;

/**
 * Service Implementation for managing {@link Category}.
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        log.debug("Request to save Category : {}", categoryDTO);
        if (categoryRepository.findByNameIgnoreCase(categoryDTO.getName()).isPresent()) {
            throw new CustomBadRequestException("Ce nom de catégorie existe déjà");
        }
        Category category = categoryMapper.toEntity(categoryDTO);
        category = categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) {
        log.debug("Request to update Category : {}", categoryDTO);
        categoryRepository
            .findByNameIgnoreCase(categoryDTO.getName())
            .ifPresent(b -> {
                if (!b.getId().equals(categoryDTO.getId())) {
                    throw new CustomBadRequestException("Ce nom de catégorie existe déjà");
                }
            });
        Category category = categoryMapper.toEntity(categoryDTO);
        category = categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    @Override
    public Optional<CategoryDTO> partialUpdate(CategoryDTO categoryDTO) {
        log.debug("Request to partially update Category : {}", categoryDTO);

        return categoryRepository
            .findById(categoryDTO.getId())
            .map(existingCategory -> {
                categoryMapper.partialUpdate(existingCategory, categoryDTO);

                return existingCategory;
            })
            .map(categoryRepository::save)
            .map(categoryMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        log.debug("Request to get all Categories");
        return categoryRepository.findAll().stream().map(categoryMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CategoryDTO> findOne(Long id) {
        log.debug("Request to get Category : {}", id);
        return categoryRepository.findById(id).map(categoryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Category : {}", id);
        categoryRepository.deleteById(id);
    }
}
