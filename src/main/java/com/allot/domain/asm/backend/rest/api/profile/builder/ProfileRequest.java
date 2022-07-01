package com.allot.domain.asm.backend.rest.api.profile.builder;

import com.allot.domain.asm.backend.rest.api.account.model.provision.*;
import com.allot.domain.asm.backend.rest.api.profile.model.*;
import com.allot.domain.asm.frontend.model.AccountProfile;
import java.util.Collections;


public class ProfileRequest {

    public static Profile addProfile(AccountProfile profile) {
        var profiles = Collections.singletonList(ProfileI18n.builder()
                .profileName(profile.getName())
                .build());
        var serviceConfiguration = ServicesConfiguration.builder()
                .adsFree(AdsFree.builder().provisioned(profile.isAdsFree()).build())
                .antiBullying(AntiBullying.builder().enabled(profile.isAntiBullying()).build())
                .autoNotice(AutoNotice.builder().provisioned(profile.isAutoNotice()).build())
                .parentalControl(ParentalControl.builder()
                        .enabled(profile.isParentalControl())
                        .blockedCategories(profile.getBlockedCategories())
                        .blockedUrls(profile.getBlockedUrls())
                        .build())
                .threatProtection(ThreatProtection.builder().provisioned(profile.isThreatProtection()).build())
                .build();
        return Profile.builder()
                .profileDefinitionId(profile.getId())
                .profileI18n(profiles)
                .servicesConfiguration(serviceConfiguration)
                .build();
    }

}
