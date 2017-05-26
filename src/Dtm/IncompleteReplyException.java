package Dtm;

import Vos.ListaFunciones;

public class IncompleteReplyException extends Exception
{
private static final long serialVersionUID = 1L;
	
	/**
	 * Atributo con las respuestas parciales
	 */
	private ListaFunciones partialResponse;
	
	/**
	 * Método constructor de la clase IncompleteReplyException
	 * <b>post: </b> Crea la  IncompleteReplyException con los valores que entran como parámetro
	 * @param message - mensaje de la IncompleteReplyException
	 * @param partialResponse - respuesta parcial a guardar.
	 */
	
	
	public IncompleteReplyException(String message,ListaFunciones partialResponse){
		super(message);
		this.partialResponse = partialResponse;
	}

	/**
	 * Método que retorna la respuesta parcial
	 * @return ListaVideos - respuesta parcial
	 */
	
	public ListaFunciones getPartialResponse(){
		return this.partialResponse;
	}

}
