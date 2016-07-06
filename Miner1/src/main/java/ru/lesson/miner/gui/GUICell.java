package ru.lesson.miner.gui;

import ru.lesson.miner.Cell;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 08.04.2015
 */
public class GUICell implements Cell<Graphics> {

    private boolean bomb = false;
    private boolean suggestBomb = false;
    private boolean suggestEmpty = false;

    private static final int PADDING = 50;
    private static final int OFFSET = 20;

    private BufferedImage imgBomb, imgFlag, imgEmpty;

    public GUICell() {
        this.bomb = false;

        try {
            imgBomb = ImageIO.read(new File("bomb.jpg"));
            imgFlag = ImageIO.read(new File("flag.jpg"));
            imgEmpty = ImageIO.read(new File("empty.jpg"));
        } catch (Exception e) {

        }
    }

    public GUICell(boolean bomb) {
        this.bomb = bomb;
    }


    public boolean isBomb() {
        return bomb;
    }

    public boolean isSuggestBomb() {
        return this.suggestBomb;
    }

    public boolean isSuggestEmpty() {
        return this.suggestEmpty;
    }

    public void suggectEmpty() {
        this.suggestEmpty = true;
        this.suggestBomb=false;

    }

    public void suggectBomb() {
        this.suggestBomb = true;
        this.suggestEmpty = false;
    }

    public void draw(Graphics paint, boolean real) {

    }

    public void draw(Graphics paint, int x, int y, boolean real) {
        int X = x * PADDING;
        int Y = y * PADDING;
        if (real) {
            if (this.isBomb()) {
                paint.drawImage(this.imgBomb, X, Y, null);
            } else {
                //paint.print("[ ] ");
            }
        } else {
            if (this.suggestBomb) {
                paint.drawImage(this.imgFlag, X, Y, null);

              //  paint.drawString("?", X + OFFSET, Y + OFFSET);
                //paint.print("[?] ");
            } else if (this.suggestEmpty) {
                paint.drawImage(this.imgEmpty, X, Y, null);

                //paint.drawString("-", X + OFFSET, Y + OFFSET);
                //paint.print("[ ] ");
            } else {
                //paint.drawString("X", X, Y);
                //paint.print("[Х] ");
            }
        }
    }

    public void setBomb() {
        this.bomb = true;
    }
}
