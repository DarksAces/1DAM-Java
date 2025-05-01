/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.*;
import java.util.Scanner;

public class Funciones {

    // 1. Crear carpeta
    public static void createFolder(String folderName) {
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    // 2. Crear o añadir contenido a archivo
    public static void createFile(String path, String fileName, String content) throws IOException {
        File file = new File(path, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(content);
            writer.newLine();
        }
    }

    // 3. Listar archivos
    public static String[] showListFiles(String path) {
        File folder = new File(path);
        return folder.list();
    }

    // 4. Mostrar contenido del archivo
    public static String showFile(String path, String fileName) throws IOException {
        File file = new File(path, fileName);
        StringBuilder content = new StringBuilder();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                content.append(sc.nextLine()).append("\n");
            }
        }
        return content.toString();
    }

    // 5. Sobrescribir archivo
    public static boolean overWriteFile(String path, String fileName, String newContent) throws IOException {
        File file = new File(path, fileName);
        if (!file.exists()) return false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            writer.write(newContent);
        }
        return true;
    }

    // 6. Borrar archivo
    public static void deleteFile(String path, String fileName) {
        File file = new File(path, fileName);
        file.delete();
    }

    // 7. Contar caracteres
    public static int countChars(String path, String fileName) throws IOException {
        File file = new File(path, fileName);
        int count = 0;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                count += sc.nextLine().length();
            }
        }
        return count;
    }

    // 8. Contar palabras
    public static int countWords(String path, String fileName) throws IOException {
        File file = new File(path, fileName);
        int count = 0;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                sc.next();
                count++;
            }
        }
        return count;
    }

    // 9. Reemplazar palabras
    public static String swapWords(String path, String fileName, String oldWord, String newWord) throws IOException {
        File file = new File(path, fileName);
        StringBuilder content = new StringBuilder();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().replace(oldWord, newWord);
                content.append(line).append("\n");
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content.toString());
        }
        return content.toString();
    }

    // 10. Crear PDF (se deja vacío o como tarea si no se usa iText o PDFBox)
    public static void printPDF(String path, String fileName) {
        // Requiere librería externa como iText o Apache PDFBox (no obligatorio aquí)
    }
}
