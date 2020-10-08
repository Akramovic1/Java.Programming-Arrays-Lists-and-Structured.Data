import edu.duke.*;
import java.util.*;

/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    private ArrayList<String> myNames;
    private ArrayList<Integer> myFreqs;
    
    public CharactersInPlay(){
        myNames = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    public void update (String person){
        int index = myNames.indexOf(person);
        if(index == -1){
            myNames.add(person);
            myFreqs.add(1);            
        } else {
            int value = myFreqs.get(index);
            myFreqs.set(index , value+1);
        }
    }
    public void findAllCharacters(){
        myNames.clear();myFreqs.clear();
        FileResource fr = new FileResource();
        for(String line : fr.lines()){
            int index = line.indexOf(".");
            if(index != -1){
                String person = line.substring(0,index);
                if(person.equals(person.toUpperCase()) && person.startsWith("SCENE") == false){
                    update(person);
                }
            }
        }
    }
    public void charactersWithNumParts(int num1, int num2){
        System.out.println("Characters that occurre more than " + num1 + " times and less than " + num2 + " times are: ");
        for (int i = 0; i < myFreqs.size(); i++){
            if(myFreqs.get(i)>= num1 && myFreqs.get(i)<=num2){
                System.out.println(myNames.get(i) + myFreqs.get(i));
            }
        }
    }
    public void tester(){
        findAllCharacters();
        for(int i = 0; i < myNames.size(); i++){
            if(myFreqs.get(i)>1){
                System.out.println(myNames.get(i) + "\t" + myFreqs.get(i));
            }
        }
        charactersWithNumParts(10,15);
        System.out.println("the total characters " + myNames.size());
    }
}
