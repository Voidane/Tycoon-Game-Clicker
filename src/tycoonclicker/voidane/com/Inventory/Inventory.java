package tycoonclicker.voidane.com.Inventory;

import tycoonclicker.voidane.com.store.MachineItem;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Inventory {

    private static final int[][] xCoordinateInventory = new int[11][21];
    private static final int[][] yCoordinateInventory = new int[11][21];
    private static final MachineItem[][] machine = new MachineItem[11][21];
    private static int size;

    public Inventory() {

    }

    public static void addNewInventoryItem(int xLocation, int yLocation, int pixelX, int pixelY, MachineItem itemType) {
        System.out.println("int[" + xLocation + "][" + yLocation + "] = " + pixelX);
        System.out.println("int[" + xLocation + "][" + yLocation + "] = " + pixelY);
        xCoordinateInventory[xLocation][yLocation] = pixelX;
        yCoordinateInventory[xLocation][yLocation] = pixelY;
        machine[xLocation][yLocation] = itemType;
        size++;
    }

    public int getSize() {
        return size;
    }

    public static int[][] getxCoordinateInventory() {
        return xCoordinateInventory;
    }

    public static int[][] getyCoordinateInventory() {
        return yCoordinateInventory;
    }

    public static MachineItem[][] getMachineInventory() {
        return machine;
    }

    public class MouseAction extends Inventory implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

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

        private boolean checkIsInBound() {
            return false;
        }

    }

}
