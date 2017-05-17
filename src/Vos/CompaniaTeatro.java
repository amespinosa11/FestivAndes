package Vos;

public class CompaniaTeatro 
{
	private Integer idCompania;
	
	private String nombre;
	
	private String paisOrigen;
	
	private Representante representante;
	
	public CompaniaTeatro()
	{
		super();
	}
	
	public CompaniaTeatro(Integer pId, String pNombre, String pPaisOrigen)
	{
		super();
		this.idCompania = pId;
		this.nombre = pNombre;
		this.paisOrigen = pPaisOrigen;
	}
	
	public void setIdCompania(Integer idCompania) {
		this.idCompania = idCompania;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}
	
	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}
	
	public Integer getIdCompania() {
		return idCompania;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getPaisOrigen() {
		return paisOrigen;
	}
	
	public Representante getRepresentante() {
		return representante;
	}
	

}
