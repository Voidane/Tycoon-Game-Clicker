package tycoonclicker.voidane.com.Graphics;

import tycoonclicker.voidane.com.JFrame.TycoonFrame;
import tycoonclicker.voidane.com.store.CopperMachineItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class TycoonGraphics extends JPanel implements ActionListener {

    private static final int PLOT_SIZE = 50;
    private static final int DELAY =  100;
    private static final int MAX_WIDTH_GAME_PANEL = 550;
    private static final int INVENTORY_BOARDER_Y = 750;

    private static List<Image> boughtMachines = new ArrayList<>();
    private boolean firstTimeRunning = true;

    private ImageIcon icon;

    Timer timer;

    public TycoonGraphics() {
        this.setPreferredSize(new Dimension(TycoonFrame.FRAME_WIDTH, TycoonFrame.FRAME_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new KeyRegister());
        this.addMouseListener(new CopperMachineItem.OnActionClick());
        begin();
        System.out.println("Created");
    }

    /**
     * Launches the game
     */
    private void begin() {
        timer = new Timer(100, this);
        timer.start();
    }

    /**
     * Updates on every timer created with awt and this
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
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
        g.setFont(new Font("Default", Font.BOLD, 20));
        g.drawString("Copper Machine", MAX_WIDTH_GAME_PANEL + 20,
                INVENTORY_BOARDER_Y + 20 + 100);
        g.drawString("Cost: " + CopperMachineItem.COST , MAX_WIDTH_GAME_PANEL + 20,
                INVENTORY_BOARDER_Y + 20 + 100 + 25);
    }

    private void addNewlyBoughtMachine(Graphics g) {

        int yHeight = 0;
        int xWidth = 0;
        CopperMachineItem machine = new CopperMachineItem();

        for ( int i = 0 ; i < boughtMachines.size() ; i++ ) {

            if (i % 11 == 0 && i != 0) {
                yHeight += PLOT_SIZE;
                xWidth = 0;
            }
            g.drawImage(machine.getImage(), xWidth, yHeight,
                    PLOT_SIZE, PLOT_SIZE, null);
            xWidth += PLOT_SIZE;
        }
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
