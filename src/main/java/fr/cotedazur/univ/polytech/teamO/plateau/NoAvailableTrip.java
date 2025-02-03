package fr.cotedazur.univ.polytech.teamO.plateau;

public class NoAvailableTrip extends RuntimeException {
    public NoAvailableTrip(String message) {
        super(message);
    }
}
