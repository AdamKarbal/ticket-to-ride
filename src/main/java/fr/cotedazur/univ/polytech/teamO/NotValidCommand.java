package fr.cotedazur.univ.polytech.teamO;

public class NotValidCommand extends RuntimeException {
    public NotValidCommand(String message) {
        super(message);
    }
}
