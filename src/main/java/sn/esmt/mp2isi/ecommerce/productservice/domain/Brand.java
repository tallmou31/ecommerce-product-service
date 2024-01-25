package sn.esmt.mp2isi.ecommerce.productservice.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

/**
 * A Brand.
 */
@Entity
@Table(name = "brand")
@SuppressWarnings("common-java:DuplicatedBlocks")
@Getter
@Setter
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Brand id(Long id) {
        this.setId(id);
        return this;
    }

    public Brand name(String name) {
        this.setName(name);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Brand)) {
            return false;
        }
        return id != null && id.equals(((Brand) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Brand{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
