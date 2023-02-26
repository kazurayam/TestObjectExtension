import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.SelectorMethod
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import groovy.json.JsonOutput

TestObject tObj = findTestObject('Object Repository/Page_CURA Healthcare Service/a_Make Appointment_BASIC')

String json = JsonOutput.toJson(tObj)
println json

println JsonOutput.prettyPrint(json)

WebUI.comment(tObj.getSelectorMethod().toString())
WebUI.comment(tObj.getSelectorCollection().toString())
WebUI.comment(tObj.getSelectorCollection().get(SelectorMethod.BASIC))
