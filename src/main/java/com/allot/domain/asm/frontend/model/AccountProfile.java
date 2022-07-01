package com.allot.domain.asm.frontend.model;

import com.allot.core.testdata.TestDataObject;
import com.allot.domain.asm.backend.rest.api.profile.model.Profile;
import lombok.*;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
public class AccountProfile extends TestDataObject {

    private String id;
    private String name;
    private List<String> blockedCategories;
    private List<String> blockedUrls;
    private boolean isAdsFree;
    private boolean isAntiBullying;
    private boolean isAutoNotice;
    private boolean isLocationManagement;
    private boolean isParentalControl;
    private boolean isThreatProtection;


    public static AccountProfile map(Profile profile) {
        return AccountProfile
                .builder()
                .id(profile.getProfileDefinitionId())
                .name(profile.getProfileI18n().get(0).getProfileName())
                .build();
    }

}
