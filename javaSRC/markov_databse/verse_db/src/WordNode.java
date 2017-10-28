import java.util.HashMap;
/**
 * @author Ari Bernstein
 */
public class WordNode {

    private int commonality = 0; /** number of instances of word */
    private int isEnd = 0; /** number of instances for which this word is the final one in a sentence */

    private String word; /** the given word as a string */

    private HashMap<String, NeighborWord> leftNeighbors = new HashMap<>(); /**Hashmap containing NeighborWords representing all of the words that are directly to the left of instances of our word */
    private HashMap<String, NeighborWord> rightNeighbors = new HashMap<>();/**Hashmap containing NeighborWords representing all of the words that are directly to the right of instances of our word */

    /**
     * Constructor
     * @param word word we would like to instantiate
     */
    public WordNode(String word){
        this.word = word;
    }

    /**
     * Adds neighbor to leftNeighbors HashMap
     * @param lneighbor verseDB.NeighborWord object to add
     */
    public void addLeftNeighbor(NeighborWord lneighbor) {
        String wordString = lneighbor.getWordString(); //string of the neighboring word
        if(!leftNeighbors.containsKey(wordString)) { //if the neighboring word does not exist in leftNeighbors:
            leftNeighbors.put(lneighbor.getWordString(), lneighbor); //add it!
        } else {
            leftNeighbors.get(wordString).incrementInstances(); //else: increment number of instances
        }
    }

    /**
     * Checks number of instances of word as a left neighbor
     * @param word (left) neighboring word to check
     * @return integer representing number of instances of given word as a left neighbor
     */
    public int checkLeftNeighbors(String word) {
        if (!leftNeighbors.isEmpty()) {
            if (leftNeighbors.containsKey(word)) {
                return leftNeighbors.get(word).getNumInstances();
            }
        }
        return 0;
    }

    /**
     * Increments number of instances of given word as a left neighbor
     * @param word (left) neighboring word to increment
     */
    public void incrementLeftNeighbor(String word) {
        leftNeighbors.get(word).incrementInstances();
    }

    /**
     * getter for leftNeighbors HashMap
     * @return leftNeighbors HashMap
     */
    public HashMap<String, NeighborWord> getLeftNeighbors() {
        return leftNeighbors;
    }


    /**
     * Adds neighbor to rightNeighbors HashMap
     * @param rneighbor verseDB.NeighborWord object to add
     */
    public void addRightNeighbor(NeighborWord rneighbor) {
        String wordString = rneighbor.getWordString(); //string of the neighboring word

        if(!rightNeighbors.containsKey(wordString)) { //if the neighboring word does not exist in leftNeighbors:
            rightNeighbors.put(rneighbor.getWordString(), rneighbor); //add it!
        } else {
            rightNeighbors.get(wordString).incrementInstances(); //else: increment number of instances
        }
    }

    /**
     * Checks number of instances of word as a right neighbor
     * @param word (right) neighboring word to check
     * @return integer representing number of instances of given word as a right neighbor
     */
    public int checkRightNeighbors(String word) {
        if(!rightNeighbors.isEmpty()) {
            if(rightNeighbors.containsKey(word)) {
                return rightNeighbors.get(word).getNumInstances();
            }
            else {
                return 0;
            }
        }
        return 0;
    }

    /**
     * Increments number of instances of given word as a right neighbor
     * @param word (right) neighboring word to increment
     */
    public void incrementRightNeighbor(String word) {
        rightNeighbors.get(word).incrementInstances();
    }

    /**
     * getter for rightNeighbors HashMap
     * @return rightNeighbors HashMap
     */
    public HashMap<String, NeighborWord> getRightNeighbors() {
        return rightNeighbors;
    }

    /**
     * increments number of instances of this word
     */
    public void incrementCommonality() {
        commonality++;
    }

    /**
     * getter for commonality
     * @return number of instances of this word
     */
    public int getCommonality() {
        return commonality;
    }

    /**
     * increments number of times that this word is the final one in a sentence
     */
    public void incrementIsEnd() {
        isEnd++;
    }

    /**
     * getter for isEnd
     * @return number of times that this word is the final one in a sentence
     */
    public int getIsEnd() {
        return isEnd;
    }



}
