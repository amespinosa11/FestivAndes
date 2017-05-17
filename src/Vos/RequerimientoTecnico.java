package Vos;

import java.util.ArrayList;

public class RequerimientoTecnico 
{
	private Integer idRequerimiento;
	
	private String descripcion;
	
	private ArrayList<Espectaculo> espectaculos;
	
	public RequerimientoTecnico()
	{
		super();
	}
	
	public RequerimientoTecnico(Integer pId, String pDescripcion)
	{
		super();
		this.idRequerimiento = pId;
		this.descripcion = pDescripcion;
	}
	
	public void setIdRequerimiento(Integer idRequerimiento) {
		this.idRequerimiento = idRequerimiento;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setEspectaculo(Espectaculo pEspectaculo)
	{
		this.espectaculos.add(pEspectaculo);
	}
	
	public Integer getIdRequerimiento() {
		return idRequerimiento;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public ArrayList<Espectaculo> getEspectaculos() {
		return espectaculos;
	}
	
}
