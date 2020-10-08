
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String alphabet ;
    private String shiftedAlphabet ; 
    private int mainkey ; 
    public CaesarCipher (int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        mainkey = key ;
    }
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if(Character.isLowerCase(currChar)){
                currChar = Character.toUpperCase(currChar);
                int index = alphabet.indexOf(currChar);
                if(index != -1){
                    char newChar = shiftedAlphabet.charAt(index);
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                }
            }else {
                int index = alphabet.indexOf(currChar);
                if(index != -1){
                    char newChar = shiftedAlphabet.charAt(index);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }
    public String decrypted (String input){
        CaesarCipher cc = new CaesarCipher(26-mainkey);
        String decrypted = cc.encrypt(input);
        return decrypted;
    }
}
