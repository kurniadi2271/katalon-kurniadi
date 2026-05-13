import static com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

String suiteType = GlobalVariable.suiteType

WebUI.comment("Suite Type = " + suiteType)

List<String> selectedTests = []

switch(suiteType){

    case 'Login':

        selectedTests = [
            'Test Cases/Login/Positive/LGN-P-01',
            'Test Cases/Login/Negative/LGN-N-01'
        ]
        break

    default:
        WebUI.comment("No suiteType matched")
        break
}

if(selectedTests.isEmpty()){
    WebUI.comment("No test cases selected")
    return
}

selectedTests.each { tc ->

    WebUI.comment("Running: " + tc)

    WebUI.callTestCase(
        findTestCase(tc),
        [:]
    )
}