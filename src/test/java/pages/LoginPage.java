package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(id = "userEmail")
    private WebElement userEmailField;

    @FindBy(css = "[for='gender-radio-1']")
    private WebElement maleGenderButton;

    @FindBy(id = "userNumber")
    private WebElement userNumberField;

    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirthField;

    @FindBy(id = "subjectsInput")
    private WebElement subjectField;

    @FindBy(id = "uploadPicture")
    private WebElement fileField;

    @FindBy(id = "currentAddress")
    private WebElement addressField;

    @FindBy(css = "#state div div.css-1wy0on6 div")
    private WebElement stateSelector;

    @FindBy(css = "#city div.css-1wy0on6 div")
    private WebElement citySelector;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(className = "subjects-auto-complete__menu")
    private WebElement subject;

    @FindAll(@FindBy(xpath = "//td"))
    private List<WebElement> tableLines;

    @FindBy(className = "modal-title")
    private WebElement successTextElement;

    @Step("Вставили {firstName} в поле ввода имени")
    public LoginPage inputFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
        return this;
    }

    @Step("Вставили {lastName} в поле ввода фамилии")
    public LoginPage inputLastName(String lastName) {
        lastNameField.sendKeys(lastName);
        return this;
    }

    @Step("Вставили {userEmail} в поле ввода почты")
    public LoginPage inputUserEmail(String userEmail) {
        userEmailField.sendKeys(userEmail);
        return this;
    }

    @Step("Выбрали мужской пол")
    public LoginPage chooseMaleGender() {
        maleGenderButton.click();
        return this;
    }

    @Step("Вводим {number} в поле ввода номера")
    public LoginPage inputNumber(String number) {
        userNumberField.sendKeys(number);
        return this;
    }

    @Step("Выбрали дату рождения")
    public LoginPage chooseDateOfBirth(String dateOfBirth) {
        dateOfBirthField.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"), dateOfBirth, Keys.ENTER);
        return this;
    }

    @Step("выбрали тему Maths")
    public LoginPage chooseMathSubject() {
        subjectField.sendKeys("maths");
        subject.click();
        return this;
    }

    @Step("Загрузили изображение")
    public LoginPage uploadFile() {
        fileField.sendKeys("C:\\Users\\Пользователь\\IdeaProjects\\testproject\\src\\test\\resources\\Prince.jpg");
        return this;
    }

    @Step("Написали {address} в поле ввода")
    public LoginPage inputAddress(String address) {
        addressField.sendKeys(address);
        return this;
    }

    @Step("Выбрали штат из выпадающего списка")
    public LoginPage chooseFirstState() {
        stateSelector.click();
        new Actions(driver).sendKeys(Keys.ENTER).perform();
        return this;
    }

    @Step("Выбрали город из выпадающего списка")
    public LoginPage chooseFirstCity() {
        citySelector.click();
        new Actions(driver).sendKeys(Keys.ENTER).perform();
        return this;
    }

    @Step("Подтвердили вход")
    public LoginPage clickSubmitButton() {
        submitButton.click();
        return this;
    }

    public String getSuccessText() {
        return successTextElement.getText();
    }

    public boolean isSuccessTextVisible() {
        return successTextElement.isDisplayed();
    }

    public List<String> getTextFromTable() {
        List<String> insertedValues = new ArrayList<>();
        for (int i = 0; i < tableLines.size(); i++) {
            if (i % 2 != 0) {
                insertedValues.add(tableLines.get(i).getText());
            }
        }
        return insertedValues;
    }
}

