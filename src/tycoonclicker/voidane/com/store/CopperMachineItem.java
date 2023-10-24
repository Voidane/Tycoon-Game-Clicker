package tycoonclicker.voidane.com.store;

import tycoonclicker.voidane.com.Graphics.TycoonGraphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import static tycoonclicker.voidane.com.Graphics.TycoonGraphics.getBoughtMachines;

public class CopperMachineItem extends JComponent {

    public static final int X_BEG = 570;
    public static final int Y_BEG = 770;
    public static final int SIZE = 75;
    public static int COST = 50;

    Image image;

    public CopperMachineItem() {
        // Wherever the user directly downloaded the software.
        String location =  System.getProperty("user.dir");
        location += "\\src\\tycoonclicker\\voidane\\com\\Images\\Copper_Machine.png";
        ImageIcon icon =  new ImageIcon(location);
        image = icon.getImage();
    }

    public Image getImage() {
        return image;
    }

    public static class OnActionClick implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (isEventInBounds(e)) {
                List<Image> list = TycoonGraphics.getBoughtMachines();
                list.add(new CopperMachineItem().getImage());
                TycoonGraphics.setBoughtMachines(list);
                COST += COST / 10;
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        private boolean isEventInBounds(MouseEvent e) {
            // 1850, 100, 50, 50
            int x = e.getX();
            int y = e.getY();

            return (x >= X_BEG && y >= Y_BEG && x <= X_BEG+SIZE && y <= Y_BEG+SIZE);
        }
    }
}
