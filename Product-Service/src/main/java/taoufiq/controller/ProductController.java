package taoufiq.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import taoufiq.DTO.ProductRequest;
import taoufiq.DTO.ProductResponse;
import taoufiq.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    public final ProductService productService;
    private static Logger log = LoggerFactory.getLogger(ProductController.class);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void creteProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProduct() {
        log.info("Product requested");
        return productService.getAllProducts();
    }
}
