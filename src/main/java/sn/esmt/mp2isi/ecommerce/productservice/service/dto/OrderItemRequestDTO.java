package sn.esmt.mp2isi.ecommerce.productservice.service.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderItemRequestDTO {

    @NotNull
    @Min(1)
    private Long quantity;

    private Long productId;
}
