package Vos;

import java.util.Date;

public class ConsultaRFC5 
{
	private Date fechaInicial;
	
	private Date fechaFinal;
	
	public ConsultaRFC5()
	{
		super();
	}
	
	public ConsultaRFC5(Date pFechaInicial, Date pFechaFinal)
	{
		super();
		this.fechaInicial= pFechaInicial;
		this.fechaFinal = pFechaFinal;
	}
	
	public Date getFechaInicial() {
		return fechaInicial;
	}
	
	public Date getFechaFinal() {
		return fechaFinal;
	}

}
