import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Ari Bernstein
 *
 */

public class Database {

    private String filePath; /** filepath of the text file from which we build database */

    private final int fileNotFoundErrorCode = 2;
    private final int javaIOErrorCode = 3;

    private HashMap<String, WordNode> wordMap = new HashMap<>(); /** HashMap to store all word objects (wordNodes)*/

    /**
     * Constructor for database
     * @param filePath filepath of the text file from which we build database
     */
    public Database(String filePath) {
        this.filePath = filePath;
        instantiateDatabase();
    }

    /**
     * checks if a give character is the final character of a sentence
     * @param lastCharOfWord character to check
     * @return true if character is a period, exclamation point, or question mark. False otherwise
     */
    private boolean isEndPunctuation(char lastCharOfWord) {
        if(lastCharOfWord == '.' || lastCharOfWord == '!' || lastCharOfWord == '?') {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * remove all non alphabetic characters from word
     * @param word word to strip
     * @return word lower-case and without extraneous characters
     */
    private String stripWord(String word) {
        word = word.replaceAll("[^a-zA-Z]", "");
        return  word.toLowerCase();
    }

    /**
     * Iterates through text file. For each word, it either instantiates a wordNode object or increments the number of instances of the word.
     * It also handles the words before and after adds them to the wordNode's neighbor HashMaps (leftNeighbors, rightNeighbors)
     */
    private void instantiateDatabase() {
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(filePath));
            String line; /** String holding the given line of the text file */

            while(((line = br.readLine())!= null)){

                String[] lineArray = line.split(" ");

                String prevWord = "";

                if(!line.equals("")) { //if the line is not empty:
                    for (int i = 0; i < lineArray.length; i++) {
                        String word = lineArray[i]; /**the given word */

                        if(!word.equals("")) {

                            Boolean isSentenceEnd = false; /**True if word ends with a period, exclamation point, or question mark. False otherwise*/


                            if (isEndPunctuation(word.charAt(word.length() - 1))) { //checks last character of word
                                isSentenceEnd = true;
                                //word = word.replace(String.valueOf(word.charAt(word.length() - 1)), ""); //removes sentence-ending character from word prior to adding it to database
                            }

                            word = stripWord(word);

                            if(word.length() == 1) { //removes all single character words that aren't 'a' or 'i'
                                if(!word.equals("a") && !word.equals("i")) {
                                    continue;
                                }
                            }

                            if (wordMap.containsKey(word)) { //increments commonality if word is already in database
                                wordMap.get(word).incrementCommonality();
                            } else {
                                WordNode thisWordNode = new WordNode(word); //else, instantiates new verseDB.WordNode object
                                wordMap.put(word, thisWordNode); //puts object into database
                            }

                            if (isSentenceEnd) {
                                wordMap.get(word).incrementIsEnd(); //increments sentence end if this word is last one in sentence. Must do this after we know word is in database
                            }

                            if (!prevWord.equals("")) { //If not first word in line
                                if ((wordMap.get(word).checkLeftNeighbors(prevWord)) == 0) { //if left neighbor doesn't exist in left neighbor database:
                                    NeighborWord prevWordNeighbor = new NeighborWord(prevWord); //instantiate new Neighbor
                                    wordMap.get(word).addLeftNeighbor(prevWordNeighbor); //add it to left neighbor database
                                } else {
                                    wordMap.get(word).incrementLeftNeighbor(prevWord); //if already exists in left neighbor database: increment number of instances
                                }
                            }

                            prevWord = word; //updates the previous word for the next iteration

                            if ((i + 1) < lineArray.length) { //if we are not at the end of the line:
                                String nextWord = lineArray[i + 1]; //set next word in line

                                nextWord = stripWord(nextWord);

                                if ((wordMap.get(word).checkRightNeighbors(nextWord)) == 0) { //if next word not in right neighbor database:
                                    NeighborWord nextWordNeighbor = new NeighborWord(nextWord); //instantiate new Neighbor
                                    wordMap.get(word).addRightNeighbor(nextWordNeighbor); //add neighbor to right neighbor database
                                } else {
                                    wordMap.get(word).incrementRightNeighbor(nextWord); //if already exists in right neighbor database: increment number of instances
                                }
                            }
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

    /**
     * returns node object for a given word.
     * @param word word node to return
     * @return verseDB.WordNode of the given word
     */
    public WordNode getWordNode(String word) {
        if(wordMap.containsKey(word)) {
            return wordMap.get(word);
        } else {
            System.out.println("WORD NOT IN wordMap DATABASE");
            return null;
        }
    }

    /**
     * getter for HashMap of left neighboring words containing verseDB.NeighborWord objects
     * @param word word for which you would like its left neighbors
     * @return hashmap of left neighboring words containing verseDB.NeighborWord objects
     */
    public HashMap<String, NeighborWord> getLeftNeighborsMap (String word) {
        return wordMap.get(word).getLeftNeighbors();
    }

    /**
     * getter for HashMap of right neighboring words containing verseDB.NeighborWord objects
     * @param word word for which you would like its right neighbors
     * @return hashmap of right neighboring words containing verseDB.NeighborWord objects
     */
    public HashMap<String, NeighborWord> getRightNeighborsMap (String word) {
        return wordMap.get(word).getRightNeighbors();
    }

    public static void main(String [] args) {
        String path = "C:\\Users\\Dawn\\Documents\\GitHub\\VerseArchive\\allpoems.txt";
        MakeVerse verse = new MakeVerse("love");
        System.out.println(verse);




    }

}