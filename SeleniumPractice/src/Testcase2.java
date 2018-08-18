
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Testcase2
{
	public static void main(String[] args) throws Exception
	{
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium_Demp\\chromedriver.exe");
		WebDriver Driver=new ChromeDriver();
		//Maximize browser
		Driver.manage().window().maximize();
		//--------------------------------------------
		//Wait object
		WebDriverWait wait=new WebDriverWait(Driver, 60);
		//Actions object
		Actions Act=new Actions(Driver);
		//----------------------------------------------		
		//Open applications
		Driver.get("http://apps.qaplanet.in/qahrm/login.php");
		//Verify home page
		if(wait.until(ExpectedConditions.titleIs("OrangeHRM - New Level of HR Management")))
		{
			System.out.println("Home Page displayed");
		}
		else
		{
			System.out.println("Failed to display");
			return;
		}
		//Create webElement
		WebElement objUN=wait.until(ExpectedConditions.presenceOfElementLocated(By.name("txtUserName")));
		WebElement objPWD=Driver.findElement(By.name("txtPassword"));
		WebElement objLogin=Driver.findElement(By.name("Submit"));
		WebElement objClear=Driver.findElement(By.name("clear"));		
		//---------------------------------------------------------------
		//Verify user name
		if(objUN.isDisplayed())
		{
			System.out.println("User name displayed");
		}
		//Verify password
		if(objPWD.isDisplayed())
		{
			System.out.println("Password displayed");
		}
		//Verify login and clear
		if(objLogin.isDisplayed() && objClear.isDisplayed())
		{
			System.out.println("Login and clear buttons are displayed");
		}
		//------------------------------------------------------------------
		String sUN="qaplanet1";
		String sPWD="lab1";
		//--------------------------------------------------------------
		//Enter user name
		objUN.clear();
		objUN.sendKeys(sUN);
		//Enter password
		objPWD.clear();
		objPWD.sendKeys(sPWD);
		//Click on login
		objLogin.click();
		//Verify OrangeHRM
		if(wait.until(ExpectedConditions.titleIs("OrangeHRM")))
		{
			System.out.println("OrangeHRM page displayed");
		}
		else
		{
			System.out.println("Failed to login");
			return;
		}
		//---------------------------------------------------------
		//Get welcome text
		String sWelText=Driver.findElement(By.xpath("//ul[@id='option-menu']/li[1]")).getText();
		//Verify welcome text
		if(sWelText.equals("Welcome "+sUN))
		{
			System.out.println("Welcome "+sUN+" displayed");
		}
		//Verify only user name
		String[] Arr=sWelText.split(" ");
		if(Arr[1].equals(sUN))
		{
			System.out.println(sUN+" displayed");
		}
		//Create webElements for Change password and logout
		WebElement objCP=Driver.findElement(By.linkText("Change Password"));
		WebElement objLogout=Driver.findElement(By.linkText("Logout"));
		//Verify Change password and logout
		if(objCP.isDisplayed() && objLogout.isDisplayed())
		{
			System.out.println("Change password and logout are displayed");
		}
		//------------------------------------------------------------------------
		WebElement objPIM=Driver.findElement(By.id("pim"));
		//Mouse over on PIM
		Act.moveToElement(objPIM).perform();
		//Click on add employee
		Driver.findElement(By.linkText("Add Employee")).click();
		Thread.sleep(2000);
		//wait for frame and switch to it
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("rightMenu"));
		//Verify PIM : Add Employee
		if(Driver.findElement(By.xpath("//div[@class='mainHeading']/h2")).getText().equals("PIM : Add Employee"))
		{
			System.out.println("PIM : Add Employee displayed");
		}
		else
		{
			System.out.println("PIM : Add Employee not displayed");
		}
		//Get employee code
		String sEMPID=Driver.findElement(By.name("txtEmployeeId")).getAttribute("value");
		//----------------------------------------------------------------------------
		String sFN="Sreedhar";
		String sLN="M";
		//----------------------------------------------------------------------------
		//Create webElement for save
		WebElement objSave=Driver.findElement(By.id("btnEdit"));
		//Keep all fields as blank and click on save
		objSave.click();
		//wait for alert
		Alert A;
		A=wait.until(ExpectedConditions.alertIsPresent());
		if(A.getText().equals("Last Name Empty!"))
		{
			//accept alert
			A.accept();
		}
		//Enter last name
		Driver.findElement(By.name("txtEmpLastName")).sendKeys(sLN);
		//Click on save
		objSave.click();
		//wait for alert
		A=wait.until(ExpectedConditions.alertIsPresent());
		if(A.getText().equals("First Name Empty!"))
		{
			//accept alert
			A.accept();
		}		
		//Enter first name
		Driver.findElement(By.name("txtEmpFirstName")).sendKeys(sFN);
		//Click on save
		objSave.click();
		//Wait and verify Personal Details
		if(wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@class='mainHeading']/h2"), "Personal Details")))
		{
			System.out.println("Personal Details displayed");
		}
		else
		{
			System.out.println("failed to display Personal Details");
		}
		//Switch to parent frame
		Driver.switchTo().parentFrame();
		//---------------------------------------------------------------
		//Mouse over on PIM
		Act.moveToElement(objPIM).perform();
		//CLick on employee list
		Driver.findElement(By.linkText("Employee List")).click();
		Thread.sleep(2000);
		//wait for frame and switch to it
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("rightMenu"));
		//Verify Employee Information
		if(Driver.findElement(By.xpath("//div[@class='mainHeading']/h2")).getText().equals("Employee Information"))
		{
			System.out.println("Employee Information displayed");
		}
		else
		{
			System.out.println("Employee Information not displayed");
		}
		//-------------------------------------------------------
		//Verify employee information
		//-----------------------------------------------------------------
		//Get row count
		int rc=Driver.findElements(By.xpath("//table[@class='data-table']/tbody/tr")).size();
		int i;
		for(i=1;i<=rc;i++)
		{
			//Get second column data
			String sEmpCode=Driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["+i+"]/td[2]")).getText();
			//Get third column data
			String sEmpName=Driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["+i+"]/td[3]/a")).getText();
			if(sEMPID.equals(sEmpCode) && sEmpName.equals(sFN+" "+sLN))
			{
				System.out.println(sEmpCode+", "+sEmpName+", displayed at: "+i);
				break;
			}
		}
		//switch to parent/default
		Driver.switchTo().defaultContent();
		//--------------------------------------------------------------------------------
		//Click on logout
		Driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(2000);
		//Verify home page
		if(Driver.getTitle().equals("OrangeHRM - New Level of HR Management"))
		{
			System.out.println("Signoff sucessfully completed & Home Page displayed");
		}
		else
		{
			System.out.println("Failed to Signoff");
			return;
		}		
		//----------------------------------------
		Driver.close();
		Driver.quit();
	}
}