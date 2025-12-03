package com.enrique.mapstructlombokdemo.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import com.enrique.mapstructlombokdemo.dto.GetProduct;
import com.enrique.mapstructlombokdemo.entity.Product;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { CategoryMapper.class })
public interface ProductMapper {

  @Mappings({
      @Mapping(source = "creationDate", target = "creationDate", dateFormat = "yyyy-MM-dd HH-mm-ss"),
      @Mapping(source = "name", target = "productName"),
      @Mapping(source = "id", target = "productId"),
      @Mapping(source = "category", target = "productCategory"),
      // @Mapping(source = "category.id", target = "productCategory.categoryId"),
      // @Mapping(source = "category.name", target = "productCategory.categoryName")
      @Mapping(source = "price", target = "price", numberFormat = "$#0.00"),
  })
  GetProduct toGetDTO(Product product);

  @InheritInverseConfiguration
  @Mappings({
      @Mapping(target = "creationDate", ignore = true),
  })
  Product toEntity(GetProduct getProduct);

  List<GetProduct> toGetProductList(List<Product> products);

  List<Product> toEntityList(List<GetProduct> getProductList);
}