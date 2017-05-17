package Vos;

public class Boleta 
{
	private Integer idBoleta;
	
	private Double costo;
	
	private Integer idSilla;
	
	private Integer idCliente;
	
	//private String registrado;
	
	private Integer numFila;
	
	private Integer idFuncion;
	
	private Integer idLocalidad;
	
	private String tipo;
	
	public Boleta()
	{
		super();
	}
	
	public Boleta(Integer pId, Double pCosto,Integer pIdSilla, String pTipo,Integer pIdCliente,Integer pNumFila,Integer pIdF, Integer pIdLocalidad)
	{
		super();
		this.idBoleta = pId;
		this.costo = pCosto;
		this.idSilla = pIdSilla;
		this.idCliente = pIdCliente;
		//this.registrado = pRegistrado;
		this.numFila = pNumFila;
		this.idFuncion = pIdF;
		this.idLocalidad = pIdLocalidad;
		this.tipo = pTipo;
	}
	
	public void setIdBoleta(Integer idBoleta) {
		this.idBoleta = idBoleta;
	}
	
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	
	public void setSilla(Integer pSilla)
	{
		this.idSilla = pSilla;
	}
	
	public void setFuncion(Integer funcion) {
		this.idFuncion = funcion;
	}
	
	public void setCliente(Integer cliente) {
		this.idCliente = cliente;
	}
	
	
	
	public Integer getIdBoleta() {
		return idBoleta;
	}
	
	public Double getCosto() {
		return costo;
	}
	
	
	public Integer getIdFuncion() {
		return idFuncion;
	}
	
	public void setIdLocalidad(Integer idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	
	public Integer getIdLocalidad() {
		return idLocalidad;
	}
	
	public Integer getIdCliente() {
		return idCliente;
	}
	
	public Integer getIdSilla() {
		return idSilla;
	}
	
	public void setNumFila(Integer numFila) {
		this.numFila = numFila;
	}
	
	public Integer getNumFila() {
		return numFila;
	}
	
	
}
