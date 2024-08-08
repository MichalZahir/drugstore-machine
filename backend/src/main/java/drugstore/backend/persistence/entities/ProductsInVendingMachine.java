package drugstore.backend.persistence.entities;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products_in_vending_machine")
@Data
public class ProductsInVendingMachine {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private ProductEntity productEntity;

    private Integer amount;
}
