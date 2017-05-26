package Dtm;

public class NonReplyException extends Exception
{
private static final long serialVersionUID = 1L;
	
	/**
	 * Método constructor de la clase IncompleteReplyException
	 * <b>post: </b> Crea la  NonReplyException con los valores que entran como parámetro
	 * @param message - mensaje de la NonReplyException
	 */
	public NonReplyException(String message){
		super(message);
	}

}
