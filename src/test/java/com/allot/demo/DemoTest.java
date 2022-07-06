package com.allot.demo;

import com.allot.cs.CSBaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;

class DemoTest extends CSBaseTest {

    @Description("Demo test")
    @Test
    void demoTest(SoftAssertions soft) {
        open();
        Selenide.$(By.name("q")).shouldBe(visible).setValue("gegege");
        soft.assertThat(Selenide.$(By.name("q")).val()).contains("gegege");
    }

    @Step("Demo test")
    private void open() {
        Selenide.open("https://www.google.com/");
    }

}