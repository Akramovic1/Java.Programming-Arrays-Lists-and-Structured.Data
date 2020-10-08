import edu.duke.FileResource;
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarBreaker {
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
     public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxindex = maxIndex(freqs);
        int dKey = maxindex - 4;
        if (maxindex<4){
            dKey = 26 - (4-maxindex);
        }
        return cc.encrypt(encrypted, 26 - dKey);
    }
    public String halfOfString(String message, int start){
        StringBuilder newMessage = new StringBuilder();
        for(int i = start; i<message.length(); i+=2){
            char ch = message.charAt(i);
            newMessage.append(message.charAt(i));
        }
        return newMessage.toString();
    }
    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxindex = maxIndex(freqs);
        int key = maxindex - 4;
        if(maxindex<4){
            key = 26 - (4-maxindex);
        }
        return key;
    }
    public String decryptTwoKeys(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int key1 = getKey(halfOfString(encrypted,0));
        int key2 = getKey(halfOfString(encrypted,1));
        System.out.println(key1 + ", " + key2);
        return cc.encryptTwoKeys(encrypted,26-key1, 26-key2);
    }
    public void testDecrypt(){
        CaesarCipher cc = new CaesarCipher();
        String encrypted = cc.encrypt("Just a test string with lots of eeeeeeeeeeeeeeeees ",23);
        String decrypted = decrypt(encrypted);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }
    public void testHalfOfString(){
        String newMessage = halfOfString("Qbkm Zgis",1);
        System.out.println(newMessage);
    }
    public void testGetKey(){
        int key = getKey("Zkij q juij ijhydw myjx beji ev uuuuuuuuuuuuuuuuui");
        System.out.println(key);
    }
    public void testDecryptTwoKeys(){
        /*String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        String decrypted = decryptTwoKeys(message);
        System.out.println(decrypted);*/
        FileResource fr = new FileResource();
        String file = fr.asString();
        CaesarBreaker cb = new CaesarBreaker();
        CaesarCipher cc = new CaesarCipher();
        String decrypted = cb.decryptTwoKeys(file);
        System.out.println(decrypted);
    
    }

}
