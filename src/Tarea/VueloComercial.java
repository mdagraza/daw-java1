package Tarea;

import java.time.LocalDate;
import java.util.Random;

public class VueloComercial extends Vuelo{

	//Atributos
	private Asiento asientos[];
	final private int asientosFila = 6;
	private boolean ventanasOcupadas;
	
	//Constructor
	public VueloComercial(Compañia compañia, String origen, String destino, LocalDate fechaSalida, int nFilas) {
		super(compañia, origen, destino, fechaSalida);
		this.asientos = new Asiento[nFilas*asientosFila];
		initAsientos(nFilas);
		ventanasOcupadas = false;
		
	}
	
	//Getter y setter
	public String getAsientos() {
		String textoAsientos="";
		for(int i = 0; i< asientos.length;i++) {
			textoAsientos+=asientos[i]+"\n";
		}
		return textoAsientos;
	}
	

	//Metodos sobreescritos
	@Override
	double calcularGanancia() {
		double ganancia = 0;
		for(int i = 0; i<asientos.length;i++) {
			if(asientos[i].isOcupado()) {
				ganancia+=asientos[i].getPrecioBillete();
			}
		}
		return Math.round(ganancia * 100.0) / 100.0; //Por defecto esta ya redondeado a 2 decimales, pero así se salva cuando el double aproxima a ,9999...
	}

	//Metodos
	public double establecerPrecioBillete() {
		//Precio = base + 0,05*distancia + 70* (1 -%asientosLibres) + (150 - díashastaFechaSalida) 
		double precio = -1;
		if(this.getDiasHastaSalida()>150) {
			precio = obtenerPrecioBase(this.getCompañia())+0.05*this.getDistancia()+70*(1-obtenerAsientosLibres());
		}else {
			precio = obtenerPrecioBase(this.getCompañia())+0.05*this.getDistancia()+70*(1-obtenerAsientosLibres())+(150-this.getDiasHastaSalida());
		}
		
		//En distancias cortas, el precio es negativo, por lo que se le pasa el precio base cuando eso pasa
		if(precio<0) {
			precio=obtenerPrecioBase(this.getCompañia());
		}
				
		return Math.round(precio * 100.0) / 100.0;
	}
	
	public String reservarAsiento() {
		return reservarAsiento(false);
	}
	
	//Funcion para reservar asiento. Se comprueba si se quiere reservar ventanilla o no. Cada vez que se reserve un asiento, se llama a la función de disponibilidad ventanilla
	public String reservarAsiento(boolean ventana) {
		Random aleatorio = new Random();
		boolean asientoLibre=false;
		
		//Verificar si hay algún asiento libre
		for(int i=0; i<this.asientos.length;i++) {
			if(!asientos[i].isOcupado()) {
				asientoLibre=true;
				break;
			}
		}
		if(!asientoLibre) {
			return "[Vuelo lleno]";
		}
		
		while(true) {
			int asiento = aleatorio.nextInt(0,asientos.length);
			
			
			//Se verifica si el asiento esta libre y si se quiere ventanilla(que se buscan solo asientos con A o F) o no. Si todas las ventanillas estan ocupadas, se asigna un asiento normal.
			if((!asientos[asiento].isOcupado() && (!ventana || ventanasOcupadas)) || (!asientos[asiento].isOcupado() && ventana && (asientos[asiento].getId().contains("A") || asientos[asiento].getId().contains("F")))) {
				asientos[asiento].setOcupado(true);
				comprobarDisponibilidadVentanilla();
				
				//Poner precios a los demas billetes sin reservar
				for(int i = 0; i<asientos.length;i++) {
					if(!asientos[i].isOcupado()) {
						asientos[i].setPrecioBillete(this.establecerPrecioBillete());
					}
				}
				return asientos[asiento].getId();
			}
		}
	}
	
	//Funcion para anular reserva del asiento. Devuelve true si se ha anulado o false si no se ha encontrado el asiento o el asiento no estaba reservado.
	public boolean anularAsiento(String asiento) {
		for(int i = 0; i<asientos.length;i++) {
			if(asientos[i].getId().equals(asiento)) {
				if(asientos[i].isOcupado()) {
					asientos[i].setOcupado(false);
					
					//Poner precios a los demas billetes sin reservar
					for(int j = 0; j<asientos.length;j++) {
						if(!asientos[j].isOcupado()) {
							asientos[j].setPrecioBillete(this.establecerPrecioBillete());
						}
					}
					return true;	
				}
				return false;
			}
		}
		return false;
	}
	
	private double obtenerPrecioBase(Compañia compañia) {
		switch(compañia) {
			case Compañia.SkyAir:
				return 75;
			case Compañia.FlyInfinity:
				return 125;
			case Compañia.MacAir:
				return 175;
			default:
				return -1;
		}
	}
	
	//Funcion para comprobar si quedan asientos de ventanilla libres.
	private void comprobarDisponibilidadVentanilla() {
		int contador=0;
		for(int i=0;i<this.asientos.length;i++) {
			if(asientos[i].isOcupado() && (asientos[i].getId().contains("A") || asientos[i].getId().contains("F"))) {
				contador++;
			}
		}
		//Hay dos asientos de ventanilla por fila, por lo que se comprueba que el contador sea igual o no.
		if(contador==(asientos.length/asientosFila)*2) {
			ventanasOcupadas=true;
		}else {
			ventanasOcupadas=false;
		}
	}
	
	
	//Funcion para devolver el % de asientos libres
	private int obtenerAsientosLibres() {
		int contAsientos=0;
		for(int i=0;i<this.asientos.length;i++) {
			if(!asientos[i].isOcupado()) {
				contAsientos++;
			}
		}
		return (contAsientos*1)/this.asientos.length;
	}
	
	//Funcion para asignar el id del asiento. Recorre las filas y el nAsientos por Fila y asigna el número de fila y una letra por cada asiento en la fila
	private void initAsientos(int nFilas) {
		for(int i = 0; i<nFilas;i++) {
			for(int j=0;j<asientosFila;j++) {
				asientos[i*asientosFila+j]=new Asiento(Integer.toString(i+1)+Character.toString(65+j),this.obtenerPrecioBase(this.getCompañia()));
			}
		}
		for(int i = 0; i<asientos.length;i++) {
			asientos[i].setPrecioBillete(this.establecerPrecioBillete());
		}
	}
	
	
}

class Asiento{
	//Atributos
	private String id;
	private boolean ocupado;
	private double precioBillete;
	private double precioBase;
	
	//Constructor
	public Asiento(String id, double precioBase) {
		super();
		this.id=id;
		this.ocupado = false;
		this.precioBillete = precioBase;
		this.precioBase = precioBase;
	}

	//Setter y getter
	public String getId() {
		return id;
	}

	protected void setId(String id) {
		this.id = id;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public double getPrecioBillete() {
		return precioBillete;
	}

	public void setPrecioBillete(double precioBillete) {
		this.precioBillete = precioBillete;
	}

	public double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}

	//Metodos sobreescritos
	@Override
	public String toString() {
		return "Asiento [id=" + id + ", ocupado=" + ocupado + ", precioBillete=" + precioBillete + ", precioBase="
				+ precioBase + "]";
	}
	
	//Metodos


	
	
}

