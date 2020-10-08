
import java.lang.*;

/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch){
        String vowels = "aeiouAEIOU";
        if(vowels.indexOf(ch) != -1){return true;}
        else{return false;}
    }
    public String replaceVowels(String phrase , char ch){
        StringBuilder newPhrase = new StringBuilder(phrase);
        String vowels = "aeiouAEIOU";
        for(int i = 0 ; i < phrase.length(); i++){if(isVowel(newPhrase.charAt(i))){newPhrase.setCharAt(i,ch);}} 
        return newPhrase.toString();
    }
    public String emphasize (String phrase , char ch){
        StringBuilder newPhrase = new StringBuilder(phrase);
        for(int i = 0 ; i < phrase.length(); i++){
            if(newPhrase.charAt(i)== Character.toLowerCase(ch) || newPhrase.charAt(i)==Character.toUpperCase(ch)){
                if(i%2==0){newPhrase.setCharAt(i,'*');}
                else{newPhrase.setCharAt(i,'+');}
            }
        } return newPhrase.toString();
    }
     public void testIsVowel() {
        System.out.println("a:" + isVowel('a'));
        System.out.println("I:" + isVowel('I'));
        System.out.println("b:" + isVowel('b'));
        System.out.println("Z:" + isVowel('Z'));
    }
    public void testReplaceVowels() {
        System.out.println("Hello World with *: " + replaceVowels("Hello World", '*'));
        System.out.println("Live Long and Prosper with a : " + replaceVowels("Live Long and Prosper", 'a'));
    }
    public void testEmphasize() {
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
}
