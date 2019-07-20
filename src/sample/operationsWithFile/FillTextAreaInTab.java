package sample.operationsWithFile;

import javafx.scene.control.TextArea;

import java.io.*;

public class FillTextAreaInTab implements Runnable {
    private String pathToFile;
    private TextArea textArea;

    public FillTextAreaInTab(String pathToFile, TextArea textArea) {
        this.pathToFile = pathToFile;
        this.textArea = textArea;
    }

    // заполнение поля текстом из файла
    public void run () {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile), "UTF-8"))){
            String line;
            while((line = br.readLine()) != null){
                textArea.setText(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
