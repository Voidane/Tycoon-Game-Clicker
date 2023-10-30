package tycoonclicker.voidane.com.store;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface MachineItem {

    Image getImage();

    boolean haveEnoughMoney();

    void setImagesAndClassInventory();

    void calculateCurrency();

    int getX_BEG();

    int getY_BEG();

    int getIconSize();

    int getCost();

    int[] getXInv();

    void setXInv(int x, int loc);

    int[] getYInv();

    void setYInv(int y, int loc);

}
