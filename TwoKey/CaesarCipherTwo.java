
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabetWithKey1;
    private String shiftedAlphabetWithKey2;
    private int mainKey1 , mainKey2;
    public CaesarCipherTwo (int key1, int key2){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabetWithKey1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        String shiftedAlphabetWithKey2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    public String encrypt (String input){
        StringBuilder encrypted = new StringBuilder(input);
        int count = 0;
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if(Character.isLowerCase(currChar)){
                currChar = Character.toUpperCase(currChar);
                int index = alphabet.indexOf(currChar);
                if(index != -1){
                    if(i%2 ==0){
                        char newChar = shiftedAlphabetWithKey1.charAt(index);
                        encrypted.setCharAt(i, Character.toLowerCase(newChar));
                    }else{
                        char newChar = shiftedAlphabetWithKey2.charAt(index);
                        encrypted.setCharAt(i, Character.toLowerCase(newChar));
                    }
                }
            }else {
                int index = alphabet.indexOf(currChar);
                if(index != -1){
                    if(i%2 ==0){
                        char newChar = shiftedAlphabetWithKey1.charAt(index);
                        encrypted.setCharAt(i,newChar);
                    }else{
                        char newChar = shiftedAlphabetWithKey2.charAt(index);
                        encrypted.setCharAt(i,newChar);
                    }
                }
            }
        }
        return encrypted.toString();
    }
    public String decrypt(String input){
        CaesarCipherTwo cc2 = new CaesarCipherTwo(26 - mainKey1 , 26 - mainKey2);
        String decrypted = cc2.encrypt(input);
        return decrypted;
    }
}
