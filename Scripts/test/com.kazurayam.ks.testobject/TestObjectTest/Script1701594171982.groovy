import org.openqa.selenium.By

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.TestObject as ConditionType

By byIdApple = By.id("apple")
println byIdApple.toString()

By byCssSelector = By.cssSelector("img#id")
println byCssSelector

By byXPath = By.xpath("//img[@id='apple']")
println byXPath
