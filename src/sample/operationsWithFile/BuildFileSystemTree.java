package sample.operationsWithFile;

import javafx.scene.control.TreeItem;
import java.io.*;

public class BuildFileSystemTree {
    private String keyWords;
    private String format;
    private TreeItem<String> root;

    public BuildFileSystemTree(String directory, String keyWords, String format) {
        this.keyWords = keyWords;
        if (format.equals("")){
            this.format = ".log"; }
        else { this.format = format; }
        root = new TreeItem<>(directory);
    }


    // заполняем список файлов
    public TreeItem<String> makeTreeFromFile(String directory){
        File file = new File(directory);
        for(File f : file.listFiles()) {
            if(f.isDirectory()) {
                // рекурсивно проходим по каждой папке в директории
                makeTreeFromFile(f.getAbsolutePath());
            } else {
                // проверяем, что файл нужного формата
                if (f.getName().toLowerCase().endsWith(format)){
                    // проверяем, содержит ли он ключевые слова
                    CheckTheEntryOfWordsInTheFile checkOfWords = new CheckTheEntryOfWordsInTheFile(keyWords);
                    if (checkOfWords.checkOnConteinsWords(f))
                        root.getChildren().add(new TreeItem<>(f.getAbsolutePath()));
                }
            }
        }
        return root;
    }

    // можно вместо списка вернуть дерево файловой системы, но со всеми файлами (как вернуть дерево только с файлами нужного формата не смог реализовать)
    /*public TreeItem<String> makeTreeWithFoldersAndFiles (String directory){
        File file = new File(directory);
        TreeItem<String> root = new TreeItem<>(directory);
        for(File f : file.listFiles()) {
            if(f.isDirectory()) {
                root.getChildren().add(makeTreeWithFoldersAndFiles(f.getAbsolutePath()));
            } else {
                if (f.getName().toLowerCase().endsWith(format)){
                    // проверяем, содержит ли он ключевые слова
                    CheckTheEntryOfWordsInTheFile checkOfWords = new CheckTheEntryOfWordsInTheFile(keyWords);
                    if (checkOfWords.checkOnConteinsWords(f))
                        root.getChildren().add(new TreeItem<>(f.getAbsolutePath()));
                }
            }
        }
        return root;
    }*/
}
