package gift.controller.restcontroller;

import gift.controller.dto.request.ProductRequest;
import gift.controller.dto.response.ProductResponse;
import gift.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductRestController {
    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getProducts() {
        List<ProductResponse> responses = productService.findAll();
        return ResponseEntity.ok().body(responses);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("id")
                                                      @NotNull @Min(1) Long id) {
        ProductResponse response = productService.findById(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/product")
    public ResponseEntity<Long> createProduct(@Valid @RequestBody ProductRequest request, UriComponentsBuilder uriBuilder) {

        Long id = productService.save(request);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/api/product/{id}").buildAndExpand(id).toUri());
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(id);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Long> updateProduct(@PathVariable("id") @NotNull @Min(1) Long id,
                                              @Valid @RequestBody ProductRequest request) {
        productService.updateById(id, request);
        return ResponseEntity.ok().body(id);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") @NotNull @Min(1) Long id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
