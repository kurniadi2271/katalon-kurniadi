import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling

// 1. Buka Browser dan Navigasi ke URL
WebUI.openBrowser('')
WebUI.navigateToUrl('https://www.saucedemo.com/')
WebUI.maximizeWindow()

// 2. Input Username & Password dari Variabel (Data Binding)
// Variabel 'username' dan 'password' diambil dari Test Data
WebUI.setText(findTestObject('Object Repository/Page_Login/input_username'), username)
WebUI.setText(findTestObject('Object Repository/Page_Login/input_password'), password)

// 3. Klik Tombol Login
WebUI.click(findTestObject('Object Repository/Page_Login/btn_login'))

// Gunakan FailureHandling.OPTIONAL agar script tidak langsung berhenti (Merah) jika elemen tidak ketemu
boolean isPresent = WebUI.waitForElementPresent(findTestObject('Object Repository/Page_Inventory/span_Products'), 10, FailureHandling.OPTIONAL)

if (isPresent) {
	WebUI.comment("Login Berhasil!")
} else {
	// Jika tidak ada 'Products', mungkin ada pesan error?
	WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Login/error_message'), 5)
	WebUI.comment("Login Gagal sesuai skenario.")
}

// 5. Tutup Browser
WebUI.closeBrowser()