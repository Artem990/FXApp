package sample.operationsWithFile;

import java.io.File;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;

public class CreateNewTab {
    private File newFile;

    public CreateNewTab(File newFile) {
        this.newFile = newFile;
    }

    // создание ногого таба
    public Tab getTab() {
        Tab tab = new Tab();
        tab.setText(this.newFile.getName());
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        // заполнение этого таба текстом
        FillTextAreaInTab fillTextAreaInTab = new FillTextAreaInTab(this.newFile.getAbsolutePath(), textArea);
        Thread thread = new Thread(fillTextAreaInTab);
        thread.start();
        tab.setContent(textArea);
        return tab;
    }
}
