package tests;


import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.PagesLocate;
import stepDefinitions.StepDefinitions;
import utilities.Driver;

import java.lang.reflect.Method;




@Listeners({AllureListener.class})
public class TestsSenarioRunner extends StepDefinitions {

    @Test(priority = 1, description = "Scenario to log in to the site as a registered user")
    @Epic("Web interface")
    @Feature("Features")
    @Story("TrendyolTest Senaryo 1")
    @Description("Test Description:Login to the site as a registered user")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("İsmail Temiz")
    @Link(name = "Trendyol site",url = "https://trendyol.com/")
    @Issue("TR-001")
    @TmsLink("US-001")
    public static void testSenario01() throws Exception {

        registered_user_go_to_the_site_with_the_given_URL();
        click_on_the_login_button();
        user_clicks_on_the_email_box_and_enters_email();
        user_clicks_on_the_password_box_and_enters_the_password();
        user_clicks_on_submit_button();
        verifying_that_the_user_has_accessed_the_site();
        close_Driver();


    }

    @Test(priority = 2,description = "Registered user's product search test on the site")
    @Epic("Web interface")
    @Feature("Features")
    @Story("TrendyolTest Senaryo 2")
    @Description("Test Description:Registered user's product search test on the site")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("İsmail Temiz")
    @Link(name = "Trendyol site",url = "https://trendyol.com/")
    @Issue("TR-002")
    @TmsLink("US-001")
    public static void testSenario02() throws Exception {

        registered_user_go_to_the_site_with_the_given_URL();
        click_on_the_login_button();
        user_clicks_on_the_email_box_and_enters_email();
        user_clicks_on_the_password_box_and_enters_the_password();
        user_clicks_on_submit_button();
        user_types_in_the_search_box_and_searches("Tava");
        user_prints_the_number_of_all_results_and_verifies_that_they_contain_the_word("Tava");
        close_Driver();


    }

    @Test(priority = 3,description = "Test of adding items to registered user's shopping cart")
    @Epic("Web interface")
    @Feature("Features")
    @Story("TrendyolTest Senaryo 3")
    @Description("Test Description:Test of adding items to registered user's shopping cart")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("İsmail Temiz")
    @Link(name = "Trendyol site",url = "https://trendyol.com/")
    @Issue("TR-003")
    @TmsLink("US-001")
    public static void testSenario03() throws Exception {

        registered_user_go_to_the_site_with_the_given_URL();
        click_on_the_login_button();
        user_clicks_on_the_email_box_and_enters_email();
        user_clicks_on_the_password_box_and_enters_the_password();
        user_clicks_on_submit_button();
        user_types_in_the_search_box_and_searches("Java");
        user_clicks_on_the_first_product_that_appears();
        verifies_that_the_product_the_user_selected_is_the_same_product();
        user_adds_the_selected_product_to_the_shopping_cart();
        verifies_that_the_product_has_been_added_to_the_shopping_cart();
        quit_Driver();


    }



}
