package dev.lfjaramillos.marketplace.demo.persistence.crud;

import dev.lfjaramillos.marketplace.demo.domain.Product;
import dev.lfjaramillos.marketplace.demo.domain.repository.ProductRepository;
import dev.lfjaramillos.marketplace.demo.persistence.entity.ProductEntity;
import dev.lfjaramillos.marketplace.demo.persistence.mapper.ProductoMapper;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private ProductCrudRepository productCrudRepository;

    @Autowired
    private ProductoMapper mapper;

    public List<Product> getAll(){
        return  mapper.toProducts((List<ProductEntity>) productCrudRepository.findAll()) ;
    }

    public Optional<List<Product>> getByCategory(Integer idCategory){
        List<ProductEntity> entities = productCrudRepository.findByIdCategoryOrderByNameAsc(idCategory);
        return Optional.of(mapper.toProducts(entities));
    }

    @Override
    public  Optional<List<Product>> getScarceProducts(Integer quantity) {
        Optional<List<ProductEntity>> byStockQuantityLessThanAndState =
                productCrudRepository.findByStockQuantityLessThanAndState(quantity, true);
        return byStockQuantityLessThanAndState.map(productEntities -> mapper.toProducts(productEntities));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productCrudRepository.findById(productId).map(productEntity -> mapper.toProduct(productEntity));
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = mapper.toProductEntity(product);
        return mapper.toProduct(productCrudRepository.save(productEntity));
    }

    @Override
    public void delete(int productId) {
        productCrudRepository.deleteById(productId);
    }

}
