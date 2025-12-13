import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import internal.GlobalVariable
import common.MobileCommonKeywords
import auth.RegisterKeywords
import auth.LoginKeywords
import gadai.GadaiBPKBKeywords

// Initialize Keywords
MobileCommonKeywords common = new MobileCommonKeywords()
RegisterKeywords registerKW = new RegisterKeywords()
LoginKeywords loginKW = new LoginKeywords()
GadaiBPKBKeywords gadaiKW = new GadaiBPKBKeywords()

try {
	Mobile.comment("╔════════════════════════════════════════╗")
	Mobile.comment("║  E2E TEST: COMPLETE GADAI BPKB FLOW   ║")
	Mobile.comment("╚════════════════════════════════════════╝")
	
	// ========================================
	// STEP 1: START APPLICATION
	// ========================================
	Mobile.comment("\n>>> STEP 1: Start Application <<<")
	common.startApplication()
	Mobile.delay(2)
	
//	// ========================================
//	// STEP 2: REGISTER NEW USER
//	// ========================================
//	Mobile.comment("\n>>> STEP 2: Register New User <<<")
//	
//	String timestamp = new Date().format('yyyyMMddHHmmss')
//	String testEmail = "test${timestamp}@gmail.com"
//	String testPhone = "8123456${timestamp.substring(8)}"
//	
//	registerKW.register(
//		testEmail,
//		GlobalVariable.TEST_FULLNAME,
//		testPhone,
//		GlobalVariable.TEST_PASSWORD
//	)
//	
//	Mobile.comment("✓ Registration completed")
	
//	// ========================================
//	// STEP 3: LOGIN
//	// ========================================
//	Mobile.comment("\n>>> STEP 3: Login <<<")
//	
//	loginKW.login(testEmail, GlobalVariable.TEST_PASSWORD)
//	Mobile.comment("✓ Login completed")
//	
	// ========================================
	// STEP 4: CREATE GADAI BPKB MOBIL FIDUSIA
	// ========================================
	Mobile.comment("\n>>> STEP 4: Create Gadai BPKB Mobil Fidusia <<<")
	
	// Prepare form data
	Map formData = [
		informasiUsaha: [
			namaUsaha: 'PT Automation Test',
			alamatUsaha: 'Jl. Testing No. 123, Jakarta Selatan',
			jabatan: 'Manager',
			teleponUsaha: '02112345678',
			statusUsaha: 'Aktif',
			lamaUsaha: '5',
			omsetMin: '10000000',
			omsetMax: '50000000',
			izinUsaha: 'SIUP',
			bidangUsaha: 'Perdagangan',
			karyawanMin: '5',
			karyawanMax: '20'
		],
		buktiKepemilikan: [
			buktiKepemilikan: 'Sertifikat Hak Milik',
			kondisiRumah: 'Baik',
			kondisiLingkungan: 'Ramai',
			aksesJalan: 'Mudah',
			lamaTinggal: '10',
			garasi: 'Ada'
		],
		dataNasabah: [
			alamatDomisili: 'Jl. Sudirman No. 456, Jakarta Pusat, DKI Jakarta',
			sesuaiKTP: true,
			kepemilikan: 'Milik Sendiri',
			buktiKepemilikan: 'Sertifikat Hak Milik (SHM)'
		],
		kendaraan: [
			merk: 'Toyota',
			jenis: 'Avanza',
			tahun: '2020',
			nomorPolisi: 'B1234XYZ',
			namaSTNK: 'Automation Test User',
			odometer: '50000',
			warnaUnit: 'Hitam',
			pengajuanPinjaman: '50000000'
		]
	]
	
	gadaiKW.createGadaiBPKBMobil(formData)
	Mobile.comment("✓ Gadai BPKB created")
	
//	// ========================================
//	// STEP 5: LOGOUT
//	// ========================================
//	Mobile.comment("\n>>> STEP 5: Logout <<<")
//	
//	loginKW.logout()
//	Mobile.comment("✓ Logout completed")
//	
//	// ========================================
//	// TEST COMPLETED
//	// ========================================
//	Mobile.comment("\n╔════════════════════════════════════════╗")
//	Mobile.comment("║     ✓ ALL TESTS PASSED SUCCESSFULLY   ║")
//	Mobile.comment("╚════════════════════════════════════════╝")
//	
//	common.takeScreenshot("E2E_Test_Completed")
	
} catch (Exception e) {
	Mobile.comment("\n╔════════════════════════════════════════╗")
	Mobile.comment("║          ✗ TEST FAILED                 ║")
	Mobile.comment("╚════════════════════════════════════════╝")
	Mobile.comment("Error: ${e.getMessage()}")
	
	common.takeScreenshot("E2E_Test_Failed")
	throw e
	
} finally {
	// Cleanup
	Mobile.delay(3)
	common.closeApplication()
}

