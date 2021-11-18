package guru.qa.pages;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PagePdf {

    private String
            pdfUrl = "https://file-examples.com/index.php/sample-documents-download/sample-pdf-download/",
            pdfText = "Lorem ipsum dolor sit amet";

    private SelenideElement
            searchInput = $("#table-files_filter input");

    private ElementsCollection
            pdfDownloadBtn = $$("#table-files tbody tr");

    private File pdfFile;

    @Step("Открываем страницу")
    public PagePdf openPage() {
        open(pdfUrl);
        return this;
    }

    @Step("Скачиваем PDF файл")
    public PagePdf downloadPdfFile() throws FileNotFoundException {
        searchInput.setValue("pdf").pressEnter();
        pdfFile = pdfDownloadBtn.first().$("a").download();
        return this;
    }

    @Step("Проверка PDF файла")
    public PagePdf checkPdfFile() throws IOException {
        PDF parsedPdf = new PDF(pdfFile);
        assertTrue(parsedPdf.text.contains(pdfText));
        return this;
    }
}
