package com.enrique.mapstructlombokdemo.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.enrique.mapstructlombokdemo.dto.GetProduct;
import com.enrique.mapstructlombokdemo.entity.Product;
import com.enrique.mapstructlombokdemo.mapper.ProductMapper;
import com.enrique.mapstructlombokdemo.repository.ProductRepository;

@Configuration
public class InitDatabase {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ProductMapper productMapper;
  // private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
  // private ProductMapper productMapper = ProductMapper.INSTANCE;

  @Bean
  public CommandLineRunner testProductMapperCommand() {
    return args -> {
      List<Product> products = productRepository.findAll();
      System.out.println("PRODUCTS");

      // products.forEach(product -> System.out.println(product));
      products.forEach(System.out::println);

      System.out.println("GET PRODUCT");
      // List<GetProduct> getProductList = products.stream().map(product ->
      // productMapper.toGetDTO(product))
      // .peek(System.out::println)
      // .collect(Collectors.toList());

      List<GetProduct> getProductList = productMapper.toGetProductList(products);
      getProductList.forEach(System.out::println);

      System.out.println("MAPPED PRODUCTS");
      List<Product> mappedProducts = productMapper.toEntityList(getProductList);
      mappedProducts.forEach(System.out::println);

    };
  }

}
