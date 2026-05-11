import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl('https://www.saucedemo.com/')

WebUI.setText(findTestObject('Page_Login/input_username'), 'standard_user')

WebUI.setText(findTestObject('Page_Login/input_password'), 'secret_sauce')

// Validasi tombol enabled
WebUI.verifyElementClickable(
    findTestObject('Page_Login/btn_login'))

WebUI.closeBrowser()