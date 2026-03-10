package Tarea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Coordenadas {
	
	//Metodos
	//funcion para guardar el nombre del aeropuerto y sus coordenadas en el archivo .txt
	public static boolean guardarAeropuerto(String nombre, double longitud, double latitud) {
		String textoArchivo=leerArchivo("Coordenadas");
		
		//Comprobar que el nombre pasado no este ya guardado
		if(textoArchivo.indexOf(nombre) != -1) {
			System.out.println("Las coordenadas de el Aeropuerto " + nombre + " ya esta guardado");
			return false;
		}
		
		//Se comprueba que el archivo este creado y se escriben los datos
		if(comprobarArchivo("Coordenadas")) {
			try {
				FileWriter archivo = new FileWriter(".\\Coordenadas\\Coordenadas.txt",true);
				
				//Se escribe en el archivo y luego se cierra
				archivo.write(nombre + ";" + longitud + ";" + latitud + "\n");
				archivo.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.err.println(e); //err para que imprima en rojo
				return false;
			}
		}else {
			System.out.println("Ha habido algún error al guardar las coordenadas del Aeropuerto " + nombre);
			return false;
		}
		return true;
		
		
	}
	
	public static String[] obtenerLocalizaciones(String nombreArchivo) {
		String matriz[] = new String[1];
		int contador = 0;
		
		String rutaArchivo = "archivo.txt"; // Ruta del archivo
        try (BufferedReader br = new BufferedReader(new FileReader(".\\"+ nombreArchivo +"\\"+ nombreArchivo +".txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String leido[] = linea.split(";");
                
                matriz[contador++] = leido[0];
                
                //Aumentamos la matriz 1 posición más
                String matriz2[] = new String[contador+1];
                for(int i=0;i<matriz.length;i++) {
                	matriz2[i] = matriz[i];
                }
                matriz=matriz2;
            }
        } catch (IOException e) {
        	// TODO Auto-generated catch block
			//e.printStackTrace();
			System.err.println(e); //err para que imprima en rojo
        }
		return matriz;
	}
	
	//funcion para devolver en GeoPunto la longitud y latitud guardadas en el archivo .txt
	public static GeoPunto obtenerLocalizacion(String nombreAeropuerto) {
		String textoArchivo=leerArchivo("Coordenadas");
		
		//Verificar que el nombre esta guardado
		if(textoArchivo.indexOf(nombreAeropuerto) == -1) {
			System.out.println("Las coordenadas de el Aeropuerto " + nombreAeropuerto + " no esta guardado");
			return null;
		}
		
		//Filtrar el nombre y devolver sus coordenadas
		double longitud = 0,latitud = 0;
		String textoConcat="";
		for(int i=textoArchivo.indexOf(nombreAeropuerto)+nombreAeropuerto.length()+1;i<textoArchivo.length();i++) { //i empieza en la posicion de coordenadas de ese nombre de Aeropuerto, +1 para saltarse tmb el separador ;
			if(textoArchivo.charAt(i)==';') {
				longitud=Double.parseDouble(textoConcat);
				textoConcat="";
				continue;
			}else if(textoArchivo.charAt(i)=='\n') {
				latitud=Double.parseDouble(textoConcat);
				break;
			}
			
			textoConcat+=textoArchivo.charAt(i);
		}
		
		return new GeoPunto(longitud,latitud);
	}
	
	//Funcion para comprobar si el archivo existe, y si no, crear la carpeta y el archivo, pasandole el nombre del archivo, que se usa como nombre y carpeta
	private static boolean comprobarArchivo(String nombreArchivo) {
		File carpeta = new File(nombreArchivo);
		File archivo = new File(carpeta,nombreArchivo+".txt");
		
		//Comprobar existencia de archivo, si no, crearlo
		if(!archivo.exists()) {
			carpeta.mkdir();
			try {
				archivo.createNewFile();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.err.println(e); //err para que imprima en rojo
				return false;
			}
		}	
		return true;
	}
	
	//Funcion para devolver el archivo leido en un String, pasandole el nombre del archivo, que se usa como nombre y carpeta
	private static String leerArchivo(String nombreArchivo) {
		String textoArchivo="";
		
		//Comprobar si el archivo existe, si sí, leerlo y guardar sus datos en String
		if(comprobarArchivo(nombreArchivo)) {
			try {
				FileReader archivo = new FileReader(".\\"+ nombreArchivo +"\\"+ nombreArchivo +".txt");
				
				//Leer archivo y guardarlo en un String
				int caracter=0;
				while(caracter!=-1) {
					caracter = archivo.read();
					textoArchivo+=(char)caracter;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.err.println(e); //err para que imprima en rojo
				return "";
			}
		}else {
			System.out.println("Ha habido algún error al leer el archivo " + nombreArchivo);
			return "";
		}
		
		return textoArchivo;
	}
}
