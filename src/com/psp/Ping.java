package com.psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ping {

	public static void main(String[] args) {
		if(args.length <= 0) {
			System.err.println("Se necesita como argumento un proceso a ejecutar");
			System.exit(-1);
		}
		
		ProcessBuilder pb = new ProcessBuilder(args);
		
		try {
			Process proceso = pb.start();
			MostrarSalidaProceso(proceso);
			if(proceso  != null) {
				proceso.destroy();
			}
			try {
				proceso.waitFor();
			} catch (InterruptedException e) {
				
			}
			System.exit(0);
			
		} catch (IOException e) {
			System.err.println("Error de E/S");
			System.exit(-1);
		}		
	}

	private static void MostrarSalidaProceso(Process proceso) {
		try {			
			InputStreamReader lector = new InputStreamReader(proceso.getInputStream(), "utf-8");
			BufferedReader br = new BufferedReader(lector);
			
			String linea;
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}