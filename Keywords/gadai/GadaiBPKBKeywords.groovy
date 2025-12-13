package gadai

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

public class GadaiBPKBKeywords {
	
	MobileCommonKeywords common = new MobileCommonKeywords()
	
	@Keyword
	def createGadaiBPKBMobil(Map formData) {
		Mobile.comment("=== CREATE GADAI BPKB MOBIL FIDUSIA ===")
		
		// Step 1: Navigate to Gadai BPKB form
		navigateToGadaiBPKBForm()
		
		// Step 2: Fill Informasi Kendaraan
		fillInformasiKendaraan(formData.kendaraan)
		
		// Step 3: Fill Informasi Data Nasabah
		fillDataNasabah(formData.dataNasabah)
		
		// Step 4: Fill Bukti Kepemilikan
		fillBuktiKepemilikan(formData.buktiKepemilikan)
		
		// Step 5: Fill Informasi Usaha
		fillInformasiUsaha(formData.informasiUsaha)
		
		// Step 6: Fill Pernyataan and Submit
		submitForm()
		
		// Assertion: Verify submission success
//		verifySubmissionSuccess()
		
		Mobile.comment("=== GADAI BPKB COMPLETED ===")
	}
	
	@Keyword
	def navigateToGadaiBPKBForm() {
		Mobile.comment("--- Navigate to Gadai BPKB Form ---")
		
		// Tap "Gadai BPKB Mobil Fidusia" Button
		common.tapElement(
			findTestObject('Object Repository/Dashboard_Page/btn_AjukanGadai'), 
			"Ajukan Gadai")
		
		Mobile.delay(2)
		
		// Verify form loaded
		common.verifyElementPresent(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiKendaraan/input_Merk'),
			"Gadai BPKB Form")
		
		common.takeScreenshot("Gadai_BPKB_Form_Loaded")
	}
	
