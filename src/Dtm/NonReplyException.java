package Dtm;

public class NonReplyException extends Exception
{
private static final long serialVersionUID = 1L;
	
	/**
	 * M�todo constructor de la clase IncompleteReplyException
	 * <b>post: </b> Crea la  NonReplyException con los valores que entran como par�metro
	 * @param message - mensaje de la NonReplyException
	 */
	public NonReplyException(String message){
		super(message);
	}

}
