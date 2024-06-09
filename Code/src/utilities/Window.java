package utilities;

import javax.swing.*;
import java.awt.*;

// Class that controls the game window
// Links to the GUI class, for anything inside the window see that class

public class Window extends JFrame {
    public Component gui;

    public Window(Component gui) {
        super("Street Fighter");
        this.gui = gui;

        getContentPane().add(BorderLayout.CENTER, gui);
        pack();

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        repaint();
    }
}
