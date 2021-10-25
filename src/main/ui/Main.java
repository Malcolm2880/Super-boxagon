package ui;

import java.io.FileNotFoundException;

//Starts the program
public class Main {
    public static void main(String[] args) {
        try {
            GameApp g = new GameApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }



    }
}
