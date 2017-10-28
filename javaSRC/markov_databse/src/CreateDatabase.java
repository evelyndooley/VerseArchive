import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

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

//    private String removeEndPunctuation(String word) {
//        String strippedWord = word;
//
//        for(int i = 0; i < strippedWord.length(); i++) {
//            if(isEndPunctuation(word.charAt(i))) {
//                word = word.replace(String.valueOf(word.charAt(i)), "");
//            }
//        }
//        return  word;
//    }

    private void instantiateDatabase() {
        BufferedReader br;

        HashMap<String, WordNode> wordMap = new HashMap<>();

        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;

            while(((line = br.readLine())!= null)){

                String[] lineArray = line.split(" ");

                String prevWord = "";

                for(int i = 0; i < lineArray.length; i++) {

                    String word =  lineArray[i];

                    Boolean isSentenceEnd = false;


                    if(isEndPunctuation(word.charAt(word.length() -1))) {
                        isSentenceEnd = true;
                        word = word.replace(String.valueOf(word.charAt(word.length() -1)), "");
                    }

                    if(wordMap.containsKey(word)) {
                        wordMap.get(word).incrementCommonality();
                    } else {
                        WordNode thisWordNode = new WordNode(word);
                        wordMap.put(word, thisWordNode);
                    }

                    if(isSentenceEnd) {
                        wordMap.get(word).incrementIsEnd();
                    }

                    if(!prevWord.equals("")) {
                        if((wordMap.get(word).checkLeftNeighbors(prevWord)) == 0) {
                            NeighborWord prevWordNeighbor = new NeighborWord(prevWord);
                            wordMap.get(word).addLeftNeighbor(prevWordNeighbor);
                        }

                        else {
                            wordMap.get(word).incrementLeftNeighbor(prevWord);
                        }
                    }

                    prevWord = word;

                    if ((i + 1) < lineArray.length) {
                        String nextWord = lineArray[i+1];

                        if((wordMap.get(word).checkLeftNeighbors(nextWord)) == 0) {
                            NeighborWord nextWordNeighbor = new NeighborWord(nextWord);
                            wordMap.get(word).addLeftNeighbor(nextWordNeighbor);
                        }
                        else {
                            wordMap.get(word).incrementRightNeighbor(word);
                        }
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
