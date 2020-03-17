package com.kazurayam.ks.testobject

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.By

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import groovy.json.JsonOutput

@RunWith(JUnit4.class)
public class TestObjectExtensionTest {

	@Test
	void test_apply_toJson() {
		// setup:
		TestObjectExtension.apply()  // here we extend TestObject class
		// when:
		TestObject tObj = findTestObject('Object Repository/Page_CURA Healthcare Service/a_Make Appointment_BASIC')
		assert tObj != null
		String json = tObj.toJson()  // surprisingly TestObject has toJson() method now!
		// then:
		WebUI.comment("#test_apply\n" + json)
		// then:
		assertTrue(json.length() > 0)
		assertTrue("objectId is missing", json.contains('objectId'))
	}

	@Test
	void test_apply_prettyPrint() {
		// setup:
		TestObjectExtension.apply()  // here we extend TestObject class
		// when:
		TestObject tObj = findTestObject('Object Repository/Page_CURA Healthcare Service/a_Make Appointment_BASIC')
		assert tObj != null
		String json = tObj.prettyPrint()  // surprisingly TestObject has toJson() method now!
		// then:
		WebUI.comment("#test_apply\n" + json)
		// then:
		assertTrue(json.length() > 0)
		assertTrue("objectId is missing", json.contains('objectId'))
	}

	@Test
	void test_toJson() {
		// when:
		TestObject tObj = findTestObject('Object Repository/Page_CURA Healthcare Service/a_Make Appointment_BASIC')
		assert tObj != null
		String json = TestObjectExtension.toJson(tObj)
		WebUI.comment("#test_toJson\n" + json)
		// then:
		assertTrue(json.length() > 0)
		assertTrue("objectId is missing", json.contains('objectId'))
	}

	@Test
	void test_prettyPrint_BASIC() {
		// when:
		TestObject tObj = findTestObject('Object Repository/Page_CURA Healthcare Service/a_Make Appointment_BASIC')
		assert tObj != null
		String json = TestObjectExtension.prettyPrint(tObj)
		WebUI.comment("#test_prettyPrint_BASIC\n" + json)
		// then:
		assertTrue(json.length() > 0)
		assertTrue("objectId is missing", json.contains('objectId'))
	}

	@Test
	void test_prettyPrint_XPATH() {
		// when:
		TestObject tObj = findTestObject('Object Repository/Page_CURA Healthcare Service/a_Make Appointment_XPATH')
		assertNotNull(tObj)
		String json = TestObjectExtension.prettyPrint(tObj)
		WebUI.comment("#test_prettyPrint_XPATH\n" + json)
		// then:
		assertTrue(json.length() > 0)
		assertTrue("objectId is missing", json.contains('objectId'))
	}

	@Test
	void test_prettyPrint_CSS() {
		// when:
		TestObject tObj = findTestObject('Object Repository/Page_CURA Healthcare Service/a_Make Appointment_CSS')
		assertNotNull(tObj)
		String json = TestObjectExtension.prettyPrint(tObj)
		WebUI.comment("#test_prettyPrint_CSS\n" + json)
		// then:
		assertTrue(json.length() > 0)
		assertTrue("objectId is missing", json.contains('objectId'))
		assertTrue("#btn-make-appointment is missing", json.contains('#btn-make-appointment'))
	}

	@Test
	void test_toBy_BASIC() {
		// when:
		TestObject tObj = findTestObject('Object Repository/Page_CURA Healthcare Service/a_Make Appointment_BASIC')
		By by = TestObjectExtension.toBy(tObj)
		WebUI.comment("#test_toBy_BASIC\n"+ JsonOutput.toJson(by))
		// then:
		assertTrue(by.toString().contains("//a[@id = 'btn-make-appointment']"))
	}

	@Test
	void test_toBy_CSS() {
		// when:
		TestObject tObj = findTestObject('Object Repository/Page_CURA Healthcare Service/a_Make Appointment_CSS')
		By by = TestObjectExtension.toBy(tObj)
		WebUI.comment("#test_toBy_CSS\n" + JsonOutput.toJson(by))
		// then:
		assertTrue(by.toString().contains("#btn-make-appointment"))
	}

	@Test
	void test_toBy_XPATH() {
		// when:
		TestObject tObj = findTestObject('Object Repository/Page_CURA Healthcare Service/a_Make Appointment_XPATH')
		By by = TestObjectExtension.toBy(tObj)
		WebUI.comment("#test_toBy_XPATH\n" + JsonOutput.toJson(by))
		// then:
		assertTrue(by.toString().contains("//a[@id='btn-make-appointment']"))
	}
	
	@Test
	void test_toBy_List() {
		WebUI.comment("test_toBy_List")
		List<TestObject> toList = new ArrayList<TestObject>()
		toList.add(findTestObject('Object Repository/Page_CURA Healthcare Service/a_Make Appointment_BASIC'))
		toList.add(findTestObject('Object Repository/Page_CURA Healthcare Service/a_Make Appointment_CSS'))
		toList.add(findTestObject('Object Repository/Page_CURA Healthcare Service/a_Make Appointment_XPATH'))
		List<By> byList = TestObjectExtension.toBy(toList)
		//println byList
		assertEquals(3, byList.size())
	}
}