import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Andrew on 10/28/2017.
 */

public class MakeVerse {
    private Database verseDB;
    private Random dice;
    private String verse ="";
    private String currWord = "";
    public MakeVerse(String initialWord){
        currWord= verse  = initialWord;
        dice = new Random(System.currentTimeMillis());
        verseDB = new Database("C:\\Users\\Dawn\\Documents\\GitHub\\VerseArchive\\allpoems.txt");
    }
    /// sortedNeighbors turns the hashmap of the most recent words right hand neighbors into an ordered list
    /// of its most likely child words.
    private  ArrayList<Map.Entry<String, NeighborWord>> sortedNeighbors(String lastWord){
        if(verseDB.getWordNode(lastWord) != null){
            HashMap<String,NeighborWord> nextMap = verseDB.getRightNeighborsMap(lastWord);
            ArrayList<Map.Entry<String, NeighborWord>> sortMap= new ArrayList<>(nextMap.entrySet());
            sortMap.sort((b,a)-> a.getValue().getNumInstances() - b.getValue().getNumInstances());//lambda comparator
            return sortMap;
        }
        else
            return null;
    }
    private String findNextWord(String lastWord){
        ArrayList<Map.Entry<String, NeighborWord>> entries = sortedNeighbors(lastWord);
        if(entries ==null)
            return null;
        else if( entries.size() > 2){
            int eol = verseDB.getWordNode(lastWord).getIsEnd()/3;// odds that this word is the end of the line
            int first = entries.get(0).getValue().getNumInstances();// odds that the most likely word is next
            int second = entries.get(1).getValue().getNumInstances();// odds of the second most likely word
            int third = entries.get(2).getValue().getNumInstances();// odds of the third most likely word
            int roll = dice.nextInt(eol+first+second+third);
            if(roll < eol){
                verse+=".";
                return null;
            }
            else if(roll < eol + first){
                return entries.get(0).getKey();
            }
            else if(roll < eol+first+second){
                return entries.get(1).getKey();
            }
            else{
                return entries.get(2).getKey();
            }
        }
        else return entries.get(0).getKey();
    }

    @Override
    public String toString() {
        String next = findNextWord(currWord);
        while(next != null){
            verse+= " "+ next;
            currWord = next;
            next = findNextWord(currWord);
        }
        return verse;
    }
}
