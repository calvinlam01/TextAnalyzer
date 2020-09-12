/**
 * @author Calvin Lam
 * 112763367
 * CSE 214-R07
 *
 * This is the FrequencyList class which stores a word and the number of occurences in each passage
 */
import java.util.*;
public class FrequencyList extends ArrayList{
    private String word;
    private ArrayList<Integer> frequencies;
    private Hashtable<String, Integer> passageIndices;
    private ArrayList<Passage> passages;

    /**
     * This is the constructor for Frequency List
     * @param word name of word
     * @param name name of passage
     */
    public FrequencyList(String word, Passage name){
        setWord(word);
        frequencies=new ArrayList<>();
        passageIndices=new Hashtable<>();
    }

    /**
     * This is the other constructor for Frequency List
     * @param word
     * @param passages
     */
    public FrequencyList(String word,ArrayList<Passage> passages){
        setWord(word);
        setPassages(passages);
        frequencies=new ArrayList<>();
        passageIndices=new Hashtable<>();
        passages=new ArrayList<>();
    }

    /**
     * This method adds an additional Passage to the Frequency list
     * @param p name of passage
     */
    public void addPassage(Passage p){
        passages.add(p);
        if(p.getSimilarTitles().containsKey(word))
            frequencies.add(p.getSimilarTitles().get(word));
        else
            frequencies.add(0);
        passageIndices.put(p.getTitle(),getFrequency(p));
    }

    /**
     * This method returns the number of occurences for inputted passage
     * @param p desired passage
     * @return
     */
    public int getFrequency(Passage p){
        return frequencies.get(passages.indexOf(p));
    }

    /**
     * This is the setter method for word
     * @param word word being stored by frequency list
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * This is the getter method for word
     * @return word being stored
     */
    public String getWord() {
        return word;
    }

    /**
     * This is the setter method for Passages
     * @param passages stores all the passages in an array list
     */
    public void setPassages(ArrayList<Passage> passages) {
        this.passages = passages;
    }

    /**
     * This is the getter method for all frequencies
     * @return arraylist storing the number of occurences for every passage
     */
    public ArrayList<Integer> getFrequencies() {
        return frequencies;
    }

}
