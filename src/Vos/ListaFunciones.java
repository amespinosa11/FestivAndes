package Vos;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaFunciones 
{
	/**
	 * List con los videos
	 */
	@JsonProperty(value="funciones")
	private List<Funcion> funciones;
	
	/**
	 * Constructor de la clase ListaFunciones
	 * @param videos - videos para agregar al arreglo de la clase
	 */
	public ListaFunciones( @JsonProperty(value="videos")List<Funcion> funcs){
		this.funciones = funcs;
	}
	
	
	public ListaFunciones() {
		funciones = new ArrayList<Funcion>();
	}

	/**
	 * Método que retorna la lista de funciones
	 * @return  List - List con las funciones
	 */
	public List<Funcion> getFunciones() {
		return funciones;
	}

	/**
	 * Método que asigna la lista de funciones que entra como parametro
	 * @param  funcs - List con las funciones ha agregar
	 */
	public void setFunciones(List<Funcion> funcs) {
		this.funciones = funcs;
	}
	
	public void addFuncion(ListaFunciones funcionesNew){
		this.funciones.addAll(funcionesNew.getFunciones());
	}

}
