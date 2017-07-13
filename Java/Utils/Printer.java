/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 *
 * @author joni
 */
public class Printer {

    public static void print(String content, String file) throws FileNotFoundException {
        try (PrintStream out = new PrintStream(new FileOutputStream(file))) {
            out.print(content);
        }
    }

    public static void arrayPrint(int[][] data, String file) {
        try {
            PrintWriter pr = new PrintWriter(file);

            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    pr.print(data[i][j]);
                    if (j != data[0].length - 1) {
                        pr.print(" ");
                    }
                }
                pr.println();
            }
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No such file exists.");
        }
    }

    public static void arrayPrint(double[][] data, String file) {
        try {
            PrintWriter pr = new PrintWriter(file);

            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    pr.print(data[i][j]);
                    if (j != data[0].length - 1) {
                        pr.print(" ");
                    }
                }
                pr.println();
            }
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No such file exists.");
        }
    }
}
