package Vos;

import java.util.ArrayList;
import java.util.Date;

public class Espectaculo 
{
	private Integer idEspectaculo;
	
	private String nombre;
	
	private Double duracion;
	
	private Double costoRealizacion;
	
	private String idioma;
	
	private Date fechaRealizacion;
	
	private String publicoObjetivo;
	
	private ArrayList<Categoria> clasificaciones;
	
	private ArrayList<Funcion> funciones;
	
	private ArrayList<CompaniaTeatro> companias;
	
	private ArrayList<RequerimientoTecnico> requerimientos;
	
	
	public Espectaculo()
	{
		super();
	}
	
	public Espectaculo(Integer pId, String pNombre, Double pDuracion, Double pCosto,
			String pIdioma, Date pFecha, String pPublico)
	{
		super();
		this.idEspectaculo = pId;
		this.nombre = pNombre;
		this.duracion = pDuracion;
		this.costoRealizacion = pCosto;
		this.idioma = pIdioma;
		this.fechaRealizacion = pFecha;
		this.publicoObjetivo = pPublico;
	}
	
	public void setIdEspectaculo(Integer idEspectaculo) {
		this.idEspectaculo = idEspectaculo;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setDuracion(Double duracion) {
		this.duracion = duracion;
	}
	
	public void setCostoRealizacion(Double costoRealizacion) {
		this.costoRealizacion = costoRealizacion;
	}
	
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}
	
	public void setPublicoObjetivo(String publicoObjetivo) {
		this.publicoObjetivo = publicoObjetivo;
	}
	
	public Integer getIdEspectaculo() {
		return idEspectaculo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Double getDuracion() {
		return duracion;
	}
	
	public Double getCostoRealizacion() {
		return costoRealizacion;
	}
	
	public String getIdioma() {
		return idioma;
	}
	
	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}
	
	public String getPublicoObjetivo() {
		return publicoObjetivo;
	}
	
	public ArrayList<Categoria> getClasificaciones() {
		return clasificaciones;
	}
	
	public ArrayList<CompaniaTeatro> getCompanias() {
		return companias;
	}
	
	public ArrayList<Funcion> getFunciones() {
		return funciones;
	}
	
	public ArrayList<RequerimientoTecnico> getRequerimientos() {
		return requerimientos;
	}
	
	public void setClasificacion(Categoria clasificacion) {
		this.clasificaciones.add(clasificacion);
		}
	
	public void setFuncion(Funcion funcion)
	{
		this.funciones.add(funcion);
	}
	
	public void setCompanias(CompaniaTeatro compania)
	{
		this.companias.add(compania);
	}
	
	public void setRequerimientos(RequerimientoTecnico req)
	{
		this.requerimientos.add(req);
	}
	
}
