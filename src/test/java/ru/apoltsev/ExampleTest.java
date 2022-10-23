package ru.apoltsev;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class ExampleTest {

    @BeforeAll
    static void configuration() {
        Configuration.holdBrowserOpen = true;
    }

    @Test
    @Owner("apoltsev")
    @DisplayName("Простой тест")
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");
        $(".header-search-input").click();
        $(".header-search-input").setValue("qaguru");
        $(".header-search-input").submit();
        $(linkText("eroshenkoam/allure-qaguru")).click();
        $("#issues-tab").click();
        $("#issue_5_link").shouldHave(Condition.text("Заменяем степы на Listener"));

    }
}
