package Vos;

public class Silla 
{	
	private Integer idSilla;
	
	private Integer numeroSilla;
	
	private Integer numeroFila;
	
	private Localidad localidad;
	
	private String estado;
	
	private Boleta boleta;
	
	public Silla()
	{
		super();
	}
	
	public Silla(Integer pId,Integer pNumeroSilla, Integer pNumeroFila,String pEstado)
	{
		super();
		this.idSilla = pId;
		this.numeroSilla = pNumeroSilla;
		this.numeroFila = pNumeroFila;
		this.estado = pEstado;
	}
	
	public void setIdSilla(Integer idSilla) {
		this.idSilla = idSilla;
	}
	
	public Integer getIdSilla() {
		return idSilla;
	}
	
	public void setNumeroSilla(Integer numeroSilla) {
		this.numeroSilla = numeroSilla;
	}
	
	public void setNumeroFila(Integer numeroFila) {
		this.numeroFila = numeroFila;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public void setBoleta(Boleta boleta) {
		this.boleta = boleta;
	}
	
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	
	public Integer getNumeroSilla() {
		return numeroSilla;
	}
	
	public Integer getNumeroFila() {
		return numeroFila;
	}
	
	public Localidad getLocalidad() {
		return localidad;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public Boleta getBoleta() {
		return boleta;
	}
	

}
