package ui.tools;


import ui.GameApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;



//This class has been taken and modified from the simple Drawing project
public class AddScoreButton extends Tool {


    //EFFECTS: Creates the add score button using parent class
    public AddScoreButton(GameApp current, JComponent parent)  {
        super(current, parent);
    }


    // MODIFIES: this
    // EFFECTS:  creates a new "Add Score" button and invokes addToParent() on the
    //           parent passed to this method
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Add Score");
        button = customizeButton(button);
        addToParent(parent);
    }


    // MODIFIES: this
    // EFFECTS:  sets the activeTool in button to this when clicked
    @Override
    protected void addListener() {
        button.addActionListener(new ButtonToolClickHandler());
    }

    private class ButtonToolClickHandler implements ActionListener {

        //MODIFIES: current
        // EFFECTS: gets the top score on click
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = current.createName();
            int points = current.createPoints();
            current.createScore(name, points);

        }
    }
}