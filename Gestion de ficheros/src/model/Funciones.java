/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.IOException;

public class Funciones {

    public static void createFolder(String fileName) {
        new File(fileName).mkdir(); // Simple y directo
    }
}
