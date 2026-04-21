import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling

// 1. LOGIN (Pastikan project setting sudah --incognito ya biar gak ada pop-up password)
WebUI.callTestCase(findTestCase('Test Cases/Login_Valid'), [('username') : 'standard_user', ('password') : 'secret_sauce'], 
    FailureHandling.STOP_ON_FAILURE)

// 2. NAVIGASI KE CHECKOUT
WebUI.waitForElementVisible(findTestObject('Object Repository/Page_Inventory/button_Add_To_Cart'), 10)
WebUI.executeJavaScript("document.getElementById('add-to-cart-sauce-labs-backpack').click()", [])
WebUI.executeJavaScript("document.querySelector('.shopping_cart_link').click()", [])
WebUI.waitForElementVisible(findTestObject('Object Repository/Page_Cart/button_Checkout'), 10)
WebUI.executeJavaScript("document.getElementById('checkout').click()", [])

// 3. LOGIKA PENGISIAN FORM (Berdasarkan Excel kamu)
WebUI.waitForElementVisible(findTestObject('Object Repository/Page_Checkout_Info/input_firstName'), 10)

// Kalau isinya '-' (seperti di baris CCO-P-02), kita lewati atau biarkan kosong
if (first_name != "-") {
    WebUI.setText(findTestObject('Object Repository/Page_Checkout_Info/input_firstName'), first_name)
    WebUI.setText(findTestObject('Object Repository/Page_Checkout_Info/input_lastName'), last_name)
    WebUI.setText(findTestObject('Object Repository/Page_Checkout_Info/input_postalCode'), postal_code)
}

// 4. KLIK CONTINUE
WebUI.executeJavaScript("document.getElementById('continue').click()", [])

// 5. VERIFIKASI (MATCHING DENGAN EXCEL)
if (tc_id.contains('CCO-N')) {
    // Skenario NEGATIVE: Cek pesan error
    WebUI.waitForElementVisible(findTestObject('Object Repository/Page_Checkout_Info/h3_ErrorMessage'), 5)
    String actualError = WebUI.getText(findTestObject('Object Repository/Page_Checkout_Info/h3_ErrorMessage'))
    WebUI.verifyMatch(actualError, expected_result, false)
    WebUI.comment("Berhasil memvalidasi error: " + actualError)
} 
else {
    // Skenario POSITIVE: Cek judul halaman Overview
    WebUI.waitForElementVisible(findTestObject('Object Repository/Page_Checkout_Overview/span_Title_Overview'), 10)
    String actualTitle = WebUI.getText(findTestObject('Object Repository/Page_Checkout_Overview/span_Title_Overview'))
    WebUI.verifyMatch(actualTitle, expected_result, false)
    WebUI.comment("Berhasil masuk ke halaman: " + actualTitle)
}

WebUI.closeBrowser()