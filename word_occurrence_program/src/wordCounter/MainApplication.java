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
		
        File file = new File("C:\\Users\\ccolo\\Desktop\\Raven.txt");//Creating file object to hold the file that will be used for the word count
       
        BufferedReader br = new BufferedReader(new FileReader(file));//Creating a buffered reader that will take the above file as an argument
        
        String line;//Creating a line object that will hold the individual lines provided by the buffered reader
        
        Map<String, Integer> count = new HashMap<>();//Creating a map to contain the keys/words and values/occurrences 
        
        while ((line = br.readLine()) != null) {//While loop that will continue as long as there are lines in the buffered reader/file
            String[] words = line.split("\\W+");//Creating a string index to hold individual words through each line with the argument that there are no special characters
            for (String word : words) {//For argument that takes each of the words from the string provided by the buffered reader and makes them all lower case to avoid errors in occurrences
                word = word.toLowerCase();
                if (word.length() > 0) {//If argument that will add instances of occurrence when a word is found as 
                						//an existing key or will add the word to the map is the word is not already there as long as there
                    if (count.containsKey(word)) {
                        count.put(word, count.get(word) + 1);
                    } else {
                        count.put(word, (Integer) 1);
                    }
                }
            }
        }
        
        br.close();//Closes the buffered reader
        
        Set<Map.Entry<String, Integer>> countSet = count.entrySet();//Converting the hash map with the words and occurrences into a set
   
        ArrayList<Map.Entry<String, Integer>> countList = new ArrayList<Entry<String, Integer>>(countSet);//Converting previous set of words and occurrences into an array list
        
        Collections.sort(countList, new Comparator<Map.Entry<String, Integer>>(){//Sorting the array list using the comparator method

			@Override
		public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {//Creating the function/argument to sort the array list
			return -o1.getValue().compareTo(o2.getValue());//Returning the sorted array list based on comparing occurrences in decreasing order
			}
        });     
        
        for (int i=0; i<20; i++) {//For argument with logic that will go through the array list and take the first 20 sets of data. After the sorting this will be the 20 most used words
        	Map.Entry<String, Integer> sorted = countList.get(i);
        	System.out.println(sorted.getKey() + ": " + sorted.getValue());//Prints the 20 most used words and occurrences
        }

     }
	
}
