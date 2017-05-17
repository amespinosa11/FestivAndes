package Vos;

public class MensajeDevolucion 
{
	private String mensaje;
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public MensajeDevolucion()
	{
		super();
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public MensajeDevolucion(String pM)
	{
		super();
		this.mensaje = pM;
	}


}
