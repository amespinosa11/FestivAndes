package Vos;

import java.util.ArrayList;

public class TipoPersona
{	
	private Integer idTipoPersona;
	
	private String descripcion;
	
	private ArrayList<Sitio> sitios;
	
	public TipoPersona()
	{
		super();
	}
	
	public TipoPersona(Integer pId, String pDescripcion)
	{
		super();
		this.idTipoPersona = pId;
		this.descripcion = pDescripcion;
	}
	
	public void setIdTipoPersona(Integer idTipoPersona) {
		this.idTipoPersona = idTipoPersona;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setSitio(Sitio pSitio)
	{
		this.sitios.add(pSitio);
	}
	
	public Integer getIdTipoPersona() {
		return idTipoPersona;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public ArrayList<Sitio> getSitios() {
		return sitios;
	}
	

}
