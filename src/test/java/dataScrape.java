import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;

public class dataScrape {

    @Test
    public void openLandP() {
        ChromeDriver driver = new ChromeDriver();
        String url = "https://www.cvbankas.lt/?location%5B%5D=606&padalinys%5B%5D=76&keyw=";
        driver.get(url);
        //accept cookies
        driver.findElement(By.xpath("/html/body/div[18]/div[2]/div[1]/div[2]/div[2]/button[1]")).click();
    }


    @Test
    public void scrape() {
        ChromeDriver driver = new ChromeDriver();
        String url = "https://www.cvbankas.lt/?location%5B%5D=606&padalinys%5B%5D=76&keyw=";
        driver.get(url);
        driver.manage().window().maximize();
        //accept cookies
        driver.findElement(By.xpath("/html/body/div[18]/div[2]/div[1]/div[2]/div[2]/button[1]")).click();
        //Loop through every page
        for (int i = 1; i < 201; i++) {

            String url1 = "https://www.cvbankas.lt/?location%5B0%5D=606&padalinys%5B0%5D=76&page=" + i;
            driver.get(url1);
            if (!driver.getCurrentUrl().equals(url1)) {
                break; //end loop
            }
            //Wait
            try {
                Thread.sleep(5000); // Wait for 5 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Check all adds
            List<WebElement> h3Elements = driver.findElements(By.xpath("//article"));
            List<WebElement> h3Position = driver.findElements(By.xpath("//h3"));
            List<WebElement> h3Salary = driver.findElements(By.xpath("//*[@id=\"job_ad_10241525\"]/a/div[2]/div[2]/span/span"));
            //Run it through condition
            for (WebElement h3 : h3Elements) {
                String h3Text = h3.getText();
                if (h3Text.contains("QA") || h3Text.contains("automation") || h3Text.contains("engineer") || h3Text.contains("testuotojas")) {
                    System.out.println("");
                    System.out.println("Position: " + h3.findElement(By.xpath(".//h3")).getText()); // Print position
                    System.out.println("Salary: " + h3.findElement(By.xpath(".//*[@id=\"job_ad_10241525\"]/a/div[2]/div[2]/span/span")).getText()); //Print salary
                    System.out.println("Skelbimo URL: " + h3.findElement(By.xpath(".//a")).getAttribute("href")); //Print URL
                }

            }

        }
    }

}
