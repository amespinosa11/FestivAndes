package Vos;

import java.util.ArrayList;

public class Categoria
{
	private Integer idCategoria;
	
	private String nombre;
	
	private ArrayList<Espectaculo> espectaculos;
	
	public Categoria()
	{
		super();
	}
	
	public Categoria(Integer pId, String pNombre)
	{
		super();
		this.idCategoria = pId;
		this.nombre = pNombre;
	}
	
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getIdCategoria() {
		return idCategoria;
	}
	
	public void setEspectaculo(Espectaculo pEspectaculo)
	{
		espectaculos.add(pEspectaculo);
	}
	public String getNombre() {
		return nombre;
	}
	
	public ArrayList<Espectaculo> getEspectaculos() {
		return espectaculos;
	}
	
}
