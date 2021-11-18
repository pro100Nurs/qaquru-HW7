package guru.qa.pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PageTxt {

    private String
            txtUrl = "https://filesamples.com/formats/txt",
            txtText = "Quis istum dolorem timet?";

    private ElementsCollection
            txtDownloadBtn = $$("#output a");

    private File txtFile;

    @Step("Открываем страницу")
    public PageTxt openPage() {
        open(txtUrl);
        return this;
    }

    @Step("Скачиваем TXT файл")
    public PageTxt downloadTxtFile() throws FileNotFoundException {
        txtFile = txtDownloadBtn.first().download();
        return this;
    }

    @Step("Проверка TXT файла")
    public PageTxt checkTxtFile() throws IOException {
        String txtFileContent = IOUtils.toString(new FileReader(txtFile));
        assertTrue(txtFileContent.contains(txtText));
        return this;
    }
}
