package com.allot.domain.asm.backend.rest.api.session.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class CaptchaConf {

    @SerializedName("captcha_conf")
    private Captcha captcha;

}
