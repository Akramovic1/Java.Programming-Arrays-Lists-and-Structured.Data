import edu.duke.*;
import java.util.*;
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies (){
        myWords = new ArrayList<String>();
        myFreqs= new ArrayList<Integer>();
    }
    public void findUnique (){
        myWords.clear();myFreqs.clear();
        FileResource fr = new FileResource();
        for(String word : fr.words()){
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if(index == -1){
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int valueOfFreqs = myFreqs.get(index);
                myFreqs.set(index , valueOfFreqs + 1 );
            }
        }
    }
    public int findIndexOfMax(){
        int maxIndex = 0 , maxNumber = myFreqs.get(0);
        for(int i = 0 ; i < myFreqs.size() ; i++){
            if(myFreqs.get(i)>maxNumber){
                maxNumber = myFreqs.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    public void tester(){
        findUnique();
        System.out.println("Number of unique words " + myWords.size());
        for(int i=0; i<myWords.size();i++){
            System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
        }
        int max = findIndexOfMax();
        System.out.println("The most words occurs is \"" + myWords.get(max) + "\" number of occurance is :" + myFreqs.get(max));
        System.out.println("The numbers of unique words are " + myWords.size());
    }
}
