package Vos;

import java.util.Date;

public class ReporteAsistencia 
{
private Integer idFuncion;
	
	private Date fecha;
	
	private Integer costo;
	
	private String estado;
	
	private String est;
	
	private Integer idCli;
	
	public ReporteAsistencia()
	{
		super();
	}
	
	public ReporteAsistencia(Integer pId, Date pFecha, Integer pCosto, String pEstado, String pEs, Integer idCliente)
	{
		super();
		this.idFuncion = pId;
		this.fecha = pFecha;
		this.costo = pCosto;
		this.estado = pEstado;
		this.est = pEs;
		this.idCli = idCliente;
	}
	
	public Integer getCosto() {
		return costo;
	}
	
	public String getEst() {
		return est;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public Integer getIdCli() {
		return idCli;
	}
	
	public Integer getIdFuncion() {
		return idFuncion;
	}
	

}
