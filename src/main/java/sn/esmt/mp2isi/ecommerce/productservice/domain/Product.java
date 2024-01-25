package sn.esmt.mp2isi.ecommerce.productservice.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
@SuppressWarnings("common-java:DuplicatedBlocks")
@Getter
@Setter
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "reference", nullable = false, unique = true)
    private String reference;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "image_src")
    @Lob
    private String imageSrc;

    @NotNull
    @Min(value = 0L)
    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Brand brand;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Product id(Long id) {
        this.setId(id);
        return this;
    }

    public Product name(String name) {
        this.setName(name);
        return this;
    }

    public Product reference(String reference) {
        this.setReference(reference);
        return this;
    }

    public Product description(String description) {
        this.setDescription(description);
        return this;
    }

    public Product imageSrc(String imageSrc) {
        this.setImageSrc(imageSrc);
        return this;
    }

    public Product quantity(Long quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public Product price(Double price) {
        this.setPrice(price);
        return this;
    }

    public Product active(Boolean active) {
        this.setActive(active);
        return this;
    }

    public Product category(Category category) {
        this.setCategory(category);
        return this;
    }

    public Product brand(Brand brand) {
        this.setBrand(brand);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return id != null && id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", reference='" + getReference() + "'" +
            ", description='" + getDescription() + "'" +
            ", imageSrc='" + getImageSrc() + "'" +
            ", quantity=" + getQuantity() +
            ", price=" + getPrice() +
            ", active='" + getActive() + "'" +
            "}";
    }
}
