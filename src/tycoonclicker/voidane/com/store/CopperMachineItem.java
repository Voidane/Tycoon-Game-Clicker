package tycoonclicker.voidane.com.store;

import tycoonclicker.voidane.com.Graphics.TycoonGraphics;
import tycoonclicker.voidane.com.Mechanics.Currency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CopperMachineItem extends JComponent implements MachineItem {

    // The image in the store X
    public static final int X_BEG = 570;
    // The image in the store Y
    public static final int Y_BEG = 770;
    // The size of the image
    public static final int SIZE = 75;
    // Cost for a new copper machine
    public static int COST = 50;
    // The size of the icon in the inventory
    public static int INV_SIZE = 50;


    Image image;

    public CopperMachineItem() {
        // Wherever the user directly downloaded the software.
        String location =  System.getProperty("user.dir");
        location += "\\src\\tycoonclicker\\voidane\\com\\Images\\Copper_Machine.png";
        ImageIcon icon =  new ImageIcon(location);
        image = icon.getImage();
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public boolean haveEnoughMoney() {
        if (Currency.getCurrency() >= COST)
            return true;
        return false;
    }

    @Override
    public void setImagesAndClassInventory() {
        // Draw the lists of machines we bought into our inventory
        List<Image> list = TycoonGraphics.getBoughtMachines();
        list.add(new CopperMachineItem().getImage());
        TycoonGraphics.setBoughtMachines(list);

        // The class that needs to implemented for the getimage interface and all other assets
        TycoonGraphics.machineItems.add(new CopperMachineItem());
    }

    public void calculateCurrency() {
        // New currency total income from machines
        Currency.setCurrencyFromCopper(Currency.getCurrencyFromCopper() + 0.5);

        // Subtract the currency from the player which they just spent.
        Currency.setCurrency(Currency.getCurrency()-COST);

        // Price raises for machine
        COST += COST / 10;
    }

    @Override
    public int getX_BEG() {
        return X_BEG;
    }

    @Override
    public int getY_BEG() {
        return Y_BEG;
    }

    @Override
    public int getIconSize() {
        return SIZE;
    }

    @Override
    public int getCost() {
        return COST;
    }


    public static class OnActionClick extends CopperMachineItem implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            // Is the click inside bounds of the store
            if (isEventInBoundsOfStore(e)) {
                // When player doesn't have enough money
                if (!haveEnoughMoney())
                    return;
                // List of icons and classes that need to fill up the inventory
                setImagesAndClassInventory();
                // Adjust all currency and raise machine price
                calculateCurrency();
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

        private boolean isEventInBoundsOfStore(MouseEvent e) {
            // 1850, 100, 50, 50
            int x = e.getX();
            int y = e.getY();

            return (x >= X_BEG && y >= Y_BEG && x <= X_BEG+SIZE && y <= Y_BEG+SIZE);
        }
    }
}
