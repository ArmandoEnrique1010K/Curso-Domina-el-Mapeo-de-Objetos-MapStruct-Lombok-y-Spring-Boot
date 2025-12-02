package com.enrique.mapstructlombokdemo.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.enrique.mapstructlombokdemo.dto.GetCategory;
import com.enrique.mapstructlombokdemo.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

  public CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

  // // @Autowired
  // // private CategoryRepository categoryRepository;

  @Mappings({
      @Mapping(source = "id", target = "categoryId"),
      @Mapping(source = "name", target = "categoryName")
  })
  GetCategory toGetCategory(Category category);

  @InheritInverseConfiguration
  Category toEntity(GetCategory getCategory);
  // // {
  // // if (getCategory == null)
  // // return null;

  // // Category category =
  // categoryRepository.findById(getCategory.getCategoryId()).orElse(null);

  // // if (category == null)
  // // return null;

  // // category.setId(getCategory.getCategoryId());
  // // category.setName(getCategory.getCategoryName());
  // // return category;
  // // };

  List<GetCategory> toGetCategoryList(List<Category> categories);

  List<Category> toEntityList(List<GetCategory> getCategorylist);
}
