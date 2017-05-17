package Vos;

import java.util.ArrayList;

public class Sitio 
{
	private Integer idSitio;
	
	private String nombre;
	
	private Integer capacidad;
	
	private String tipoSilleteria;
	
	private String tipoProteccion;
	
	private Ciudad ciudad;
	
	private ArrayList<Caracteristica> caracteristicas;
	
	private ArrayList<TipoPersona> tiposPersonas;
	
	private ArrayList<Funcion> funciones;
	
	private ArrayList<Localidad> localidades;
	
	public Sitio()
	{
		super();
	}
	
	public Sitio(Integer pId, String pNombre, Integer pCapacidad, String pSilleteria, String pProteccion)
	{
		super();
		this.idSitio = pId;
		this.nombre = pNombre;
		this.capacidad = pCapacidad;
		this.tipoSilleteria = pSilleteria;
		this.tipoProteccion = pProteccion;
	}
	
	public void setIdSitio(Integer idSitio) {
		this.idSitio = idSitio;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}
	
	public void setTipoSilleteria(String tipoSilleteria) {
		this.tipoSilleteria = tipoSilleteria;
	}
	
	public void setTipoProteccion(String tipoProteccion) {
		this.tipoProteccion = tipoProteccion;
	}
	
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	
	public void setCaracteristica(Caracteristica pCaracteristica)
	{
		this.caracteristicas.add(pCaracteristica);
	}
	
	public void setTipoPersona(TipoPersona pTipo)
	{
		this.tiposPersonas.add(pTipo);
	}
	
	public void setFuncion(Funcion pFuncion)
	{
		this.funciones.add(pFuncion);
	}
	
	public void setFunciones(ArrayList<Funcion> funciones) {
		this.funciones = funciones;
	}
	
	public void setLocalidad(Localidad pLocalodad)
	{
		this.localidades.add(pLocalodad);
	}
	
	public void setLocalidades(ArrayList<Localidad> localidades) {
		this.localidades = localidades;
	}
	
	public Integer getIdSitio() {
		return idSitio;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Integer getCapacidad() {
		return capacidad;
	}
	
	public String getTipoSilleteria() {
		return tipoSilleteria;
	}
	
	public String getTipoProteccion() {
		return tipoProteccion;
	}
	
	public Ciudad getCiudad() {
		return ciudad;
	}
	
	public ArrayList<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}
	
	public ArrayList<Funcion> getFunciones() {
		return funciones;
	}
	
	public ArrayList<Localidad> getLocalidades() {
		return localidades;
	}
	
	public ArrayList<TipoPersona> getTiposPersonas() {
		return tiposPersonas;
	}
	
	
}
