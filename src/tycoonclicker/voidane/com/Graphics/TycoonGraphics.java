package tycoonclicker.voidane.com.Graphics;

import tycoonclicker.voidane.com.Inventory.Inventory;
import tycoonclicker.voidane.com.JFrame.TycoonFrame;
import tycoonclicker.voidane.com.Mechanics.Currency;
import tycoonclicker.voidane.com.Mechanics.FactoryClicker;
import tycoonclicker.voidane.com.store.CopperMachineItem;
import tycoonclicker.voidane.com.store.MachineItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


/**
 * Does all the drawing graphics for the game. Takes into account from other classes
 * and repaints the methods in order to keep the game updated.
 */
public class TycoonGraphics extends JPanel implements ActionListener {

    private static final int PLOT_SIZE = 50;

    // 0.1 of a second
    private static final int DELAY =  25;
    private static final int MAX_WIDTH_GAME_PANEL = 550;
    private static final int INVENTORY_BOARDER_Y = 750;
    private static final int DELAY_DIVIDER = 80;

    private static List<Image> boughtMachines = new ArrayList<>();
    private boolean firstTimeRunning = true;

    public static List<MachineItem> machineItems = new ArrayList<>();
    private ImageIcon icon;

    Timer timer;

    public TycoonGraphics() {
        this.setPreferredSize(new Dimension(TycoonFrame.FRAME_WIDTH, TycoonFrame.FRAME_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new KeyRegister());
        this.addMouseListener(new CopperMachineItem.OnActionClick());
        this.addMouseListener(new FactoryClicker());
        begin();
    }

    /**
     * Launches the game
     */
    private void begin() {
        timer = new Timer(DELAY, this);
        timer.start();
    }

    /**
     * Updates on every timer created with awt and this
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        double currencyGain = Currency.getCurrency();
        currencyGain = (currencyGain + (Currency.getCurrencyFromCopper()/DELAY_DIVIDER));
        Currency.setCurrency(currencyGain);
        repaint();
    }

    /**
     * Use to draw the frame graphics
     * <p>
     * Note - Runs at the start of the program
     * @param g the <code>Graphics</code> object to protect
     * <p>
     * @see        javax.swing.JComponent#paintComponent(Graphics)
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLeftPanelGame(g);
        drawBottomPaneStore(g);
        drawPanelDisplayStoreText(g);
        createCopperMachineForStore(g);
        addNewlyBoughtMachine(g);
        drawCurrency(g);
        drawFactoryClicker(g);
        drawCurrencyPerSecond(g);
        drawInstructions(g);
    }

    /**
     * Draw the left side panel of where gameplay will occur
     * <p>
     * The grid layout by plot size is 11 X 21
     * @param g The protected graphics from JComponent
     */
    private void drawLeftPanelGame(Graphics g) {
        int x = MAX_WIDTH_GAME_PANEL;
        int y = TycoonFrame.FRAME_HEIGHT;

        // Background
        g.setColor(new Color(99, 96, 96));
        g.fillRect(0,0,x,y);

        // Grid layout 11x21
        g.setColor(Color.WHITE);
        for ( int i = 0 ; i < (y / PLOT_SIZE)+1 ; i++ )
            g.drawLine(0, (PLOT_SIZE * i), x, (PLOT_SIZE * i));
        for ( int i = 0 ; i < (x / PLOT_SIZE)+1 ; i++ )
            g.drawLine((i * PLOT_SIZE) , 0, (i * PLOT_SIZE), y);
    }

    private void drawBottomPaneStore(Graphics g) {
        int xBeg = MAX_WIDTH_GAME_PANEL;
        int yBeg = INVENTORY_BOARDER_Y;
        int xEnd = TycoonFrame.FRAME_WIDTH;
        int yEnd = TycoonFrame.FRAME_HEIGHT;
        int offsetBoarder = 10;
        g.setColor(Color.WHITE);
        g.fillRect(xBeg, yBeg, xEnd, yEnd);
        g.setColor(new Color(155,155,155));
        g.fillRect(xBeg + offsetBoarder, yBeg + offsetBoarder,
                ((TycoonFrame.FRAME_WIDTH - MAX_WIDTH_GAME_PANEL) - offsetBoarder*2), yBeg + offsetBoarder);
    }

    private void drawPanelDisplayStoreText(Graphics g) {
        int xBeg = MAX_WIDTH_GAME_PANEL;
        int yBeg = 700;
        int xEnd = 200;
        int yEnd = 50;
        g.setColor(Color.WHITE);
        g.fillRect(xBeg, yBeg, xEnd, yEnd);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Ink Free", Font.BOLD, 25));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("STORE", (TycoonFrame.FRAME_WIDTH - MAX_WIDTH_GAME_PANEL)-1515 / 2, yBeg+35);
    }

    private void createCopperMachineForStore(Graphics g) {
        CopperMachineItem machine = new CopperMachineItem();
        g.drawImage(machine.getImage(), MAX_WIDTH_GAME_PANEL + 20, INVENTORY_BOARDER_Y + 20,
                75, 75, null);
        g.setFont(new Font("Default", Font.BOLD, 15));
        g.drawString("Copper Machine", MAX_WIDTH_GAME_PANEL + 100,
                INVENTORY_BOARDER_Y + 20 + 20);
        g.drawString("Cost: " + CopperMachineItem.COST , MAX_WIDTH_GAME_PANEL + 100,
                INVENTORY_BOARDER_Y + 40  + 25);
    }

    /**
     * Sets up our inventory with the correct corresponding machines.
     */
    private void addNewlyBoughtMachine(Graphics g) {

        int yHeight = 0;
        int xWidth = 0;

        for ( int i = 0 ; i < boughtMachines.size() ; i++ ) {

            MachineItem item = machineItems.get(i);
            
            if (i % 11 == 0 && i != 0) {
                yHeight += PLOT_SIZE;
                xWidth = 0;
            }
            
            // Draw the image at the next open inventory slot
            g.drawImage(item.getImage(), xWidth, yHeight,
                    PLOT_SIZE, PLOT_SIZE, null);

            Inventory.addNewInventoryItem(xWidth/PLOT_SIZE, yHeight/PLOT_SIZE, xWidth, yHeight, item);

            xWidth += PLOT_SIZE;
        }
        
    }

    private void drawCurrency(Graphics g) {
        double currency = Currency.getCurrency();
        String formatCurrency = String.format("%,.2f", currency);
        g.setFont(new Font("Default", Font.BOLD, 25));
        g.setColor(Color.GREEN);
        g.drawString("Currency: " + formatCurrency,MAX_WIDTH_GAME_PANEL + 10, 30);
    }

    private void drawFactoryClicker(Graphics g) {
        FactoryClicker factoryClicker = new FactoryClicker();
        g.drawImage(factoryClicker.getImage(),
                (TycoonFrame.FRAME_WIDTH/2)-150 + (MAX_WIDTH_GAME_PANEL/2),
                250, 300,300,null);
    }

    private void drawCurrencyPerSecond(Graphics g) {
        g.setColor(Color.CYAN);
        double x1 = Currency.getCurrencyFromCopper();
        g.setFont(new Font("Default", Font.BOLD, 20));
        g.drawString("Currency Per Hour: " + x1,MAX_WIDTH_GAME_PANEL + 10, 60);
    }

    private void drawInstructions(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Default", Font.BOLD, 15));
        g.drawString("Instructions: ", 1650,80);
        g.drawString("Click factory to gain money", 1650,100);
        g.drawString("Buy Items from store", 1650,120);
    }

    public static List<Image> getBoughtMachines() {
        return boughtMachines;
    }

    public static void setBoughtMachines(List<Image> boughtMachines) {
        TycoonGraphics.boughtMachines = boughtMachines;
    }


    public class KeyRegister extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

        }

    }
}
