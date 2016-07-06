package ru.lesson.miner;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 08.04.2015
 */
public interface GeneratorBoard {
    Cell[][] generate();

    int getCountBomb();
}
