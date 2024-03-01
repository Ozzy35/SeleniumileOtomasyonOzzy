package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sayfalar.AnaSayfa;
import sayfalar.GirisYapSayfasi;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoginTests {
   private WebDriver driver;
   private AnaSayfa anaSayfa;
   private GirisYapSayfasi girisYapSayfasi;

    @BeforeEach
    void setup(){


        driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(400, TimeUnit.SECONDS);
        anaSayfa =new AnaSayfa(driver);
        girisYapSayfasi=new GirisYapSayfasi(driver);

        //deniz chrome onerısı
        ChromeOptions options =new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable notifications");
        DesiredCapabilities cp=new DesiredCapabilities();
        cp.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(cp);

    }

    @Test
    void hesiburadaLoginTesti() throws InterruptedException {

        try {

            WebElement regulationsConcent = driver.findElement(By.cssSelector("button.fc-button.fc-cta-consent.fc-primary-button[aria-label='Consent'] p"));
            regulationsConcent.click();

            System.out.println("Consent butonu tıklandı.");
        } catch (Exception e) {
            System.out.println("Consent butonu bulunamadı veya tıklanabilir olmadı.");

        } finally {
            driver.get("https://www.hepsiburada.com/");

            WebElement linkButtons = driver.findElement(By.cssSelector("button#onetrust-accept-btn-handler"));
            linkButtons.click();


        }

        anaSayfa.elementGozukeneKadarBekle(anaSayfa.hesabim);
        anaSayfa.tusaBas(anaSayfa.hesabim);
        anaSayfa.elementGozukeneKadarBekle(anaSayfa.girisYap);
        anaSayfa.tusaBas(anaSayfa.girisYap);

        anaSayfa.elementGozukeneKadarBekle(girisYapSayfasi.emailAdresi);
        girisYapSayfasi.alanaYaziYaz(girisYapSayfasi.emailAdresi, "seleniumotomasyonu@gmail.com");
        girisYapSayfasi.elementGozukeneKadarBekle(girisYapSayfasi.girisYapTusu); //  driver.findElement(By.id("txtUserName")).sendKeys("seleniumotomasyonu@gmail.com");
        girisYapSayfasi.tusaBas(girisYapSayfasi.girisYapTusu);

//        excilicpt bekleme yontemı, tesımzı tek bır sınıufda yazdık ve kodladık


    }

    @AfterEach
    void tearDown(){
        driver.close();

    }
}
