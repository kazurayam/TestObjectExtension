import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By

import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import com.kazurayam.ks.testobject.TestObjectExtension

// We extends TestObject class on the go by Groovy's Metaprogramming technique
CustomKeywords."com.kazurayam.ks.testobject.TestObjectExtension.apply"()
// or simply;
//TestObjectExtension.apply()

// a fixture
TestObject tObj = findTestObject('Object Repository/Page_CURA Healthcare Service/a_Make Appointment_BASIC')

// I found that TestObject#toString() method is not very useful
WebUI.comment("tObj.toString(): " + tObj.toString())

// TestObject class now implements toJson() method 
WebUI.comment("#toJson()\n" + tObj.toJson())

// TestObject class now implements prettyPrint() method
WebUI.comment("#prettyPrint()\n" + tObj.prettyPrint())

// You can create a WebDriver's By object out of a TestObject easily
By by = tObj.toBy()
WebUI.comment("#toBy()\n" + by.toString())
assert by.toString().contains("//a[@id = 'btn-make-appointment']")

// You can convert a List<TestObject> into a List<By>
List<TestObject> listTO = new ArrayList<TestObject>()
listTO.add(findTestObject('Object Repository/Page_CURA Healthcare Service/a_Make Appointment_BASIC'))
listTO.add(findTestObject('Object Repository/Page_CURA Healthcare Service/a_Make Appointment_CSS'))
listTO.add(findTestObject('Object Repository/Page_CURA Healthcare Service/a_Make Appointment_XPATH'))
List<By> listBY = TestObjectExtension.toBy(listTO)
assert listBY != null
assert listBY.size() == 3 

