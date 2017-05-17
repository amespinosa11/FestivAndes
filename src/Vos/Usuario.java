package Vos;

public class Usuario
{
	private Integer idUsuario;
	
	private String nombre;
	
	private String apellido;
	
	private String correo;
	
	private Integer identificacion;
	
	private String rol;
	
	public Usuario()
	{
		super();
	}
	
	public Usuario(Integer pId, String pNombre, String pApellido, String pCorreo, Integer pIdentificacion, String pRol)
	{
		super();
		this.idUsuario = pId;
		this.nombre = pNombre;
		this.apellido = pApellido;
		this.correo = pCorreo;
		this.identificacion = pIdentificacion;
		this.rol = pRol;
	}
	
	public void setIdentificacion(Integer identificacion) {
		this.identificacion = identificacion;
	}
	
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
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
	
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public Integer getIdentificacion() {
		return identificacion;
	}
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getRol() {
		return rol;
	}
	
}
