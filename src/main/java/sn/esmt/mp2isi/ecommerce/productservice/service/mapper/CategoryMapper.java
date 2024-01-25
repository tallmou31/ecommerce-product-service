package sn.esmt.mp2isi.ecommerce.productservice.service.mapper;

import org.mapstruct.*;
import sn.esmt.mp2isi.ecommerce.productservice.domain.Category;
import sn.esmt.mp2isi.ecommerce.productservice.service.dto.CategoryDTO;

/**
 * Mapper for the entity {@link Category} and its DTO {@link CategoryDTO}.
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {}
