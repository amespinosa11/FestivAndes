package Vos;

import java.util.ArrayList;

public class Ciudad 
{
	private Integer idCiudad;
	
	private String nombre;
	
	private ArrayList<Sitio> sitios;
	
	public Ciudad()
	{
		super();
	}
	
	public Ciudad(Integer pId, String pNombre)
	{
		super();
		this.idCiudad= pId;
		this.nombre = pNombre;
	}
	
	public void setIdCiudad(Integer idCiudad) {
		this.idCiudad = idCiudad;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setSitio(Sitio pSitio)
	{
		this.sitios.add(pSitio);
	}
	
	public Integer getIdCiudad() {
		return idCiudad;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public ArrayList<Sitio> getSitios() {
		return sitios;
	}
	
}
