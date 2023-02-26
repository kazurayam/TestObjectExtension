import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.SelectorMethod
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * This test case script shows how to retrieve "Selected Locator" of TestObject
 * 
 * The javadoc of TestObject is here
 * https://docs.katalon.com/javadoc/com/kms/katalon/core/testobject/TestObject.html
 */

TestObject testObjectAttribute = findTestObject("Page_CURA Healthcare Service/a_Make Appointment_BASIC")
String selectedLocatorAttribute = testObjectAttribute.getSelectorCollection()[SelectorMethod.BASIC]
WebUI.comment("selectedLocatorAttribute='${selectedLocatorAttribute}'")

TestObject testObjectCSS = findTestObject("Page_CURA Healthcare Service/a_Make Appointment_CSS")
String selectedLocatorCSS = testObjectCSS.getSelectorCollection()[SelectorMethod.CSS]
WebUI.comment("selectedLocatorCSS='${selectedLocatorCSS}'")

TestObject testObjectXPATH = findTestObject("Page_CURA Healthcare Service/a_Make Appointment_XPATH")
String selectedLocatorXPATH = testObjectXPATH.getSelectorCollection()[SelectorMethod.XPATH]
WebUI.comment("selectedLocatorXPATH='${selectedLocatorXPATH}'")

