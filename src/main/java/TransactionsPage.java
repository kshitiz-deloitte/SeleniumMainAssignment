import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;

public class TransactionsPage {
    WebDriver driver;
    List<WebElement> amountList, typeList;
    By amount = By.xpath("//td[@class='ng-binding'][2]");
    By type = By.xpath("//td[@class='ng-binding'][3]");
    public TransactionsPage(WebDriver driver) {
        this.driver = driver;
    }
    public Boolean verifyTransaction(Transactions transactions){
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(amount));
        amountList = driver.findElements(amount);
        typeList = driver.findElements(type);
        List<String> type = transactions.getType();
        List<Double> amount = transactions.getAmount();
        int i = 0;
        for (WebElement amountWeb:amountList){
            if (!Double.valueOf(amountWeb.getText()).equals(amount.get(i))){
                return false;
            }
            if (typeList.get(i).getText().compareTo(type.get(i))!=0){
                return false;
            }
            i++;
        }
        return true;
    }

}
