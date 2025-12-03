package com.enrique.mapstructlombokdemo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.mapstruct.Mappings;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;

import java.util.List;

import com.enrique.mapstructlombokdemo.repository.CategoryRepository;
import com.enrique.mapstructlombokdemo.entity.Category;
import com.enrique.mapstructlombokdemo.dto.GetCategory;

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
    if (getCategory == null)
      return null;

    Category category = categoryRepository.findById(getCategory.getCategoryId()).orElse(null);

    if (category == null)
      return null;

    category.setId(getCategory.getCategoryId());
    category.setName(getCategory.getCategoryName());
    return category;
  };

  abstract List<GetCategory> toGetCategoryList(List<Category> categories);

  abstract List<Category> toEntityList(List<GetCategory> getCategorylist);
}
