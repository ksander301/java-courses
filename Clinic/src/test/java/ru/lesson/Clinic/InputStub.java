package ru.lesson.Clinic;

import java.util.Iterator;

public class InputStub implements Input {
    private final Iterator<String> answers;
    private final Output output;

    public InputStub(Iterator<String> answers, Output output) {
        this.answers = answers;
        this.output = output;
    }

    @Override
    public String next() {
        return answers.next();
    }

    @Override
    public String ask(String question) {
        this.output.Println(question);
        return this.answers.next();
    }

    @Override
    public int getValidNumber(String question, int minNumber, int maxNumber) {
        return Integer.valueOf(this.answers.next());
    }

    @Override
    public void close() {

    }
}
