package sn.esmt.mp2isi.ecommerce.productservice.service.mapper;

import org.mapstruct.*;
import sn.esmt.mp2isi.ecommerce.productservice.domain.Brand;
import sn.esmt.mp2isi.ecommerce.productservice.service.dto.BrandDTO;

/**
 * Mapper for the entity {@link Brand} and its DTO {@link BrandDTO}.
 */
@Mapper(componentModel = "spring")
public interface BrandMapper extends EntityMapper<BrandDTO, Brand> {}
