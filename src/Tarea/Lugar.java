package Tarea;

public class Lugar {

	//Atributos
	private String nombre;
	private GeoPunto coordenadas;
	
	//Constructor
	public Lugar(String nombre) {
		super();
		this.nombre = nombre;
		this.coordenadas = Coordenadas.obtenerLocalizacion(nombre);
	}

	//Setter y getter
	public String getNombre() {
		return nombre;
	}

	public GeoPunto getCoordenadas() {
		return coordenadas;
	}

	//metodos sobreescritos
	@Override
	public String toString() {
		return "Nombre: " + nombre + ", Coordenadas:" + coordenadas;
	}
	
	
	
}
