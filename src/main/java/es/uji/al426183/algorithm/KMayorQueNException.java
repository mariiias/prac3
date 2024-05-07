package es.uji.al426183.algorithm;

public class KMayorQueNException extends Exception{
    public KMayorQueNException(int k, int n) {
        super("El número de grupos K (" + k + ") es mayor que el número de datos N (" + n + ").");
    }
}
