package drugstore.backend.service;


import drugstore.backend.model.AddProductToVendingMachineRequest;
import drugstore.backend.persistence.entities.ProductEntity;
import drugstore.backend.persistence.entities.ProductsInVendingMachine;
import drugstore.backend.persistence.repo.ProductRepository;
import drugstore.backend.persistence.repo.ProductsInInventoryRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductsInInventoryRepo productsInInventoryRepo;

    public List<ProductEntity> getAllProducts() {

        return productRepository.findAll();

    }

    public void addProductToVendingMachine(AddProductToVendingMachineRequest request) {

        productsInInventoryRepo.findByProductEntityId(request.getProductId())
                .ifPresentOrElse(
                        updateProductInVendingMachine(request),
                        () -> createNewProductInVendingMachine(request));

    }

    private Consumer<ProductsInVendingMachine> updateProductInVendingMachine(AddProductToVendingMachineRequest request) {
        return productInVendingMachine -> {
            productInVendingMachine.setAmount(productInVendingMachine.getAmount() + request.getAmount());
            productsInInventoryRepo.save(productInVendingMachine);
        };
    }

    private void createNewProductInVendingMachine(AddProductToVendingMachineRequest request) {
        ProductsInVendingMachine productsInVendingMachine = new ProductsInVendingMachine();
        productsInVendingMachine.setProductEntity(productRepository.findById(request.getProductId()).get());
        productsInVendingMachine.setAmount(request.getAmount());

        productsInInventoryRepo.save(productsInVendingMachine);
    }
}
