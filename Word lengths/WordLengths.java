import edu.duke.FileResource;

/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    public void countWordLengths (FileResource resource ,int[] counts){
        int maxlength = 0;
        String words[] = new String[31];
        for(String word : resource.words()){
            int LW = word.length();
            if(word.endsWith(",")  || word.endsWith(".")){
                counts[LW-1]++; 
                if(words[LW-1] == null){words[LW-1]=" " + word.substring(0,LW-1);}
                else {words[LW-1]=words[LW-1] + " " + word.substring(0,LW-1);}
            }
            else if (word.endsWith("\"")){
                counts[LW-2]++;
                if(words[LW-2] == null){words[LW-2]=" " + word.substring(1,LW-1);}
                else {words[LW-2]=words[LW-2] + " " + word.substring(1,LW-1);}
            }
            else{
                counts[LW]++;
                if(words[LW] == null){words[LW] =" " + word.substring(0,LW);}
                else {words[LW] =words[LW] + " " + word.substring(0,LW);}
            }
            maxlength = Math.max(maxlength,LW);
        }
        for(int i = 1 ; i < maxlength + 1 ; i++){
            if(counts[i]!=0){
                System.out.println(counts[i] + " words of length " + i + " :" + words[i]);
            }
        }
    }
    public int indexOfMax(int[] values){
        int maxindex = 0;
        for(int i = 0; i<values.length; i++){
            if(values[i]>values[maxindex]){
                maxindex = i;
            }
        }
        return maxindex;
    }
    public void testCountWordLengths(){
        FileResource resource = new FileResource();
        int[] counts = new int[31];
        countWordLengths(resource,counts);
        int [] value = counts;
        int maxIndex = indexOfMax(value);
        System.out.println("The most common word length in the file " + maxIndex);
    }
    
}
