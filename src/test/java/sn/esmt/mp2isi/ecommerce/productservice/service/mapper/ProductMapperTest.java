package sn.esmt.mp2isi.ecommerce.productservice.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;

class ProductMapperTest {

    private ProductMapper productMapper;

    @BeforeEach
    public void setUp() {
        productMapper = new ProductMapperImpl();
    }
}
