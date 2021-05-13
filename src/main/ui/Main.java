package ui;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DietGUI start = new DietGUI();
                    //Display the window.
                    start.frame.pack();
                    start.frame.setVisible(true);
                    start.frame.validate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}