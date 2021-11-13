package ui.tools;


import ui.GameApp;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

// Tool is taken from the "simple drawing player" project
//the code has been modified to remove unneeded methods

public abstract class Tool {

    protected JButton button;
    protected GameApp current;
    private boolean active;

    //EFFECTS: Creates a Tool object that manages the buttons
    public Tool(GameApp current, JComponent parent) {

        this.current = current;
        createButton(parent);
        addToParent(parent);
        active = false;
        addListener();
    }

    // MODIFIES: this
    // EFFECTS:  customizes the button used for this tool
    protected JButton customizeButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        return button;
    }

    // EFFECTS: creates button to activate tool
    protected abstract void createButton(JComponent parent);

    // EFFECTS: adds a listener for this tool
    protected abstract void addListener();

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }


}
