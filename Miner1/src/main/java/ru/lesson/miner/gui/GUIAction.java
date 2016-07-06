package ru.lesson.miner.gui;

import ru.lesson.miner.BaseAction;
import ru.lesson.miner.Board;
import ru.lesson.miner.GeneratorBoard;
import ru.lesson.miner.SaperLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 08.04.2015
 */
public class GUIAction extends BaseAction implements ActionListener, MouseListener {
    private GUIBoard board;
    private int countBombs;

    public GUIAction(SaperLogic logic, GUIBoard board, GeneratorBoard generator) {
        super(logic, board, generator);
        this.board = board;
        this.board.addMouseListener(this);
        this.countBombs = generator.getCountBomb();
    }

    public void actionPerformed(ActionEvent e) {

        this.initGame();
    }

    public void mouseClicked(MouseEvent e) {

        int x = e.getY() / this.board.getPadding();
        int y = e.getX() / this.board.getPadding();

        System.out.println(" Clicked x = " + x + " y=" + y);
        if (board.getClickedCell(x, y)) {
            if (e.getButton() == 1)
                select(x, y, false, countBombs);
            if (e.getButton() == 3)
                select(x, y, true, countBombs);
        }
        this.board.repaint();

    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
