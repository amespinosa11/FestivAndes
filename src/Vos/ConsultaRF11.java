package Vos;

import java.util.Date;

public class ConsultaRF11
{
	private Date fechaInicial;
	
	private Date fechaFinal;
	
	private Integer idLocalidad;
	
	private Integer idCaracteristica;
	
	public ConsultaRF11()
	{
		super();
	}
	
	public ConsultaRF11(Date pFecha1, Date pFecha2, Integer pIdLocalidad, Integer pIdCaracteristica)
	{
		super();
		this.fechaInicial = pFecha1;
		this.fechaFinal = pFecha2;
		this.idLocalidad = idLocalidad;
		this.idCaracteristica = idCaracteristica;
	}
	
	public Date getFechaFinal() {
		return fechaFinal;
	}
	
	public Date getFechaInicial() {
		return fechaInicial;
	}
	
	public Integer getIdCaracteristica() {
		return idCaracteristica;
	}
	
	public Integer getIdLocalidad() {
		return idLocalidad;
	}
	
	
}
