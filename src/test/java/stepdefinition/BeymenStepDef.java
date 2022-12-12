package stepdefinition;

import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.support.ui.Select;
import page.BeymenPage;
import page.Dashboard;
import logger.TestResultLogger;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import java.io.*;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(TestResultLogger.class)

public class BeymenStepDef {
    Dashboard dashboardPage = new Dashboard();
    BeymenPage beymenPage = new BeymenPage();
    Cell cell;
    Cell cell1;
    Actions actions = new Actions(Driver.getDriver());
    String productInfo;
    String productPrice;

    Logger logger = Logger.getLogger(String.valueOf(BeymenStepDef.class));

    @Given("The user should be able to login the beymen page")
    public void the_user_should_be_able_to_login_the_beymen_page() {


        Driver.getDriver().get(ConfigReader.getProperty("Url"));
        logger.info("url'e gidildi");
        ReusableMethods.waitFor(3);

        String actualUrl = Driver.getDriver().getCurrentUrl();
        assertEquals("https://www.beymen.com/",actualUrl);

        beymenPage.popup.click();
        beymenPage.gender.click();
        logger.info("popup kabul edildi");

    }
    @When("The user should be able to write the product name")
    public void the_user_should_be_able_to_write_the_product_name() throws IOException {

        String filePath="src/test/resources/testFile/BeymenSearch.xlsx";
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook= WorkbookFactory.create(fis);
        Sheet sheet=workbook.getSheet("Sayfa1");
        Row row=sheet.getRow(0);
        cell=row.getCell(0);
        String expected = cell.toString();
        actions.click(beymenPage.searchBar).perform();
        ReusableMethods.waitFor(3);
        actions.sendKeys(expected).perform();
        ReusableMethods.waitFor(3);
        actions.doubleClick().sendKeys(Keys.DELETE).perform();

    }
    @Then("The user should be able to delete the product name from search bar")
    public void the_user_should_be_able_to_delete_the_product_name_from_search_bar() {

        ReusableMethods.waitFor(3);
        beymenPage.searchBar.clear();

    }
    @Then("The user should be able to write the other product name")
    public void the_user_should_be_able_to_write_the_other_product_name() throws IOException {

        String filePath="src/test/resources/testFile/BeymenSearch.xlsx";
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook= WorkbookFactory.create(fis);
        Sheet sheet=workbook.getSheet("Sayfa1");
        Row row=sheet.getRow(0);
        cell1=row.getCell(1);
        String expected = cell1.toString();
        actions.click(beymenPage.searchBar).perform();
        ReusableMethods.waitFor(2);
        actions.sendKeys(expected).perform();
        ReusableMethods.waitFor(2);

    }


    @Then("The user should be able to search for the product name entered")
    public void theUserShouldBeAbleToSearchForTheProductNameEntered() {
        actions.sendKeys(Keys.ENTER).perform();
        ReusableMethods.waitFor(5);
    }

    @Then("The user should be able to choice a product")
    public void theUserShouldBeAbleToChoiceAProduct() {
        beymenPage.firstProduct.click();
        ReusableMethods.waitFor(2);
    }

    @Then("User saves selected product and price information")
    public void userSavesSelectedProductAndPriceInformation() throws IOException {
        File file = new File("src/test/resources/testFile/productInformation1.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        productInfo=beymenPage.productInfo.getText();
        productPrice=beymenPage.productPrice.getText();
        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        bWriter.write(productInfo);
        bWriter.write(" --> ");
        bWriter.write(productPrice);

        bWriter.close();

    }

    @Then("The user should be able to add the selected product to the basket")
    public void theUserShouldBeAbleToAddTheSelectedProductToTheBasket() {

        ReusableMethods.waitFor(3);
        beymenPage.productSize.click();
        ReusableMethods.waitFor(3);
        beymenPage.basketButton.click();
        ReusableMethods.waitFor(8);
        beymenPage.myBasket.click();

    }

    @Then("The user should be able to verify the price information of the product in the basket")
    public void theUserShouldBeAbleToVerifyThePriceInformationOfTheProductInTheBasket() {
        ReusableMethods.waitForVisibility(beymenPage.basketProductInfo,15);

        String basketProductPrice=beymenPage.basketProductPrice.getText();

        assertEquals(productPrice,basketProductPrice);

    }

    @Then("The user should be able to increase the number of products")
    public void theUserShouldBeAbleToIncreaseTheNumberOfProducts() {
        ReusableMethods.waitFor(5);
        Select select=new Select(beymenPage.basketProductPiece);
        select.selectByVisibleText("2 adet");
        ReusableMethods.waitFor(3);
        String selectObject=select.getFirstSelectedOption().getText();
        assertEquals("2 adet",selectObject);

        
    }

    @Then("The user should be able to delete items in basket")
    public void theUserShouldBeAbleToDeleteItemsInBasket() {
        beymenPage.basketProductDelete.click();
        ReusableMethods.waitFor(3);
        String expectedDeleteMessage="SEPETINIZDE ÜRÜN BULUNMAMAKTADIR";
        String actualDeleteMessage=beymenPage.deleteResult.getText();
        assertEquals(expectedDeleteMessage,actualDeleteMessage);
    }
}

