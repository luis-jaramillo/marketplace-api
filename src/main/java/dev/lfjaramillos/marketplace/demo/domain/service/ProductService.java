package dev.lfjaramillos.marketplace.demo.domain.service;

import dev.lfjaramillos.marketplace.demo.domain.Product;
import dev.lfjaramillos.marketplace.demo.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
      return   productRepository.getAll();
    }

    public Optional<Product> getProduct(Integer productId){
        return productRepository.getProduct(productId);
    }

   public Optional<List<Product>> getByCategory(Integer categoryId) {
      return   productRepository.getByCategory(categoryId);
    }

    public Optional<List<Product>> getScarceProducts(Integer quantity){
        return productRepository.getScarceProducts(quantity);
    }

    public Product save(Product product){
       return productRepository.save(product);
    }


    public boolean delete(int productId){

         return productRepository.getProduct(productId).map(product ->{
              productRepository.delete(product.getProductId());
              return true;
        }).orElse(false);

    }

}