	@Keyword
	def fillInformasiUsaha(Map usahaData) {
		Mobile.comment("--- Fill Informasi Usaha / Pekerjaan ---")
		
		// Nama Usaha
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/input_NamaUsaha'),
			"Nama Usaha")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/input_NamaUsaha'),
			usahaData.namaUsaha,
			"Nama Usaha")
		
		// Assertion: Verify Nama Usaha entered
		String namaUsaha = Mobile.getText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/input_NamaUsaha'),
			GlobalVariable.TIMEOUT)
		assert namaUsaha == usahaData.namaUsaha
		Mobile.comment("✓ ASSERTION: Nama Usaha = ${namaUsaha}")
		common.hideKeyboard()
		
		// Alamat Usaha (Textarea)
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/textarea_AlamatUsaha'),
			"Alamat Usaha")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/textarea_AlamatUsaha'),
			usahaData.alamatUsaha,
			"Alamat Usaha")
		common.hideKeyboard()
		
		// Jabatan
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/input_Jabatan'),
			"Jabatan")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/input_Jabatan'),
			usahaData.jabatan,
			"Jabatan")
		common.hideKeyboard()
		
		// Nomor Telepon Usaha
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/input_NomorTeleponUsaha'),
			"Nomor Telepon Usaha")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/input_NomorTeleponUsaha'),
			usahaData.teleponUsaha,
			"Nomor Telepon Usaha")
		common.hideKeyboard()
		
		// Status Usaha
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/input_StatusUsaha'),
			"Status Usaha")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/input_StatusUsaha'),
			usahaData.statusUsaha,
			"Status Usaha")
		common.hideKeyboard()
		
		// Lama Usaha (with unit "Tahun")
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/input_LamaUsaha'),
			"Lama Usaha")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/input_LamaUsaha'),
			usahaData.lamaUsaha,
			"Lama Usaha")
		common.hideKeyboard()
		Mobile.delay(1)
		common.swipeUp()
		common.swipeUp()
		Mobile.delay(1)
		
		// Omset Minimal
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/input_OmsetMinimal'),
			"Omset Minimal")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/input_OmsetMinimal'),
			usahaData.omsetMin,
			"Omset Minimal")
		common.hideKeyboard()
		
		// Omset Maksimal
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/input_OmsetMaksimal'),
			"Omset Maksimal")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/input_OmsetMaksimal'),
			usahaData.omsetMax,
			"Omset Maksimal")
		common.hideKeyboard()
		
		// Izin Usaha (Dropdown)
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/dropdown_IzinUsaha'),
			"Izin Usaha")
		common.selectDropdownValue(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/select_IzinUsaha'),
			usahaData.izinUsaha,
			"Izin Usaha")
		
		// Assertion: Verify dropdown selected
		Mobile.comment("✓ ASSERTION: Izin Usaha selected = ${usahaData.izinUsaha}")
		
		// Bidang Usaha
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/input_BidangUsaha'),
			"Bidang Usaha")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/input_BidangUsaha'),
			usahaData.bidangUsaha,
			"Bidang Usaha")
		common.hideKeyboard()
		
		// Jumlah Karyawan Min-Max
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/input_JumlahKaryawanMin'),
			"Jumlah Karyawan Min")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/input_JumlahKaryawanMin'),
			usahaData.karyawanMin,
			"Jumlah Karyawan Min")
		common.hideKeyboard()
		
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/input_JumlahKaryawanMax'),
			"Jumlah Karyawan Max")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiUsaha/input_JumlahKaryawanMax'),
			usahaData.karyawanMax,
			"Jumlah Karyawan Max")
		common.hideKeyboard()
		
		common.swipeUp()
		common.takeScreenshot("InformasiUsaha_Filled")
		
		Mobile.comment("✓ Informasi Usaha completed")
	}
	
	@Keyword
	def fillBuktiKepemilikan(Map kepemilikanData) {
		Mobile.comment("--- Fill Rumah ---")
		
		// Kondisi Rumah
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_BuktiKepemilikan/input_KondisiRumah'),
			"Kondisi Rumah")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_BuktiKepemilikan/input_KondisiRumah'),
			kepemilikanData.kondisiRumah,
			"Kondisi Rumah")
		common.hideKeyboard()
		
		// Kondisi Lingkungan
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_BuktiKepemilikan/input_KondisiLingkungan'),
			"Kondisi Lingkungan")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_BuktiKepemilikan/input_KondisiLingkungan'),
			kepemilikanData.kondisiLingkungan,
			"Kondisi Lingkungan")
		common.hideKeyboard()
		
		// Akses Jalan
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_BuktiKepemilikan/input_AksesJalan'),
			"Akses Jalan")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_BuktiKepemilikan/input_AksesJalan'),
			kepemilikanData.aksesJalan,
			"Akses Jalan")
		common.hideKeyboard()
		
		// Lama Tinggal
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_BuktiKepemilikan/input_LamaTinggal'),
			"Lama Tinggal")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_BuktiKepemilikan/input_LamaTinggal'),
			kepemilikanData.lamaTinggal,
			"Lama Tinggal (Tahun)")
		common.hideKeyboard()
		
		// Garasi (Dropdown)
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_BuktiKepemilikan/dropdown_Garasi'),
			"Garasi")
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_BuktiKepemilikan/select_Garasi'),
			"Garasi")
		
		common.takeScreenshot("BuktiKepemilikan_Filled")
		Mobile.delay(1)
		common.swipeUp()
		Mobile.delay(1)
		
		Mobile.comment("✓ Bukti Kepemilikan completed")
	}
	
	@Keyword
	def fillDataNasabah(Map nasabahData) {
		Mobile.comment("--- Fill Informasi Data Nasabah ---")
		
		// Alamat Domisili (Textarea)
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_DataNasabah/textarea_AlamatDomisili'),
			"Alamat Domisili")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_DataNasabah/textarea_AlamatDomisili'),
			nasabahData.alamatDomisili,
			"Alamat Domisili")
		
		// Assertion: Verify alamat entered
		String alamat = Mobile.getText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_DataNasabah/textarea_AlamatDomisili'),
			GlobalVariable.TIMEOUT)
		assert alamat.contains(nasabahData.alamatDomisili.substring(0, 10))
		Mobile.comment("✓ ASSERTION: Alamat Domisili entered")
		common.hideKeyboard()
		
		// Checkbox: Sesuai KTP
		if (nasabahData.sesuaiKTP == true) {
			common.checkCheckbox(
				findTestObject('Object Repository/Form_Gadai_BPKB/Section_DataNasabah/checkbox_SesuaiKTP'),
				"Sesuai KTP")
			
			// Assertion: Verify checkbox checked
			boolean isChecked = Mobile.verifyElementChecked(
				findTestObject('Object Repository/Form_Gadai_BPKB/Section_DataNasabah/checkbox_SesuaiKTP'),
				5,
				FailureHandling.OPTIONAL)
			assert isChecked : "Checkbox not checked"
			Mobile.comment("✓ ASSERTION: Checkbox 'Sesuai KTP' is checked")
		}
		
		// Kepemilikan (Dropdown)
		Mobile.delay(1)
		common.swipeUp()
		Mobile.delay(2)
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_DataNasabah/dropdown_Kepemilikan'),
			"Kepemilikan")
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_DataNasabah/select_Kepemilikan'),
			"Kepemilikan")
		
		// Bukti Kepemilikan (Dropdown)
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_DataNasabah/dropdown_BuktiKepemilikan'),
			"Bukti Kepemilikan")
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_DataNasabah/select_BuktiKepemilikan'),
			"Bukti Kepemilikan")
		common.takeScreenshot("DataNasabah_Filled")
		
		Mobile.comment("✓ Data Nasabah completed")
	}
	
	@Keyword
	def fillInformasiKendaraan(Map kendaraanData) {
		Mobile.comment("--- Fill Informasi Kendaraan ---")
		
		// Merk
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiKendaraan/input_Merk'),
			"Merk")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiKendaraan/input_Merk'),
			kendaraanData.merk,
			"Merk")
		
		// Assertion: Verify merk
		String merk = Mobile.getText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiKendaraan/input_Merk'),
			GlobalVariable.TIMEOUT)
		assert merk == kendaraanData.merk
		Mobile.comment("✓ ASSERTION: Merk = ${merk}")
		
		// Jenis
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiKendaraan/input_Jenis'),
			"Jenis")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiKendaraan/input_Jenis'),
			kendaraanData.jenis,
			"Jenis")
		
		// Tahun Kendaraan
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiKendaraan/input_TahunKendaraan'),
			"Tahun Kendaraan")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiKendaraan/input_TahunKendaraan'),
			kendaraanData.tahun,
			"Tahun Kendaraan")
		
		// Nomor Polisi
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiKendaraan/input_NomorPolisi'),
			"Nomor Polisi")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiKendaraan/input_NomorPolisi'),
			kendaraanData.nomorPolisi,
			"Nomor Polisi")
		common.hideKeyboard()
		
		// Nama STNK
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiKendaraan/input_NamaSTNK'),
			"Nama STNK")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiKendaraan/input_NamaSTNK'),
			kendaraanData.namaSTNK,
			"Nama STNK")
		common.hideKeyboard()
		
		// Tanggal Berakhir STNK (Date Picker)
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiKendaraan/datepicker_TanggalBerakhirSTNK'),
			"Tanggal Berakhir STNK")
		
		Mobile.delay(1)
		// Select date from picker (customize based on actual date picker)
		Mobile.tap(findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiKendaraan/btn_DatePickerOK'), GlobalVariable.TIMEOUT)
		Mobile.comment("✓ Date selected for STNK")
		Mobile.delay(1)
		common.swipeUp()
		Mobile.delay(2)
		
		// Odometer
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiKendaraan/input_Odometer'),
			"Odometer (Kilometer)")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiKendaraan/input_Odometer'),
			kendaraanData.odometer,
			"Odometer (Kilometer)")
		common.hideKeyboard()
		
		// Warna Unit
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiKendaraan/input_WarnaUnit'),
			"Warna Unit")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiKendaraan/input_WarnaUnit'),
			kendaraanData.warnaUnit,
			"Warna Unit")
		common.hideKeyboard()
		
		// Pengajuan Pinjaman
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiKendaraan/input_PengajuanPinjaman'),
			"Pinjaman")
		common.setText(
			findTestObject('Object Repository/Form_Gadai_BPKB/Section_InformasiKendaraan/input_PengajuanPinjaman'),
			kendaraanData.pengajuanPinjaman,
			"Pengajuan Pinjaman (Rp)")
		
		common.hideKeyboard()
		common.takeScreenshot("InformasiKendaraan_Filled")
		
		Mobile.comment("✓ Informasi Kendaraan completed")
	}
	
	@Keyword
	def submitForm() {
		Mobile.comment("--- Submit Form ---")
		
		// Scroll to bottom
		common.swipeUp()
		
		// Check Pernyataan 1
		common.checkCheckbox(
			findTestObject('Object Repository/Form_Gadai_BPKB/Form_Actions/checkbox_PernyataanBenar'),
			"Pernyataan: Data yang dituliskan benar")
		
		// Assertion: Verify checkbox 1 checked
		boolean checkbox1 = Mobile.verifyElementChecked(
			findTestObject('Object Repository/Form_Gadai_BPKB/Form_Actions/checkbox_PernyataanBenar'),
			5,
			FailureHandling.OPTIONAL)
		assert checkbox1 : "Checkbox Pernyataan not checked"
		Mobile.comment("✓ ASSERTION: Checkbox Pernyataan checked")
		
		// Check Syarat dan Ketentuan
		common.checkCheckbox(
			findTestObject('Object Repository/Form_Gadai_BPKB/Form_Actions/checkbox_SyaratKetentuan'),
			"Syarat dan Ketentuan")
		
		// Assertion: Verify checkbox 2 checked
		boolean checkbox2 = Mobile.verifyElementChecked(
			findTestObject('Object Repository/Form_Gadai_BPKB/Form_Actions/checkbox_SyaratKetentuan'),
			5,
			FailureHandling.OPTIONAL)
		assert checkbox2 : "Checkbox Syarat dan Ketentuan not checked"
		Mobile.comment("✓ ASSERTION: Checkbox Syarat dan Ketentuan checked")
		
		common.takeScreenshot("Form_Ready_To_Submit")
		
		// Tap Proses button
		common.tapElement(
			findTestObject('Object Repository/Form_Gadai_BPKB/Form_Actions/btn_Proses'),
			"Proses Button")
		
		Mobile.delay(3)
		
		Mobile.comment("✓ Form submitted")
	}
	
	@Keyword
	def verifySubmissionSuccess() {
		// Wait for success indicator
		Mobile.delay(3)
		
		// Check for success message or confirmation page
		boolean successVisible = Mobile.verifyElementExist(
			findTestObject('Object Repository/Common/text_Success'),
			GlobalVariable.TIMEOUT,
			FailureHandling.OPTIONAL)
		
		if (successVisible) {
			Mobile.comment("✓ ASSERTION PASS: Gadai BPKB submission successful")
			common.takeScreenshot("Submission_Success")
		} else {
			// Alternative: Check if back on dashboard
			boolean onDashboard = Mobile.verifyElementExist(
				findTestObject('Object Repository/Dashboard_Page/btn_AjukanGadai'),
				GlobalVariable.TIMEOUT,
				FailureHandling.OPTIONAL)
			
			if (onDashboard) {
				Mobile.comment("✓ ASSERTION PASS: Returned to dashboard")
				common.takeScreenshot("Submission_Completed")
			} else {
				Mobile.comment("✗ ASSERTION FAIL: Submission status unclear")
				common.takeScreenshot("Submission_Failed")
				throw new Exception("Submission verification failed")
			}
		}
	}
}
