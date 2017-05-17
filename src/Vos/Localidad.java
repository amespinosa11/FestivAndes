package Vos;

import java.util.ArrayList;

public class Localidad 
{
	private Integer idLocalidad;
	
	private String tipo;
	
	private Integer capacidad;
	
	private ArrayList<Beneficio> beneficios;
	
	private ArrayList<Silla> sillas;
	
	private ArrayList<Sitio> sitios;
	
	public Localidad()
	{
		super();
	}
	
	public Localidad(Integer pId, String pTipo, Integer pCapacidad)
	{
		super();
		this.idLocalidad = pId;
		this.tipo = pTipo;
		this.capacidad = pCapacidad;
	}
	
	public void setIdLocalidad(Integer idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}
	
	public void setSilla(Silla pSilla)
	{
		this.sillas.add(pSilla);
	}
	
	public void setBenedicio(Beneficio pBeneficio)
	{
		this.beneficios.add(pBeneficio);
	}
	
	public void setSitio(Sitio pSitio)
	{
		this.sitios.add(pSitio);
	}
	
	public Integer getIdLocalidad() {
		return idLocalidad;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public Integer getCapacidad() {
		return capacidad;
	}
	
	public ArrayList<Beneficio> getBeneficios() {
		return beneficios;
	}
	
	public ArrayList<Silla> getSillas() {
		return sillas;
	}
	
	public ArrayList<Sitio> getSitios() {
		return sitios;
	}
	
	
}
