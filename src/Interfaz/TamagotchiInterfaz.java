package Interfaz;

import Filtro.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import PersistenciaDatos.Tamagotchi;
import PersistenciaDatos.Manejo_Archivos;

public class TamagotchiInterfaz extends JFrame implements Runnable {

    // Iconos
    ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource("/IconosTamagotchi/IconoPrincipal.png")));
    ImageIcon img1 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/IconosTamagotchi/Goku_Normal_1.png")));
    ImageIcon img2 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/IconosTamagotchi/Goku_Jugando _1.png")));
    ImageIcon img3 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/IconosTamagotchi/Goku_Baño _1.png")));
    ImageIcon img4 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/IconosTamagotchi/Goku_Durmiendo _1.png")));
    ImageIcon img5 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/IconosTamagotchi/Goku_Comiendo _1.png")));
    ImageIcon img6 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/IconosTamagotchi/Goku_Muerto _1.png")));
    ImageIcon img7 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/IconosTamagotchi/Goku_Level.png")));

    // JLabel
    public JLabel NivelLabel;
    private JLabel statusimagen;
    private JLabel EatLabel;
    private JLabel DormirLabel;
    private JLabel JugarLabel;
    private JLabel BañarLabel;
    private JLabel HambreLabel;
    private JLabel felicidadLabel;
    private JLabel suciedadLabel;
    private JLabel energiaLabel;
    private JLabel LevelLabel;

    // JButton
    private JButton EatButton;
    private JButton DormirButton;
    private JButton JugarButton;
    private JButton BañarButton;
    private JButton volverButton;

    // JProgressBar
    public JProgressBar hambre;
    public JProgressBar felicidad;
    public JProgressBar suciedad;
    public JProgressBar energia;

    // Constructores
    Manejo_Archivos manejoArchivos = new Manejo_Archivos();
    Tamagotchi ruta = new Tamagotchi();
    // Variables
    int valueHambre;
    int valueSuciedad;
    int valueEnergia;
    int valuefelicidad;
    private static final String RUTA = "src/Archivos_Bin"; // Ruta donde se crearán los archivos

    public TamagotchiInterfaz() {

        // Configuración Ventana
        setTitle("Tamagotchi");
        setSize(400, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(229, 124, 35));
        setIconImage(img.getImage());

        //Configuración JProgressBar
        hambre = new JProgressBar(0,100);
        hambre.setForeground(Color.red);
        hambre.setBounds(20,20,130,20);
        hambre.setStringPainted(true); // habilitación para llenar la barra de progreso

        felicidad = new JProgressBar(0,100);
        felicidad.setForeground(Color.green);
        felicidad.setBounds(20,60,130,20);
        felicidad.setStringPainted(true);

        suciedad = new JProgressBar(0,100);
        suciedad.setForeground(Color.pink);
        suciedad.setBounds(220,20,130,20);
        suciedad.setStringPainted(true);

        energia = new JProgressBar(0,100);
        energia.setForeground(Color.orange);
        energia.setBounds(220,60,130,20);
        energia.setStringPainted(true);

        // Configuración Label
        NivelLabel = new JLabel("0");
        NivelLabel.setForeground(Color.orange);
        NivelLabel.setBounds(170,20,50,40);
        NivelLabel.setFont(new Font("Arial", Font.BOLD, 44));

        statusimagen = new JLabel();
        statusimagen.setIcon(img1);
        statusimagen.setBounds(70,90,270,280);
        statusimagen.setOpaque(true);
        statusimagen.setBackground(Color.white);
        statusimagen.setBorder(new RoundedBorder(10));

        HambreLabel = new JLabel("Hambre");
        HambreLabel.setForeground(Color.black);
        HambreLabel.setBounds(60,1,130,20);

        felicidadLabel = new JLabel("Felicidad");
        felicidadLabel.setForeground(Color.black);
        felicidadLabel.setBounds(60,40,130,20);

        suciedadLabel = new JLabel("Suciedad");
        suciedadLabel.setForeground(Color.black);
        suciedadLabel.setBounds(260,1,130,20);

        energiaLabel = new JLabel("Energía");
        energiaLabel.setForeground(Color.black);
        energiaLabel.setBounds(260,40,130,20);

        LevelLabel = new JLabel("Nivel");
        LevelLabel.setForeground(Color.orange);
        LevelLabel.setFont(new Font("Arial", Font.BOLD, 15));
        LevelLabel.setBounds(170,50,40,40);

        EatLabel = new JLabel("Comer");
        EatLabel.setForeground(Color.black);
        EatLabel.setBounds(30,365,40,40);

        DormirLabel = new JLabel("Dormir");
        DormirLabel.setForeground(Color.black);
        DormirLabel.setBounds(210,365,40,40);

        JugarLabel = new JLabel("Jugar");
        JugarLabel.setForeground(Color.black);
        JugarLabel.setBounds(120,365,40,40);

        BañarLabel = new JLabel("Bañar");
        BañarLabel.setForeground(Color.black);
        BañarLabel.setBounds(300,365,40,40);

        // Configuración Button
        EatButton = new JButton();
        EatButton.setBounds(35,400,40,40);
        EatButton.setBackground(Color.white);
        EatButton.setForeground(Color.black);
        EatButton.setBorder(new RoundedBorder(100)); // Aquí se establece el radio del botón

        DormirButton = new JButton();
        DormirButton.setBounds(215,400,40,40);
        DormirButton.setBackground(Color.white);
        DormirButton.setForeground(Color.black);
        DormirButton.setBorder(new RoundedBorder(100));

        JugarButton = new JButton();
        JugarButton.setBounds(125,400,40,40);
        JugarButton.setBackground(Color.white);
        JugarButton.setForeground(Color.black);
        JugarButton.setBorder(new RoundedBorder(100));

        BañarButton = new JButton();
        BañarButton.setBounds(305,400,40,40);
        BañarButton.setBackground(Color.white);
        BañarButton.setForeground(Color.black);
        BañarButton.setBorder(new RoundedBorder(100));

        volverButton = new JButton();
        volverButton.setBounds(10,200,40,40);
        volverButton.setBackground(Color.red);
        volverButton.setForeground(Color.black);
        volverButton.setBorder(new RoundedBorder(100));

        EatButton.addActionListener(new ActionListener() { // escucha para alimentar
            public void actionPerformed(ActionEvent e) {
                int valueHambre = hambre.getValue(); // Obtengo el porcentaje de hambre
                if (valueHambre < hambre.getMaximum()) { // hambre menor al maximo
                    if(valueHambre > hambre.getMinimum()) { // Hambre mayor al minimo
                        hambre.setValue(valueHambre - 10);
                        statusimagen.setIcon(img5);
                    }
                }
            }
        });

        DormirButton.addActionListener(new ActionListener() { // escucha para dormir
            public void actionPerformed(ActionEvent e) {
                int valueEnergia = energia.getValue();
                if (valueEnergia < energia.getMaximum()) {
                    if(valueEnergia > energia.getMinimum()) {
                        energia.setValue(valueEnergia + 10);
                        statusimagen.setIcon(img4);
                    }
                }
            }
        });

        JugarButton.addActionListener(new ActionListener() { // escucha para jugar
            public void actionPerformed(ActionEvent e) {
                int valuefelicidad = felicidad.getValue();
                if (valuefelicidad < felicidad.getMaximum()) {
                    if(valuefelicidad > felicidad.getMinimum()) {
                        felicidad.setValue(valuefelicidad + 10);
                        statusimagen.setIcon(img2);
                    }
                }
            }
        });
        BañarButton.addActionListener(new ActionListener() { // escucha para bañar
            public void actionPerformed(ActionEvent e) {
                int valueSuciedad = suciedad.getValue();
                if (valueSuciedad < suciedad.getMaximum()) {
                    if(valueSuciedad > suciedad.getMinimum()) {
                        suciedad.setValue(valueSuciedad - 10);
                        statusimagen.setIcon(img3);
                    }
                }
            }
        });
        volverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String rutaActual = "src/Archivos_Bin/" + obtenerUltimoArchivoBin();
                System.out.println(rutaActual);
                ruta.setRuta(rutaActual);
                guardarTamagochi();
                TamagotchiMenu menu = new TamagotchiMenu();
                setVisible(false);
                menu.setVisible(true);
            }
        });

        add(EatButton);
        add(EatLabel);
        add(DormirButton);
        add(DormirLabel);
        add(JugarButton);
        add(JugarLabel);
        add(BañarButton);
        add(BañarLabel);
        add(statusimagen);
        add(suciedad);
        add(felicidad);
        add(energia);
        add(hambre);
        add(NivelLabel);
        add(LevelLabel);
        add(HambreLabel);
        add(suciedadLabel);
        add(felicidadLabel);
        add(energiaLabel);
        add(volverButton);

        tiempo();
        tiempo_Level();
        run();
    }

    public void tiempo(){
        // inicializo timer en 10000 ms
        Timer timer = new Timer(10000, e -> {
            // Obtengo los valores de las barras de progreso
            valueHambre = hambre.getValue();
            valueSuciedad = suciedad.getValue();
            valueEnergia = energia.getValue();
            valuefelicidad = felicidad.getValue();

            // condición para morir
            if(valueHambre > 80 & valueSuciedad > 80 | valueEnergia < 20 & valuefelicidad < 20){
                statusimagen.setIcon(img6);
                EatButton.setEnabled(false);
                DormirButton.setEnabled(false);
                JugarButton.setEnabled(false);
                BañarButton.setEnabled(false);

            }else {
                statusimagen.setIcon(img1);

                //  Control de los eventos de la barra de estado
                if (valueHambre <= hambre.getMaximum()) {
                    if (valueHambre >= hambre.getMinimum()) {
                        hambre.setValue(valueHambre + 10);
                    }
                }

                if (valueSuciedad <= suciedad.getMaximum()) {
                    if (valueSuciedad >= suciedad.getMinimum()) {
                        suciedad.setValue(valueSuciedad + 10);
                    }
                }

                if (valueEnergia <= energia.getMaximum()) {
                    if (valueEnergia >= energia.getMinimum()) {
                        energia.setValue(valueEnergia - 10);
                    }
                }

                if (valuefelicidad <= felicidad.getMaximum()) {
                    if (valuefelicidad <= felicidad.getMaximum()) {
                        felicidad.setValue(valuefelicidad - 10);
                    }
                }
            }
        });
        timer.start(); // incio el timer
    }

    public void tiempo_Level(){ // incremento del nivel
        AtomicInteger Nivel = new AtomicInteger(); // contador
        // timer de 10000 ms
        Timer timer = new Timer(10000, e ->{
            int valueHambre = hambre.getValue();
            int valueSuciedad = suciedad.getValue();
            int valueEnergia = energia.getValue();
            int valuefelicidad = felicidad.getValue();

            // validación para aumentar el nivel
            if(valueHambre < 20 & valueSuciedad < 20 & valueEnergia > 80 & valuefelicidad > 80){
                Nivel.set(Nivel.get() + 1);
                NivelLabel.setText(String.valueOf((Nivel.get())));
                statusimagen.setIcon(img7);
            }
        });
        timer.start(); // inicio del timer
    }
    public static String obtenerUltimoArchivoBin() {
        File directorio = new File(RUTA);
        if (!directorio.exists()) {
            System.out.println("El directorio no existe.");
            return null;
        }

        File[] archivos = directorio.listFiles((dir, name) -> name.toLowerCase().endsWith(".bin"));
        if (archivos == null || archivos.length == 0) {
            System.out.println("No se encontraron archivos .bin en la carpeta.");
            return null;
        }

        Arrays.sort(archivos, Comparator.comparingLong(File::lastModified)); // Ordenar por marca de tiempo

        File archivoMasReciente = archivos[archivos.length - 1];
        return archivoMasReciente.getName();
    }

    @Override
    public void run() {
        Timer autoGuardado = new Timer(120000, e ->{
            guardarTamagochi();
        });
        autoGuardado.start();
    }
}