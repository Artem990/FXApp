package sample.operationsWithFile;

import java.io.*;

public class CheckTheEntryOfWordsInTheFile {
    private String keyWords;

    public CheckTheEntryOfWordsInTheFile(String keyWords) {
        this.keyWords = keyWords;
    }

    // функция проверяет, содержит ли файл искомое слово
    boolean checkOnConteinsWords (File file){
        if (keyWords.equals("")){
            return true;
        }else {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath()), "UTF-8"))){
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    if (line.contains(keyWords))
                        return true;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
            return false;
        }
    }
}