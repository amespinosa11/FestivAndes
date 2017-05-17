package Vos;

public class Representante 
{
	private Integer idRepresentante;
	
	private String nombre;
	
	private String apellido;
	
	private String correo;
	
	private Integer identificacion;
	
	public Representante()
	{
		super();
	}
	
	public Representante(Integer pId,String pNombre, String pApellido, String pCorreo, Integer pIdentificacion)
	{
		super();
		this.idRepresentante = pId;
		this.nombre = pNombre;
		this.apellido = pApellido;
		this.correo = pCorreo;
		this.identificacion = pIdentificacion;
	}
	
	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public void setIdentificacion(Integer identificacion) {
		this.identificacion = identificacion;
	}
	
	public Integer getIdentificacion() {
		return identificacion;
	}
	
	public Integer getIdRepresentante() {
		return idRepresentante;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public String getCorreo() {
		return correo;
	}
	
}
