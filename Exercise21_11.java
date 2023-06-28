import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;import java.io.*;
import java.util.*;


public class Exercise21_11 extends Application {
  private Map<String, Integer>[] mapForBoy = new HashMap[10];
  private Map<String, Integer>[] mapForGirl = new HashMap[10];
  
  final int boyNames = 1;
  final int girlNames = 3;
  private Map[] boys = getNames(boyNames);
  private Map[] girls = getNames(girlNames);
  private Button btFindRanking = new Button("Find Ranking");
  private ComboBox<String> cboYear = new ComboBox<>();
  private ComboBox<String> cboGender = new ComboBox<>();
  private TextField tfName = new TextField();
  private Label lblResult = new Label();
  
  public String getRank() {
    int year = Integer.parseInt(cboYear.getValue()) - 2001;
    
    if (cboGender.getValue().equals("Male")) {
      return boys[boyNames].get(tfName.getText()) + "";
    }
    
    return girls[girlNames].get(tfName.getText()) + "";
  }
  
  public void GUIRank() {
    lblResult.setText(getGender() + " name " + tfName.getText() + " is ranked #" + getRank() + " in year " + cboYear.getValue());
  }
  
  public String getGender() {
    return cboGender.getValue().equals("Male") ? "Boy" : "Girl";
  }
  
  public Map[] getNames(int gender) {
    Map[] array = new Map[10];
    
    for (int year = 2001, i = 0; year <= 2010 && i < 10; year++, i++) {
      Map<String, String> map = new HashMap<>();
      
      try {
        java.net.URL url = new java.net.URL("http://liveexample.pearsoncmg.com/data/babynamesranking" + year + ".txt");
        
        Scanner input = new Scanner(url.openStream());
        while (input.hasNext()) {
          ArrayList<String> list = new ArrayList<>();
          for (int l = 0; l < 5; l++) {
            list.add(l, input.next());
          }
          map.put(list.get(gender), list.get(0));
        }
      }
      
      catch (IOException ex){
        System.out.println("File doesn't exist.");
      }
      array[i] = map;
    }
    
    return array;
  }
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    
    GridPane gridPane = new GridPane();
    gridPane.add(new Label("Select a year:"), 0, 0);
    gridPane.add(new Label("Boy or girl?"), 0, 1);
    gridPane.add(new Label("Enter a name:"), 0, 2);
    gridPane.add(cboYear, 1, 0);
    gridPane.add(cboGender, 1, 1);
    gridPane.add(tfName, 1, 2);
    gridPane.add(btFindRanking, 1, 3);
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setHgap(5);
    gridPane.setVgap(5);
    
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(gridPane);
    borderPane.setBottom(lblResult);
    BorderPane.setAlignment(lblResult, Pos.CENTER);
    
    btFindRanking.setOnAction(e -> GUIRank());

    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 370, 160);
    primaryStage.setTitle("Exercise21_11"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    for (int i = 2001; i <= 2010; i++) 
      cboYear.getItems().add(i + "");
    
    cboGender.getItems().addAll("Male", "Female");
    
  }
  

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
