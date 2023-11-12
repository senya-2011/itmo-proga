package Exceptions;

public class AirDefenceException extends RuntimeException{
    public AirDefenceException(){
        System.err.println("На одном из вылетов система ПВО вс РОССИИ сбила Карлосана\nприняв за ракету загнивающего запада");
    }
}
