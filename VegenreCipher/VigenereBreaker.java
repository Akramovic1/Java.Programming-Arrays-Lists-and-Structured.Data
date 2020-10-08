import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slice = new StringBuilder();
        for(int i = whichSlice; i<message.length(); i+=totalSlices){
            char c = message.charAt(i);
            slice.append(c);
        }
        return slice.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker();
        for(int i = 0; i<klength;i++){
            int keys = cc.getKey(sliceString(encrypted,i,klength));
            key[i]=keys;
        }
        return key;
    }

    public void breakVigenere () {
        /*FileResource fr = new FileResource();
        String input = fr.asString();
        int[] key = tryKeyLength(input,4,'e');
        VigenereCipher vc = new VigenereCipher(key);
        System.out.println(vc.decrypt(input));
        System.out.println("\n \n" + Arrays.toString(key));*/
        
        /*FileResource fr = new FileResource();
        String input = fr.asString();
        HashSet<String> dict=readDictionary(new FileResource());
        String decryptrd=breakForLanguage(input,dict);
        System.out.println(decryptrd);*/
        String input=new FileResource().asString();
        DirectoryResource dir = new DirectoryResource();
        HashMap<String,HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        for(File f : dir.selectedFiles())
        {
            System.out.println("Reading words in "+f.getName());
            languages.put(f.getName(),readDictionary(new FileResource(f)));
        }
        breakForAllLangs(input,languages);
        
    }
    public HashSet<String> readDictionary (FileResource fr){
        HashSet<String> HS = new HashSet<String>();
        for(String line : fr.lines()){
            line = line.toLowerCase();
            HS.add(line);
        }
        return HS;
    }
    public int countWords(String message, HashSet<String> dict){
        String[] words = message.split("\\W");
        int c = 0;
        for(String word : words){
            if(dict.contains(word.toLowerCase())){
                c++;
            }
        }
        return c;
    }
    public String breakForLanguage(String encrypted,HashSet<String> dict){
        int max=0,temp=0,keys[]=null;
        for(int i=1;i<100;i++){
            int tempkeys[] = tryKeyLength(encrypted,i,mostCommonCharIn(dict));
            String decrypted = new VigenereCipher(tempkeys).decrypt(encrypted);
            temp=countWords(decrypted,dict);
            if(temp>max){max=temp ; keys=tempkeys;}
        }
        System.out.println("the total number of words is "+max);
        System.out.println("the key length is " + keys.length + "\n the key is " + Arrays.toString(keys) );
        return new VigenereCipher(keys).decrypt(encrypted);
    }
    public char mostCommonCharIn(HashSet<String> dict){
         HashMap<Character,Integer> charCounter = new HashMap<Character,Integer>();
         for(String s : dict){
             for(char c : s.toLowerCase().toCharArray()){
                 if(!charCounter.containsKey(c))
                    charCounter.put(c,1);
                else
                    charCounter.put(c,charCounter.get(c)+1);
            }
         }
         int max=0;
         char mostChar = '0';
         for(char c : charCounter.keySet()){
             if(charCounter.get(c) > max){
            
                max = charCounter.get(c);
                mostChar = c;
            }
         }
         return mostChar;
    }
    public void breakForAllLangs(String encrypted,HashMap<String,HashSet<String>> languages){
        int maxwords=0,tmp=0;
        String keylang=null,decrypted=null,ans=null;
        for(String lang:languages.keySet())
        {
            decrypted=breakForLanguage(encrypted,languages.get(lang));
            tmp=countWords(decrypted,languages.get(lang));
            if(maxwords<tmp)
            {
                maxwords=tmp;
                keylang=lang;
                ans=decrypted;
            }
        }
        System.out.println("Language :"+keylang);
        System.out.println("Decrypted :"+ans);
    }
}


