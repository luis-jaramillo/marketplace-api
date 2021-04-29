package dev.lfjaramillos.market.demo.persistence.mapper;

import dev.lfjaramillos.market.demo.domain.Product;
import dev.lfjaramillos.market.demo.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductoMapper {

    @Mappings({
            @Mapping(source = "productId", target = "productId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "idCategory", target = "categoryId"),
            @Mapping(source = "sale_Price", target = "price"),
            @Mapping(source = "stockQuantity", target = "stock"),
            @Mapping(source = "state", target = "active"),
            @Mapping(source = "categoryEntity", target = "category"),
    })
    Product toProduct(ProductEntity productEntity);

    List<Product> toProducts(List<ProductEntity> productEntities);


    @Mappings({
            @Mapping(target = "barCode", ignore = true),
            @Mapping(target = "idCategory", ignore = true),
            @Mapping(target = "sale_Price", ignore = true),
            @Mapping(target = "stockQuantity", ignore = true),
            @Mapping(target = "state", ignore = true),
            @Mapping(target = "categoryEntity", ignore = true),

    })
    ProductEntity toProductEntity(Product product);
}
