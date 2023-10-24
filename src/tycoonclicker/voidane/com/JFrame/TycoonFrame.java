package tycoonclicker.voidane.com.JFrame;

import tycoonclicker.voidane.com.Graphics.TycoonGraphics;
import tycoonclicker.voidane.com.store.CopperMachineItem;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class TycoonFrame extends JFrame {

    // The width of the frame
    public static final int FRAME_WIDTH = 1920;

    // The height of the frame
    public static final int FRAME_HEIGHT = 1080;

    JLabel copperMachiner;

    public TycoonFrame() {
        createFrame();
        new TycoonGraphics();
        this.setVisible(true);

        // Icon
        CopperMachineItem i = new CopperMachineItem();
        this.setIconImage(i.getImage());
    }

    private void createFrame() {
        this.add(new TycoonGraphics());
        this.setTitle("Factory Tycoon Clicker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
