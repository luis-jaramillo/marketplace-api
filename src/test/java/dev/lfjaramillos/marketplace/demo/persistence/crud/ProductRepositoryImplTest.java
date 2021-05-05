package dev.lfjaramillos.marketplace.demo.persistence.crud;



import dev.lfjaramillos.marketplace.demo.domain.Product;
import dev.lfjaramillos.marketplace.demo.domain.service.DemoMarketUserDetailService;
import dev.lfjaramillos.marketplace.demo.domain.service.ProductService;
import dev.lfjaramillos.marketplace.demo.persistence.entity.ProductEntity;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public  class ProductRepositoryImplTest {

    @Autowired private ProductCrudRepository productService;

    @Test
   public void myTest() throws Exception {
        Iterable<ProductEntity> all = productService.findAll();
        all.forEach(System.out::println);
        assertThat(all).isNotNull();
    }

}