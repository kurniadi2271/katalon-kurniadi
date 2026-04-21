import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

// --- PRECONDITION ---
WebUI.callTestCase(findTestCase('Test Cases/Login_Valid'), [('username') : 'standard_user', ('password') : 'secret_sauce'], 
    FailureHandling.STOP_ON_FAILURE)

// 2. Cek apakah ini skenario Sort atau skenario Akses Ilegal (INV-N-01)
if (tc_id == 'INV-N-01') {
    WebUI.navigateToUrl('https://www.saucedemo.com/inventory.html')
    String currentUrl = WebUI.getUrl()
    WebUI.verifyMatch(currentUrl, '.*' + expected_result + '.*', true) // Cek apakah diredirect ke login
    WebUI.comment("Berhasil diredirect ke: " + currentUrl)
} 
else {
    WebUI.waitForElementVisible(findTestObject('Object Repository/Page_Inventory/select_SortContainer'), 10)

	// 3. LOGIKA KHUSUS: Pakai selectByValue supaya lebih akurat daripada Label
	// A-Z = 'az', Z-A = 'za', Low-High = 'lohi', High-Low = 'hilo'
	String sortValue = ""
	if (sort_option == 'Name (A to Z)') { sortValue = 'az' }
	else if (sort_option == 'Name (Z to A)') { sortValue = 'za' }
	else if (sort_option == 'Price (low to high)') { sortValue = 'lohi' }
	else if (sort_option == 'Price (high to low)') { sortValue = 'hilo' }
	
	// Eksekusi Pilih Sorting
	WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Inventory/select_SortContainer'), sortValue, false)
	
	// 4. FORCE DELAY (Penting banget karena SauceDemo lambat render urutan)
	WebUI.delay(3) 
	
	// 5. AMBIL DATA DARI LAYAR
	String actualName = WebUI.getText(findTestObject('Object Repository/Page_Inventory/div_FirstItemName'))
	String actualPrice = WebUI.getText(findTestObject('Object Repository/Page_Inventory/div_FirstItemPrice'))
	
	WebUI.comment("DEBUG: Pilihan Sort = " + sort_option)
	WebUI.comment("DEBUG: Nama yang muncul = " + actualName)
	WebUI.comment("DEBUG: Harga yang muncul = " + actualPrice)
	
	// 6. VERIFIKASI (Bandingkan dengan isi Excel kamu)
	// Catatan: Pastikan isi Excel kamu SUDAH DIGANTI dari kalimat jadi Nama Produk/Harga asli!
	if (sort_option.contains('Price')) {
	    // Kalau ngetes harga
	    WebUI.verifyMatch(actualPrice, expected_result, false)
	} else {
	    // Kalau ngetes nama
	    WebUI.verifyMatch(actualName, expected_result, false)
	}
}
WebUI.closeBrowser()