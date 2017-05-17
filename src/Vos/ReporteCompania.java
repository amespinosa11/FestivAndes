package Vos;

public class ReporteCompania 
{
	private Integer idCompania;
	
	private String nombreComapnia;
	
	private String paisOrigen;
	
	private String nombreEspectaculo;
	
	private Integer gananciasTaquilla;
	
	private Integer capacidadTotal;
	
	private Integer capacidadOcupacionActual;
	
	//private Integer numeroCompaniasReporte;
	
	public ReporteCompania()
	{
		super();
	}
	
	public ReporteCompania(Integer pId, String pNombreC, String pPais, String pNomEspec, Integer pGanancias, Integer pCapTotal, Integer pCapActual)
	{
		super();
		this.idCompania =  pId;
		this.nombreComapnia = pNombreC;
		this.paisOrigen = pPais;
		this.nombreEspectaculo = pNomEspec;
		this.gananciasTaquilla = pGanancias;
		this.capacidadTotal = pCapTotal;
		this.capacidadOcupacionActual = pCapActual;
		//this.numeroCompaniasReporte = pNumCo;
	}
	
	public Integer getCapacidadOcupacionActual() {
		return capacidadOcupacionActual;
	}
	
	public Integer getCapacidadTotal() {
		return capacidadTotal;
	}
	
	public Integer getGananciasTaquilla() {
		return gananciasTaquilla;
	}
	
	public Integer getIdCompania() {
		return idCompania;
	}
	public String getNombreComapnia() {
		return nombreComapnia;
	}
	
	public String getNombreEspectaculo() {
		return nombreEspectaculo;
	}
	
	
	public String getPaisOrigen() {
		return paisOrigen;
	}
	
	

}
