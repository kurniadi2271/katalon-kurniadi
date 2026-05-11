import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl('https://www.saucedemo.com/')

// Input password
WebUI.setText(findTestObject('Page_Login/input_password'), 'secret_sauce')

// Validasi type=password
WebUI.verifyElementAttributeValue(
    findTestObject('Page_Login/input_password'),
    'type',
    'password',
    5)

WebUI.closeBrowser()