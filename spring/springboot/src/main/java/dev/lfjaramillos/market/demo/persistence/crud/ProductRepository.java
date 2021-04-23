package dev.lfjaramillos.market.demo.persistence.crud;

import dev.lfjaramillos.market.demo.persistence.entity.Product;

import java.util.List;
import java.util.Optional;

public class ProductRepository {


    private ProductCrudRepository productCrudRepository ;

    public List<Product> getAll(){
     return (List<Product>) productCrudRepository.findAll();
    }
    public List<Product> getByCategory(Integer idCategory){
     return (List<Product>) productCrudRepository.findByIdCategoryOrderByNameAsc(idCategory);
    }
    public Optional< List<Product>> getByCategory2(Integer quantity){
     return   productCrudRepository.findByStockQuantityLessThanAndState(quantity, true);
    }
}
