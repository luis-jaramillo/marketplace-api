package dev.lfjaramillos.market.demo.webcontroller;

import dev.lfjaramillos.market.demo.domain.Product;
import dev.lfjaramillos.market.demo.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> getAllProducts(){
        return productService.getAll();
    }
    @GetMapping("/{id}")
    public Optional<Product> getProductById (@PathVariable("id") Integer productId){
        return productService.getProduct(productId);
    }
    @GetMapping("/category/{id}")
    public Optional<List<Product>> getCategory (@PathVariable("id") Integer categoryId){
        return productService.getByCategory(categoryId);
    }

    @DeleteMapping("/{id}")
    public boolean delete(Integer productId){
     return productService.delete(productId);
    }
    @PostMapping("/save")
    public Product save(@RequestBody Product product){
     return productService.save(product);
    }
}
