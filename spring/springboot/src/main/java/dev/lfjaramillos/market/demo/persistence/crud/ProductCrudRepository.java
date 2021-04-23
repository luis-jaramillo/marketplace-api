package dev.lfjaramillos.market.demo.persistence.crud;

import dev.lfjaramillos.market.demo.persistence.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCrudRepository extends CrudRepository<Product,Integer> {


   List<Product> findByIdCategoryOrderByNameAsc(Integer idCategory);

   Optional<List<Product>> findByStockQuantityLessThanAndState(Integer stockQuantity, boolean state);

}
