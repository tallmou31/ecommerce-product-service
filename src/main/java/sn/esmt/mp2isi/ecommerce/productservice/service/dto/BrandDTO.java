package sn.esmt.mp2isi.ecommerce.productservice.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import sn.esmt.mp2isi.ecommerce.productservice.domain.Brand;

/**
 * A DTO for the {@link Brand} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Getter
@Setter
public class BrandDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BrandDTO)) {
            return false;
        }

        BrandDTO brandDTO = (BrandDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, brandDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BrandDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
