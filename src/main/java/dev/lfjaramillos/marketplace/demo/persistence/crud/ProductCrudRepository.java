package dev.lfjaramillos.marketplace.demo.persistence.crud;

import dev.lfjaramillos.marketplace.demo.persistence.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCrudRepository extends CrudRepository<ProductEntity,Integer> {


   List<ProductEntity> findByIdCategoryOrderByNameAsc(Integer idCategory);

   Optional<List<ProductEntity>> findByStockQuantityLessThanAndState(Integer stockQuantity, boolean state);

}
