public class NeighborWord {

    private int numInstances = 1;
    private String word;
    private WordNode node;


    public  NeighborWord(String word) {this.word = word;}

    public void incrementInstances() {numInstances++;}

    public void decrementInstances() {numInstances--;}

    public String getWordString(){return word;}

    public WordNode getWordNode(){return node;}

    public  int getNumInstances() {
        return  numInstances;
    }
}
