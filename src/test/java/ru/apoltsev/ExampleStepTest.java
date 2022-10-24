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
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class ExampleStepTest {

    private static final String REPOSITORY = "qaguru";
    private static final String ISSUE = "Заменяем степы на Listener";

    @BeforeAll
    static void configuration() {
        Configuration.holdBrowserOpen = true;
    }

    @Test
    @Owner("apoltsev")
    @DisplayName("Простой тест с применением лямбды")
    public void testStepIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").setValue(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Кликаем на репозиторий " + REPOSITORY, () -> {
            $(linkText("eroshenkoam/allure-qaguru")).click();

        });
        step("Кликаем на таб Issue  ", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue с названием  " + ISSUE, () -> {
            $("#issue_5_link").shouldHave(Condition.text(ISSUE));
        });

    }

    @Test
    public void testWebStepIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        ExampleWebStepTest steps = new ExampleWebStepTest();
        steps.openPage();
        steps.searchRepository();
        steps.clickOnRepository();
        steps.clickOnIssueTab();
        steps.shouldHaveIssue();
    }
}