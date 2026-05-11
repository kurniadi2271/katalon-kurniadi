import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.baseUrl)

WebUI.maximizeWindow()

// 2. Input Kredensial (Menggunakan variabel agar bisa Data-Driven)
WebUI.setText(findTestObject('Object Repository/Page_Login/input_username'), GlobalVariable.username)

WebUI.setText(findTestObject('Object Repository/Page_Login/input_password'), GlobalVariable.password)

// 3. Klik Login
WebUI.sendKeys(findTestObject('Object Repository/Page_Login/input_password'), Keys.chord(Keys.ENTER))

// 4. Verifikasi Login Berhasil
// Kita pastikan element "Products" atau "Cart" muncul
WebUI.waitForElementPresent(findTestObject('Object Repository/Page_Inventory/span_Products'), 10)

WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Inventory/span_Products'), 5)

WebUI.comment('Login Berhasil sebagai: ' + GlobalVariable.username)

WebUI.navigateToUrl(GlobalVariable.dashboardUrl)

WebUI.closeBrowser()

