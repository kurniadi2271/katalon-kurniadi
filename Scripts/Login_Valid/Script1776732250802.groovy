import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// 1. Buka Browser dan ke URL
WebUI.openBrowser('')
WebUI.navigateToUrl('https://www.saucedemo.com/')
WebUI.maximizeWindow()

// 2. Input Kredensial (Menggunakan variabel agar bisa Data-Driven)
WebUI.setText(findTestObject('Object Repository/Page_Login/input_username'), username)
WebUI.setText(findTestObject('Object Repository/Page_Login/input_password'), password)

// 3. Klik Login
WebUI.click(findTestObject('Object Repository/Page_Login/btn_login'))

// 4. Verifikasi Login Berhasil
// Kita pastikan element "Products" atau "Cart" muncul
WebUI.waitForElementPresent(findTestObject('Object Repository/Page_Inventory/span_Products'), 10)
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Inventory/span_Products'), 5)

WebUI.comment("Login Berhasil sebagai: " + username)