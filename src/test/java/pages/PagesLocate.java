package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;

public class PagesLocate {
    public PagesLocate(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(xpath = "(//p[@class='link-text'])[1]")
    public static WebElement loginBoxElement;

    @FindBy(id = "login-email")
    public static WebElement emailBoxElement;

    @FindBy(id = "login-password-input")
    public static WebElement passwordBoxElement;

    @FindBy(xpath = "//button[@type='submit']")
    public static WebElement submitButtonElement;

    @FindBy(xpath = "//input[@data-testid='suggestion']")
    public static WebElement searhBoxElement;

    @FindBy(id = "Combined-Shape")
    public static WebElement xPupupIconElement;
    @FindBy(css = "#onetrust-accept-btn-handler")
    public static WebElement acceptPupupElement;

  //  @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
  //  public static WebElement acceptPupupElement;

    @FindBy(xpath = "(//div[@class='product-down'])[1]")
    public static WebElement firstProductElement;

    @FindBy(xpath = "(//*[text()='Yeni Başlayanlar Için Java'])[2]")
    public static WebElement secondPageProductNameElement;

    @FindBy(xpath = "//h1[@class='pr-new-br']")
    public static WebElement secondPageProductNameElement2;

    @FindBy(xpath = "//div[@class='add-to-basket-button-text']")
    public static WebElement addToCardButtonElement;

    @FindBy(xpath = "//span[@class='product-preview-status-text']")
    public static WebElement productAddedElement;

    @FindBy(xpath = "(/html/body)[1]")
    public static WebElement dataTrackerRootElement;

    @FindBy(xpath = "//div[@class='campaign-button bold']")
    public static WebElement campaignUnderstandElement;

}
