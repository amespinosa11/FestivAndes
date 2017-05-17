package Vos;

import java.util.ArrayList;

public class Beneficio 
{
	private Integer idBeneficio;
	
	private String descripcion;
	
	private ArrayList<Localidad> localidades;
	
	public Beneficio()
	{
		super();
	}
	
	public Beneficio(Integer pId, String pDescripcion)
	{
		super();
		this.idBeneficio = pId;
		this.descripcion = pDescripcion;
	}
	
	public void setIdBeneficio(Integer idBeneficio) {
		this.idBeneficio = idBeneficio;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setLocalidad(Localidad pLocalidad)
	{
		this.localidades.add(pLocalidad);
	}
	
	public Integer getIdBeneficio() {
		return idBeneficio;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public ArrayList<Localidad> getLocalidades() {
		return localidades;
	}
	
}
