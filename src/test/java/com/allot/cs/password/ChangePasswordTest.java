package com.allot.cs.password;

import com.allot.cs.*;
import com.allot.cs.page.*;
import com.allot.cs.step.LoginSteps;
import io.qameta.allure.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.*;

import java.util.stream.Stream;

import static com.allot.core.assertj.PageAssert.assertThat;

class ChangePasswordTest extends CSBaseTest {

    @Autowired
    private LoginSteps loginSteps;
    @Value("${app.user}")
    private String userName;
    @Value("${user.password}")
    private String password;
    @Value("${intermediate.password}")
    private String intermediatePassword;
    @Value("${changed.password}")
    private String newPassword;

    Stream<Arguments> getPasswords() {
        return Stream.of(
                Arguments.of(password, newPassword),
                Arguments.of(newPassword, intermediatePassword),
                Arguments.of(intermediatePassword, password));
    }

    @Epic(value = "New CLRS automation")
    @Feature(value = "[SmartTF][CS Front-end] Return back password to default after first login")
    @Issue("AS-29993")
    @TmsLink(value = "76787")
    @Owner(value = "Natalia Polevnichaya - npaliaunichaya@allot.com")
    @Description("Change password")
    @ParameterizedTest
    @MethodSource("getPasswords")
    @Disabled
    void changePasswordTest(String userPassword, String newPassword) {
        HomePage homePage = loginSteps.login(userName, userPassword);
        assertThat(homePage).isPageDisplayed();
        PreferencePage preferencePage = homePage.expandNavigationPanel().clickPreferenceButton();
        var isPasswordUpdated = preferencePage
                .clickChangePasswordButton()
                .updatePassword(userPassword, newPassword);
        Assertions.assertThat(isPasswordUpdated).isTrue();
        LogoutForm logoutForm = homePage.logout();
        assertThat(logoutForm)
                .withFailMessage("Logout Form is not displayed! The user is not logged out")
                .isPageDisplayed();
    }

}