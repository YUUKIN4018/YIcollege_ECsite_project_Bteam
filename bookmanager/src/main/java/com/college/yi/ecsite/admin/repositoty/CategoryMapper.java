package com.college.yi.ecsite.admin.repositoty;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryMapper {
    @Select("SELECT parent_category_id FROM categories WHERE category_id = #{categoryId}")
    Long findParentCategoryId(Long categoryId);
}
