package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;

import java.util.ArrayList;
import java.util.List;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {
        SoftAssert softAssert = new SoftAssert();
        String firstName = "Damir";
        String lastName = "Shakiryanov";
        String email = "12345@mail.ru";
        String mobileNumber = "1234567890";
        String dateOfBirth = "27 September,2001";
        String address = "Respublikanskaya";
        List<String> testData = new ArrayList<>();
        testData.add(firstName + " " + lastName);
        testData.add(email);
        testData.add("Male");
        testData.add(mobileNumber);
        testData.add(dateOfBirth);
        testData.add("Maths");
        testData.add("");
        testData.add("Prince.jpg");
        testData.add(address);
        testData.add("NCR Delhi");
        List<String> displayedText;
        LoginPage loginPage = new LoginPage(driver);

        loginPage.inputFirstName(firstName)
                .inputLastName(lastName)
                .inputUserEmail(email)
                .chooseMaleGender()
                .inputNumber(mobileNumber)
                .chooseDateOfBirth(dateOfBirth)
                .chooseMathSubject()
                .uploadFile()
                .inputAddress(address)
                .chooseFirstState()
                .chooseFirstCity()
                .clickSubmitButton();
        displayedText = loginPage.getTextFromTable();

        softAssert.assertTrue(loginPage.isSuccessTextVisible(), "Заголовок не отобразился");
        softAssert.assertEquals(loginPage.getSuccessText(), "Thanks for submitting the form",
                "Текст заголовка не совпадает");
        for (int i = 0; i < testData.size(); i++) {
            softAssert.assertEquals(displayedText.get(i), testData.get(i));
        }
        softAssert.assertAll();
    }
}
