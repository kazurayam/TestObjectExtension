TestObject Extension
====

This is a small [Katalon Studio](https://www.katalon.com/) project for demonstration purpose.
You can download the zip from the [Releases](https://github.com/kazurayam/TestObjectExtension/releases) page
and open it with Katalon Studio on your PC.

This project was developed using Katalon Studio v7.2.1.

# Problem to solve

Once I had a dicusssion in the Katalon Forum where I hacked Katalon Studio concerning TestObject.

- [Cannot find elements when XPath expression is null](https://forum.katalon.com/t/cannot-find-elements-when-xpath-expression-is-null/13840)

In the end a small bug was found in Katalon Studio v5.7.1, which was fixed later.

Then I wanted to look at the internal of `com.kms.katalon.core.testobject.TestObject` object.
I needed to debug-print every details of a `TestObject` instance.
I needed to see all properties (name-value pairs).
I wanted to pretty-print a `TestObject` in JSON format.
I wanted to make it as easy as possible to print a `TestOject` in JSON.

Also I remembered that quite often I need to convert a Katalon's `TestObject` into Selenium WebDriver's `By` object.
I wanted to make it as easy as possible to create a `By` object out of a `TestObject`.

# Solution proposed

I developed a utility class `com.kazurayam.ks.testobject.TestObjectExtension`.
With it a test case script can extend the `com.kms.katalon.core.testobject.TestObject` class to add a few custom methods runtime.
It employs [Groovy's Runtime Metaprogramming](https://groovy-lang.org/metaprogramming.html) technique.



# Description

## How to run the demo

1. Open this project with your local Katalon Studio
2. Open `Test Cases/main/demo1`
3. Run the `demo1`



## How the demo1 code looks like

Here I would dectate the [Test Cases/main/demo1](Scripts/main/demo1/Script1584390825818.groovy) as it goes


### apply()

By invoking `TestObjectExteion#apply()` method, we can add a few methods to `TestObject` class:
```
CustomKeywords."com.kazurayam.ks.testobject.TestObjectExtension.apply"()
```

Or simply you can write:
```
import com.kazurayam.ks.testobject.TestObjectExtension
TestObjectExtension.apply()
```

### toString()

I create a `TestObject` object as a test fixture:
```
TestObject tObj = findTestObject('Object Repository/Page_CURA Healthcare Service/a_Make Appointment_BASIC')
```

TestObject class originally implements `toString()` method:
```
WebUI.comment("tObj.toString(): " + tObj.toString())
```
This emits this output:
```
tObj.toString(): TestObject - 'Object Repository/Page_CURA Healthcare Service/a_Make Appointment_BASIC'
```

I am afraid, this output is not useful for hacking purposes.

### toJson()

By invoking `TestObjectExtension.apply()` the `TestObject` class is now added with `toJson()` method:

```
WebUI.comment("#toJson()\n" + tObj.toJson())
```
This emits this output:
```
{"cachedWebElement":null,"objectId":"Object Repository/Page_CURA Healthcare Service/a_Make Appointment_BASIC","parentObjectShadowRoot":false,"properties":[{"active":true,"value":"a","condition":"EQUALS","name":"tag"},{"active":true,"value":"btn-make-appointment","condition":"EQUALS","name":"id"},{"active":false,"value":"./profile.php#login","condition":"EQUALS","name":"href"},{"active":false,"value":"btn btn-dark btn-lg","condition":"EQUALS","name":"class"},{"active":false,"value":"Make Appointment","condition":"EQUALS","name":"text"},{"active":false,"value":"id(\"btn-make-appointment\")","condition":"EQUALS","name":"xpath"}],"imagePath":null,"selectorMethod":"BASIC","selectorCollection":{"BASIC":"//a[@id = 'btn-make-appointment']"},"useRelativeImagePath":false,"parentObject":null,"xpaths":[],"activeProperties":[{"active":true,"value":"a","condition":"EQUALS","name":"tag"},{"active":true,"value":"btn-make-appointment","condition":"EQUALS","name":"id"}],"activeXpaths":[]}
```

A single line of text in JSON format. It's too long. I can not comprehend it at a glance. It's not very helpful.

### prettyPrint()

The `TestObject` class also added with `prettyPrint()` method:
```
WebUI.comment("#prettyPrint()\n" + tObj.prettyPrint())
```
This emits this output:
```
{
    "cachedWebElement": null,
    "objectId": "Object Repository/Page_CURA Healthcare Service/a_Make Appointment_BASIC",
    "parentObjectShadowRoot": false,
    "properties": [
        {
            "active": true,
            "value": "a",
            "condition": "EQUALS",
            "name": "tag"
        },
        {
            "active": true,
            "value": "btn-make-appointment",
            "condition": "EQUALS",
            "name": "id"
        },
        {
            "active": false,
            "value": "./profile.php#login",
            "condition": "EQUALS",
            "name": "href"
        },
        {
            "active": false,
            "value": "btn btn-dark btn-lg",
            "condition": "EQUALS",
            "name": "class"
        },
        {
            "active": false,
            "value": "Make Appointment",
            "condition": "EQUALS",
            "name": "text"
        },
        {
            "active": false,
            "value": "id(\"btn-make-appointment\")",
            "condition": "EQUALS",
            "name": "xpath"
        }
    ],
    "imagePath": null,
    "selectorMethod": "BASIC",
    "selectorCollection": {
        "BASIC": "//a[@id = 'btn-make-appointment']"
    },
    "useRelativeImagePath": false,
    "parentObject": null,
    "xpaths": [

    ],
    "activeProperties": [
        {
            "active": true,
            "value": "a",
            "condition": "EQUALS",
            "name": "tag"
        },
        {
            "active": true,
            "value": "btn-make-appointment",
            "condition": "EQUALS",
            "name": "id"
        }
    ],
    "activeXpaths": [

    ]
}
```
OK. Finally, this is what I wanted to see.

### toBy()

The `TestObject` class is also added with `toBy()` method.  You can create a WebDriver's `By` object with the same locator as TestObject.

```
import org.openqa.selenium.By
...
By by = tObj.toBy()
```

Simple is the best.

### TestObjectExtenstion.create(By by)

You can create an instance of TestObject from a By object.

```
	@Test
	void test_create_cssSelector() {
		TestObject to = TestObjectExtension.create(By.cssSelector("img#apple"))
		assertEquals("TestObject - 'By.cssSelector: img#apple'", to.toString())
	}

	@Test
	void test_create_xpath() {
		TestObject to = TestObjectExtension.create(By.xpath("//img[@id='apple']"))
		assertEquals("TestObject - 'By.xpath: //img[@id='apple']'", to.toString())
	}
```


## Source of `TestObjectExtension` class

See the source of `TestObjectExtension` keyword located [here](./Keywords/com/kazurayam/ks/testobject/)

## API documentation

Groovydoc of TestObjectExtension is [here](./docs/api/index.html)

## How to use TestObjectExtension in your own project

In the [Releases](https://github.com/kazurayam/TestObjectExtension/releases) page
you can find jar file that contains the compiled class file of `TestObjectExtension`.
You can download it and locate it into the `Drivers` directory of your own Katalon Studio project
as described in the document [External Libraries](https://docs.katalon.com/katalon-studio/docs/external-libraries.html)
