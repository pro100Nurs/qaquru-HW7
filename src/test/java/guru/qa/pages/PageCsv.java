package guru.qa.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import io.qameta.allure.Step;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PageCsv {

    private String
            csvUrl = "https://file-examples.com/index.php/text-files-and-archives-download/",
            csvText = "First Name";

    private SelenideElement
            searchInput = $("#table-files_filter input");

    private ElementsCollection
            csvDownloadBtn = $$("#table-files tbody tr");

    private File csvFile;

    @Step("Открываем страницу")
    public PageCsv openPage() {
        open(csvUrl);
        return this;
    }

    @Step("Скачиваем CSV файл")
    public PageCsv downloadCsvFile() throws FileNotFoundException {
        searchInput.setValue("CSV").pressEnter();
        csvFile = csvDownloadBtn.first().$("a").download();
        return this;
    }

    @Step("Проверка CSV файла")
    public PageCsv checkCsvFile() throws IOException, CsvException {
        try (FileReader fileReader = new FileReader(csvFile.getPath());
             CSVReader csvReader = new CSVReader(fileReader)) {
            List<String[]> strings = csvReader.readAll();
            boolean checkCsvText = strings.get(0)[1].contains(csvText);
            assertTrue(checkCsvText);
        }
        return this;
    }
}
