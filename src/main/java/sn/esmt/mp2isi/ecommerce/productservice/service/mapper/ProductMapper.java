package sn.esmt.mp2isi.ecommerce.productservice.service.mapper;

import org.mapstruct.*;
import sn.esmt.mp2isi.ecommerce.productservice.domain.Brand;
import sn.esmt.mp2isi.ecommerce.productservice.domain.Category;
import sn.esmt.mp2isi.ecommerce.productservice.domain.Product;
import sn.esmt.mp2isi.ecommerce.productservice.service.dto.BrandDTO;
import sn.esmt.mp2isi.ecommerce.productservice.service.dto.CategoryDTO;
import sn.esmt.mp2isi.ecommerce.productservice.service.dto.ProductDTO;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {
    ProductDTO toDto(Product s);

    @Named("categoryId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoryDTO toDtoCategoryId(Category category);

    @Named("brandId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    BrandDTO toDtoBrandId(Brand brand);
}
