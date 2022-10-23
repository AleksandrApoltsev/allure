package ru.apoltsev;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;


public class ExampleWebStepTest {

    @Step("Открываем главную страницу")
    public void openPage() {
        open("https://github.com");
    }

    @Step("Ищем репозиторий ")
    public void searchRepository() {
        $(".header-search-input").click();
        $(".header-search-input").setValue("qaguru");
        $(".header-search-input").submit();
    }

    @Step("Кликаем на репозиторий ")
    public void clickOnRepository() {
        $(linkText("eroshenkoam/allure-qaguru")).click();
    }

    @Step("Кликаем на таб Issue ")
    public void clickOnIssueTab() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issue с названием  \"Заменяем степы на Listener\"")
    public void shouldHaveIssue() {
        $("#issue_5_link").shouldHave(Condition.text("Заменяем степы на Listener"));
    }
}
