package view.console;

import model.Funciones;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String opcion;

        do {
            // Menú directamente en el main
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Crear carpeta");
            System.out.println("2. Crear archivo y añadir contenido");
            System.out.println("3. Listar archivos");
            System.out.println("4. Mostrar contenido archivo");
            System.out.println("5. Sobrescribir archivo");
            System.out.println("6. Borrar archivo");
            System.out.println("7. Contar caracteres");
            System.out.println("8. Contar palabras");
            System.out.println("9. Reemplazar palabras");
            System.out.println("Z. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextLine().toUpperCase();

            switch (opcion) {
                case "1" :
                    crearCarpeta();
                case "2" :
                    crearArchivo();
                case "3" :
                    listarArchivos();
                case "4" :
                    mostrarArchivo();
                case "5" :
                    sobrescribirArchivo();
                case "6" :
                    borrarArchivo();
                case "7" :
                    contarCaracteres();
                case "8" :
                    contarPalabras();
                case "9" :
                    reemplazarPalabras();
                case "Z" :
                    System.out.println("Saliendo del programa...");
                default :
                    System.out.println("Opción inválida.");
            }

        } while (!opcion.equals("10"));

        sc.close();
    }

    private static void crearCarpeta() {
        System.out.print("Nombre de la carpeta: ");
        String folderName = sc.nextLine();
        Funciones.createFolder(folderName);
        System.out.println("Carpeta creada (si no existía).");
    }

    private static void crearArchivo() {
        System.out.print("Ruta: ");
        String path = sc.nextLine();
        System.out.print("Nombre del archivo: ");
        String fileName = sc.nextLine();
        System.out.print("Contenido: ");
        String content = sc.nextLine();
        try {
            Funciones.createFile(path, fileName, content);
            System.out.println("Contenido añadido.");
        } catch (IOException e) {
            System.out.println("Error al escribir archivo.");
        }
    }

    private static void listarArchivos() {
        System.out.print("Ruta: ");
        String path = sc.nextLine();
        String[] files = Funciones.showListFiles(path);
        if (files != null) {
            for (String file : files) {
                System.out.println(file);
            }
        } else {
            System.out.println("No se pudo listar archivos.");
        }
    }

    private static void mostrarArchivo() {
        System.out.print("Ruta: ");
        String path = sc.nextLine();
        System.out.print("Nombre del archivo: ");
        String fileName = sc.nextLine();
        try {
            System.out.println(Funciones.showFile(path, fileName));
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
    }

    private static void sobrescribirArchivo() {
        System.out.print("Ruta: ");
        String path = sc.nextLine();
        System.out.print("Nombre del archivo: ");
        String fileName = sc.nextLine();
        System.out.print("Nuevo contenido: ");
        String content = sc.nextLine();
        try {
            if (Funciones.overWriteFile(path, fileName, content)) {
                System.out.println("Archivo sobrescrito.");
            } else {
                System.out.println("El archivo no existe.");
            }
        } catch (IOException e) {
            System.out.println("Error al sobrescribir el archivo.");
        }
    }

    private static void borrarArchivo() {
        System.out.print("Ruta: ");
        String path = sc.nextLine();
        System.out.print("Nombre del archivo: ");
        String fileName = sc.nextLine();
        Funciones.deleteFile(path, fileName);
        System.out.println("Archivo eliminado (si existía).");
    }

    private static void contarCaracteres() {
        System.out.print("Ruta: ");
        String path = sc.nextLine();
        System.out.print("Nombre del archivo: ");
        String fileName = sc.nextLine();
        try {
            int charCount = Funciones.countChars(path, fileName);
            System.out.println("Caracteres: " + charCount);
        } catch (IOException e) {
            System.out.println("Error al contar caracteres.");
        }
    }

    private static void contarPalabras() {
        System.out.print("Ruta: ");
        String path = sc.nextLine();
        System.out.print("Nombre del archivo: ");
        String fileName = sc.nextLine();
        try {
            int wordCount = Funciones.countWords(path, fileName);
            System.out.println("Palabras: " + wordCount);
        } catch (IOException e) {
            System.out.println("Error al contar palabras.");
        }
    }

    private static void reemplazarPalabras() {
        System.out.print("Ruta: ");
        String path = sc.nextLine();
        System.out.print("Nombre del archivo: ");
        String fileName = sc.nextLine();
        System.out.print("Palabra a reemplazar: ");
        String oldWord = sc.nextLine();
        System.out.print("Nueva palabra: ");
        String newWord = sc.nextLine();
        try {
            String result = Funciones.swapWords(path, fileName, oldWord, newWord);
            System.out.println("Contenido actualizado:\n" + result);
        } catch (IOException e) {
            System.out.println("Error al reemplazar palabras.");
        }
    }
}
