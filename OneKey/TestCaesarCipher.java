import edu.duke.FileResource;

/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipher {
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
    public String breakCaesarCipher(String input){
        int[] freqs = countLetters(input);
        int maxindex = maxIndex(freqs);
        int dKey = maxindex - 4;
        if (maxindex<4){
            dKey = 26 - (4-maxindex);
        }
        CaesarCipher cc = new CaesarCipher(dKey);
        return cc.decrypted(input);
    }
    public void simpleTest(){
        FileResource fr = new FileResource();
        String file = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(file);
        System.out.println(encrypted);
        System.out.println(breakCaesarCipher(encrypted));
    }

}
