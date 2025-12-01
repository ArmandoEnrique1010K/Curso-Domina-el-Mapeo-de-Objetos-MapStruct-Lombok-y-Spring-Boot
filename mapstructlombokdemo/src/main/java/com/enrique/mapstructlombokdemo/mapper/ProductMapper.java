package com.enrique.mapstructlombokdemo.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import com.enrique.mapstructlombokdemo.dto.GetProduct;
import com.enrique.mapstructlombokdemo.entity.Product;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

  @Mappings({
      @Mapping(source = "creationDate", target = "creationDate", dateFormat = "yyyy-MM-dd HH-mm-ss")
  })
  GetProduct toGetDTO(Product product);

  @InheritInverseConfiguration
  Product toEntity(GetProduct getProduct);

  List<GetProduct> toGetProductList(List<Product> products);

  List<Product> toEntityList(List<GetProduct> getProductList);
}