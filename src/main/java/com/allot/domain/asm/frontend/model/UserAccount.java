package com.allot.domain.asm.frontend.model;

import com.allot.core.testdata.TestDataObject;
import com.allot.domain.asm.backend.rest.api.account.model.response.Item;
import lombok.*;
import java.util.Arrays;


@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
public class UserAccount extends TestDataObject {

    @NonNull
    private String accountId;
    private Type type;
    //TODO refactor with models List :
    private String productList;
    private String serviceList;
    private String profileList;

    public static UserAccount map(Item accountItem) {
        return UserAccount.builder()
                .accountId(accountItem.getAccountId())
                .type(Type.getTypeByApiValue(accountItem.getAccountType()))
                .build();
    }

    public enum Type {
        RESIDENTIAL("Residential", "residential"),
        ENTERPRISE("Enterprise groups", "enterprise_groups"),
        ;

        String uiValue;
        String apiValue;

        public String getApiValue() {
            return apiValue;
        }

        Type(String uiValue, String apiValue) {
            this.uiValue = uiValue;
            this.apiValue = apiValue;
        }

        public String getUiValue() {
            return uiValue;
        }

        public static Type getTypeByApiValue(String apiValue) {
            return Arrays.stream(Type.values())
                    .filter(type -> type.getApiValue().equals(apiValue))
                    .findFirst()
                    .orElse(null);
        }

    }

}
