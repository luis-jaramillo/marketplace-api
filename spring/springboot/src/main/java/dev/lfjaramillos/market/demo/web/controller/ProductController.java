package dev.lfjaramillos.market.demo.web.controller;

import dev.lfjaramillos.market.demo.domain.Product;
import dev.lfjaramillos.market.demo.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @ApiOperation("Get all supermarket products")
    @ApiResponse(code= 200, message = "OK")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Search product by ID ")
    @ApiResponses({
            @ApiResponse(code= 200, message = "OK"),
            @ApiResponse(code= 404, message = "Product not found"),
    })
    public ResponseEntity<Product> getProductById(@ApiParam(value = "The id of the product", required = true, example = "7") @PathVariable("id") Integer productId) {

        return productService.getProduct(productId).map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> getCategory(@PathVariable("id") Integer categoryId) {
        return productService.getByCategory(categoryId).map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(Integer productId) {
        if(productService.delete(productId))
           return new ResponseEntity<>(HttpStatus.OK);
            else
            return   new ResponseEntity<>(HttpStatus.NOT_FOUND)  ;

    }

    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }
}
