# 📱 Mobile Automation Testing - Katalon Studio

Automated mobile testing menggunakan Katalon Studio dengan GitHub Actions CI/CD.

## 📋 Prerequisites

- Java JDK 17
- Katalon Studio v9.7.2+
- Android Studio (untuk Android testing)

## 🚀 Quick Start

### 1. Clone Repository
```bash
git clone https://github.com/your-username/mobile-automation.git
cd mobile-automation
```

### 2. Setup GitHub Secrets

Go to **Settings → Secrets → Actions**, tambahkan:

| Secret | Cara Dapat |
|--------|------------|
| `KATALON_API_KEY` | [testops.katalon.io](https://testops.katalon.io) → Profile → API Keys |
| `TESTOPS_PROJECT_ID` | TestOps → Create Project → Copy ID dari URL |
| `TESTOPS_RELEASE_ID` | TestOps → Create Release → Copy Release ID |

### 3. Run Tests

**Local:**
```bash
# Via Katalon Studio GUI
File → Open Project → Run Test Suite

# Via Command Line
./katalonc -noSplash -runMode=console \
  -projectPath="$(pwd)" \
  -testSuitePath="Test Suites/TS_All_FLow" \
  -browserType="Android"
```

**GitHub Actions:**
- Push ke branch `main` (auto trigger)
- Atau: Actions tab → Run workflow (manual trigger)

## 📁 Project Structure
```
mobile-automation/
├── .github/workflows/     # CI/CD pipeline
├── Data Files/            # Test data (images, excel, csv)
├── Object Repository/     # UI elements
├── Test Cases/           # Test scripts
├── Test Suites/          # Test suite collections
└── Reports/              # Test results
```

## 🧪 Test Suite

**TS_All_FLow** mencakup:
- Login & Register
- Upload KTP
- Date Picker
- Form Filling

## 🐛 Common Issues

### 1. File Upload Error
```groovy
// Unhide input dulu
WebUI.executeJavaScript(
    "document.querySelector('input[name=\"uploadKtp\"]').classList.remove('hidden');",
    null
)
WebUI.uploadFile(findTestObject('Input_UploadKTP'), filePath)
```

### 2. Date Picker Error
```groovy
// Set value dengan JavaScript
WebUI.executeJavaScript(
    """
    var input = document.querySelector('.dp__input_readonly');
    input.removeAttribute('readonly');
    input.value = '12/12/2008';
    input.dispatchEvent(new Event('input', { bubbles: true }));
    """,
    null
)
```

### 3. CI/CD License Error
- Pastikan `TESTOPS_PROJECT_ID` dan `TESTOPS_RELEASE_ID` sudah di-set
- Atau request Katalon Trial (30 days free)

## 📊 View Results

**Local:** Check folder `Reports/`

**GitHub Actions:**
1. Go to **Actions** tab
2. Click workflow run
3. Download **Artifacts** → `test-results`

**TestOps:** [testops.katalon.io](https://testops.katalon.io) → Your Project → Test Runs

1. Fork repository
2. Create branch: `git checkout -b feature/your-feature`
3. Commit: `git commit -m 'Add feature'`
4. Push: `git push origin feature/your-feature`
5. Create Pull Request
