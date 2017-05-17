package Vos;

import java.util.ArrayList;
import java.util.Date;

public class Funcion 
{
	private Integer idFuncion;
	
	private Date fecha;
	
	private Integer costo;
	
	private String estado;
	
	//private String realizacion;
	
	private Espectaculo espectaculo;
	
	//private ArrayList<Boleta> boletas;
	
	private Sitio sitio;
	
	private String est;
	
	private Integer idCli;
	
	public Funcion()
	{
		super();
	}
	
	public Funcion(Integer pId, Date pFecha, Integer pCosto,String pEstado)
	{
		super();
		this.idFuncion = pId;
		this.fecha = pFecha;
		this.costo = pCosto;
		this.estado = pEstado;
	}
	
	public Funcion(Integer pId, Date pFecha, Integer pCosto, String pEstado, String pEs, Integer idCliente)
	{
		super();
		this.idFuncion = pId;
		this.fecha = pFecha;
		this.costo = pCosto;
		this.estado = pEstado;
		this.est = pEs;
		this.idCli = idCliente;
	}
	
	public void setIdFuncion(Integer idFuncion) {
		this.idFuncion = idFuncion;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public void setCosto(Integer costo) {
		this.costo = costo;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	
	public void setEspectaculo(Espectaculo espectaculo) {
		this.espectaculo = espectaculo;
	}
	
	
	
	public void setSitio(Sitio sitio) {
		this.sitio = sitio;
	}
	
	public Integer getIdFuncion() {
		return idFuncion;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public Integer getCosto() {
		return costo;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public Espectaculo getEspectaculo() {
		return espectaculo;
	}
	
	public Sitio getSitio() {
		return sitio;
	}
	
	
	
	
}
