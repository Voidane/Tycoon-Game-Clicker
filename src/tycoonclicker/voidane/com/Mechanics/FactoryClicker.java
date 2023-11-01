package tycoonclicker.voidane.com.Mechanics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FactoryClicker implements MouseListener {

    public static final int X_BEG = 1085;
    public static final int Y_BEG = 250;
    public static final int SIZE = 300;

    Image image;

    public FactoryClicker() {
        // Wherever the user directly downloaded the software.
        String location =  System.getProperty("user.dir");
        location += "\\src\\tycoonclicker\\voidane\\com\\Images\\FactoryImage.png";
        ImageIcon icon =  new ImageIcon(location);
        image = icon.getImage();
    }

    public Image getImage() {
        return this.image;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (IsInBoundClick(e)) {

            double curr = Currency.getCurrency();
            curr += Currency.getCurrencyPerClick();
            Currency.setCurrency(curr);
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

    private boolean IsInBoundClick(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        return (x >= X_BEG && y >= Y_BEG && x <= X_BEG+SIZE && y <= Y_BEG+SIZE);
    }
}
