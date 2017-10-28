import java.util.ArrayList;
import java.util.HashMap;

public class WordNode {

    private int commonality = 1;
    private int isEnd = 0;

    private HashMap<String, NeighborWord> leftNeighbors;
    private HashMap<String, NeighborWord> rightNeighbors;



    public void addLeftNeighbor(NeighborWord lneighbor) {
        String wordString = lneighbor.getWordString();

        if(!leftNeighbors.containsKey(wordString)) {
            leftNeighbors.put(lneighbor.getWordString(), lneighbor);
        } else {
            leftNeighbors.get(wordString).incrementInstances();
        }
    }

    public int checkLeftNeighbors(String word) {
        if(leftNeighbors.containsKey(word)) {
            return leftNeighbors.get(word).getNumInstances();
        }
        else {
            return 0;
        }
    }

    public void addRightNeighbor(NeighborWord lneighbor) {
        String wordString = lneighbor.getWordString();

        if(!rightNeighbors.containsKey(wordString)) {
            rightNeighbors.put(lneighbor.getWordString(), lneighbor);
        } else {
            rightNeighbors.get(wordString).incrementInstances();
        }
    }

    public int checkRightNeighbors(String word) {
        if(rightNeighbors.containsKey(word)) {
            return rightNeighbors.get(word).getNumInstances();
        }
        else {
            return 0;
        }
    }

    public void incrementCommonality() {
        commonality++;
    }

    public int getCommonality() {
        return commonality;
    }

    public void incrementIsEnd() {
        isEnd++;
    }

    public int getIsEnd() {
        return isEnd;
    }


}
