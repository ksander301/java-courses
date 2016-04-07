package clinic.entity;

public interface Action {
    String operation ();

     void execute(Clinic clinic, Input input, Output output);
}
