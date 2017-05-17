package Vos;

import java.util.Date;

public class DevolverRF11
{
	private String nombreEspectaculo;
	
	private Date fecha;
	
	private Integer idSitio;
	
	private Integer cantidadBoletas;
	
	public DevolverRF11()
	{
		super();
	}
	
	public DevolverRF11(String pNombre, Date pFecha, Integer pIdS, Integer pCantidad)
	{
		super();
		this.nombreEspectaculo = pNombre;
		this.fecha = pFecha;
		this.idSitio = pIdS;
		this.cantidadBoletas = pCantidad;
		
	}
	
	public Integer getCantidadBoletas() {
		return cantidadBoletas;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public Integer getIdSitio() {
		return idSitio;
	}
	
	public String getNombreEspectaculo() {
		return nombreEspectaculo;
	}

}
