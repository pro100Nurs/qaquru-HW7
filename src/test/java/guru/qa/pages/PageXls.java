package guru.qa.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.xlstest.XLS;
import io.qameta.allure.Step;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PageXls {

    private String
            xlsUrl = "https://file-examples.com/index.php/sample-documents-download/sample-xls-download/",
            xlsText = "First Name";

    private SelenideElement
            searchInput = $("#table-files_filter input");

    private ElementsCollection
            xlsDownloadBtn = $$("#table-files tbody tr");

    private File xlsFile;

    @Step("Открываем страницу")
    public PageXls openPage() {
        open(xlsUrl);
        return this;
    }

    @Step("Скачиваем XLS файл")
    public PageXls downloadXlsFile() throws FileNotFoundException {
        searchInput.setValue("xls").pressEnter();
        xlsFile = xlsDownloadBtn.first().$("a").download();
        return this;
    }

    @Step("Проверка XLS файла")
    public PageXls checkXlsFile() {
        XLS parsedXls = new XLS(xlsFile);
        boolean checkXls = parsedXls.excel
                .getSheetAt(0)
                .getRow(0)
                .getCell(1)
                .getStringCellValue()
                .contains(xlsText);
        assertTrue(checkXls);
        return this;
    }
}
