package sn.esmt.mp2isi.ecommerce.productservice.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;

class BrandMapperTest {

    private BrandMapper brandMapper;

    @BeforeEach
    public void setUp() {
        brandMapper = new BrandMapperImpl();
    }
}
