package Vos;

import java.util.ArrayList;

public class Preferencia 
{
	private Integer idPreferencia;
	
	private String nombreCategoria;
	
	private String nombreSitio;
	
	private ArrayList<Cliente> clientes;
	
	public Preferencia()
	{
		super();
	}
	
	public Preferencia(Integer pId,String pNombreCat, String pNombreSitio)
	{
		super();
		this.idPreferencia = pId;
		this.nombreCategoria = pNombreCat;
		this.nombreSitio = pNombreSitio;
		
	}
	
	public void setIdPreferencia(Integer idPreferencia) {
		this.idPreferencia = idPreferencia;
	}
	
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	
	public void setNombreSitio(String nombreSitio) {
		this.nombreSitio = nombreSitio;
	}
	
	public void setCliente(Cliente pCliente)
	{
		this.clientes.add(pCliente);
	}
	
	
	public Integer getIdPreferencia() {
		return idPreferencia;
	}
	
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	
	public String getNombreSitio() {
		return nombreSitio;
	}
	
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}
	
	
	
}
