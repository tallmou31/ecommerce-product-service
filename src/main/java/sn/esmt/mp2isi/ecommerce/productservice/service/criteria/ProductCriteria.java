package sn.esmt.mp2isi.ecommerce.productservice.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import org.springdoc.api.annotations.ParameterObject;
import sn.esmt.mp2isi.ecommerce.productservice.domain.Product;
import sn.esmt.mp2isi.ecommerce.productservice.web.rest.ProductResource;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link Product} entity. This class is used
 * in {@link ProductResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /products?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
@Getter
@Setter
public class ProductCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter description;

    private StringFilter reference;

    private StringFilter imageSrc;

    private LongFilter quantity;

    private DoubleFilter price;

    private BooleanFilter active;

    private LongFilter categoryId;

    private LongFilter brandId;

    private Boolean distinct;

    public ProductCriteria() {}

    public ProductCriteria(ProductCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.reference = other.reference == null ? null : other.reference.copy();
        this.imageSrc = other.imageSrc == null ? null : other.imageSrc.copy();
        this.quantity = other.quantity == null ? null : other.quantity.copy();
        this.price = other.price == null ? null : other.price.copy();
        this.active = other.active == null ? null : other.active.copy();
        this.categoryId = other.categoryId == null ? null : other.categoryId.copy();
        this.brandId = other.brandId == null ? null : other.brandId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ProductCriteria copy() {
        return new ProductCriteria(this);
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public StringFilter name() {
        if (name == null) {
            name = new StringFilter();
        }
        return name;
    }

    public StringFilter description() {
        if (description == null) {
            description = new StringFilter();
        }
        return description;
    }

    public StringFilter reference() {
        if (reference == null) {
            reference = new StringFilter();
        }
        return reference;
    }

    public StringFilter imageSrc() {
        if (imageSrc == null) {
            imageSrc = new StringFilter();
        }
        return imageSrc;
    }

    public LongFilter quantity() {
        if (quantity == null) {
            quantity = new LongFilter();
        }
        return quantity;
    }

    public DoubleFilter price() {
        if (price == null) {
            price = new DoubleFilter();
        }
        return price;
    }

    public BooleanFilter active() {
        if (active == null) {
            active = new BooleanFilter();
        }
        return active;
    }

    public LongFilter categoryId() {
        if (categoryId == null) {
            categoryId = new LongFilter();
        }
        return categoryId;
    }

    public LongFilter brandId() {
        if (brandId == null) {
            brandId = new LongFilter();
        }
        return brandId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProductCriteria that = (ProductCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(description, that.description) &&
            Objects.equals(reference, that.reference) &&
            Objects.equals(imageSrc, that.imageSrc) &&
            Objects.equals(quantity, that.quantity) &&
            Objects.equals(price, that.price) &&
            Objects.equals(active, that.active) &&
            Objects.equals(categoryId, that.categoryId) &&
            Objects.equals(brandId, that.brandId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, reference, imageSrc, quantity, price, active, categoryId, brandId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (reference != null ? "reference=" + reference + ", " : "") +
            (imageSrc != null ? "imageSrc=" + imageSrc + ", " : "") +
            (quantity != null ? "quantity=" + quantity + ", " : "") +
            (price != null ? "price=" + price + ", " : "") +
            (active != null ? "active=" + active + ", " : "") +
            (categoryId != null ? "categoryId=" + categoryId + ", " : "") +
            (brandId != null ? "brandId=" + brandId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
