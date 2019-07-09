/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainsimulation;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Yuken4real
 */
public class tableFile {
    
    private SimpleStringProperty one, two, three, four, five;

    public tableFile(String one, String two, String three, String four, String five) {
        this.one = new SimpleStringProperty(one);
        this.two = new SimpleStringProperty(two);
        this.three = new SimpleStringProperty(three);
        this.four = new SimpleStringProperty(four);
        this.five = new SimpleStringProperty(five);
    }

    public String getOne() {
        return one.get();
    }

    public void setOne(SimpleStringProperty one) {
        this.one = one;
    }

    public String getTwo() {
        return two.get();
    }

    public void setTwo(SimpleStringProperty two) {
        this.two = two;
    }

    public String getThree() {
        return three.get();
    }

    public void setThree(SimpleStringProperty three) {
        this.three = three;
    }
    
    public String getFour() {
        return four.get();
    }

    public void setFour(SimpleStringProperty four) {
        this.four = four;
    }

    public String getFive() {
        return five.get();
    }
    public void setFive(SimpleStringProperty five) {
        this.five = five;
    }
    
}
