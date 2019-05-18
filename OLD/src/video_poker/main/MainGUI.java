package video_poker.main;

import javax.swing.*;

public class MainGUI {

    public static void main(String[] args) {
        GUI gui = new GUI();
        JFrame frame = new JFrame();
        frame.setSize(660, 300);
        frame.add(gui.$$$getRootComponent$$$());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        gui.update();
    }

}
