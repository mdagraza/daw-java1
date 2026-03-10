package Tarea;

import java.time.LocalDate;

public class VueloMercancia extends Vuelo{

	//Artributos
	private Mercancia tipoMercancia;
	private int pesoKg;
	private double coste;
	private double costeBase;
	
	//Constructor
	public VueloMercancia(Compañia compañia, String origen, String destino, LocalDate fechaSalida,
			Mercancia tipoMercancia, int pesoKg) {
		super(compañia, origen, destino, fechaSalida);
		this.tipoMercancia = tipoMercancia;
		this.pesoKg = pesoKg;
		this.costeBase = establecerCosteBaseVuelo(tipoMercancia);
		this.coste = establecerCosteVuelo(pesoKg);
	}
	
	//Getter y setter
	public Mercancia getTipoMercancia() {
		return tipoMercancia;
	}

	public void setTipoMercancia(Mercancia tipoMercancia) {
		this.tipoMercancia = tipoMercancia;
	}

	public int getPesoKg() {
		return pesoKg;
	}

	public void setPesoKg(int pesoKg) {
		this.pesoKg = pesoKg;
	}

	public double getCoste() {
		return coste;
	}

	public double getCosteBase() {
		return costeBase;
	}
	
	//Metodos
	public double establecerCosteVuelo(int pesoKg) {
		double precio = -1;
		
		precio = this.costeBase*pesoKg;
		
		return Math.round(precio * 100.0) / 100.0;
	}
	
	public double establecerCosteBaseVuelo(Mercancia tipoMercancia) {
		switch(tipoMercancia) {
		case Mercancia.Alimentacion:
			return 100;
		case Mercancia.ResiduosNucleares:
			return 450;
		case Mercancia.Combustible:
			return 275;
		default:
			return -1;
		}
	}
	
	//Metodos sobreescritos
	@Override
	double calcularGanancia() {
		// TODO Auto-generated method stub
		return this.coste;
	}

	@Override
	public String toString() {
		return "VueloMercancia [tipoMercancia=" + tipoMercancia + ", pesoKg=" + pesoKg + ", coste=" + coste
				+ ", costeBase=" + costeBase + ", getCodigo()=" + getCodigo() + ", getCompañia()=" + getCompañia()
				+ ", getOrigen()=" + getOrigen() + ", getDestino()=" + getDestino() + ", getFechaSalida()="
				+ getFechaSalida() + ", getDistancia()=" + getDistancia() + " kms, getDiasHastaSalida()="
				+ getDiasHastaSalida() + "]";
	}


	
	

	

}
