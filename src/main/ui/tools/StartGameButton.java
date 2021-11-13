package ui.tools;


import ui.GameApp;
import ui.tools.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;



//This class has been taken and modified from the simple Drawing project

public class StartGameButton extends Tool {



    //EFFECTS: Creates the start game button using parent class

    public StartGameButton(GameApp current, JComponent parent) {
        super(current, parent);
    }


    // MODIFIES: this
    // EFFECTS:  creates a new "Start game button" button and invokes addToParent() on the
    //           parent passed to this method
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("StartGame");
        button = customizeButton(button);
        addToParent(parent);
    }


    // MODIFIES: this
    // EFFECTS:  sets the activeTool in button to this when clicked
    @Override
    protected void addListener() {
        button.addActionListener(new ui.tools.StartGameButton.ButtonToolClickHandler());
    }

    private class ButtonToolClickHandler implements ActionListener {

        //MODIFIES: current
        // EFFECTS: starts the game

        @Override
        public void actionPerformed(ActionEvent e) {
            current.runGame();
        }
    }
}
