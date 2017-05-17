package Vos;

import java.util.Date;

public class ConsultaRF9 
{
	private Date fechaInicial;
	
	private Date fechaFinal;
	
	private String agrupado;
	
	private String ordenado1;
	
	private String ordenado2; 
	
	public ConsultaRF9()
	{
		super();
	}
	
	public ConsultaRF9(Date pFecha1, Date pFecha2, String pAgrupado, String pOrdenado1, String pOrdenado2)
	{
		super();
		this.fechaInicial = pFecha1;
		this.fechaFinal = pFecha2;
		this.agrupado = pAgrupado;
		this.ordenado1 = pOrdenado1;
		this.ordenado2 = pOrdenado2;
		
	}
	
	public String getAgrupado() {
		return agrupado;
	}
	
	public Date getFechaFinal() {
		return fechaFinal;
	}
	
	public Date getFechaInicial() {
		return fechaInicial;
	} 
	
	public String getOrdenado1() {
		return ordenado1;
	}
	
	public String getOrdenado2() {
		return ordenado2;
	}

}
