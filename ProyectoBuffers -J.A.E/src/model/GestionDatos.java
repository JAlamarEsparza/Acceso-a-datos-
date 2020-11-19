package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestionDatos {

	public GestionDatos() {

	}

	
	
	//Con esta función abriremos abriremos los ficheros
	public static BufferedReader abrirFichero (String fichero) throws FileNotFoundException{
		
		//Creamos un objeto FileReader el cual le decimos que utilice el fichero como argumento para que entre en el
		
		FileReader abrirFichero = new FileReader(fichero);
		
		// Creamos el BufferedReader y le pasamos como parametro el FileReader para que ya una vezz dentro se pueda leer el contenido
		
		BufferedReader leerContenido = new BufferedReader(abrirFichero);
	
		
		return leerContenido;
		//Al devolver el BufferedReader llamando a esta funcion podremos entrar y leer lo que hay dentro de los ficheros
	}
	
	//Con esta funcion que vamos a declarar cerraremos el buffer del fichero que este abierto mediante un buffer, se utilizara después de abrirFichero
	
	public static void cerrarFichero (BufferedReader contenido) throws IOException {
		contenido.close();
	}
	
	//Con esta funcion pretendemos comparar el contenido de los ficheros que se pasan por parametro para saber si son iguales o no mediante un boolean
	
	public static boolean compararContenido (String fichero1, String fichero2) throws IOException{
		
		//Creamos una array que contendra 2 elementos BuffereReader, las lineas 
		
		BufferedReader[] leerContenido = new BufferedReader[1];
		//Mediante el llamado a la funcion abrirFichero hacemos que el fichero se abra y podamos acceder a su contenido convirtiendolo en un BufferedReader		
		leerContenido[0] = abrirFichero(fichero1);
		leerContenido[1] = abrirFichero(fichero2);
		
		//Ahora creamos las variables para poder leer el contenido de la primera linea de los BufferedReader dentro de la array
		String lineaFichero1 = leerContenido[0].readLine();
		String lineaFichero2 = leerContenido[1].readLine();
		
		//Creacion del bulce para leer linea a linea los archivos hasta que uno de los dos sea diferente o lleguen al final que es cuando se devuelve null, que 
		//quiere decir que ya no devuelve nada por lo que no hay mas contenido
		while(lineaFichero1 != null && lineaFichero2 != null) {
			if(lineaFichero1.equals(lineaFichero2)) {
				return true;
			}else {
				lineaFichero1 = leerContenido[0].readLine();
				lineaFichero2 = leerContenido[1].readLine();
			}
	}	
	//Siempre se deben de cerrar los archivos que abramos para no obtener fallos en la apliaccion
	cerrarFichero(leerContenido[0]);
	cerrarFichero(leerContenido[1]);
		
		
		return true;
	}
	
	public static int buscarPalabra (String fichero1, String palabra, boolean primera_aparicion) throws FileNotFoundException, IOException{
		//Abrimos los archivos y leemos la primera linea
		
		BufferedReader[] br =new BufferedReader[1];
		br[0] =abrirFichero(fichero1);
		String lineaFichero1 = br[0].readLine();
		
		//la inicianlizacion de finFichero a -1 es porque cuando se acaba el documento devuelve el valor -1
		
		int linea = 1, ultimaLinea =-1;
		
		//Creamos una ArrayList para posteriormente separar las lineas en palabras guardandolas en la Array para su posterior comparacion con la palabra pasada por parametro
		
		List<String> palabras = new ArrayList<String>();
		
		//Entrara en el bucle si la opcion de buscar palabra esta seleccionada en la interfaz grafica
		if(primera_aparicion) {
			
			//Con este bucle recorreremos el fichero mientras que no se acabe, es decir, mientras no de -1 
			//Dividiremos la linea del contenido en palabras que se guardaran de manera independiente en la array y se compararan con la palabra pasada por parametro
			//Repitiendo así el bucle hasta que encuentre la primera coincidencia y devolvera la linea del fichero en la que se ha encontrado la palabra por primera vez
			
			while (lineaFichero1 != null) {
				palabras = Arrays.asList(lineaFichero1.split(" "));
				if(palabras.contains(palabra)) {
					return linea;
				}
				lineaFichero1 = br[0].readLine();
				linea++;
			}
		//Si la opcion no esta marcada en la interfaz grafica entrara por esta parte del bucle
		}else {
			//Este bucle es el mismo que arriba pero la diferencia reside en que aqui la variable linea es utilizada como un incrementador de la variable ultima linea
			//Por lo que la variable que devolveremos mostrara la ultima aparicion de esta palabra si es que esta en el contenido del fichero
			while (lineaFichero1 != null) {
				palabras = Arrays.asList(lineaFichero1.split(" "));
				if(palabras.contains(palabra)) {
					ultimaLinea = linea;
				}
				lineaFichero1 = br[0].readLine();
				linea++;
			}
			return ultimaLinea;
		}
		cerrarFichero(br[0]);
		
		
		return -1;
	}	

}
