import edu.duke.*;
import java.io.File;
import java.util.*;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {
    private HashMap<String,ArrayList<String>> myMap;
    
    public WordsInFiles(){
        myMap = new HashMap<String,ArrayList<String>>();
    }
    private void addWordsFromFile(File f){
        String fileName = f.getName();
        FileResource fr = new FileResource(f);
        for(String word : fr.words()){
            if(!myMap.containsKey(word)){
                ArrayList<String> NL = new ArrayList<String>();
                NL.add(fileName);
                myMap.put(word,NL);
            } else {
                ArrayList<String> L = new ArrayList<String>();
                L = myMap.get(word);
                if(!L.contains(fileName)){
                    L.add(fileName);
                    myMap.put(word,L);
                }
            }
        }
    }
    public void buildWordFileMap(){
        myMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    public int maxNumber(){
        int maxNumber = 0;
        int length = 0;
        for(ArrayList<String> values : myMap.values()){
            length = values.size();
            if(length>maxNumber){
                maxNumber = length;
            }
        }
        return maxNumber;
    }
    public ArrayList<String> wordsInNumFiles (int number){
        ArrayList <String> words = new ArrayList<String>();
        for(String word : myMap.keySet()){
            ArrayList<String> values = myMap.get(word);
            if(values.size() == number){
                words.add(word);
            }
        }
        System.out.println("There are " + words.size() + " words that appears in " + number + " files.");
        return words;
    }
    public void printFilesIn(String word){
        ArrayList<String> files = myMap.get(word);
        for(int i = 0 ; i<files.size() ; i++){
            System.out.println(files.get(i));
        }
    }
    public void tester(){
        buildWordFileMap();
        for(String word : myMap.keySet()){
            System.out.println(word + "\t" + myMap.get(word));
        }
        System.out.println("Words that occur in files: " + myMap.size());
        int maxNumber = maxNumber();
        ArrayList<String> words = wordsInNumFiles(maxNumber);
        System.out.println("The greatest number of files a word appears in is " + maxNumber + ", and there are two such words: " + words);
        System.out.println(wordsInNumFiles(4));
        printFilesIn("tree");
    }
}
