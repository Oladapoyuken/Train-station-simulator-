/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainsimulation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 *
 * @author Yuken4real
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button genBtn;
    @FXML
    private TextField genBox1;
    @FXML
    private TextField genBox2;
    @FXML
    private Label result;
    
    
    @FXML
    private ListView<String> matList;
    
    
    @FXML
    private TableView<tableFile> table;
    @FXML
    private TableColumn<tableFile, String> colOne;
    @FXML
    private TableColumn<tableFile, String> colTwo;
    @FXML
    private TableColumn<tableFile, String> colThree;
    @FXML
    private TableColumn<tableFile, String> colFour;
    @FXML
    private TableColumn<tableFile, String> colFive;
    
    @FXML
    private Pane tablePage;
     @FXML
    private SplitPane homePage;
    
    List<Float> randNumbers = new ArrayList<>();
    List<Integer> timeOne = new ArrayList<>();
    List<Integer> timeTwo = new ArrayList<>();
    List<Integer> timeThree = new ArrayList<>();
    List<String> remark = new ArrayList<>();
    int number, prob = 0;
    float finalNumm = 0;
    
    public ObservableList<String> members = FXCollections.observableArrayList();
    public String[] matricNumbers = {
        "1.    131308","2.    131920","3.    131583","4.    132676","5.    132188",
        "6.    132099","7.    132176","8.    130983","9.    132706","10.    132891",
        "11.    131163","12.    132689","13.    133377","14.    133358","15.    133540",
        "16.    130102","17.    130750","18.    131539","19.    131470","20.    132051",
        "21.    132614","22.    131335","23.    133053"
    };
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tablePage.setVisible(false);
        homePage.setVisible(true);
        
        colOne.setCellValueFactory(new PropertyValueFactory<tableFile, String>("one"));
        colTwo.setCellValueFactory(new PropertyValueFactory<tableFile, String>("two"));
        colThree.setCellValueFactory(new PropertyValueFactory<tableFile, String>("three"));
        colFour.setCellValueFactory(new PropertyValueFactory<tableFile, String>("four"));
        colFive.setCellValueFactory(new PropertyValueFactory<tableFile, String>("five"));
        colOne.setStyle("-fx-alignment: CENTER;");
        colTwo.setStyle("-fx-alignment: CENTER;");
        colThree.setStyle("-fx-alignment: CENTER;");
        colFour.setStyle("-fx-alignment: CENTER;");
        colFive.setStyle("-fx-alignment: CENTER;");
        
        for(String s: matricNumbers){
            members.add(s);
        }
        matList.setStyle("-fx-font-size: 15;");
        matList.setItems(members);
        
        
        
    }    
    
    @FXML
    void startSimulation(ActionEvent event) {
        tablePage.setVisible(true);
        homePage.setVisible(false);
    }
    
    @FXML
    void Generate() throws IOException{
        randNumbers.clear();
        timeOne.clear();
        timeTwo.clear();
        timeThree.clear();
        remark.clear();
        prob = 0;
        
        int randSeed = Integer.valueOf(genBox1.getText().trim());
        number = Integer.valueOf(genBox2.getText().trim());
        int val = randSeed;
        if(number % 4 == 0){
            for(int i = 0; i < number; i++){
                float init = ((97 * randSeed) + 3) % 1000;
                randSeed = (int)init;
                init  = init/1000;
                randNumbers.add(init);
            }
            calculate();
            table.setItems(getComponets());
            float num = (float) number;
            float finalAns = (prob * 4)/num;
            finalNumm = finalAns;
            result.setText("Thus, from the " + number/4 + " iterations, the probability that you"
                    + " caught the train = " + finalAns);
            printDoc();
        }
        else{
            result.setText("Please insert random number counter which is divisible by 4");
        }
    }
    
    
    private ObservableList<tableFile> getComponets() {
        ObservableList<tableFile> components = FXCollections.observableArrayList();
        for (int i = 0; i < number; i++) {
            components.add(new tableFile( printRndNum(i),  printColTwo(i), printColThree(i),
                printColFour(i), printColFive(i)));
        }
        return components;
    }
    
    String printRndNum(int input){
        Float fd = randNumbers.get(input);
        fd = Float.parseFloat(String.format("%.3f", fd));
        return String.valueOf(fd);
    }
    public void calculate(){
        int t1, t2, t3;
        int i = 0;
        while(i < number){
            
            
            t1 = genTimeOne(randNumbers.get(i));
            timeOne.add(genTimeOne(randNumbers.get(i)));
            timeOne.add(1001);
            timeOne.add(1001);
            timeOne.add(1001);
            
            i++;
            timeTwo.add(1001);
            t2 = genTimeTwo(randNumbers.get(i), randNumbers.get(i+1));
            timeTwo.add(genTimeTwo(randNumbers.get(i), randNumbers.get(i+1)));
            timeTwo.add(1001);
            timeTwo.add(1001);
            
            i = i + 2;
            timeThree.add(1001);
            timeThree.add(1001);
            timeThree.add(1001);
            t3 = genTimethree(randNumbers.get(i));
            timeThree.add(genTimethree(randNumbers.get(i)));
            
            i++;
            
            if(t3 < (t1+t2)){
                int temp = t1 + t2;
                int temp2 = temp - t3;
                remark.add("t3 < t1+t2 i.e. " + t3 + " < " + temp);
                remark.add("Catch(i.e. arrived " + temp2 + "min");
                remark.add("before the train)");
                remark.add("");
                prob++;
            }
            else if(t3 == (t1+t2)){
                int temp = t1 + t2;
                int temp2 = t3 - temp;
                remark.add("t3 = t1+t2 i.e. " + t3 + " = " + temp);
                remark.add("Not catch(i.e. arrived exactly ");
                remark.add("when train leaves)");
                remark.add("");
            }
            else{
                int temp = t1 + t2;
                int temp2 = t3 - temp;
                remark.add("t3 > t1+t2 i.e. " + t3 + " > " + temp);
                remark.add("Not catch(i.e. arrived " + temp2 + "min");
                remark.add("after the train)");
                remark.add("");
            }
        }
    }
    
    
    
    public int genTimeOne(float input){
        int value = 0;
        if(input > 0 && input < 0.7 ){
            value = 0;
        }
        if(input > 0.7 && input < 0.9 ){
            value = 5;
        }
        if(input > 0.9 && input < 1 ){
            value = 10;
        }
        return value;
        
    }
    public int genTimethree(float input){
        int value = 0;
        if(input > 0 && input < 0.3 ){
            value = 28;
        }
        if(input > 0.3 && input < 0.7 ){
            value = 30;
        }
        if(input > 0.7 && input < 0.9 ){
            value = 32;
        }
        if(input > 0.9 && input < 1 ){
            value = 34;
        }
        return value;
        
    }
    public int genTimeTwo(float input1, float input2){
        int value = 0;
        double x = Math.round(2 * ( (Math.sqrt(-2*2.303*Math.log10(input1))) * (Math.cos(6.28 * input2))  ) + 30);
        value = (int)x;
        return value;            
    }
    
    
    public String printColTwo(int input){       
        if(timeOne.get(input) == 1001){
            return "";
        }
        else{
            return String.valueOf(timeOne.get(input));
        }
    }
    public String printColThree(int input){
        if(timeTwo.get(input) == 1001){
            return "";
        }
        else{
            return String.valueOf(timeTwo.get(input));
        }
    }
    public String printColFour(int input){
        if(timeThree.get(input) == 1001){
            return "";
        }
        else{
            return String.valueOf(timeThree.get(input));
        }
    }
    public String printColFive(int input){
        return remark.get(input);
    }
    
    public String deskTop = System.getProperty("user.home")+"/Desktop";
    
    private void printDoc() throws IOException {
        FileWriter fw = new FileWriter(new File(deskTop + "\\Train Simulation Table.txt"));
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("n\tRND\t    T1\t     T2\t        T3\t\t   REMARK");
        bw.newLine();
        bw.newLine();
        int n = 1;
        for(int i = 0; i < number ; i++){
            bw.write(n + "\t" + colOne.getCellData(i)+ "\t    " + colTwo.getCellData(i)+ "\t     " +
                    colThree.getCellData(i)+ "\t\t" + colFour.getCellData(i)+ "\t     " + 
                        colFive.getCellData(i));
            bw.newLine();
            bw.newLine();
            n++;
        }
        bw.newLine();
        bw.newLine();
        bw.write("Thus, from the " + number/4 + " iterations, the probability that you"
                    + " caught the train = " + finalNumm);
        
        bw.close();
        fw.close();
    }
    
}
