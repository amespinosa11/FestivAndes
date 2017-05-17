package Vos;

import java.util.ArrayList;

public class InfoCompraAbonamiento 
{
	private ArrayList<Integer> idsFunciones;
	
	private Integer idLocalidad;
	
	public InfoCompraAbonamiento()
	{
		super();
	}
	
	public InfoCompraAbonamiento(ArrayList<Integer> pIds, Integer pIdLoca)
	{
		super();
		this.idsFunciones = pIds;
		this.idLocalidad = pIdLoca;
	}
	
	public Integer getIdLocalidad() {
		return idLocalidad;
	}
	
	public ArrayList<Integer> getIdsFunciones() {
		return idsFunciones;
	}
	
}
