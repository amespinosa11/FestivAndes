package Vos;

import java.util.ArrayList;

public class ClienteSinRegistrar 
{
	private Integer idClienteSinRegistrar;
	
	private String nombre;
	
	private String apellido;
	
	private String correo;
	
	private Integer identificacion;
	
	private ArrayList<Boleta> boletas;
	
	public ClienteSinRegistrar()
	{
		super();
	}
	
	public ClienteSinRegistrar(Integer pId, String pNombre, String pApellido, String pCorreo, Integer pIdentificacion)
	{
		super();
		this.idClienteSinRegistrar = pId;
		this.nombre = pNombre;
		this.apellido = pApellido;
		this.correo = pCorreo;
		this.identificacion = pIdentificacion;
	}
	
	public void setIdClienteSinRegistrar(Integer idClienteSinRegistrar) {
		this.idClienteSinRegistrar = idClienteSinRegistrar;
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
	
	public void setBoleta(Boleta pBoleta)
	{
		this.boletas.add(pBoleta);
	}
	
	public Integer getIdClienteSinRegistrar() {
		return idClienteSinRegistrar;
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
	
	public Integer getIdentificacion() {
		return identificacion;
	}
	
	public ArrayList<Boleta> getBoletas() {
		return boletas;
	}
	
	
	
}
