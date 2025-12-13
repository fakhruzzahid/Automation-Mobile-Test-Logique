package auth

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
import common.MobileCommonKeywords

public class LoginKeywords {
	
	MobileCommonKeywords common = new MobileCommonKeywords()
	
	@Keyword
	def login(String emailOrPhone, String password) {
		Mobile.comment("=== LOGIN FLOW ===")
		
		// Step 1: Enter Email/Phone
		common.tapElement(
			findTestObject('Object Repository/Login_Page/input_Email'),
			"Email / No. Handphone")
		common.setText(
			findTestObject('Object Repository/Login_Page/input_Email'), 
			emailOrPhone, 
			"Email / No. Handphone")
		
		// Assertion: Verify input entered
		String enteredValue = Mobile.getText(
			findTestObject('Object Repository/Login_Page/input_Email'),
			GlobalVariable.TIMEOUT)
		assert enteredValue.contains(emailOrPhone) || enteredValue == emailOrPhone
		Mobile.comment("✓ ASSERTION PASS: Credential entered = ${emailOrPhone}")
		
		// Step 2: Enter Password
		common.tapElement(
			findTestObject('Object Repository/Login_Page/input_Password'),
			"Password")
		common.setText(
			findTestObject('Object Repository/Login_Page/input_Password'), 
			password, 
			"Password")
		
		// Assertion: Verify password field not empty
		String enteredPwd = Mobile.getText(
			findTestObject('Object Repository/Login_Page/input_Password'),
			GlobalVariable.TIMEOUT)
		assert enteredPwd.length() > 0 : "Password field is empty"
		Mobile.comment("✓ ASSERTION PASS: Password entered")
		
		// Hide keyboard
		common.hideKeyboard()
		
		// Take screenshot
		common.takeScreenshot("Login_Credentials_Filled")
		
		// Step 3: Tap Masuk button
		common.tapElement(
			findTestObject('Object Repository/Login_Page/btn_Masuk'), 
			"Masuk Button")
		
		Mobile.delay(3)
		
		// Assertion: Verify login success
//		verifyLoginSuccess()
		
		Mobile.comment("=== LOGIN COMPLETED ===")
	}
	
	@Keyword
	def verifyLoginSuccess() {
		// Check for dashboard elements
		boolean dashboardVisible = Mobile.verifyElementExist(
			findTestObject('Android/Dashboard_Page/btn_AjukanGadai'),
			GlobalVariable.TIMEOUT,
			FailureHandling.OPTIONAL)
		
		if (dashboardVisible) {
			Mobile.comment("✓ ASSERTION PASS: Login successful - Dashboard loaded")
			common.takeScreenshot("Login_Success_Dashboard")
		} else {
			Mobile.comment("✗ ASSERTION FAIL: Dashboard not loaded")
			common.takeScreenshot("Login_Failed")
			throw new Exception("Login failed - Dashboard not visible")
		}
	}
	
	@Keyword
	def logout() {
		Mobile.comment("=== LOGOUT FLOW ===")
		
		// Navigate to Profile
		common.tapElement(
			findTestObject('Android/Dashboard_Page/menu_Profile'), 
			"Profile Menu")
		
		Mobile.delay(2)
		
		// Tap Logout
		common.scrollToElement(
			findTestObject('Android/Profile_Page/btn_Logout'),
			"Logout")
		
		common.tapElement(
			findTestObject('Android/Profile_Page/btn_Logout'), 
			"Logout Button")
		
		Mobile.delay(2)
		
		// Verify logout success
		boolean onLoginPage = Mobile.verifyElementExist(
			findTestObject('Android/Login_Page/btn_Masuk'),
			GlobalVariable.TIMEOUT,
			FailureHandling.OPTIONAL)
		
		if (onLoginPage) {
			Mobile.comment("✓ ASSERTION PASS: Logout successful")
			common.takeScreenshot("Logout_Success")
		} else {
			throw new Exception("Logout failed")
		}
		
		Mobile.comment("=== LOGOUT COMPLETED ===")
	}
}
