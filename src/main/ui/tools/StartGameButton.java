package ui.tools;


import ui.GameApp;
import ui.tools.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

public class StartGameButton extends Tool {

    public StartGameButton(GameApp current, JComponent parent) throws FileNotFoundException {
        super(current, parent);
    }


    // MODIFIES: this
    // EFFECTS:  creates a new "Play Shape" button and invokes addToParent() on the
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

        // EFFECTS: gets the top score
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame game = new JFrame();
            game.setLayout(new BorderLayout());
            game.setMinimumSize(new Dimension(300, 300));




            game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            game.setLocationRelativeTo(null);
            game.setVisible(true);
        }
    }
}
