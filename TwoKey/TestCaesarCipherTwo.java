import edu.duke.FileResource;
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipherTwo {
    public int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int i = 0; i<message.length(); i++){
            char ch = Character.toLowerCase(message.charAt(i));
            int index = alph.indexOf(ch);
            if (index!=-1){
                counts[index]++;
            }
        }
        return counts;
    }
    public int maxIndex(int[] values){
        int maxindex = 0;
        for(int i = 0; i<values.length; i++){
            if(values[i]>values[maxindex]){
                maxindex = i;
            }
        }
        return maxindex;
    }
    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int key = maxDex - 4;
        if(maxDex<4){
            key = 26 - (4-maxDex);
        }
        return key;
    }
    public String halfOfString(String message, int start){
        StringBuilder newMessage = new StringBuilder();
        for(int i = start; i<message.length(); i+=2){
            char ch = message.charAt(i);
            newMessage.append(message.charAt(i));
        }
        return newMessage.toString();
    }
    public void simpleTest(){
        FileResource fr = new FileResource();
        String file = fr.asString();
        CaesarCipherTwo cc2 = new CaesarCipherTwo(17,3);
        String encrypted = cc2.encrypt(file);
        String decrypted = cc2.decrypt(encrypted);
        System.out.println(encrypted);
        System.out.println(decrypted);
        System.out.println(breakCaesarCipher(file));
    }
    public String breakCaesarCipher(String input){
        int key1 = getKey(halfOfString(input,0));
        int key2 = getKey(halfOfString(input,1));
        System.out.println(key1 + ", " + key2);
        CaesarCipherTwo cc2 = new CaesarCipherTwo(key1,key2);
        return cc2.decrypt(input);
    }

}
