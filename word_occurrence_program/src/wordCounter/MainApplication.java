package wordCounter;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class MainApplication {

	public static void main(String[] args) throws Exception {
		
        File file = new File("C:\\Users\\ccolo\\Desktop\\Raven.txt");
        
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        String line;
        
        Map<String, Integer> count = new HashMap<>();
        
        while ((line = br.readLine()) != null) {
            String[] words = line.split("\\W+");
            for (String word : words) {
                word = word.toLowerCase();
                if (word.length() > 0) {
                    if (count.containsKey(word)) {
                        count.put(word, count.get(word) + 1);
                    } else {
                        count.put(word, (Integer) 1);
                    }
                }
            }
        }
        
        Set<Map.Entry<String, Integer>> countSet = count.entrySet();
   
        ArrayList<Map.Entry<String, Integer>> countList = new ArrayList<Entry<String, Integer>>(countSet);
        
        Collections.sort(countList, new Comparator<Map.Entry<String, Integer>>(){

			@Override
		public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
			return -o1.getValue().compareTo(o2.getValue());
			}
        });     
        
        for (int i=0; i<20; i++) {
        	Map.Entry<String, Integer> sorted = countList.get(i);
        	System.out.println(sorted.getKey() + ": " + sorted.getValue());
        }
        
        br.close();

     }
	
}
