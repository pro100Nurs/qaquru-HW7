package guru.qa.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.*;

public class PageZip {

    private String
            zipUrl = "https://file-examples.com/index.php/text-files-and-archives-download/";

    private SelenideElement
            searchInput = $("#table-files_filter input");

    private ElementsCollection
            zipDownloadBtn = $$("#table-files tbody tr");

    private File zipFile;

    @Step("Открываем страницу")
    public PageZip openPage() {
        open(zipUrl);
        return this;
    }

    @Step("Скачиваем ZIP файл")
    public PageZip downloadZipFile() throws FileNotFoundException {
        searchInput.setValue("ZIP").pressEnter();
        zipFile = zipDownloadBtn.first().$("a").download();
        return this;
    }

    @Step("Проверка ZIP файла")
    public PageZip checkZipFile() throws IOException {
        try (InputStream is = new FileInputStream(zipFile.getPath());
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                System.out.println(entry.getName());
            }
        }
        return this;
    }
}
