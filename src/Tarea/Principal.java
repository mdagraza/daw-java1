package Tarea;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Declaraciones
		Scanner lector = new Scanner(System.in);
		char opcion;
		boolean salirWhile=false;
		String textoLector;
		Aeropuerto aeropuertos[] = new Aeropuerto[10];
		boolean bucleLeerDestino = false;

		//Crear el archivo de coordenadas de aeropuertos y guardar los datos
		Coordenadas.guardarAeropuerto("Vigo", -8.6299611, 42.2311768);
		Coordenadas.guardarAeropuerto("Santiago", -8.4221688, 42.8920853);
		Coordenadas.guardarAeropuerto("Coruña", -8.3813663, 43.3012571);
		Coordenadas.guardarAeropuerto("Madrid", -3.5665397, 40.4899066);
		Coordenadas.guardarAeropuerto("Barcelona", 2.0660932, 41.2955978);
		Coordenadas.guardarAeropuerto("Paris", 2.4533748, 48.9851188);
		Coordenadas.guardarAeropuerto("Berlin", 13.5049712, 52.3645625);
		Coordenadas.guardarAeropuerto("Londres", 0.0493356, 51.504611);
		Coordenadas.guardarAeropuerto("Moscu", 37.4099281, 55.9736512);
		Coordenadas.guardarAeropuerto("Lisboa", -9.1423124, 38.7705281);
		Coordenadas.guardarAeropuerto("Bruselas", 4.4761847, 50.9004546);
		Coordenadas.guardarAeropuerto("NewYork", -73.7781, 40.6413);
		
		
		
		
		//Inicializaciones de Aeropuertos
		aeropuertos[0] = new Aeropuerto("Aeropuerto Internacional de Vigo-Peinador", "Vigo");
		aeropuertos[1] = new Aeropuerto("Aeropuerto Adolfo Suárez Madrid-Barajas", "Madrid");
		aeropuertos[2] = new Aeropuerto("Aeropuerto de la Ciudad de Londres", "Londres");
		
		
		//Asignacion de vuelos por defecto
		aeropuertos[0].addVuelo(new VueloComercial(Compañia.FlyInfinity, "Vigo", "Madrid", LocalDate.of(2025, 3, 1), 5));
		aeropuertos[0].addVuelo(new VueloComercial(Compañia.MacAir, "Vigo", "Londres", LocalDate.of(2025, 4, 2), 6));
		aeropuertos[0].addVuelo(new VueloComercial(Compañia.SkyAir, "Vigo", "NewYork", LocalDate.of(2025, 5, 3), 7));
		aeropuertos[0].addVuelo(new VueloMercancia(Compañia.MacAir, "Vigo", "Santiago", LocalDate.of(2025, 6, 8), Mercancia.Combustible, 599));
		aeropuertos[0].addVuelo(new VueloMercancia(Compañia.MacAir, "Vigo", "Coruña", LocalDate.of(2025, 7, 9), Mercancia.ResiduosNucleares, 852));
		aeropuertos[0].addVuelo(new VueloMercancia(Compañia.FlyInfinity, "Vigo", "Paris", LocalDate.of(2025, 8, 10), Mercancia.Alimentacion, 1057));
		aeropuertos[0].addVuelo(new VueloComercial(Compañia.SkyAir, "Vigo", "Moscu", LocalDate.of(2025, 9, 11), 5));
		aeropuertos[0].addVuelo(new VueloComercial(Compañia.MacAir, "Vigo", "Lisboa", LocalDate.of(2025, 10, 12), 6));
		aeropuertos[0].addVuelo(new VueloComercial(Compañia.MacAir, "Vigo", "Bruselas", LocalDate.of(2025, 11, 13), 7));
		aeropuertos[0].addVuelo(new VueloComercial(Compañia.FlyInfinity, "Vigo", "Barcelona", LocalDate.of(2024, 12, 14), 8));
		
		
		aeropuertos[1].addVuelo(new VueloComercial(Compañia.FlyInfinity, "Madrid", "Vigo", LocalDate.of(2025, 4, 1), 10));
		aeropuertos[1].addVuelo(new VueloComercial(Compañia.FlyInfinity, "Madrid", "Londres", LocalDate.of(2025, 5, 2), 10));
		aeropuertos[1].addVuelo(new VueloMercancia(Compañia.SkyAir, "Madrid", "Paris", LocalDate.of(2025, 6, 10), Mercancia.ResiduosNucleares, 3335));
		
		
		aeropuertos[2].addVuelo(new VueloComercial(Compañia.SkyAir, "Londres", "Madrid", LocalDate.of(2025, 7, 1), 11));

		
		while(!salirWhile) {
			//Menu
			System.out.println("==============================================================");
			System.out.println("=    a. Añadir vuelo                                         =");
			System.out.println("=    b. Mostrar info vuelo por código                        =");
			System.out.println("=    c. Gestionar asiento                                    =");
			System.out.println("=    d. Mostrar vuelos comerciales o mercancia               =");
			System.out.println("=    e. Mostrar vuelos a determinado destino                 =");
			System.out.println("=    f. Mostrar vuelos desde determinado origen              =");
			System.out.println("=    g. Mostrar ganancia de vuelos comerciales o mercancia   =");
			System.out.println("=    h. Mostrar ganancia total de aeropuerto                 =");
			System.out.println("=    i. Mostrar vuelo de más distancia                       =");
			System.out.println("=    Salir. Pulsar cualquier otra tecla                      =");
			System.out.println("==============================================================");
			
			
			System.out.print("Opción: ");
			opcion = lector.nextLine().toLowerCase().charAt(0);
			
			//Declaraciones
			int origenSeleccionado=-1, destinoSeleccionado=-1, compañiaSeleccionada=-1;
			Compañia CompañiaSelect=null;
			switch(opcion) {
			case 'a': // APARTADO A *******************************************************************************************************************************************************************
				/* Añadir Vuelo: Muestra un listado con los destinos y los orígenes disponibles. y permite seleccionar también la compañia. Mostrar información de todos los vuelos */
				//Declaraciones
				origenSeleccionado=-1;
				destinoSeleccionado=-1;
				
				//Origen :::::::::::::::::::::::::::::::::::::::::::::::
				System.out.println("Lista de Aeropuertos:");
				for(int i = 0; i<aeropuertos.length;i++) {
					if (aeropuertos[i] == null) {
						break;
					}
					System.out.println((i+1) + ": " + aeropuertos[i].getNombre());
				}
				
				System.out.print("Seleccionar Aeropuerto de origen: ");
				textoLector = lector.nextLine().toLowerCase();
				
				//Si se ha seleccionado la opcion con numero, se usa el propio numero
				if(textoLector.charAt(0) >= '1' && textoLector.charAt(0) <= '9') {
					origenSeleccionado = textoLector.charAt(0)-'0'-1; //-1 porque los i mostrados son +1 para no mostrar el 0
				}else { //Se da por hecho que han puesto el nombre del aeropuerto, por lo que se busca cual es
					for(int i = 0; i<aeropuertos.length;i++) {
						if (aeropuertos[i] == null) {
							break;
						}
						if (aeropuertos[i].getNombre().toLowerCase().contains(textoLector)) {
							origenSeleccionado = i;
							break;
						}
					}
				}
				try { //try catch para verificar que no hay errores de codigo si se escoge una opcion que no aparece en la lista
					System.out.println("Origen seleccionado: " + aeropuertos[origenSeleccionado].getNombre());
				}catch(Exception e) {
					System.out.println("{ NO EXISTE ESA OPCIÓN }");
					break; //Se sale del case y vuelta a empezar
				}
				
				
				//Destino :::::::::::::::::::::::::::::::::::::::::::::::
				System.out.println("Lista de destinos:");
				String destinos[] = Coordenadas.obtenerLocalizaciones("Coordenadas");
				
				for(int i=0;i<destinos.length;i++) {
					if(destinos[i]==null) {
						break;
					}
					System.out.println((i+1)+ ": " + destinos[i].split(";")[0]);
				}
				
				System.out.print("Seleccionar el destino deseado: ");
				bucleLeerDestino = false;
				while(!bucleLeerDestino) { //Se crea un bucle para leer el destino, por si el destino seleccionado es el mismo que origen
					
					textoLector = lector.nextLine().toLowerCase();
					//Si se ha seleccionado la opcion con numero, se usa el propio numero
					if(textoLector.charAt(0) >= '1' && textoLector.charAt(0) <= '9') { 
						destinoSeleccionado = textoLector.charAt(0)-'0'-1; //-1 porque los i mostrados son +1 para no mostrar el 0
						if(textoLector.length()>1) {
							if(textoLector.charAt(1) >= '0' && textoLector.charAt(1) <= '9') {
								destinoSeleccionado = ((textoLector.charAt(0)-'0')*10) + textoLector.charAt(1)-'0'-1; //Si es mas de 10, se cogen los dos caracteres
							}
						}
						
						
					}else { //Se da por hecho que han puesto el nombre del aeropuerto, por lo que se busca cual es
						for(int i = 0; i<destinos.length;i++) {
							if (destinos[i] == null) {
								break;
							}
							if (destinos[i].split(";")[0].toLowerCase().contains(textoLector)) {
								destinoSeleccionado = i;
								break;
							}
						}
					}
					
					//Se comprueba si origen = destino
					try { //try catch para verificar que no hay errores de codigo si se escoge una opcion que no aparece en la lista
						if(aeropuertos[origenSeleccionado].getLugar().getNombre().equals(destinos[destinoSeleccionado].split(";")[0])) {
							System.out.print("El destino y origen son iguales, selecciona otro destino: ");
						}else {
							bucleLeerDestino=true;
						}
					}catch(Exception e) {
						break; //Se sale del while
					}
					
				}
				
				try { //try catch para verificar que no hay errores de codigo si se escoge una opcion que no aparece en la lista
					System.out.println("Destino seleccionado: " + destinos[destinoSeleccionado].split(";")[0]);
				}catch(Exception e) {
					System.out.println("{ NO EXISTE ESA OPCIÓN }");
					break; //Se sale del case y vuelta a empezar
				}
				
				//Compañia :::::::::::::::::::::::::::::::::::::::::::::::
				int contadorC = 1;
				System.out.println("Lista de compañias:");
				for(Compañia c : Compañia.values()) {
					if(c==null) {
						break;
					}
					System.out.println(contadorC++ + ": " +c);
				}
				
				System.out.print("Seleccionar compañia deseada: ");
				textoLector = lector.nextLine().toLowerCase();
				//Si se ha seleccionado la opcion con numero, se usa el propio numero
				if(textoLector.charAt(0) >= '1' && textoLector.charAt(0) <= '9') {
					//Se selecciona la compañia directamente del array del enumerado
					CompañiaSelect = Compañia.values()[textoLector.charAt(0)-'0'-1];
				}else { //Se da por hecho que han puesto el nombre de la compañia, por lo que se busca cual es
					for(Compañia c : Compañia.values()) {
						if (c == null) {
							break;
						}
						if(c.name().toLowerCase().contains(textoLector)) {
							CompañiaSelect = c; //Se selecciona la compañia que coincida con el nombre puesto
							break;
						}
					}
				}
				
				//Si la opcion escogida de compañia no es ninguna de la lista, se sale del case y vuelta a empezar
				if(CompañiaSelect != null) {
					System.out.println("Compañia seleccionada: " + CompañiaSelect);
				} else {
					System.out.println("{ NO EXISTE ESA OPCIÓN }");
					break; //Se sale del case y vuelta a empezar
				}
				
				//Guardar vuelo en el aeropuerto ::::::::::::::::::::::::::::::::::::::::::
				//Declaraciones para guardar
				boolean resultado = false;
				Random aleatorio = new Random();
				int year = aleatorio.nextInt(LocalDate.now().getYear(),LocalDate.now().getYear()+2); //Año entre este año y uno más
				int month;
				//verificar que el mes sea posterior al día de hoy
				if(year>LocalDate.now().getYear()) {
					month = aleatorio.nextInt(1,13);
				}else {
					if(LocalDate.now().getMonthValue()+1 == 13) {
						year+=1; //Si estamos en diciembre y en el mismo año, se pasa a Enero y se suma un año
						month=1;
					}else {
						month = aleatorio.nextInt(LocalDate.now().getMonthValue()+1,13); //si el año es en el que estamos, el vuelo debe ser en un mes posterior al mes actual
					}
					
				}
				int day;
				if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) { //Se comprueba que mes es, para saber si poner limite de 1-30 ó 1-31
					day = aleatorio.nextInt(1,32);
				}else if(month == 2 && !LocalDate.of(year,1,1).isLeapYear()){ //Se tiene en cuenta si el año es bisiesto o no para el mes de febrero
					day = aleatorio.nextInt(1,29);
				}else if(month == 2 && LocalDate.of(year,1,1).isLeapYear()){
					day = aleatorio.nextInt(1,30);
				} else {
					day = aleatorio.nextInt(1,31);
				}
				int filas = aleatorio.nextInt(5,21); //Filas un poco al azar los limites
				while(!resultado) {
					resultado = aeropuertos[origenSeleccionado].addVuelo(new VueloComercial(CompañiaSelect, aeropuertos[origenSeleccionado].getLugar().getNombre(), destinos[destinoSeleccionado].split(";")[0], LocalDate.of(year, month, day), filas));
				}
				
				
				//Vuelos del Aeropuerto seleccionado ::::::::::::::::::::::::::::::::::::::::::::::::::::::
				System.out.println(aeropuertos[origenSeleccionado]);
				
				
				break;
			case 'b': // APARTADO B *******************************************************************************************************************************************************************
				/* Mostrar información de un vuelo a partir del código: La aplicación muestra todos los códigos y el usuario escribe el código del cuál quiere obtener la información. */
				//Declaraciones
				origenSeleccionado=-1;
				
				//Aeropuerto :::::::::::::::::::::::::::::::::::::::::::::::
				System.out.println("Lista de Aeropuertos:");
				for(int i = 0; i<aeropuertos.length;i++) {
					if (aeropuertos[i] == null) {
						break;
					}
					System.out.println((i+1) + ": " + aeropuertos[i].getNombre());
				}
				
				System.out.print("Seleccionar Aeropuerto: ");
				textoLector = lector.nextLine().toLowerCase();
				
				//Si se ha seleccionado la opcion con numero, se usa el propio numero
				if(textoLector.charAt(0) >= '1' && textoLector.charAt(0) <= '9') {
					origenSeleccionado = textoLector.charAt(0)-'0'-1; //-1 porque los i mostrados son +1 para no mostrar el 0
				}else { //Se da por hecho que han puesto el nombre del aeropuerto, por lo que se busca cual es
					for(int i = 0; i<aeropuertos.length;i++) {
						if (aeropuertos[i] == null) {
							break;
						}
						if (aeropuertos[i].getNombre().toLowerCase().contains(textoLector)) {
							origenSeleccionado = i;
							break;
						}
					}
				}
				try { //try catch para verificar que no hay errores de codigo si se escoge una opcion que no aparece en la lista
					System.out.println("Aeropuerto seleccionado: " + aeropuertos[origenSeleccionado].getNombre());
				}catch(Exception e) {
					System.out.println("{ NO EXISTE ESA OPCIÓN }");
					break; //Se sale del case y vuelta a empezar
				}
				
				//Mostrar vuelos de ese Aeropuerto ::::::::::::::::::::::::::::::::::::::::::::::::::::
				System.out.println(aeropuertos[origenSeleccionado]);				
				
				//Pedir codigo ::::::::::::::::::::::::::::::::::::::::::::::::::::
				System.out.print("Seleccionar código de vuelo: ");
				textoLector = lector.nextLine().toUpperCase();
				
				//Mostrar info del vuelo seleccionado ::::::::::::::::::::::::::::::::::::::::::::::::::::
				System.out.println(aeropuertos[origenSeleccionado].obtenerInfoVuelo(textoLector));	
				
				
				break;
			case 'c': // APARTADO C *******************************************************************************************************************************************************************
				/* Gestionar asiento: Permite reservar asientos o anular la reserva. */ 
				//Declaraciones
				origenSeleccionado=-1;
				
				//Aeropuerto :::::::::::::::::::::::::::::::::::::::::::::::
				System.out.println("Lista de Aeropuertos:");
				for(int i = 0; i<aeropuertos.length;i++) {
					if (aeropuertos[i] == null) {
						break;
					}
					System.out.println((i+1) + ": " + aeropuertos[i].getNombre());
				}
				
				System.out.print("Seleccionar Aeropuerto: ");
				textoLector = lector.nextLine().toLowerCase();
				
				//Si se ha seleccionado la opcion con numero, se usa el propio numero
				if(textoLector.charAt(0) >= '1' && textoLector.charAt(0) <= '9') {
					origenSeleccionado = textoLector.charAt(0)-'0'-1; //-1 porque los i mostrados son +1 para no mostrar el 0
				}else { //Se da por hecho que han puesto el nombre del aeropuerto, por lo que se busca cual es
					for(int i = 0; i<aeropuertos.length;i++) {
						if (aeropuertos[i] == null) {
							break;
						}
						if (aeropuertos[i].getNombre().toLowerCase().contains(textoLector)) {
							origenSeleccionado = i;
							break;
						}
					}
				}
				try { //try catch para verificar que no hay errores de codigo si se escoge una opcion que no aparece en la lista
					System.out.println("Aeropuerto seleccionado: " + aeropuertos[origenSeleccionado].getNombre());
				}catch(Exception e) {
					System.out.println("{ NO EXISTE ESA OPCIÓN }");
					break; //Se sale del case y vuelta a empezar
				}
				
				//Mostrar vuelos de ese Aeropuerto ::::::::::::::::::::::::::::::::::::::::::::::::::::
				System.out.println(aeropuertos[origenSeleccionado]);				
				
				//Pedir codigo ::::::::::::::::::::::::::::::::::::::::::::::::::::
				System.out.print("Seleccionar código de vuelo: ");
				textoLector = lector.nextLine().toUpperCase();
				
				//Mostrar info del vuelo seleccionado ::::::::::::::::::::::::::::::::::::::::::::::::::::
				System.out.println(aeropuertos[origenSeleccionado].obtenerInfoVuelo(textoLector));	
				Vuelo v = aeropuertos[origenSeleccionado].obtenerVuelo(textoLector); //Se guarda el vuelo seleccionado por codigo
				
				//Se comprueba si el vuelo es de mercancia, para salir al no poder reservar / anular asientos
				if(aeropuertos[origenSeleccionado].getVuelos().get(aeropuertos[origenSeleccionado].getVuelos().indexOf(v)) instanceof VueloMercancia) {
					System.out.println("El vuelo seleccionado es de Mercancía. No se puede reservar / anular asientos.");
					break;
				}
				
				//Preguntar si reservar o anular ::::::::::::::::::::::::::::::::::::::::::::::::::::
				System.out.print("Reservar o anular? (r/a): ");
				textoLector = lector.nextLine().toLowerCase();
				if(textoLector.charAt(0) == 'r') { //Reservar asiento
					String asientoR = "";
					System.out.print("Con ventanilla? (s/n): ");
					textoLector = lector.nextLine().toLowerCase();
					if(textoLector.charAt(0) == 's') {
						//Se accede a reservarAsiento(), downcasteando el vuelo del ArrayList a VueloComercial y obteniendo la posicion del array usando el indexOf del array seleccionado anteriormente que fue guardado en v al seleccionar el codigo del vuelo
						asientoR = ((VueloComercial)aeropuertos[origenSeleccionado].getVuelos().get(aeropuertos[origenSeleccionado].getVuelos().indexOf(v))).reservarAsiento(true); 
					}else if(textoLector.charAt(0) == 'n') {
						asientoR = ((VueloComercial)aeropuertos[origenSeleccionado].getVuelos().get(aeropuertos[origenSeleccionado].getVuelos().indexOf(v))).reservarAsiento();
					}
					System.out.println("Reservado el asiento: " + asientoR);
				}else if(textoLector.charAt(0) == 'a') { //Anular reserva
					boolean asientoA = false;
					System.out.print("Seleccionar asiento: ");
					textoLector = lector.nextLine().toUpperCase();
					
					asientoA = ((VueloComercial)aeropuertos[origenSeleccionado].getVuelos().get(aeropuertos[origenSeleccionado].getVuelos().indexOf(v))).anularAsiento(textoLector);
					if(asientoA) {
						System.out.println("Asiento anulado correctamente");
					}else {
						System.out.println("Ha habido un problema para anular");
					}
				}
				
				break;
			case 'd': // APARTADO D *******************************************************************************************************************************************************************
				/* Mostrar vuelos de mercancías o comerciales */ 
				//Declaraciones
				origenSeleccionado=-1;
				
				//Aeropuerto :::::::::::::::::::::::::::::::::::::::::::::::
				System.out.println("Lista de Aeropuertos:");
				for(int i = 0; i<aeropuertos.length;i++) {
					if (aeropuertos[i] == null) {
						break;
					}
					System.out.println((i+1) + ": " + aeropuertos[i].getNombre());
				}
				
				System.out.print("Seleccionar Aeropuerto: ");
				textoLector = lector.nextLine().toLowerCase();
				
				//Si se ha seleccionado la opcion con numero, se usa el propio numero
				if(textoLector.charAt(0) >= '1' && textoLector.charAt(0) <= '9') {
					origenSeleccionado = textoLector.charAt(0)-'0'-1; //-1 porque los i mostrados son +1 para no mostrar el 0
				}else { //Se da por hecho que han puesto el nombre del aeropuerto, por lo que se busca cual es
					for(int i = 0; i<aeropuertos.length;i++) {
						if (aeropuertos[i] == null) {
							break;
						}
						if (aeropuertos[i].getNombre().toLowerCase().contains(textoLector)) {
							origenSeleccionado = i;
							break;
						}
					}
				}
				try { //try catch para verificar que no hay errores de codigo si se escoge una opcion que no aparece en la lista
					System.out.println("Aeropuerto seleccionado: " + aeropuertos[origenSeleccionado].getNombre());
				}catch(Exception e) {
					System.out.println("{ NO EXISTE ESA OPCIÓN }");
					break; //Se sale del case y vuelta a empezar
				}
				
				//Preguntar tipo de vuelo para mostrar los datos ::::::::::::::::::::::::::::::::::::::::::::::::::::
				System.out.print("Mostrar vuelos comerciales o de mercancia? (c/m): ");
				textoLector = lector.nextLine().toLowerCase();
				
				if(textoLector.charAt(0) =='c') {
					for(Vuelo vi : aeropuertos[origenSeleccionado].getVuelos()) {
						if(vi.getFechaSalida().isBefore(LocalDate.now())) {
							continue; //No muestra los vuelos que sean anteriores al día de hoy
						}
						if(vi instanceof VueloComercial && vi !=null) {
							System.out.println(vi);
						}
					}
				}else if(textoLector.charAt(0) =='m') {
					for(Vuelo vi : aeropuertos[origenSeleccionado].getVuelos()) {
						if(vi.getFechaSalida().isBefore(LocalDate.now())) {
							continue; //No muestra los vuelos que sean anteriores al día de hoy
						}
						if(vi instanceof VueloMercancia && vi !=null) {
							System.out.println(vi);
						}
					}
				}
					
				break;
			case 'e': // APARTADO E *******************************************************************************************************************************************************************
				/* Mostrar todos los vuelos a un determinado destino */
				//Declaraciones
				destinoSeleccionado=-1;
				int contadorV=0;
				
				//Destino :::::::::::::::::::::::::::::::::::::::::::::::
				System.out.println("Lista de destinos:");
				String destinos2[] = Coordenadas.obtenerLocalizaciones("Coordenadas");
				
				for(int i=0;i<destinos2.length;i++) {
					if(destinos2[i]==null) {
						break;
					}
					System.out.println((i+1)+ ": " + destinos2[i].split(";")[0]);
				}
				
				System.out.print("Seleccionar el destino deseado: ");
					
				textoLector = lector.nextLine().toLowerCase();
				//Si se ha seleccionado la opcion con numero, se usa el propio numero
				if(textoLector.charAt(0) >= '1' && textoLector.charAt(0) <= '9') { 
					destinoSeleccionado = textoLector.charAt(0)-'0'-1; //-1 porque los i mostrados son +1 para no mostrar el 0
					if(textoLector.length()>1) {
						if(textoLector.charAt(1) >= '0' && textoLector.charAt(1) <= '9') {
							destinoSeleccionado = ((textoLector.charAt(0)-'0')*10) + textoLector.charAt(1)-'0'-1; //Si es mas de 10, se cogen los dos caracteres
						}
					}
					
					
				}else { //Se da por hecho que han puesto el nombre del aeropuerto, por lo que se busca cual es
					for(int i = 0; i<destinos2.length;i++) {
						if (destinos2[i] == null) {
							break;
						}
						if (destinos2[i].split(";")[0].toLowerCase().contains(textoLector)) {
							destinoSeleccionado = i;
							break;
						}
					}
				}

				try { //try catch para verificar que no hay errores de codigo si se escoge una opcion que no aparece en la lista
					System.out.println("Destino seleccionado: " + destinos2[destinoSeleccionado].split(";")[0]);
				}catch(Exception e) {
					System.out.println("{ NO EXISTE ESA OPCIÓN }");
					break; //Se sale del case y vuelta a empezar
				}
				
				
				//Mostrar vuelos por destino de todos los aeropuertos ::::::::::::::::::::::::::::::::::::::::::::::::::::
				for(int i=0;i<aeropuertos.length;i++) {
					if(aeropuertos[i] != null) { //Se muestran todos los vuelos, sin tener en cuenta si la fecha es anterior o posterior
						contadorV = 0;
						System.out.println("-- [ "+ aeropuertos[i].getNombre() +" ] --");
						
						aeropuertos[i].getVuelosPorDestino(destinos2[destinoSeleccionado].split(";")[0]);
					}
				}
				
				break;
			case 'f': // APARTADO F *******************************************************************************************************************************************************************
				/* Mostrar todos los vuelos desde un determinado lugar */ 
				//Declaraciones
				origenSeleccionado=-1;
				
				//Aeropuerto :::::::::::::::::::::::::::::::::::::::::::::::
				System.out.println("Lista de Aeropuertos:");
				for(int i = 0; i<aeropuertos.length;i++) {
					if (aeropuertos[i] == null) {
						break;
					}
					System.out.println((i+1) + ": " + aeropuertos[i].getNombre());
				}
				
				System.out.print("Seleccionar Aeropuerto: ");
				textoLector = lector.nextLine().toLowerCase();
				
				//Si se ha seleccionado la opcion con numero, se usa el propio numero
				if(textoLector.charAt(0) >= '1' && textoLector.charAt(0) <= '9') {
					origenSeleccionado = textoLector.charAt(0)-'0'-1; //-1 porque los i mostrados son +1 para no mostrar el 0
				}else { //Se da por hecho que han puesto el nombre del aeropuerto, por lo que se busca cual es
					for(int i = 0; i<aeropuertos.length;i++) {
						if (aeropuertos[i] == null) {
							break;
						}
						if (aeropuertos[i].getNombre().toLowerCase().contains(textoLector)) {
							origenSeleccionado = i;
							break;
						}
					}
				}
				try { //try catch para verificar que no hay errores de codigo si se escoge una opcion que no aparece en la lista
					System.out.println("Aeropuerto seleccionado: " + aeropuertos[origenSeleccionado].getNombre());
				}catch(Exception e) {
					System.out.println("{ NO EXISTE ESA OPCIÓN }");
					break; //Se sale del case y vuelta a empezar
				}
				
				//Mostrar vuelos de ese Aeropuerto ::::::::::::::::::::::::::::::::::::::::::::::::::::
				for(Vuelo vi : aeropuertos[origenSeleccionado].getVuelos()) { //Se muestran todos los vuelos, sin tener en cuenta si la fecha es anterior o posterior
					if(vi == null) {
						break;
					}
					System.out.println(vi);
				}
				
				break;
			case 'g': // APARTADO G *******************************************************************************************************************************************************************
				/* Mostrar la ganancia de vuelos comerciales o mercancía */
				//Declaraciones
				origenSeleccionado=-1;
				double gananciaG = 0;
				
				//Aeropuerto :::::::::::::::::::::::::::::::::::::::::::::::
				System.out.println("Lista de Aeropuertos:");
				for(int i = 0; i<aeropuertos.length;i++) {
					if (aeropuertos[i] == null) {
						break;
					}
					System.out.println((i+1) + ": " + aeropuertos[i].getNombre());
				}
				
				System.out.print("Seleccionar Aeropuerto: ");
				textoLector = lector.nextLine().toLowerCase();
				
				//Si se ha seleccionado la opcion con numero, se usa el propio numero
				if(textoLector.charAt(0) >= '1' && textoLector.charAt(0) <= '9') {
					origenSeleccionado = textoLector.charAt(0)-'0'-1; //-1 porque los i mostrados son +1 para no mostrar el 0
				}else { //Se da por hecho que han puesto el nombre del aeropuerto, por lo que se busca cual es
					for(int i = 0; i<aeropuertos.length;i++) {
						if (aeropuertos[i] == null) {
							break;
						}
						if (aeropuertos[i].getNombre().toLowerCase().contains(textoLector)) {
							origenSeleccionado = i;
							break;
						}
					}
				}
				try { //try catch para verificar que no hay errores de codigo si se escoge una opcion que no aparece en la lista
					System.out.println("Aeropuerto seleccionado: " + aeropuertos[origenSeleccionado].getNombre());
				}catch(Exception e) {
					System.out.println("{ NO EXISTE ESA OPCIÓN }");
					break; //Se sale del case y vuelta a empezar
				}
				
				//Preguntar tipo de vuelo para mostrar los datos ::::::::::::::::::::::::::::::::::::::::::::::::::::
				System.out.print("Mostrar vuelos comerciales o de mercancia? (c/m): ");
				textoLector = lector.nextLine().toLowerCase();
				
				if(textoLector.charAt(0) =='c') {
					for(Vuelo vi : aeropuertos[origenSeleccionado].getVuelos()) {
						if(vi instanceof VueloComercial && vi !=null) {
							gananciaG += vi.calcularGanancia();
							
							
						}
					}
					//Mostrar ganancias si son comerciales
					System.out.println("Las ganancias de vuelos comerciales del " + aeropuertos[origenSeleccionado].getNombre() + " son " + gananciaG + "€");
				}else if(textoLector.charAt(0) =='m') {
					for(Vuelo vi : aeropuertos[origenSeleccionado].getVuelos()) {
						if(vi instanceof VueloMercancia && vi !=null) {
							gananciaG += vi.calcularGanancia();
						}
					}
					//Mostrar ganancias si son mercancia
					System.out.println("Las ganancias de vuelos de mercancia del " + aeropuertos[origenSeleccionado].getNombre() + " son " + gananciaG + "€");
				}
				
				break;
			case 'h': // APARTADO H *******************************************************************************************************************************************************************
				/* Mostrar la ganancia total del aeropuerto. */
				//Declaraciones
				origenSeleccionado=-1;
				
				//Aeropuerto :::::::::::::::::::::::::::::::::::::::::::::::
				System.out.println("Lista de Aeropuertos:");
				for(int i = 0; i<aeropuertos.length;i++) {
					if (aeropuertos[i] == null) {
						break;
					}
					System.out.println((i+1) + ": " + aeropuertos[i].getNombre());
				}
				
				System.out.print("Seleccionar Aeropuerto: ");
				textoLector = lector.nextLine().toLowerCase();
				
				//Si se ha seleccionado la opcion con numero, se usa el propio numero
				if(textoLector.charAt(0) >= '1' && textoLector.charAt(0) <= '9') {
					origenSeleccionado = textoLector.charAt(0)-'0'-1; //-1 porque los i mostrados son +1 para no mostrar el 0
				}else { //Se da por hecho que han puesto el nombre del aeropuerto, por lo que se busca cual es
					for(int i = 0; i<aeropuertos.length;i++) {
						if (aeropuertos[i] == null) {
							break;
						}
						if (aeropuertos[i].getNombre().toLowerCase().contains(textoLector)) {
							origenSeleccionado = i;
							break;
						}
					}
				}
				try { //try catch para verificar que no hay errores de codigo si se escoge una opcion que no aparece en la lista
					System.out.println("Aeropuerto seleccionado: " + aeropuertos[origenSeleccionado].getNombre());
				}catch(Exception e) {
					System.out.println("{ NO EXISTE ESA OPCIÓN }");
					break; //Se sale del case y vuelta a empezar
				}
				
				//Mostrar ganancias totales ::::::::::::::::::::::::::::::::::::::::::::::::::::
				System.out.println("Las ganancias del " + aeropuertos[origenSeleccionado].getNombre() + " son " + aeropuertos[origenSeleccionado].getGanancia() + "€");
				break;
			case 'i': // APARTADO I *******************************************************************************************************************************************************************
				/* Calcular el vuelo que recorre más kilómetros */
				//Declaraciones
				origenSeleccionado=-1;
				Vuelo vDistancia;
				
				//Aeropuerto :::::::::::::::::::::::::::::::::::::::::::::::
				System.out.println("Lista de Aeropuertos:");
				for(int i = 0; i<aeropuertos.length;i++) {
					if (aeropuertos[i] == null) {
						break;
					}
					System.out.println((i+1) + ": " + aeropuertos[i].getNombre());
				}
				
				System.out.print("Seleccionar Aeropuerto: ");
				textoLector = lector.nextLine().toLowerCase();
				
				//Si se ha seleccionado la opcion con numero, se usa el propio numero
				if(textoLector.charAt(0) >= '1' && textoLector.charAt(0) <= '9') {
					origenSeleccionado = textoLector.charAt(0)-'0'-1; //-1 porque los i mostrados son +1 para no mostrar el 0
				}else { //Se da por hecho que han puesto el nombre del aeropuerto, por lo que se busca cual es
					for(int i = 0; i<aeropuertos.length;i++) {
						if (aeropuertos[i] == null) {
							break;
						}
						if (aeropuertos[i].getNombre().toLowerCase().contains(textoLector)) {
							origenSeleccionado = i;
							break;
						}
					}
				}
				try { //try catch para verificar que no hay errores de codigo si se escoge una opcion que no aparece en la lista
					System.out.println("Aeropuerto seleccionado: " + aeropuertos[origenSeleccionado].getNombre());
				}catch(Exception e) {
					System.out.println("{ NO EXISTE ESA OPCIÓN }");
					break; //Se sale del case y vuelta a empezar
				}
				
				//Inicializar primer vuelo ::::::::::::::::::::::::::::::::::::::::::::::::::::
				vDistancia = aeropuertos[origenSeleccionado].getVuelos().getFirst();
				
				//Buscar vuelo comparando distancias ::::::::::::::::::::::::::::::::::::::::::::::::::::
				for(Vuelo vi : aeropuertos[origenSeleccionado].getVuelos()) { 
					if(vi instanceof VueloComercial && vi !=null) {
						if(vi.getDistancia()>vDistancia.getDistancia()) {
							vDistancia = vi;
						}
					}
				}
				
				//Mostrar vuelo ::::::::::::::::::::::::::::::::::::::::::::::::::::
				System.out.println("El vuelo con mayor distancia es: \n" + vDistancia);
				
				break;
			default:
				salirWhile = true;
				System.out.println("Saliendo de la aplicación");
				break;				
			}
		}
		
		
	
	}

}
