package guru.qa.tests;

import com.opencsv.exceptions.CsvException;
import guru.qa.pages.*;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Owner("pro100Nurs")
public class FilesTest extends TestBase {

    PagePdf pagePdf = new PagePdf();
    PageXls pageXls = new PageXls();
    PageCsv pageCsv = new PageCsv();
    PageZip pageZip = new PageZip();
    PageTxt pageTxt = new PageTxt();

    @Test
    @Story("Скачивание и проверка PDF файла")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Тест PDF файла")
    public void downloadCheckPdf() throws IOException {
        pagePdf.openPage()
                .downloadPdfFile()
                .checkPdfFile();
    }

    @Test
    @Story("Скачивание и проверка XLS файла")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Тест XLS файла")
    public void downloadCheckXls() throws IOException {
        pageXls.openPage()
                .downloadXlsFile()
                .checkXlsFile();
    }

    @Test
    @Story("Скачивание и проверка CSV файла")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Тест CSV файла")
    public void downloadCheckCsv() throws IOException, CsvException {
        pageCsv.openPage()
                .downloadCsvFile()
                .checkCsvFile();
    }

    @Test
    @Story("Скачивание и проверка ZIP файла")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Тест ZIP файла")
    public void downloadCheckZip() throws IOException {
        pageZip.openPage()
                .downloadZipFile()
                .checkZipFile();
    }

    @Test
    @Story("Скачивание и проверка TXT файла")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Тест TXT файла")
    public void downloadCheckTxt() throws IOException {
        pageTxt.openPage()
                .downloadTxtFile()
                .checkTxtFile();
    }
}
