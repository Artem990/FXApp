package sample;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import sample.operationsWithFile.BuildFileSystemTree;
import sample.operationsWithFile.CreateNewTab;
import sample.operationsWithFile.FillTextAreaInTab;

public class Controller {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TreeView<String> treeView;
    @FXML
    private TextField enterStringField;
    @FXML
    private TextField enterDirectoryField;
    @FXML
    private TextField enterFormatField;
    @FXML
    private Button clickOnButton;
    @FXML
    private TextArea outOfMessagesField;
    @FXML
    private TabPane topPane;
    @FXML
    private TextArea textAreaInsideTab;

    public Controller() {
    }

    @FXML
    void initialize() {
        this.textAreaInsideTab.setEditable(false);
        this.textAreaInsideTab.setWrapText(true);
        this.outOfMessagesField.setEditable(false);
        // hide the root of treeview
        //treeView.setShowRoot(false);

        this.clickOnButton.setOnMouseClicked((mouseEvent) -> {
            if (!(enterDirectoryField.getText().equals(""))){
                File file = new File(enterDirectoryField.getText());
                // проверяем, является ли введенный путь директорией
                if (file.isDirectory()){
                    outOfMessagesField.clear();
                    String fileDirectory = enterDirectoryField.getText();
                    String keyWords = enterStringField.getText();
                    String format = enterFormatField.getText();
                    BuildFileSystemTree buildTree = new BuildFileSystemTree(fileDirectory, keyWords, format);
                    // получаем список файлов из директории
                    treeView.setRoot(buildTree.makeTreeFromFile(fileDirectory));
                } else {
                    outOfMessagesField.setText("Такой директории не существует");
                }
            }else if (enterDirectoryField.getText().equals("")){
                outOfMessagesField.setText("Заполните поле директории");
            }
        });

        this.treeView.setOnMouseClicked((mouseEvent) -> {
            if (mouseEvent.getClickCount() >= 2) {
                String pathToFile = (String)((TreeItem)this.treeView.getSelectionModel().getSelectedItem()).getValue();
                File newFile = new File(pathToFile);
                if (newFile.isFile()) {
                    CreateNewTab createNewTab;
                    // если не открыто не одного таба в панели
                    if (this.topPane.getSelectionModel().isEmpty()) {
                        createNewTab = new CreateNewTab(newFile);
                        this.topPane.getTabs().add(createNewTab.getTab());
                    // если открыт первый таб (пустой) заполняем его
                    } else if ((this.topPane.getSelectionModel().getSelectedItem()).getText().equals("New tab")) {
                        (this.topPane.getSelectionModel().getSelectedItem()).setText(newFile.getName());
                        TextArea textArea = (TextArea)(this.topPane.getSelectionModel().getSelectedItem()).getContent();
                        FillTextAreaInTab fillTextAreaInTab = new FillTextAreaInTab(pathToFile, textArea);
                        Thread thread = new Thread(fillTextAreaInTab);
                        thread.start();
                    // заполняем таб по клику на файл из списка
                    } else {
                        createNewTab = new CreateNewTab(newFile);
                        this.topPane.getTabs().add(createNewTab.getTab());
                    }
                }
            }
        });
    }
}
