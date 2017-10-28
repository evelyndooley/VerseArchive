import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Andrew on 10/28/2017.
 */

public class MakeVerse {
    private CreateDatabase verseDB;
    private Random dice;
    private String verse ="";
    public MakeVerse(String initialWord){
        verse = initialWord;
        dice = new Random(System.currentTimeMillis());
        verseDB = new CreateDatabase("C:\\Users\\Dawn\\Documents\\GitHub\\VerseArchive\\allpoems.txt");
    }
    /// sortedNeighbors turns the hashmap of the most recent words right hand neighbors into an ordered list
    /// of its most likely child words.
    public  ArrayList<Map.Entry<String, NeighborWord>> sortedNeighbors(String lastWord){
        if(verseDB.getWordNode(lastWord) != null){
            HashMap<String,NeighborWord> nextMap = verseDB.getRightNeighborsMap(lastWord);
            ArrayList<Map.Entry<String, NeighborWord>> sortMap= new ArrayList<>(nextMap.entrySet());
            sortMap.sort((b,a)-> a.getValue().getNumInstances() - b.getValue().getNumInstances());//lambda comparator
            return sortMap;
        }
        else
            return null;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
