package Vos;

public class ReporteRFC5 
{

	private Integer idSitio;
	
	private Integer idEspectaculo;
	
	private String categoria;
	
	private Integer numBoletasVendidas;
	
	private Integer cantAsistentes;
	
	private Integer proporcionAsistencia;
	
	private Integer valorTotalFacturado;
	
	public ReporteRFC5()
	{
		super();
	}
	
	public ReporteRFC5(Integer pIdSitio, Integer pIdEspectaculo, String pCategoria,
			Integer pNumBolVend, Integer pCnatAsistentes, Integer pProporcion,
			Integer pValorTotal)
	{
		super();
		this.idSitio = pIdSitio;
		this.idEspectaculo = pIdEspectaculo;
		this.categoria = pCategoria;
		this.numBoletasVendidas = pNumBolVend;
		this.cantAsistentes = pCnatAsistentes;
		this.proporcionAsistencia = pProporcion;
		this.valorTotalFacturado = pValorTotal;
	}
	
	public Integer getCantAsistentes() {
		return cantAsistentes;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public Integer getIdEspectaculo() {
		return idEspectaculo;
	}
	
	public Integer getIdSitio() {
		return idSitio;
	}
	
	public Integer getNumBoletasVendidas() {
		return numBoletasVendidas;
	}
	
	public Integer getProporcionAsistencia() {
		return proporcionAsistencia;
	}
	
	public Integer getValorTotalFacturado() {
		return valorTotalFacturado;
	}
}
