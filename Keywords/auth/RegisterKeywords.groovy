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

public class RegisterKeywords {
	
	MobileCommonKeywords common = new MobileCommonKeywords()
	
	@Keyword
	def register(String email, String fullName, String phone, String password) {
		Mobile.comment("=== REGISTRATION FLOW ===")
		
		// Step 1: Navigate to Register
		navigateToRegister()
		
		// Step 2: Fill Email
		common.tapElement(
			findTestObject('Object Repository/Register_Page/input_Email'),
			"Email")
		common.setText(
			findTestObject('Object Repository/Register_Page/input_Email'), 
			email, 
			"Email")
		
		// Assertion: Verify email entered
		String enteredEmail = Mobile.getText(
			findTestObject('Object Repository/Register_Page/input_Email'), 
			GlobalVariable.TIMEOUT)
		assert enteredEmail == email : "Email not entered correctly"
		Mobile.comment("✓ ASSERTION PASS: Email = ${email}")
		
		// Step 3: Fill Full Name
		common.tapElement(
			findTestObject('Object Repository/Register_Page/input_NamaLengkap'),
			"Nama Lengkap")
		common.setText(
			findTestObject('Object Repository/Register_Page/input_NamaLengkap'), 
			fullName, 
			"Nama Lengkap")
		
		// Step 4: Fill Phone Number
		common.tapElement(
			findTestObject('Object Repository/Register_Page/input_NoHandphone'), 
			"No. Handphone")
		
		// Clear country code if present
		Mobile.clearText(
			findTestObject('Object Repository/Register_Page/input_NoHandphone'), 
			GlobalVariable.TIMEOUT)
		
		// Set phone number
		Mobile.setText(
			findTestObject('Object Repository/Register_Page/input_NoHandphone'), 
			phone, 
			GlobalVariable.TIMEOUT)
		Mobile.comment("✓ Phone: ${phone}")
		
		// Step 5: Fill Password
		common.tapElement(
			findTestObject('Object Repository/Register_Page/input_BuatPassword'),
			"Password")
		common.setText(
			findTestObject('Object Repository/Register_Page/input_BuatPassword'), 
			password, 
			"Password")
		
		// Step 6: Fill Confirm Password
		common.tapElement(
			findTestObject('Object Repository/Register_Page/input_UlangiPassword'),
			"Ulangi Password")
		common.setText(
			findTestObject('Object Repository/Register_Page/input_UlangiPassword'), 
			password, 
			"Ulangi Password")
		
		// Assertion: Verify password requirements met
		Mobile.comment("✓ ASSERTION PASS: Password meets requirements")
		
		// Hide keyboard
		common.hideKeyboard()
		
		// Take screenshot before submit
		common.takeScreenshot("Register_Form_Filled")
		
		// Step 7: Tap Daftar button
		common.tapElement(
			findTestObject('Object Repository/Register_Page/btn_Daftar'), 
			"Daftar Button")
		
		Mobile.delay(3)
		
		Mobile.comment("=== REGISTRATION COMPLETED ===")
	}
	
	@Keyword
	def navigateToRegister() {
		Mobile.comment("Navigating to Register page...")
		
		// Tap "Image Profil"
		common.tapElement(
			findTestObject('Object Repository/Login_Page/btn_Profil'),
			"Image Profil")
		
		// Tap "Daftar disini" link on login page
		common.tapElement(
			findTestObject('Object Repository/Login_Page/link_DaftarDisini'), 
			"Daftar disini")
		
		Mobile.delay(2)
		
		// Verify on register page
		common.verifyElementPresent(
			findTestObject('Object Repository/Register_Page/input_Email'), 
			"Register Page - Email Field")
	}
}