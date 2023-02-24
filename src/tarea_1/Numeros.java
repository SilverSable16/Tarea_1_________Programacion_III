package tarea_1;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Numeros extends JFrame {

    private JButton btnGenerar, btnOrdenar;
    private JTextArea txtEstado;
    private final int n = 1000000;
    private int[] numeros = new int[n];
    private Random rand = new Random();

    public Numeros() {
        super("Generador y Ordenador de Números Aleatorios");

        // Crear componentes de la interfaz
        btnGenerar = new JButton("Generar");
        btnOrdenar = new JButton("Ordenar");
        txtEstado = new JTextArea("Haga clic en el botón \"Generar\" para comenzar.", 5, 30);
        txtEstado.setEditable(false);
        txtEstado.setLineWrap(true);

        // Configurar los botones
        btnGenerar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generarNumerosAleatorios();
            }
        });
        btnOrdenar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ordenarNumerosAleatorios();
            }
        });

        // Configurar el panel principal
        JPanel pnlPrincipal = new JPanel(new BorderLayout(10, 10));
        pnlPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlPrincipal.add(btnGenerar, BorderLayout.WEST);
        pnlPrincipal.add(btnOrdenar, BorderLayout.EAST);
        pnlPrincipal.add(txtEstado, BorderLayout.SOUTH);

        // Configurar la ventana
        setContentPane(pnlPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void generarNumerosAleatorios() {
        try {
            FileWriter fw = new FileWriter("numeros.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < n; i++) {
                int numero = rand.nextInt(20000001) - 10000000;
                bw.write(Integer.toString(numero));
                bw.newLine();
                numeros[i] = numero;
            }
            bw.close();
            txtEstado.setText("Archivo creado exitosamente.");
        } catch (IOException ex) {
            txtEstado.setText("Ocurrió un error al crear el archivo.");
            ex.printStackTrace();
        }
    }

    private void ordenarNumerosAleatorios() {
        try {
            FileReader fr = new FileReader("numeros.txt");
            BufferedReader br = new BufferedReader(fr);
            for (int i = 0; i < n; i++) {
                numeros[i] = Integer.parseInt(br.readLine());
            }
            br.close();
            txtEstado.setText("Archivo leído exitosamente.");

            Arrays.sort(numeros);

            FileWriter fw = new FileWriter("numeros_ordenados.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < n; i++) {
                bw.write(Integer.toString(numeros[i]));
                bw.newLine();
            }
            bw.close();
            txtEstado.setText("Archivo creado exitosamente.");
        } catch (IOException ex) {
            txtEstado.setText("Ocurrió un error al leer o crear el archivo.");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Numeros();
            }
        });
    }

}

