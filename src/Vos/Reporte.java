package Vos;

public class Reporte 
{
	private Integer cantidadBoletasVendidas;
	
	private Double producido;
	
	//private String nombreLocalidad;
	
	//private String tipoCliente;
	
	public Reporte() 
	{
		super();
	}
	
	public Reporte(Integer pCantidad, Double pProdu)
	{
		super();
		this.cantidadBoletasVendidas = pCantidad;
		this.producido = pProdu;
		//this.nombreLocalidad = pNombre;
		//this.tipoCliente = pTipo;
	}
	
	
	
	
	public void setCantidadBoletasVendidas(Integer cantidadBoletasVendidas) {
		this.cantidadBoletasVendidas = cantidadBoletasVendidas;
	}
	
	public void setProducido(Double producido) {
		this.producido = producido;
	}
	
	public Integer getCantidadBoletasVendidas() {
		return cantidadBoletasVendidas;
	}
	
	public Double getProducido() {
		return producido;
	}
	
}
