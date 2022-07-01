package com.allot.domain.asm.frontend.model;

import com.allot.core.testdata.TestDataObject;
import com.allot.domain.asm.backend.rest.api.category.model.Category;
import lombok.*;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
public class AccountCategory extends TestDataObject {

    private String id;
    private List<String> smCategoryList;

    public static AccountCategory map(Category category) {
        return AccountCategory
                .builder()
                .id(category.getIspCategoryId())
                .smCategoryList(category.getSmCategories())
                .build();
    }

}
