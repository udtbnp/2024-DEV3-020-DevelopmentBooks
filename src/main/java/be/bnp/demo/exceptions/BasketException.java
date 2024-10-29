package be.bnp.demo.exceptions;

public class BasketException extends Exception {
    public BasketException(String message){
		super(message);
	}

    public BasketException(String message, Exception e){
        super(message, e);
    }
}
