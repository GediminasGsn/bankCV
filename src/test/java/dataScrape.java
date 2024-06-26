import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;

public class dataScrape {
    private static ChromeDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void openLandP() {

        String url = "https://www.cvbankas.lt/?location%5B%5D=606&padalinys%5B%5D=76&keyw=";
        driver.get(url);
        //accept cookies
        driver.findElement(By.xpath("/html/body/div[18]/div[2]/div[1]/div[2]/div[2]/button[1]")).click();
    }


    @Test
    public void scrape() {

        String url = "https://www.cvbankas.lt/?location%5B%5D=606&padalinys%5B%5D=76&keyw=";
        driver.get(url);
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
            //Listing all adds
            List<WebElement> h3Elements = driver.findElements(By.xpath("//article"));

            //Run it through condition
            for (WebElement h3 : h3Elements) {
                String h3Text = h3.getText();
                if (h3Text.contains("QA") || h3Text.contains("automation") || h3Text.contains("engineer") || h3Text.contains("testuotojas") || h3Text.contains("Junior") || h3Text.contains("Tester") || h3Text.contains("Quality") || h3Text.contains("Assurance") || h3Text.contains("Manual") || h3Text.contains("Rankinis")) {
                    System.out.println("");
                    System.out.println("Pozicija: " + h3.findElement(By.xpath(".//h3")).getText()); // Print position
                    try {
                        String salary = h3.findElement(By.xpath("//a/div[2]/div[2]/span/span/span")).getText(); //Print salary
                        System.out.println("Darbo uždarbis: " + salary);
                    } catch (NoSuchElementException e) {
                        System.out.println("Darbo uždarbis nematomas");
                    }
                    System.out.println("Skelbimo URL: " + h3.findElement(By.xpath(".//a")).getAttribute("href")); //Print URL
                }

            }

        }
    }

}
