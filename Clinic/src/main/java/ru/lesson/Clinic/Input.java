package ru.lesson.Clinic;

public interface Input {
    String next();

    String ask(String question);

    int getValidNumber(String question, int minNumber, int maxNumber);
    void close();
}
