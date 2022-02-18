package examenFicheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	/**
	 * Pre: ---
	 * Post: Este metodo abre un archivo que contiene los datos de los 
	 * pasajeros del Titanic y recopila los datos que se consideran
	 * necesarios.
	 */
	public static void main(String[] args) {	
		Scanner entrada = new Scanner(System.in);
		System.out.println("Introduce la ruta del fichero junto al nombre:");
		String ruta = entrada.nextLine();
		File fichero = new File (ruta);
		try {
			// Asociamos un objeto de tipo Scanner al fichero para poder leerlo
			Scanner f = new Scanner(fichero);
			// Creamos todos los contadores necesarios
			int contadorPasajeros = 0;
			int contadorMujeres = 0;
			int contadorHombres = 0;
			int contadorHombresMuertos = 0;
			int contadorMujeresMuertas = 0;
			// Mientras el fichero tenga lineas
			while (f.hasNextLine()) {
				String pasajero = f.nextLine();
				// Separamos los campos por su separador [;]
				String[] datos = pasajero.split(",");
				// Obtenemos los datos que nos interesan
				String superviviente = datos[1]; // Si es 0 está muerto y si es 1 está vivo
				String sexo = datos[5];
				// Evitamos la cabecera del .csv
				if (contadorPasajeros != 0) {
					if (sexo.equals("female")) {
						contadorMujeres++;
						if (superviviente.equals("0")) {
							contadorMujeresMuertas++;
						}
					} else {
						contadorHombres++;
						if (superviviente.equals("0")) {
							contadorHombresMuertos++;
						}
					}
				}
				// Por cada linea que tenga un fichero sera un pasajero
				contadorPasajeros++;
			}
			f.close();
			// Calculamos los porcentajes
			double porcentajeHombres = (contadorHombresMuertos * 100) / contadorHombres;
			double porcentajeMujeres = (contadorMujeresMuertas * 100) / contadorMujeres;
			// Al contador le restamos la cabecera
			System.out.println("El numero total de pasajeros del Titanic fue: " + (contadorPasajeros - 1));
			System.out.println("El numero total de hombres era de: " + contadorHombres);
			System.out.println("El numero total de mujeres era de: " + contadorMujeres);
			System.out.println("El numero total de hombres fallecidos es de: " + contadorHombresMuertos);
			System.out.println("Porcentaje de hombres fallecidos: " + porcentajeHombres);
			System.out.println("El numero total de mujeres fallecidas es de: " + contadorMujeresMuertas);
			System.out.println("Porcentaje de mujeres fallecidas es de: " + porcentajeMujeres);
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido acceder al fichero");
			e.printStackTrace();
		}
	}
}
