package ru.lesson.miner.gui;

import ru.lesson.miner.Board;
import ru.lesson.miner.Cell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 08.04.2015
 */
public class GUIBoard extends JPanel implements Board {

    public static final int PADDING = 50;

    public Cell[][] cells;

    private boolean real = false;
    private BufferedImage imgBomb;
    private JLabel jlNotice;


    public GUIBoard() {
        jlNotice = new JLabel("Congratulations!");
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        gbc.insets = new Insets(0, 350, 0, 0);
        gbl.setConstraints(jlNotice, gbc);
        jlNotice.setUI(new VerticalLabelUI(false));
        jlNotice.setFont(new Font("Verdana", Font.BOLD, 24));
        jlNotice.setVisible(false);
        this.add(jlNotice);
        this.setVisible(true);
    }


    @Override
    protected void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);
        if (this.cells != null) {
            for (int x = 0; x != cells.length; x++) {
                for (int y = 0; y != cells[0].length; y++) {
                    graphics.setColor(Color.black);
                    cells[x][y].draw(graphics, y, x, real);
                    graphics.drawRect(y * PADDING, x * PADDING, PADDING, PADDING);
                }
            }
        }

    }

    public boolean getClickedCell(int X, int Y) {
        boolean result = true;

        if (this.cells == null) {
            result = false;
        } else if (((X >= this.cells.length) || (Y >= this.cells[0].length)))
            result = false;

        return result;
    }

    public void drawBoard(Cell[][] cells) {
        this.jlNotice.setVisible(false);
        this.real = false;
        this.cells = cells;
        this.repaint();
    }

    public void drawCell(int x, int y) {

        this.repaint();
    }

    public void drawBang() {

        this.real = true;
        this.jlNotice.setText("Failed!");
        this.jlNotice.setVisible(true);
        this.repaint();

    }

    public void drawCongratulate() {
        this.jlNotice.setText("Congratulations!");
        this.real = true;
        this.repaint();
        this.jlNotice.setVisible(true);
    }

    public int getPadding() {
        return PADDING;
    }
}