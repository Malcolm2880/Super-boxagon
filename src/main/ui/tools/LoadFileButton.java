package ui.tools;




import ui.GameApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

public class LoadFileButton extends Tool {

    public LoadFileButton(GameApp current, JComponent parent) throws FileNotFoundException {
        super(current, parent);
    }


    // MODIFIES: this
    // EFFECTS:  creates a new "Play Shape" button and invokes addToParent() on the
    //           parent passed to this method
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Load Previous Leaderboard");
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

        // EFFECTS: gets the top score
        @Override
        public void actionPerformed(ActionEvent e) {
            current.loadGameApp();

        }
    }

}
