package Vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonBackReference;

public class Cliente
{
	private Integer idCliente;
	
	private String nombre;
	
	private String apellido;
	
	private String correo;
	
	private Integer identificacion;
	
	private ArrayList<Boleta> boletas;
	
	@JsonBackReference
	private ArrayList<Preferencia> preferencias;
	
	public Cliente()
	{
		super();
	}
	
	public Cliente(Integer pId, String pNombre, String pApellido, String pCorreo, Integer pIdentificacion)
	{
		super();
		this.idCliente = pId;
		this.nombre = pNombre;
		this.apellido = pApellido;
		this.correo = pCorreo;
		this.identificacion = pIdentificacion;
	}
	
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
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
	
	public void setPreferencia(Preferencia preferencia) {
		this.preferencias.add(preferencia);
	}
	
	public Integer getIdCliente() {
		return idCliente;
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
	
	
	public ArrayList<Preferencia> getPreferencias() {
		return preferencias;
	}
}
