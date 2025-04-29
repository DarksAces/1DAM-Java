/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.console;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author danielgarbru
 */
public class Main {

    static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String fileName = "C:\\Users\\danielgarbru\\Desktop\\A.txt";
        String opcion = null;

        do {
            try {
                System.out.println("Menu: ");
                System.out.println("1.- Leer fichero");
                System.out.println("Z.- Salir: ");
                System.out.print("Opcion: ");
                opcion = sc.next().toUpperCase();

                switch (opcion) {
                    case "1":
                        System.out.print("Nombre de la carpeta a crear: ");
                        sc.nextLine(); // limpiar el buffer
                        String nombreCarpeta = sc.nextLine();
                        createFolder(nombreCarpeta);
                        break;
                    case "Z":
                        System.out.println("Saliendo...");
                        System.out.println("Saliendo...");
                        System.out.println("Saliendo...");
                        System.out.println("Se marcho");
                        break;
                    default:
                        System.out.println("Opcion incorrecta");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }

        } while (!opcion.equals("Z"));

    }

    private static void createFolder(String folderName) {
        File carpeta = new File(folderName);
        if (!carpeta.exists()) {
            if (carpeta.mkdirs()) {
                System.out.println("Carpeta creada correctamente: " + folderName);
            } else {
                System.out.println("No se pudo crear la carpeta.");
            }
        } else {
            System.out.println("La carpeta ya existe: " + folderName);
        }
    }
}
