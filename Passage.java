/**
 * @author Calvin Lam
 * 112763367
 * CSE 214-R07
 *
 * This is the Passage class which parses a file and stores each word in a hashtable along with the frequency
 */
import java.io.*;
import java.io.File;
import java.util.*;


public class Passage extends Hashtable{
    private String title;
    private int wordCount;
    private Hashtable<String,Integer> similarTitles;

    /**
     * This is the constructor for Passage
     * @param title title of passage
     * @param file  file name
     * @throws IOException if file is not found
     */
    public Passage(String title, File file) throws IOException {
        similarTitles=new Hashtable<>();
        setTitle(title);
        parseFile(file);
    }

    /**
     * This is the parseFile method which adds each word and the count to a hashtable
     * @param file name of file
     * @throws IOException
     */
    public void parseFile(File file) throws IOException {
        String stopWords = "a about above after again against all am an and any are as at be because been before being below between both but by could did do does doing down during each few for from further had has have having he hed hell hes her here heres hers herself him himself his how hows i id ill im ive if in into is it its itself lets me more most my myself nor of on once only or other ought our ours ourselves out over own same she shed shell shes should so some such than that thats the their theirs them themselves then there theres these they theyd theyll theyre theyve this those through to too under until up very was we wed well were weve what whats when whens where wheres which while who whos whom why whys with would you youd youll youre youve your yours yourself yourselves";
        String[] sWords = stopWords.split(" ");
        boolean s;
        int wCount = 0;
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                s = false;
                String line = scan.next();
                line = line.toLowerCase().replaceAll("[^a-zA-Z ]", "");

                if (similarTitles.containsKey(line)&&line.length()>0) {
                    similarTitles.replace(line, similarTitles.get(line) + 1);
                    wCount++;
                } else {
                    for (int i = 0; i < sWords.length; i++) {
                        if (line.equals(sWords[i]))
                            s = true;
                    }
                    if (s == false &&line.length()>0) {
                        similarTitles.put(line, 1);
                        wCount++;
                    }
                }
            }
            }catch(Exception E){
              System.out.println("File not found");
            }
            setWordCount(wCount);
        }

    /**
     * This method calculates the similarity between two passages
     * @param passage1 name of first passage
     * @param passage2 name of second passage
     * @return double representing similarity ratio
     */
    public static double cosineSimilarity(Passage passage1, Passage passage2) {
        double numerator=0;
        double denominator1=0;
        double denominator2=0;
        for(String words: passage1.getWords()){
            if(passage2.getSimilarTitles().containsKey(words)==false)
                passage2.getSimilarTitles().put(words,0);
        }
        for(String words: passage2.getWords()) {
            if (passage1.getSimilarTitles().containsKey(words) == false)
                passage1.getSimilarTitles().put(words, 0);
        }
        for(String words:passage1.getWords()){
            numerator=((passage1.getWordFrequency(words)/passage1.wordCount) * (passage2.getWordFrequency(words)/passage2.wordCount))+numerator;
            denominator1=Math.pow(passage1.getWordFrequency(words)/passage1.wordCount,2)+denominator1;
            denominator2=Math.pow(passage2.getWordFrequency(words)/passage2.wordCount,2)+denominator2;
        }
        double denominator= (Math.sqrt(denominator1))*(Math.sqrt(denominator2));
        return numerator/denominator;

    }

    /**
     * This is the getter method for word Frequency
     * @param word name of word
     * @return number of occurence
     */
    public double getWordFrequency(String word){
        return similarTitles.get(word);
    }

    /**
     * This is the getter for words
     * @return a set storing each word in hashtable
     */
    public Set<String>getWords(){
        return similarTitles.keySet();
    }

    /**
     * This is the getter method for title
     * @return title of passage
     */
    public String getTitle() {
        return title;
    }

    /**
     * This is the setter method for title
     * @param title new title of passage
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This is the getter method for word count
     * @return number of words in text excluding stop words
     */
    public double getWordCount() {
        return wordCount;
    }

    /**
     * This is the setter method for word count
     * @param wordCount new total words
     */
    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    /**
     * This is the getter method for similarTitles
     * @return hashtable storing each word and number of occurence
     */
    public Hashtable<String, Integer> getSimilarTitles() {
        return similarTitles;
    }
}
