package Tarea;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Aeropuerto {

	//Atributos
	//private Vuelo vuelos[];
	private ArrayList<Vuelo> vuelos = new ArrayList<Vuelo>();
	private String nombre;
	private Lugar lugar;
	
	//Constructor
	public Aeropuerto(String nombre, String lugar) {
		super();
		this.nombre = nombre;
		this.lugar = new Lugar(lugar);
	}
	
	
	//Getter y setter
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Vuelo> getVuelos() {
		return vuelos;
	}

	public Lugar getLugar() {
		return lugar;
	}
	
	
	//Metodos
	public boolean addVuelo(Vuelo v) {
		for(Vuelo vi : vuelos) {
			if(vi == null) {
				break;
			}
			if(vi.getCodigo().equals(v.getCodigo())) {
				return false;
			}
		}
		vuelos.add(v);
		return true;
	}

	public double getGanancia() {
		double ganancia = 0;
		
		for(Vuelo vi : vuelos) {
			if(vi == null) {
				break;
			}
			ganancia += vi.calcularGanancia();
		}
		
		return ganancia;
	}
	
	public double getGanancia(Serializable instancia) {
		double ganancia = 0;
		
		for(Vuelo vi : vuelos) {
			if(vi == null) {
				break;
			}
			ganancia += vi.calcularGanancia();
		}
		
		return ganancia;
	}
	
	public void getVuelosPorCompañia(Compañia c) {
		//Declaraciones
		int contador=0;
				
		for(Vuelo vi : vuelos) {
			if(vi.getCompañia() == c) {
				System.out.println(vi);
				contador++;
			}
		}
		
		if(contador == 0) {
			System.out.println("No se han encontrado vuelos con esa compañia");
		}
	}
	
	public void getVuelosPorDestino(String destino) {
		//Declaraciones
		int contador=0;
		
		for(Vuelo vi : vuelos) {
			if(vi.getDestino().getNombre().equals(destino)) {
				System.out.println(vi);
				contador++;
			}
		}
		
		if(contador == 0) {
			System.out.println("No se han encontrado vuelos con ese destino");
		}
	}
	
	//Metodos
	public String obtenerInfoVuelo(String codigo) {
		String mensaje="";
		
		for(Vuelo v : vuelos) {
			if(v.getCodigo().equals(codigo)) {
				if(v instanceof VueloComercial) {
					mensaje += "Vuelo Comercial ";
					mensaje += v.getCodigo() + " con origen ( " + v.getOrigen().getNombre() + " ) y destino ( " + v.getDestino().getNombre() + " ) \n";
					mensaje += v + "\n";
					mensaje += ((VueloComercial) v).getAsientos();
				}else if(v instanceof VueloMercancia) {
					mensaje += "Vuelo de Mercancias ";
					mensaje += v.getCodigo() + " con origen ( " + v.getOrigen().getNombre() + " ) y destino ( " + v.getDestino().getNombre() + " ) ";
					mensaje += "con " + ((VueloMercancia) v).getPesoKg() +" kgs de mercancia " + ((VueloMercancia) v).getTipoMercancia() + " y precio " + ((VueloMercancia) v).getCoste() + "\n";
					mensaje += v + "\n";
				}
				break;
			}
		}
		return mensaje;
	}
	
	public Vuelo obtenerVuelo(String codigo) {
		for(Vuelo v : vuelos) {
			if(v.getCodigo().equals(codigo)) {
				return v;
			}
		}
		return null;
	}
	
	public String obtenerInfoAsiento(String codigo, String id) {
		String mensaje="";
		
		for(Vuelo v : vuelos) {
			if(v.getCodigo().equals(codigo)) {
				if(v instanceof VueloComercial) {
					mensaje += "Vuelo Comercial ";
					mensaje += v.getCodigo() + " con origen ( " + v.getOrigen().getNombre() + " ) y destino ( " + v.getDestino().getNombre() + " ) \n";
					mensaje += ((VueloComercial) v).getAsientos();
				}else if(v instanceof VueloMercancia) {
					mensaje += "Vuelo de Mercancias ";
					mensaje += v.getCodigo() + " con origen ( " + v.getOrigen().getNombre() + " ) y destino ( " + v.getDestino().getNombre() + " ) ";
					mensaje += "con " + ((VueloMercancia) v).getPesoKg() +" kgs de mercancia " + ((VueloMercancia) v).getTipoMercancia() + " y precio " + ((VueloMercancia) v).getCoste() + "\n";
				}
				break;
			}
		}
		return mensaje;
	}



	//Metodos sobreescritos
	@Override
	public String toString() {
		String mensaje="";
		
		mensaje += "[ " + this.nombre + " ]\n";
		mensaje += "***************************************************************************************************************\n";
		for(Vuelo v : vuelos) {
			if(v.getFechaSalida().isBefore(LocalDate.now())) {
				continue; //No muestra los vuelos que sean anteriores al día de hoy
			}
			if(v instanceof VueloComercial) {
				mensaje += "{ " + v.getCompañia().name() + " } - Vuelo Comercial ";
				mensaje += v.getCodigo() + " con origen ( " + v.getOrigen().getNombre() + " ) y destino ( " + v.getDestino().getNombre() + " ) ";
				mensaje += "sale el día " + v.getFechaSalida().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n";
				//mensaje += ((VueloComercial) v).getAsientos();
			}else if(v instanceof VueloMercancia) {
				mensaje += "{ " + v.getCompañia().name() + " } - Vuelo de Mercancias ";
				mensaje += v.getCodigo() + " con origen ( " + v.getOrigen().getNombre() + " ) y destino ( " + v.getDestino().getNombre() + " ) ";
				mensaje += "sale el día " + v.getFechaSalida().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n";
				//mensaje += "con " + ((VueloMercancia) v).getPesoKg() +" kgs de mercancia " + ((VueloMercancia) v).getTipoMercancia() + " y precio " + ((VueloMercancia) v).getCoste() + "\n";
			}
			mensaje += "***************************************************************************************************************\n";
			
		}
		
		
		return mensaje;
	}
	
}
