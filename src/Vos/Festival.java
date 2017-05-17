package Vos;

import java.util.Date;

public class Festival 
{
	private Integer idFestival;
	
	private String nombre;
	
	private Date fechaInicio;
	
	private Date fechaFin;
	
	public Festival()
	{
		super();
	}
	
	public Festival(Integer pId, String pNombre, Date pFechaI, Date pFechaF)
	{
		super();
		this.idFestival = pId;
		this.nombre = pNombre;
		this.fechaInicio = pFechaI;
		this.fechaFin = pFechaF;
	}
}
