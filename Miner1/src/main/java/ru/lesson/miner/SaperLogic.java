package ru.lesson.miner;

public interface SaperLogic {

	void loadBoard(Cell[][] cells);

	boolean shouldBang(int x, int y);

	boolean finish();
    boolean finish(int bomb);

	void suggest(int x, int y, boolean bomb);
}
