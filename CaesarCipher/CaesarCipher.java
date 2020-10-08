import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
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
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabetWithKey1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        String shiftedAlphabetWithKey2 = alphabet.substring(key2) + alphabet.substring(0,key2);
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
    public void testCaesar() {
        int key = 15;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
    public void test1(){
        System.out.println(encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15));
    }
    public void testEncryptTwoKeys() {
       System.out.println(encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.", 26-14, 26-24));
    }
}

