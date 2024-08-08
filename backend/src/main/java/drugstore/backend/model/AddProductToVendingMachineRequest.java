package drugstore.backend.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddProductToVendingMachineRequest {

    private Long productId;
    private Integer amount;

}
