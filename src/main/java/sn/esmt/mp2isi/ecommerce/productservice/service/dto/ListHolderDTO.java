package sn.esmt.mp2isi.ecommerce.productservice.service.dto;

import java.util.List;
import lombok.Data;

@Data
public class ListHolderDTO<E> {

    private List<E> items;
}
