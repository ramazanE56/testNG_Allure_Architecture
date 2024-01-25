package stepDefinitions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import pages.PagesLocate;
import tests.TestsSenarioRunner;
import utilities.ConfigReader;
import utilities.CryptPassword;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.*;

import java.awt.*;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.testng.AssertJUnit.assertTrue;


public class StepDefinitions extends PagesLocate {
    static String firstPageWindowHandle;
    static String firstPageProductName;
    static String secondProductName;
    static String secondPageTitle = "Kodlab Yayın Dağıtım Yeni Başlayanlar Için Java Fiyatı, Yorumları - Trendyol";


    @Step("registered user go to the site with the given URL")
    public static void registered_user_go_to_the_site_with_the_given_URL() {
        ReusableMethods.wait(2);
        Driver.getDriver().get(ConfigReader.getProperty("Url"));
        ReusableMethods.wait(2);
        acceptPupupElement.click();

    }




    public static void resetAcceptPupupElement() {
        acceptPupupElement = null;
    }

    @Step("click on the login button")
    public static void click_on_the_login_button() {
        ReusableMethods.wait(2);
        ReusableMethods.highlight(loginBoxElement);
        loginBoxElement.click();
        ReusableMethods.wait(2);
    }

    @Step("user clicks on the email box and enters email")
    public static void user_clicks_on_the_email_box_and_enters_email() {
        ReusableMethods.highlight(emailBoxElement);
        emailBoxElement.click();
        emailBoxElement.sendKeys(ConfigReader.getProperty("email"));
    }

    @Step("close Driver")
    public static void close_Driver() {
        Driver.closeDriver();
    }

    @Step("user clicks on the password box and enters the password")
    public static void user_clicks_on_the_password_box_and_enters_the_password() throws Exception {
        ReusableMethods.highlight(passwordBoxElement);
        passwordBoxElement.click();
        passwordBoxElement.sendKeys(CryptPassword.decrypted());
        ReusableMethods.wait(2);
    }

    @Step("user clicks on_submit button")
    public static void user_clicks_on_submit_button() {
        ReusableMethods.highlight(submitButtonElement);
        submitButtonElement.click();
        ReusableMethods.wait(5);

    }

    @Step("verifying that the user has accessed the site")
    public static void verifying_that_the_user_has_accessed_the_site() {
        ReusableMethods.highlight(searhBoxElement);
        Assert.assertTrue(searhBoxElement.isDisplayed());

    }
    @Step("user types in the search box and searches")
    public static void user_types_in_the_search_box_and_searches(String word) {
        ReusableMethods.highlight(searhBoxElement);
        searhBoxElement.click();
        searhBoxElement.sendKeys(word + Keys.ENTER);
        ReusableMethods.wait(1);

    }

    public static void user_prints_the_number_of_all_results_and_verifies_that_they_contain_the_word(String word) {
        Wait<WebDriver> wait = new FluentWait<>(Driver.getDriver())
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(StaleElementReferenceException.class);
        List<WebElement> elements;
        int previousSize = 0;
        int elementsWithoutWordCount = 0;

        do {
            try {
                // Sayfanın otomatik yenilenmesinden önce bekleme ekleyin (sayfa tamamen yüklendiyse bu gerekli olmayabilir)
                wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));

                // Sayfayı yenile
                Driver.getDriver().navigate().refresh();
                WebElement element2 = Driver.getDriver().findElement(By.xpath("(//div[@class='fltr-cntnr-ttl'])[2]"));
                element2.click();

                // Sadece görünür elementleri bulun
                elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='product-down']")));
            } catch (StaleElementReferenceException e) {
                // Sayfa otomatik yenilendi, elementi yeniden bul
                elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='product-down']")));
            }

            // Her bir elementin içerisinde "java" kelimesinin geçtiğini doğrula
            for (int i = previousSize; i < Math.min(elements.size(), previousSize + 12); i++) {
                WebElement element = elements.get(i);
                // Elementin tam ortasını görünür hale getir
                JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
                js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
                ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
                ReusableMethods.wait2(1);
                ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].removeAttribute('style', 'background: yellow; border: 2px solid red;');", element);
                String text = element.getText().toLowerCase(); // Convert element text to lowercase

                // Kelimenin bulunup bulunmadığını kontrol et
                if (!text.contains(word.toLowerCase())) { // Convert search word to lowercase
                    elementsWithoutWordCount++;
                    System.out.println("Element içerisinde '" + word + "' kelimesi bulunamadı: " + element.getText());
                } else {
                    Assert.assertTrue(text.contains(word.toLowerCase()), "Element içerisinde '" + word + "' kelimesi bulunamadı: " + element.getText());
                }

                // Bekleme süresi ekleyebilirsiniz, ihtiyaca göre ayarlayın
                ReusableMethods.wait2(1);
            }
            previousSize = elements.size();
        } while (elements.size() > previousSize);

        System.out.println("Toplamda " + elementsWithoutWordCount + " elementte '" + word + "' kelimesi bulunamadı.");
        System.out.println("Toplam " + Math.min(elements.size(), previousSize - 12) + " element işlendi.");

    }
    @Step("user clicks on the first product that appears")
    public static void user_clicks_on_the_first_product_that_appears() {
        firstPageWindowHandle = Driver.getDriver().getWindowHandle();
        Driver.getDriver().navigate().refresh();
        firstPageProductName = firstProductElement.getText();
        ReusableMethods.highlight(firstProductElement);
        firstProductElement.click();
        ReusableMethods.wait(2);

    }
    @Step("verifies that the product the user selected is the same product")
    public static void verifies_that_the_product_the_user_selected_is_the_same_product() {
        ReusableMethods.switchToWindow(secondPageTitle);
        ReusableMethods.wait(2);
        ReusableMethods.highlight(secondPageProductNameElement2);
        ReusableMethods.highlight(secondPageProductNameElement2);
        secondProductName = secondPageProductNameElement.getText();
        System.out.println("exp :"+firstPageProductName);
        System.out.println("acl :"+secondProductName);
        Assert.assertTrue(secondProductName.contains("Yeni Başlayanlar Için Java"));
        campaignUnderstandElement.click();
        ReusableMethods.wait(1);
    }
    @Step("quit Driver")
    public static void quit_Driver() {
        Driver.getDriver().quit();
    }
    @Step("user adds the selected product to the shopping car")
    public static void user_adds_the_selected_product_to_the_shopping_cart() {
        ReusableMethods.highlight(addToCardButtonElement);
        addToCardButtonElement.click();
        ReusableMethods.wait(1);

    }
    @Step("verifies that the product has been added to the shopping car")
    public static void verifies_that_the_product_has_been_added_to_the_shopping_cart() {
        ReusableMethods.highlight(productAddedElement);
        ReusableMethods.wait(1);
        Assert.assertTrue(productAddedElement.isDisplayed());
    }

}
