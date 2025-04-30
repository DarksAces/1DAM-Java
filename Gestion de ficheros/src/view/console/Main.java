package view.console;

import java.util.Scanner;
import model.Funciones;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String opcion;
        
        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Crear carpeta con mi nombre");
            System.out.println("Z. Salir");
            System.out.print("Elija una opción: ");
            
            opcion = sc.next().toUpperCase();
            
            switch (opcion) {
                case "1":
                    // Crear carpeta con el nombre del alumno (sin verificar existencia)
                    Funciones.createFolder("DanielGarBru"); // Nombre hardcodeado
                    break;
                case "Z":
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (!opcion.equals("Z"));
    }
}