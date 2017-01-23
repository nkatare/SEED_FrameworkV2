package javaSeedTestScripts.TestGoogle;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import javaSeed.constants.Const;
import javaSeed.objectRepository.OR;
import javaSeed.utils.Screenshot;
import javaSeedTestScripts.TestConsts;

public class Google {

	private WebDriver driver = Const.driver;
	private HashMap<String, String[]> ORMap = Const.ORMap;
	private ExtentTest TestLogger = Const.etTestCases;
	
	public String GoToGoogle() throws IOException{
		
		try{
			
			new WebDriverWait(driver, 10);
			
			// Get the Internet Banking URL
			driver.get(TestConsts.IB_URL);
		
	        // Maximize the Browser
			driver.manage().window().maximize();
			
			TestLogger.log(LogStatus.PASS,"Navigates to Google.com  Screenshot - "+Screenshot.ObjectSnapFullPage(driver));
			
			
		} catch(Exception e){
			e.printStackTrace();
			// Logger 
			TestLogger.log(LogStatus.FATAL,"Navigate to Google.com with Errors: Error Description - "+e.toString()+ " Screenshot - "+Screenshot.ObjectSnapFullPage(driver));
			return "Fatal";
		}
		return "Pass";
	}

	public String SearchIt(String SearchText) throws IOException{
		
		try{
			
			// Driver Wait object to wait until 10 Seconds for the Web Element to becomes visible.
			WebDriverWait wait = new WebDriverWait(driver, 10);
			
			// Send the Registration number 
			OR.FindElement(driver, ORMap.get("Goo_SearchTextBox")).sendKeys(SearchText);
			OR.FindElement(driver, ORMap.get("Goo_SearchButton")).click();
			
			Thread.sleep(1000);
			
			// Wait until Registration Number Edit box appear
			wait.until(ExpectedConditions.visibilityOf(OR.FindElement(driver, ORMap.get("Goo_LinkedinLink"))));
			
			TestLogger.log(LogStatus.INFO,"Search was performed");
			
			
		} catch(Exception e){
			e.printStackTrace();
			// Logger 
			TestLogger.log(LogStatus.FATAL,"Conducting Search on Google.com had Errors: Error Description - "+e.toString()+ " Screenshot - "+Screenshot.ObjectSnapFullPage(driver));
			return "Fatal";
		}
		return "Pass";
	}

	public String ValidateIt(String ValidateText) throws IOException{
		
		try{
			
			new WebDriverWait(driver, 10);
			
			// Send the Registration number 
			String LinkText = OR.FindElement(driver, ORMap.get("Goo_LinkedinLink")).getAttribute("text");
			if(LinkText.contentEquals(ValidateText)){
				TestLogger.log(LogStatus.PASS,"Searched Link Found with Text: "+LinkText+
						". Screenshot:  "+Screenshot.ObjectSnap(driver, driver.findElement(By.xpath(ORMap.get("Goo_LinkScrnshot")[1]+"[contains(.,'"+LinkText+"')]/parent::div[1]"))));
			} else {
				TestLogger.log(LogStatus.FAIL,"Searched link could NOT be found with Link Text: "+LinkText+
						". Screenshot:  "+Screenshot.ObjectSnapFullPage(driver));	
			}		
			
		} catch(Exception e){
			e.printStackTrace();
			// Logger 
			TestLogger.log(LogStatus.FATAL,"Conducting Search on Google.com had Errors: Error Description - "+e.toString()+ " Screenshot - "+Screenshot.ObjectSnapFullPage(driver));
			return "Fatal";
		}
		return "Pass";
	}

}
