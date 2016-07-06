package ru.lesson.miner.logics;

import ru.lesson.miner.Cell;
import ru.lesson.miner.SaperLogic;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 08.04.2015
 */
public class Easy implements SaperLogic {
    private Cell[][] cells;


    public void loadBoard(Cell[][] cells) {
        this.cells = cells;
    }

    public boolean shouldBang(int x, int y) {
        final Cell selected = this.cells[x][y];
        return selected.isBomb() && !selected.isSuggestBomb();
    }


    public boolean finish() {
        boolean finish = false;
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                finish = ((cell.isSuggestBomb() && cell.isBomb()) ||
                        (cell.isSuggestEmpty() && !cell.isBomb()));
            }
        }
        return finish;
    }


    public boolean finish(int bombs) {
        boolean finish = false;
        int countBombs = 0, countErr = 0;

        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if((cell.isSuggestBomb() && cell.isBomb()))
                    countBombs++;

                if((cell.isSuggestBomb() && !cell.isBomb()))
                    countErr++;
            }
        }
        if(countBombs==bombs && countErr==0)
            finish=true;
        return  finish;
    }


    public void suggest(int x, int y, boolean bomb) {
        if (bomb) {
            this.cells[x][y].suggectBomb();
        } else {
            this.cells[x][y].suggectEmpty();
        }
    }
}
