import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;

public class CreateDatabase {

    private String filePath;

    private final int fileNotFoundErrorCode = 1;
    private final int javaIOErrorCode = 2;

    public CreateDatabase(String filePath) {
        this.filePath = filePath;
    }

    private boolean isEndPunctuation(char lastCharOfWord) {
        if(lastCharOfWord == '.' || lastCharOfWord == '!' || lastCharOfWord == '?') {
            return true;
        }
        else {
            return false;
        }
    }

    private void instantiateDatabase() {

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;
            while(((line = br.readLine())!= null)){
                String[] lineArray = line.split(" ");
                for(String word : lineArray) {
                    Boolean isSentenceEnd = false;


                    if(isEndPunctuation(word.charAt(word.length() -1))) {
                        isSentenceEnd = true;
                    }


                }
            }


        }

        catch (FileNotFoundException e) {
            System.exit(fileNotFoundErrorCode);
        }
        catch (IOException i) {
            System.exit(javaIOErrorCode);
        }

    }
}
