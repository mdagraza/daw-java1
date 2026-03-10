package Tarea;

import java.time.LocalDate;
import java.util.Random;

public abstract class Vuelo{
	
	//Atributos
	private String codigo;
	private Compañia compañia;
	private Lugar origen;
	private Lugar destino;
	private LocalDate fechaSalida;
	private double distancia;
	
	//Constructor
	public Vuelo(Compañia compañia, String origen, String destino, LocalDate fechaSalida) {
		this.codigo = crearCodigo();
		this.compañia = compañia;
		this.origen = new Lugar(origen);
		this.destino = new Lugar(destino);
		this.fechaSalida = fechaSalida;
		this.distancia = this.origen.getCoordenadas().distancia(this.destino.getCoordenadas());
	}
	
	//Getter y setter
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Compañia getCompañia() {
		return compañia;
	}

	public void setCompañia(Compañia compañia) {
		this.compañia = compañia;
	}

	public Lugar getOrigen() {
		return origen;
	}

	public void setOrigen(Lugar origen) {
		this.origen = origen;
	}

	public Lugar getDestino() {
		return destino;
	}

	public void setDestino(Lugar destino) {
		this.destino = destino;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public double getDistancia() {
		return distancia;
	}
	
	//Metodos sobrescritos
	@Override
	public String toString() {
		return "Vuelo [codigo=" + codigo + ", compañia=" + compañia + ", origen=[" + origen + "], destino=[" + destino
				+ "], fechaSalida=" + fechaSalida + ", distancia=" + distancia + " kms, " + this.getDiasHastaSalida() + " días para salir]";
	}
	

	//Metodos
	private String crearCodigo() { //2 letras y 2 digitos
		//Declaraciones
		String codigo="";
		Random aleatorio = new Random();
		
		//Se obtienen 4 randoms en ASCII y se concatena en el String
		codigo += (char)aleatorio.nextInt(65,91);
		codigo += (char)aleatorio.nextInt(65,91);
		codigo += (char)aleatorio.nextInt(48,57);
		codigo += (char)aleatorio.nextInt(48,57);
		
		return codigo;
	}
	
	public int getDiasHastaSalida() {
		return (int) (this.getFechaSalida().toEpochDay() - LocalDate.now().toEpochDay());
	}

	//Metodos abstractos
	abstract double calcularGanancia();
	
	

	
}
