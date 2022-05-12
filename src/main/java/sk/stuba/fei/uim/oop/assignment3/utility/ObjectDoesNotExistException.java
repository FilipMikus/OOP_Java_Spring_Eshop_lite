package sk.stuba.fei.uim.oop.assignment3.utility;

public class ObjectDoesNotExistException extends Exception {
    public ObjectDoesNotExistException() {
        super("Object does not exist");
    }
}
