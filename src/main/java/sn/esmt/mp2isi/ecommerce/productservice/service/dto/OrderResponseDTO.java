package sn.esmt.mp2isi.ecommerce.productservice.service.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

    List<OrderItemResponseDTO> items = new ArrayList<>();
    private OrderResponseStatus status;

    public void addItem(OrderItemResponseDTO item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
    }
}
