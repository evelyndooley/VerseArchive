public class NeighborWord {

    private int numInstances = 1; /**number of instances of this neighbor */
    private String word; /**word that is a neighbor of a WordNode */
    private WordNode thisWord; /**wordNode for which this is a neighbor */

    /**
     * Constructor for NeighborWord
     * @param word word that is a neighbor or a WordNode
     */
    public  NeighborWord(String word) {this.word = word;}

    /**
     * Increments number of instances of this word as a neighbor of thisWord
     */
    public void incrementInstances() {numInstances++;}

    /**
     * Decrements number of instances of this word as a neighbor of thisWord
     */
    public void decrementInstances() {numInstances--;}

    /**
     * @return the string of this neighbor
     */
    public String getWordString(){return word;}

    /**
     * @return the WordNode for which this is a neighbor
     */
    public WordNode getWordNode(){return thisWord;}

    /**
     * @return The number of instances of this word as a neighbor of a given wordNode
     */
    public  int getNumInstances() {
        return  numInstances;
    }
}
