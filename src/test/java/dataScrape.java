import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class dataScrape {

    @Test
    public void scrape(){
        ChromeDriver driver = new ChromeDriver();
        String url = "https://www.cvbankas.lt/?location%5B%5D=606&padalinys%5B%5D=76&keyw=";
        driver.get(url);
        //accept cookies
        driver.findElement(By.xpath("/html/body/div[18]/div[2]/div[1]/div[2]/div[2]/button[1]")).click();
        //Loop through every page
        
    }




}
