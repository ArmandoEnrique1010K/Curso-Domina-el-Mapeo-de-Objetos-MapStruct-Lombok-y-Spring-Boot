package com.enrique.mapstructlombokdemo.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.enrique.mapstructlombokdemo.dto.GetProduct;
import com.enrique.mapstructlombokdemo.entity.Product;

@Mapper(uses = { CategoryMapper.class })
public interface ProductMapper {

  public ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

  @Mappings({
      @Mapping(source = "creationDate", target = "creationDate", dateFormat = "yyyy-MM-dd HH-mm-ss"),
      @Mapping(source = "name", target = "productName"),
      @Mapping(source = "id", target = "productId"),
      @Mapping(source = "category", target = "productCategory"),
      @Mapping(source = "price", target = "price", numberFormat = "$#0.00"),
      // @Mapping(source = "category.id", target = "productCategory.categoryId"),
      // @Mapping(source = "category.name", target = "productCategory.categoryName")
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