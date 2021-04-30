package dev.lfjaramillos.market.demo.persistence.crud;

import dev.lfjaramillos.market.demo.persistence.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCrudRepository extends CrudRepository<ProductEntity,Integer> {


   List<ProductEntity> findByIdCategoryOrderByNameAsc(Integer idCategory);

   Optional<List<ProductEntity>> findByStockQuantityLessThanAndState(Integer stockQuantity, boolean state);

}
