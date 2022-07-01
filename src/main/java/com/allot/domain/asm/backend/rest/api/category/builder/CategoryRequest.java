package com.allot.domain.asm.backend.rest.api.category.builder;

import com.allot.domain.asm.backend.rest.api.category.model.Category;
import com.allot.domain.asm.frontend.model.AccountCategory;


public class CategoryRequest {

    public static Category addCategory(AccountCategory category) {
        return Category.builder()
                .ispCategoryId(category.getId())
                .build();
    }

}
