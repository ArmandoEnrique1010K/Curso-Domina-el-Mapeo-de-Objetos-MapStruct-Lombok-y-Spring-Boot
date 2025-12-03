package com.enrique.mapstructlombokdemo.mapper;

import java.util.List;

import com.enrique.mapstructlombokdemo.repository.CategoryRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import com.enrique.mapstructlombokdemo.dto.GetCategory;
import com.enrique.mapstructlombokdemo.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;

// @Mapper(componentModel = "spring")
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CategoryMapper {

  // public CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

  @Autowired
  private CategoryRepository categoryRepository;

  @Mappings({
      @Mapping(source = "id", target = "categoryId"),
      @Mapping(source = "name", target = "categoryName")
  })
  abstract GetCategory toGetCategory(Category category);

  // @InheritInverseConfiguration
  Category toEntity(GetCategory getCategory) {
    if (getCategory == null) return null;

    Category category = categoryRepository.findById(getCategory.getCategoryId()).orElse(null);

    if (category == null) return null;

    category.setId(getCategory.getCategoryId());
    category.setName(getCategory.getCategoryName());
    return category;
  };

  abstract  List<GetCategory> toGetCategoryList(List<Category> categories);

  abstract  List<Category> toEntityList(List<GetCategory> getCategorylist);
}
