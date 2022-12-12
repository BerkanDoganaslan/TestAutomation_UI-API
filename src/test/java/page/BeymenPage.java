package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class BeymenPage extends BasePage {

    @FindBy(id = "onetrust-accept-btn-handler")
    public WebElement popup;
    @FindBy(xpath = "//input[@class='default-input o-header__search--input']")
    public WebElement searchBar;
    @FindBy(xpath = "//button[@id='genderManButton']")
    public WebElement gender;
    @FindBy(xpath = "//h3[@class='m-productCard__desc'][1]")
    public WebElement firstProduct;
    @FindBy(xpath = "//ins[@id='priceNew']")
    public WebElement productPrice;
    @FindBy(xpath = "//span[@class='o-productDetail__description']")
    public WebElement productInfo;
    @FindBy(xpath = "//a[@class='swiper-slide swiper-slide-active']")
    public WebElement productColor;
    @FindBy(xpath = "(//span[@class='m-variation__item'])[1]")
    public WebElement productSize;
    @FindBy(xpath = "//button[@id='addBasket']")
    public WebElement basketButton;
    @FindBy(xpath = "//a[@class='o-header__userInfo--item bwi-cart-o -cart']")
    public WebElement myBasket;
    @FindBy(xpath = "//select[@id='quantitySelect0-key-0']")
    public WebElement basketProductPiece;
    @FindBy(xpath = "//span[@class='m-basket__productInfoName']")
    public WebElement basketProductInfo;
    @FindBy(xpath = "//span[@class='m-productPrice__salePrice']")
    public WebElement basketProductPrice;

    @FindBy(xpath = "//button[@id='removeCartItemBtn0-key-0']")
    public WebElement basketProductDelete;
    @FindBy(xpath = "//strong[text()='Sepetinizde Ürün Bulunmamaktadır']")
    public WebElement deleteResult;
}
