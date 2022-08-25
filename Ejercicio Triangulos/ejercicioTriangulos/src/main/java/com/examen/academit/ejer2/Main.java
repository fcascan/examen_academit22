package main.java.com.examen.academit.ejer2;

import javax.swing.*;

import java.util.Objects;

import static java.lang.Double.parseDouble;

public class Main {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Hola! Soy una app que reconoce el tipo de triangulo segun sus lados\n" +
                "Acontinuacion se le pedira que ingrese los 3 lados del triangulo.");

        java.lang.String continuar;

        do {
            double ladoA = parseDouble(JOptionPane.showInputDialog("Ingrese el primer lado del triangulo: "));
            double ladoB = parseDouble(JOptionPane.showInputDialog("Ingrese el segundo lado del triangulo: "));
            double ladoC = parseDouble(JOptionPane.showInputDialog("Ingrese el tercer lado del triangulo: "));

            if (ladoA == ladoB && ladoA == ladoC) {
                //Equilatero: ladoA = ladoB = ladoC
                JOptionPane.showMessageDialog(null, "Su triangulo es Equilatero!\n" +
                        "Lado 1: " + ladoA + ", \nLado B: " + ladoB + ", \nLado C: " + ladoC);
            } else {
                if (ladoA != ladoB && ladoA != ladoC && ladoB != ladoC) {
                    //Escaleno: ladoA != ladoB != ladoC
                    JOptionPane.showMessageDialog(null, "Su triangulo es Escaleno!\n" +
                            "Lado 1: " + ladoA + ", \nLado B: " + ladoB + ", \nLado C: " + ladoC);
                } else {
                    //Isosceles: 2 lados iguales (todos los demas casos)
                    JOptionPane.showMessageDialog(null, "Su triangulo es Isosceles!\n" +
                            "Lado 1: " + ladoA + ",\nLado B: " + ladoB + ", \nLado C: " + ladoC);
                }
            }
            continuar = JOptionPane.showInputDialog("Desea volver a hacer otra prueba?\n(Ingrese \"Y\" para continuar): ");
        } while (Objects.equals(continuar.toLowerCase(), "y"));
        JOptionPane.showMessageDialog(null, "Adios! Gracias por usar nuestra app \"triangulator3000\"\nVuelva pronto!");
    }
}
