package common
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows


import internal.GlobalVariable
import io.appium.java_client.AppiumDriver
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory

public class MobileCommonKeywords {

	/**
	 * Start Mobile Application
	 */
	@Keyword
	def startApplication() {
		Mobile.comment("=== Starting Mobile Application ===")

		// Start app
		Mobile.startExistingApplication(GlobalVariable.APP_PACKAGE)
		Mobile.delay(3)

		Mobile.comment("✓ Application started: ${GlobalVariable.APP_PACKAGE}")
	}

	/**
	 * Start Application with APK
	 */
	@Keyword
	def startApplicationWithAPK() {
		Mobile.comment("=== Starting Application from APK ===")

		Mobile.startApplication(GlobalVariable.APK_PATH, true)
		Mobile.delay(3)

		Mobile.comment("✓ Application launched from APK")
	}

	/**
	 * Close Application
	 */
	@Keyword
	def closeApplication() {
		Mobile.closeApplication()
		Mobile.comment("✓ Application closed")
	}

	/**
	 * Take Screenshot
	 */
	@Keyword
	def takeScreenshot(String screenshotName) {
		String timestamp = new Date().format('yyyyMMdd_HHmmss')
		String fileName = "${screenshotName}_${timestamp}.png"
		String fullPath = "${GlobalVariable.SCREENSHOT_PATH}${fileName}"

		Mobile.takeScreenshot(fullPath)
		Mobile.comment("📸 Screenshot: ${fileName}")
	}

	/**
	 * Tap on Element
	 */
	@Keyword
	def tapElement(def testObject, String elementName) {
		Mobile.waitForElementPresent(testObject, GlobalVariable.TIMEOUT)
		Mobile.tap(testObject, GlobalVariable.TIMEOUT)
		Mobile.delay(1)
		Mobile.comment("✓ Tapped: ${elementName}")
	}

	/**
	 * Set Text to Element
	 */
	@Keyword
	def setText(def testObject, String text, String fieldName) {
		Mobile.waitForElementPresent(testObject, GlobalVariable.TIMEOUT)
		Mobile.clearText(testObject, GlobalVariable.TIMEOUT)
		Mobile.setText(testObject, text, GlobalVariable.TIMEOUT)
		Mobile.delay(1)
		Mobile.comment("✓ Set ${fieldName}: ${text}")
	}

	/**
	 * Scroll to Element
	 */
	@Keyword
	def scrollToElement(def testObject, String elementName) {
		Mobile.scrollToText(elementName)
		Mobile.delay(1)
		Mobile.comment("✓ Scrolled to: ${elementName}")
	}

	/**
	 * Verify Element Present
	 */
	@Keyword
	def verifyElementPresent(def testObject, String elementName) {
		boolean isPresent = Mobile.verifyElementExist(
				testObject,
				GlobalVariable.TIMEOUT,
				FailureHandling.OPTIONAL)

		if (isPresent) {
			Mobile.comment("✓ PASS: ${elementName} is present")
		} else {
			Mobile.comment("✗ FAIL: ${elementName} NOT present")
			takeScreenshot("Failed_${elementName}")
			throw new Exception("${elementName} not found")
		}

		return isPresent
	}

	/**
	 * Verify Text Present
	 */
	@Keyword
	def verifyTextPresent(String expectedText, String context) {
		boolean isPresent = Mobile.verifyElementExist(
				findTestObject('Android/Common/text_' + expectedText),
				GlobalVariable.TIMEOUT,
				FailureHandling.OPTIONAL)

		if (isPresent) {
			Mobile.comment("✓ PASS: Text '${expectedText}' found in ${context}")
		} else {
			Mobile.comment("✗ FAIL: Text '${expectedText}' NOT found")
			takeScreenshot("Failed_Text_${context}")
			throw new Exception("Expected text not found: ${expectedText}")
		}
	}

	/**
	 * Select Dropdown Value
	 */
	@Keyword
	def selectDropdownValue(def dropdownObject, String value, String dropdownName) {
		Mobile.comment("Selecting ${dropdownName}: ${value}")

		// Tap dropdown to open
		tapElement(dropdownObject, dropdownName)
		Mobile.delay(1)

		// Find and tap the value
		Mobile.scrollToText(value)
		Mobile.tap(findTestObject('Android/Common/text_' + value), GlobalVariable.TIMEOUT)
		Mobile.delay(1)

		Mobile.comment("✓ Selected: ${value}")
	}

	/**
	 * Check Checkbox
	 */
	@Keyword
	def checkCheckbox(def checkboxObject, String checkboxName) {
		Mobile.waitForElementPresent(checkboxObject, GlobalVariable.TIMEOUT)

		// Check if already checked
		boolean isChecked = Mobile.verifyElementChecked(
				checkboxObject,
				5,
				FailureHandling.OPTIONAL)

		if (!isChecked) {
			Mobile.tap(checkboxObject, GlobalVariable.TIMEOUT)
			Mobile.delay(1)
			Mobile.comment("✓ Checked: ${checkboxName}")
		} else {
			Mobile.comment("ℹ Already checked: ${checkboxName}")
		}
	}

	/**
	 * Select Radio Button
	 */
	@Keyword
	def selectRadioButton(def radioObject, String radioName) {
		tapElement(radioObject, radioName)
		Mobile.comment("✓ Selected radio: ${radioName}")
	}

	/**
	 * Hide Keyboard
	 */
	@Keyword
	def hideKeyboard() {
		try {
			Mobile.hideKeyboard()
			Mobile.comment("✓ Keyboard hidden")
		} catch (Exception e) {
			Mobile.comment("ℹ Keyboard not visible")
		}
	}

	/**
	 * Swipe Up
	 */
	@Keyword
	def swipeUp() {
		AppiumDriver driver = MobileDriverFactory.getDriver()
		int height = driver.manage().window().getSize().height
		int width = driver.manage().window().getSize().width

		 int startX = width / 2
		 int startY = (int)(height * 0.65)
		 int endY   = (int)(height * 0.50)

		Mobile.swipe(startX, startY, startX, endY)
		Mobile.delay(1)
		Mobile.comment("✓ Swiped up")
	}

	/**
	 * Swipe Down
	 */
	@Keyword
	def swipeDown() {
		AppiumDriver driver = MobileDriverFactory.getDriver()
		int height = driver.manage().window().getSize().height
		int width = driver.manage().window().getSize().width

		int startX = width / 2
		int startY = height * 0.2
		int endY = height * 0.8

		Mobile.swipe(startX, startY, startX, endY)
		Mobile.delay(1)
		Mobile.comment("✓ Swiped down")
	}
}