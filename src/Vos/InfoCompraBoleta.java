package Vos;

public class InfoCompraBoleta 
{
	private int numBoletas;
	
	private int idFuncion;
	
	private int numLocalidad;
	
	public InfoCompraBoleta()
	{
		super();
	}
	
	public InfoCompraBoleta(int pNumBoletas,int pNomFuncion,int pNomLocalidad)
	{
		this.numBoletas = pNumBoletas;
		this.idFuncion = pNomFuncion;
		this.numLocalidad = pNomLocalidad;
		
	}
	
	
	public int getNumBoletas() {
		return numBoletas;
	}
	
	public int getIdFuncion() {
		return idFuncion;
	}
	
	public int getNomLocalidad() {
		return numLocalidad;
	}
	
	
	public void setNumBoletas(int numBoletas) {
		this.numBoletas = numBoletas;
	}
	
	public void setIdFuncion(int nomFuncion) {
		this.idFuncion = nomFuncion;
	}
	
	public void setNomLocalidad(int nomLocalidad) {
		this.numLocalidad = nomLocalidad;
	}
	

}
