package drugstore.backend.controller;


import drugstore.backend.model.AddProductToVendingMachineRequest;
import drugstore.backend.persistence.entities.ProductEntity;
import drugstore.backend.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

  @GetMapping("/products")
  @CrossOrigin(origins = "http://localhost:5173") // Frontend URL
  public List<ProductEntity> getAllProducts() {
      log.info("Getting all products");
      return productService.getAllProducts();

    }
    @CrossOrigin(origins = "http://localhost:5173") // Frontend URL
    @PostMapping("/products/add")
    public void addProduct(@RequestBody AddProductToVendingMachineRequest request) {
        productService.addProductToVendingMachine(request);
    }

}
