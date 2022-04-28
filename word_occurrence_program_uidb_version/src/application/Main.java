package application;
	
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
//C:\Users\ccolo\Desktop\Raven.txt

//import com.mysql.cj.xdevapi.*;

public class Main extends Application {
	
//	public static void post() throws Exception {	
//		final ArrayList<Map.Entry<String, Integer>> countList = 
//	try {
//		Connection conn = getConnection();
//		Statement statement = conn.createStatement();
//		PreparedStatement posted = conn.prepareStatement("INSERT INTO words (word) VALUES (statement)");
//		posted.executeUpdate();
//	}catch(Exception e) {System.out.println(e);}
//		finally{System.out.println("Insert completed");}
//}
	
	public static void createTable() throws Exception{
		try {
		Connection conn = getConnection();
		PreparedStatement create = conn.prepareStatement("CREATE TABLE IF NOT EXISTS words(id int NOT NULL AUTO_INCREMENT, word varchar(255), occ int, PRIMARY KEY(id))");
		create.executeUpdate();
		}catch(Exception e) {System.out.println(e);}
			finally{System.out.println("Function complete");}
	}
	
	public static Connection getConnection() throws Exception{
		try {
			String url = "jdbc:mysql://localhost:3306/word_occurances";
			String username = "root";
			String password = "Asdf0987!";
			
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connected");
			return conn;
		} catch(Exception e) {System.out.println(e);
		
	}
		return null;		
	}
	
	@Override
	public void start(Stage primaryStage) {
		BorderPane paneForTextField = new BorderPane();
	    paneForTextField.setPadding(new Insets(5, 5, 5, 5)); 
	    paneForTextField.setStyle("-fx-border-color: green");
	    paneForTextField.setLeft(new Label("Enter a file path to test \nword occurrence: "));
	    
	    TextField tf = new TextField();
	    tf.setAlignment(Pos.BOTTOM_RIGHT);
	    paneForTextField.setCenter(tf);
	    
	    BorderPane mainPane = new BorderPane();
	    // Text area to display contents
	    TextArea ta = new TextArea();
	    mainPane.setCenter(new ScrollPane(ta));
	    mainPane.setTop(paneForTextField);
	    
	    // Create a scene and place it in the stage
	    Scene scene = new Scene(mainPane, 480, 220);
	    primaryStage.setTitle("Top 20 Words Used from File"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
			
	    tf.setOnAction(e -> {
			try {
			File file = new File((tf.getText()));//Creating file object to hold the file that will be used for the word count
		       System.out.println(file);
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
	        	ta.appendText(sorted.getKey() + ": " + sorted.getValue() + '\n');//Prints the 20 most used words and occurrences
	        }
			
		} catch(Exception e1) {
			e1.printStackTrace();
		}});
	}

	public static void main(String[] args) throws Exception {
//		post();
		createTable();
		launch(args);
		
	}
}
