package dev.lfjaramillos.marketplace.demo.domain.repository;

import dev.lfjaramillos.marketplace.demo.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> getAll();

    Optional<List<Product>> getByCategory(Integer categoryId);

    Optional<Product> getProduct(int productId);

    Optional<List<Product>> getScarceProducts(Integer quantity);

    Product save(Product product);

    void delete(int productId);

}
