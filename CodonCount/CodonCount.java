import edu.duke.*;
import java.util.*;

/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CodonCount {
    private HashMap<String,Integer> myMap;
    
    public CodonCount (){
        myMap = new HashMap<String,Integer>(); 
    }
    public void buildCodonMap(int start , String dna){
        myMap.clear();
        for(int i=start ; i < dna.length() - 3 ;i+=3){
            String codon = dna.substring(i,i+3);
            if(!myMap.containsKey(codon)){
                myMap.put(codon,1);
            } else {
                myMap.put(codon,myMap.get(codon)+1);
            }
        }
    }
    public String getMostCommonCodon(){
        String MCC = "";
        int value = 0;
        for(String codon : myMap.keySet()){
            int currentValue = myMap.get(codon);
            if(currentValue > value){
                value = currentValue;
                MCC = codon;
            }
        }
        return MCC;
    }
    public void printCodonCounts(int start , int end){
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are ");
        for(String codon : myMap.keySet()){
            if(myMap.get(codon)>=start && myMap.get(codon) <= end){
                System.out.println(codon + "\t" + myMap.get(codon));
            }
        }
    }
    public void tester(){
        FileResource fr = new FileResource();
        buildCodonMap(0,fr.asString().toUpperCase());
        for (String codon : myMap.keySet()){
            int value = myMap.get(codon);
            System.out.println(codon + "\t" + value);
        }
        System.out.println("Number of unique codons is: " + myMap.size());
        String mostComonCodon = getMostCommonCodon();
        System.out.println("The most comon codon is: " + mostComonCodon + " with count " + myMap.get(mostComonCodon));
        printCodonCounts(7, 7);
    }
}
