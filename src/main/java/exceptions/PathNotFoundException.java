package exceptions;

public class PathNotException extends RuntimeException{

    public PathNotException(){
        super("Path annotation not found on page class");
    }
}
