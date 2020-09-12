/**
 * @author Calvin Lam
 * 112763367
 * CSE 214-R07
 *
 * This is the FrequencyTable class which stores an ArrayList of FrequencyList
 */
import java.util.*;
public class FrequencyTable extends ArrayList {
    private ArrayList<Passage> passages;
    private ArrayList<FrequencyList> freqList;
    public FrequencyTable(){

    }

    /**
     * This is the constructor for FrequencyTable
     */
    public void FrequencyTable(){
        freqList=new ArrayList<>();
        //passages=new ArrayList<>();
    }

    /**
     * This method builds a Frequency Table using an Array list of passages
     * @param passages ArrayList of passages
     * @return FrequencyTable
     */
    public static FrequencyTable buildTable(ArrayList<Passage> passages){
        FrequencyTable table=new FrequencyTable();
        ArrayList<FrequencyList> fList=new ArrayList<>();
        for(int i=0; i<passages.size();i++){
                for(String words: passages.get(i).getWords()){
                    boolean repeat=false;
                    for(int k=0; k<fList.size();k++){
                        if(fList.get(k).getWord()==words)
                            repeat=true;
                    }
                    if(repeat==false) {
                        FrequencyList word= new FrequencyList(words,passages);
                        for(int j=0; j<passages.size();j++){
                            word.addPassage(passages.get(j));
                        }
                        fList.add(word);
                    }
                    }
            }
            table.setFreqList(fList);
            return table;
        }

    /**
     * This method adds a passage to the prexisting frequency lists
     * @param p
     */
    public void addPassage(Passage p){
        passages.add(p);
    }

    /**
     * This method gets the number of occurences of a specific word for inputted passage
     * @param word word being searched for
     * @param p name of passage
     * @return number of occurences
     */
    public double getFrequency(String word,Passage p){
        if(passages.contains(p)){
            if(passages.get(passages.indexOf(p)).getSimilarTitles().containsValue(word)) {
                return passages.get(passages.indexOf(p)).getSimilarTitles().get(word) / passages.get(passages.indexOf(p)).getWordCount();
            }

                return 0;
        }
        else {
            System.out.println("Passage not Found");
            return -1;
        }
    }

    /**
     * This is the getter method for freqList
     * @return array list storing frequency list
     */
    public ArrayList<FrequencyList> getfreqList() {
        return freqList;
    }

    /**
     * This is the setter method for freqList
     * @param freqList copies over an ArrayList of frequency list
     */
    public void setFreqList(ArrayList<FrequencyList> freqList) {
        this.freqList = freqList;
    }
}
