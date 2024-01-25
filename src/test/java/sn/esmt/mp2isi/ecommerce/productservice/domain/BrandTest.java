package sn.esmt.mp2isi.ecommerce.productservice.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import sn.esmt.mp2isi.ecommerce.productservice.web.rest.TestUtil;

class BrandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Brand.class);
        Brand brand1 = new Brand();
        brand1.setId(1L);
        Brand brand2 = new Brand();
        brand2.setId(brand1.getId());
        assertThat(brand1).isEqualTo(brand2);
        brand2.setId(2L);
        assertThat(brand1).isNotEqualTo(brand2);
        brand1.setId(null);
        assertThat(brand1).isNotEqualTo(brand2);
    }
}
