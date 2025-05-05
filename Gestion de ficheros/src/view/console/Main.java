import model.Funciones;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option;
        String path = "";
        String fileName;
        String content;

        do {
            System.out.println("1. Crear carpeta");
            System.out.println("2. Crear archivo");
            System.out.println("3. Ver archivos en carpeta");
            System.out.println("4. Ver contenido de un archivo");
            System.out.println("5. Sobreescribir archivo");
            System.out.println("6. Borrar archivo");
            System.out.println("7. Contar caracteres");
            System.out.println("8. Contar palabras");
            System.out.println("9. Reemplazar palabras");
            System.out.println("0. Salir");
            option = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (option) {
                case 1:
                    System.out.println("Introduce el nombre de la carpeta");
                    path = sc.nextLine();
                    Funciones.createFolder(path);
                    System.out.println("Carpeta creada");
                    break;
                case 2:
                    System.out.println("Introduce el nombre de la carpeta");
                    path = sc.nextLine();
                    System.out.println("Introduce el nombre del archivo");
                    fileName = sc.nextLine();
                    System.out.println("Introduce el contenido");
                    content = sc.nextLine();
                    try {
                        Funciones.createFile(path, fileName, content);
                        System.out.println("Archivo creado");
                    } catch (IOException e) {
                        System.out.println("Error al crear el archivo");
                    }
                    break;
                case 3:
                    System.out.println("Introduce la ruta de la carpeta");
                    path = sc.nextLine();
                    String[] files = Funciones.showListFiles(path);
                    if (files != null) {
                        for (String file : files) {
                            System.out.println(file);
                        }
                    } else {
                        System.out.println("No es una carpeta valida");
                    }
                    break;
                case 4:
                    System.out.println("Introduce la ruta de la carpeta");
                    path = sc.nextLine();
                    System.out.println("Introduce el nombre del archivo");
                    fileName = sc.nextLine();
                    try {
                        System.out.println(Funciones.showFile(path, fileName));
                    } catch (IOException e) {
                        System.out.println("Error al leer el archivo");
                    }
                    break;
                case 5:
                    System.out.println("Introduce la ruta de la carpeta");
                    path = sc.nextLine();
                    System.out.println("Introduce el nombre del archivo");
                    fileName = sc.nextLine();
                    System.out.println("Introduce el nuevo contenido");
                    content = sc.nextLine();
                    try {
                        if (Funciones.overWriteFile(path, fileName, content)) {
                            System.out.println("Archivo sobrescrito");
                        } else {
                            System.out.println("El archivo no existe");
                        }
                    } catch (IOException e) {
                        System.out.println("Error al sobrescribir el archivo");
                    }
                    break;
                case 6:
                    System.out.println("Introduce la ruta de la carpeta");
                    path = sc.nextLine();
                    System.out.println("Introduce el nombre del archivo");
                    fileName = sc.nextLine();
                    Funciones.deleteFile(path, fileName);
                    System.out.println("Archivo borrado");
                    break;
                case 7:
                    System.out.println("Introduce la ruta de la carpeta");
                    path = sc.nextLine();
                    System.out.println("Introduce el nombre del archivo");
                    fileName = sc.nextLine();
                    try {
                        int chars = Funciones.countChars(path, fileName);
                        System.out.println("Caracteres: " + chars);
                    } catch (IOException e) {
                        System.out.println("Error al contar caracteres");
                    }
                    break;
                case 8:
                    System.out.println("Introduce la ruta de la carpeta");
                    path = sc.nextLine();
                    System.out.println("Introduce el nombre del archivo");
                    fileName = sc.nextLine();
                    try {
                        int words = Funciones.countWords(path, fileName);
                        System.out.println("Palabras: " + words);
                    } catch (IOException e) {
                        System.out.println("Error al contar palabras");
                    }
                    break;
                case 9:
                    System.out.println("Introduce la ruta de la carpeta");
                    path = sc.nextLine();
                    System.out.println("Introduce el nombre del archivo");
                    fileName = sc.nextLine();
                    System.out.println("Palabra a reemplazar");
                    String oldWord = sc.nextLine();
                    System.out.println("Nueva palabra");
                    String newWord = sc.nextLine();
                    try {
                        String result = Funciones.swapWords(path, fileName, oldWord, newWord);
                        System.out.println(result);
                    } catch (IOException e) {
                        System.out.println("Error al reemplazar palabras");
                    }
                    break;
                case 0:
                    System.out.println("Saliendo");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (option != 0);

        sc.close();
    }
}
