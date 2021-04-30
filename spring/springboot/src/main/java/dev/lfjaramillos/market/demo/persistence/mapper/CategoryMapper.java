package dev.lfjaramillos.market.demo.persistence.mapper;

import dev.lfjaramillos.market.demo.domain.Category;
import dev.lfjaramillos.market.demo.persistence.entity.CategoryEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mappings({
            @Mapping(source = "id", target = "categoryId"),
            @Mapping(source = "description", target = "category"),
            @Mapping(source = "active", target = "active")

    })
    Category toCategory(CategoryEntity category);

    @InheritInverseConfiguration
    @Mapping(target = "productEntities" , ignore = true)
    CategoryEntity toCategoryEntity(Category category);

}
