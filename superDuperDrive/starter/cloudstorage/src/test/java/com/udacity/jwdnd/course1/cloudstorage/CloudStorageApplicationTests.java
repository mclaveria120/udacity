package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	private WebDriverWait wait;
	
	private JavascriptExecutor jse;

	@Autowired
	private UserService userService;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.wait = new WebDriverWait(driver, 60);
		this.jse = (JavascriptExecutor) driver;
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	@Order(1)
	public void testUserSignUp() {
		this.goToPage("/signup");

		this.setValueToInput("firstname", "inputFirstName");
		this.setValueToInput("lastname ", "inputLastName");
		this.setValueToInput("udacity", "inputUsername");
		this.setValueToInput("123456", "inputPassword");
		this.clickButton("signUpButton");

		Assertions.assertEquals("Login", driver.getTitle());
		Assertions.assertTrue(this.userService.getByUsername("udacity") != null);

	}

	@Test
	@Order(2)
	public void testUserLogin() {
		this.goToPage("/login");

		this.setValueToInput("udacity", "inputUsername");
		this.setValueToInput("123456", "inputPassword");
		this.clickButton("loginButton");

		Assertions.assertEquals("Home", driver.getTitle());
	}

	@Test
	@Order(3)
	public void testUnauthorizedAccessRestrictions() {
		this.goToPage("/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	@Order(4)
	public void testUnauthorizedUserCanOnlyAccesLoginAndSignup() {
		this.goToPage("/login");
		Assertions.assertEquals("Login", driver.getTitle());
		this.goToPage("/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
		this.goToPage("/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	@Order(5)
	public void testSigUpLoginHomeLogOutNoLongerAccessible() {
		this.goToPage("/signup");

		this.setValueToInput("firstname", "inputFirstName");
		this.setValueToInput("lastname ", "inputLastName");
		this.setValueToInput("udacity1", "inputUsername");
		this.setValueToInput("123456", "inputPassword");
		this.clickButton("signUpButton");

		Assertions.assertEquals("Login", driver.getTitle());
		Assertions.assertTrue(this.userService.getByUsername("udacity1") != null);

		this.goToPage("/login");
		this.setValueToInput("udacity1", "inputUsername");
		this.setValueToInput("123456", "inputPassword");
		this.clickButton("loginButton");

		Assertions.assertEquals("Home", driver.getTitle());

		clickButton("logoutButton");

		Assertions.assertEquals("Login", driver.getTitle());

		this.goToPage("/home");

		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	@Order(6)
	public void testCreatesNoteAndIsDisplayed() {
		
		this.goToPage("/login");
		this.setValueToInput("udacity1", "inputUsername");
		this.setValueToInput("123456", "inputPassword");
		this.clickButton("loginButton");

		Assertions.assertEquals("Home", driver.getTitle());

		clickByXPath("//a[@href='#nav-notes']");

		this.clickButton("addNewNoteButton");
		this.setValueToInput("note title", "note-title");
		this.setValueToInput("note description", "note-description");

		clickByXPath("//*[@id=\"noteSubmit\"]");

		untilJqueryIsDone(driver,30L);

		Assertions.assertEquals("note title", getTextByXPath("//*[@id=\"tr-note-id-1\"]/td[2]"));
		Assertions.assertEquals("note description", getTextByXPath("//*[@id=\"tr-note-id-1\"]/td[3]"));
	}
	
	@Test
	@Order(7)
	public void testEditingExstingNoteAndIsDisplayed() {
		
		this.goToPage("/login");
		this.setValueToInput("udacity1", "inputUsername");
		this.setValueToInput("123456", "inputPassword");
		this.clickButton("loginButton");
		
		Assertions.assertEquals("Home", driver.getTitle());
		
		clickByXPath("//a[@href='#nav-notes']");
		
		untilJqueryIsDone(driver,30L);

		clickByXPath("//*[@id=\"tr-note-id-1\"]/td[1]/button[1]");
		
		cleanInput("note-title");
		cleanInput("note-description");
		
		setValueToInput("note title 2", "note-title");
		setValueToInput("note description 2", "note-description");
		
		clickByXPath("//*[@id=\"noteSubmit\"]");
		
		untilJqueryIsDone(driver,30L);
		
		Assertions.assertEquals("note title 2", getTextByXPath("//*[@id=\"tr-note-id-1\"]/td[2]"));
		Assertions.assertEquals("note description 2", getTextByXPath("//*[@id=\"tr-note-id-1\"]/td[3]"));
	}
	
	@Test
	@Order(8)
	public void testDeletingANoteandNoLongerDisplayed() {
		this.goToPage("/login");
		this.setValueToInput("udacity1", "inputUsername");
		this.setValueToInput("123456", "inputPassword");
		this.clickButton("loginButton");
		
		Assertions.assertEquals("Home", driver.getTitle());
		
		clickByXPath("//a[@href='#nav-notes']");
		
		untilJqueryIsDone(driver,30L);
		
		clickByXPath("//*[@id=\"tr-note-id-1\"]/td[1]/button[2]");
		
		untilJqueryIsDone(driver,30L);
		
		Assertions.assertTrue(driver.findElements(By.xpath("//*[@id=\"notesTable\"]/tr")).size()==0);
		
	}
	
	@Test
	@Order(9)
	public void testCreatingCredentialAndVerifyPasswordIsEncrypted() {
		
		this.goToPage("/login");
		this.setValueToInput("udacity1", "inputUsername");
		this.setValueToInput("123456", "inputPassword");
		this.clickButton("loginButton");
		
		Assertions.assertEquals("Home", driver.getTitle());
		
		clickByXPath("//a[@href='#nav-credentials']");
		
		this.clickButton("addNewCredentialButton");
		this.setValueToInput("http://www.udacity.com", "credential-url");
		this.setValueToInput("udacity", "credential-username");
		this.setValueToInput("123123", "credential-password");

		clickByXPath("//*[@id=\"credentialSubmit\"]");
		
		untilJqueryIsDone(driver,30L);
		
		Assertions.assertNotEquals(getTextByXPath("//*[@id=\"tr-credential-id-1\"]/td[4]"),"123123");
	}
	
	@Test
	@Order(10)
	public void testEditingCredentialAndChangedDisplayed() {
		
		this.goToPage("/login");
		this.setValueToInput("udacity1", "inputUsername");
		this.setValueToInput("123456", "inputPassword");
		this.clickButton("loginButton");
		
		Assertions.assertEquals("Home", driver.getTitle());
		
		clickByXPath("//a[@href='#nav-credentials']");
		
		clickByXPath("//*[@id=\"tr-credential-id-1\"]/td[1]/button[1]");
		
		untilJqueryIsDone(driver,30L);
		
		cleanInput("credential-url");
		cleanInput("credential-username");
		cleanInput("credential-password");
		
		
		this.setValueToInput("http://www.google.com", "credential-url");
		this.setValueToInput("u", "credential-username");
		this.setValueToInput("1", "credential-password");

		clickByXPath("//*[@id=\"credentialSubmit\"]");
		
		untilJqueryIsDone(driver,30L);

		Assertions.assertEquals(getTextByXPath("//*[@id=\"tr-credential-id-1\"]/td[2]"),"http://www.google.com");
		Assertions.assertEquals(getTextByXPath("//*[@id=\"tr-credential-id-1\"]/td[3]"), ("u"));
		Assertions.assertNotEquals(getTextByXPath("//*[@id=\"tr-credential-id-1\"]/td[4]"),"1");		
		
		
	}

	@Test
	@Order(11)
	public void testDeletingACredentialndNoLongerDisplayed() {
		this.goToPage("/login");
		this.setValueToInput("udacity1", "inputUsername");
		this.setValueToInput("123456", "inputPassword");
		this.clickButton("loginButton");
		
		Assertions.assertEquals("Home", driver.getTitle());
		
		clickByXPath("//a[@href='#nav-credentials']");
		
		untilJqueryIsDone(driver,30L);
		
		clickByXPath("//*[@id=\"tr-credential-id-1\"]/td[1]/button[2]");
		
		untilJqueryIsDone(driver,30L);
		
		Assertions.assertTrue(driver.findElements(By.xpath("//*[@id=\"credentialTable\"]/tr")).size()==0);
		
	}
	
	private void clickByXPath(String xpath) {
		WebElement notes = driver.findElement(By.xpath(xpath));
		this.jse.executeScript("arguments[0].click()", notes);
	}

	private String getTextByXPath(String xpath) {
		WebElement webElement = driver.findElement(By.xpath(xpath));
		return webElement.getText();
	}


	public static void untilJqueryIsDone(WebDriver driver, Long timeoutInSeconds) {
		until(driver, (d) -> {
			Boolean isJqueryCallDone = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
			if (!isJqueryCallDone)
				System.out.println("JQuery call is in Progress");
			return isJqueryCallDone;
		}, timeoutInSeconds);
	}

	@SuppressWarnings("deprecation")
	private static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition, Long timeoutInSeconds) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
		webDriverWait.withTimeout(timeoutInSeconds, TimeUnit.SECONDS);
		try {
			webDriverWait.until(waitCondition);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void goToPage(String path) {
		driver.get("http://localhost:" + this.port + path);
	}

	private void setValueToInput(String value, String inputId) {
		WebElement input = driver.findElement(By.id(inputId));
		wait.until(ExpectedConditions.elementToBeClickable(input)).click();
		input.sendKeys(value);
	}
	
	private void cleanInput(String inputId) {
		WebElement input = driver.findElement(By.id(inputId));
		wait.until(ExpectedConditions.elementToBeClickable(input)).clear();
	}


	private void clickButton(String id) {
		WebElement element = driver.findElement(By.id(id));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		this.jse.executeScript("arguments[0].click()", element);
	}

}
