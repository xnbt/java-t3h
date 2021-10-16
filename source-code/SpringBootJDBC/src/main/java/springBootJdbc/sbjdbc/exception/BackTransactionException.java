package springBootJdbc.sbjdbc.exception;

public class BackTransactionException extends Exception{
	private static final long serialVersionUID = -3128681006635769411L;
	
	public BackTransactionException(String message) {
		super(message);
	}
}
