package sn.esmt.mp2isi.ecommerce.productservice.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;

class CategoryMapperTest {

    private CategoryMapper categoryMapper;

    @BeforeEach
    public void setUp() {
        categoryMapper = new CategoryMapperImpl();
    }
}
