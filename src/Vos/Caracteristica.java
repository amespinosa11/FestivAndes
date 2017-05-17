package Vos;

import java.util.ArrayList;

public class Caracteristica 
{
	private Integer idCaracteristica;
	
	private String descripcion;
	
	private ArrayList<Sitio> sitios;
	
	public Caracteristica()
	{
		super();
	}
	
	public Caracteristica(Integer pId, String pDescripcion)
	{
		super();
		this.idCaracteristica = pId;
		this.descripcion = pDescripcion;
	}
	
	public void setIdCaracteristica(Integer idCaracteristica) {
		this.idCaracteristica = idCaracteristica;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setSitio(Sitio pSitio)
	{
		this.sitios.add(pSitio);
	}
	
	public Integer getIdCaracteristica() {
		return idCaracteristica;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public ArrayList<Sitio> getSitios() {
		return sitios;
	}
	
}
