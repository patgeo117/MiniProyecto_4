package Interfaz;

import Filtro.RoundedBorder;

import javax.swing.*;
import java.awt.*;
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
    public JLabel VolverLabel;
    public JLabel statusimagen;
    public JLabel EatLabel;
    public JLabel DormirLabel;
    public JLabel JugarLabel;
    public JLabel BanarLabel;
    public JLabel HambreLabel;
    public JLabel felicidadLabel;
    public JLabel suciedadLabel;
    public JLabel energiaLabel;
    public JLabel LevelLabel;

    // JButton
    public JButton EatButton;
    public JButton DormirButton;
    public JButton JugarButton;
    public JButton BanarButton;
    public JButton volverButton;

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

    // Timer
    Timer timer;
    Timer timer1;

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
        VolverLabel = new JLabel("Volver");
        VolverLabel.setForeground(Color.black);
        VolverLabel.setBounds(10,170,100,40);

        NivelLabel = new JLabel();
        NivelLabel.setForeground(Color.orange);
        NivelLabel.setBounds(175,20,55,40);
        NivelLabel.setFont(new Font("Arial", Font.BOLD, 44));

        LevelLabel = new JLabel("Nivel");
        LevelLabel.setForeground(Color.orange);
        LevelLabel.setFont(new Font("Arial", Font.BOLD, 15));
        LevelLabel.setBounds(170,50,55,40);

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

        EatLabel = new JLabel("Comer");
        EatLabel.setForeground(Color.black);
        EatLabel.setBounds(30,365,40,40);

        DormirLabel = new JLabel("Dormir");
        DormirLabel.setForeground(Color.black);
        DormirLabel.setBounds(210,365,40,40);

        JugarLabel = new JLabel("Jugar");
        JugarLabel.setForeground(Color.black);
        JugarLabel.setBounds(120,365,40,40);

        BanarLabel = new JLabel("Bañar");
        BanarLabel.setForeground(Color.black);
        BanarLabel.setBounds(300,365,40,40);

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

        BanarButton = new JButton();
        BanarButton.setBounds(305,400,40,40);
        BanarButton.setBackground(Color.white);
        BanarButton.setForeground(Color.black);
        BanarButton.setBorder(new RoundedBorder(100));

        volverButton = new JButton();
        volverButton.setBounds(10,200,40,40);
        volverButton.setBackground(Color.red);
        volverButton.setForeground(Color.black);
        volverButton.setBorder(new RoundedBorder(100));

        ActionListener();

        // Agregamos los elementos al JFrame
        add(EatButton);
        add(EatLabel);
        add(DormirButton);
        add(DormirLabel);
        add(JugarButton);
        add(JugarLabel);
        add(BanarButton);
        add(BanarLabel);
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
        add(VolverLabel);

        // HILOS
        run(); // autoguardado
        tiempo_Level();

    }

    public void  guardarTamagochi(){
        String nivel = NivelLabel.getText();
        valueHambre = hambre.getValue();
        valueSuciedad = suciedad.getValue();
        valueEnergia = energia.getValue();
        valuefelicidad = felicidad.getValue();

        // Obtengo los datos del Tamagotchi;
        Tamagotchi newTamagochi = new Tamagotchi(valueHambre, valueEnergia, valuefelicidad, valueSuciedad, nivel);

        manejoArchivos.escribirDatos(newTamagochi, ruta.getRuta() );
    }

    public void tiempo(){
        // inicializo timer en 10000 ms
        timer = new Timer(5000, e -> {
            // Obtengo los valores de las barras de progreso
            valueHambre = hambre.getValue();
            valueSuciedad = suciedad.getValue();
            valueEnergia = energia.getValue();
            valuefelicidad = felicidad.getValue();

            // condición para morir
            if(valueHambre >= 80 && valueSuciedad >= 80 || valueEnergia <= 20 && valuefelicidad <= 20){
                statusimagen.setIcon(img6); // Se coloca la imagen de muerto
                // Se deshabilita los botones
                EatButton.setEnabled(false);
                DormirButton.setEnabled(false);
                JugarButton.setEnabled(false);
                BanarButton.setEnabled(false);
                timer.stop();
                timer1.stop();
            }else {
                statusimagen.setIcon(img1); // Imagen por defecto se coloca cada 10 seg

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
         // timer de 10000 ms
         timer1 = new Timer(5000, e ->{
             AtomicInteger nivel = new AtomicInteger(Integer.parseInt(NivelLabel.getText())); // contador

             // Obtener datos actuales
             valueHambre = hambre.getValue();
             valueSuciedad = suciedad.getValue();
             valueEnergia = energia.getValue();
             valuefelicidad = felicidad.getValue();

             if(nivel.get() >= 6){
                 LevelLabel.setText("WINNER");
                 LevelLabel.setBounds(155, 50, 60, 40); // se modifican las propiedades del Label para diseño
                 EatButton.setEnabled(false);
                 DormirButton.setEnabled(false);
                 JugarButton.setEnabled(false);
                 BanarButton.setEnabled(false);
                 timer1.stop();
                 timer.stop();
             }

             // validación para aumentar el nivel
             if(valueHambre < 20 & valueSuciedad < 20 & valueEnergia > 80 & valuefelicidad > 80) {
                 if (nivel.get() < 6) {
                     statusimagen.setIcon(img7);
                 }
                 nivel.getAndIncrement();
                 NivelLabel.setText(String.valueOf(nivel)); // le paso el contador al label
             }
         });
         timer1.start(); // inicio del timer1
         tiempo();
    }

    // Se crea un método llamado action listener
    public void ActionListener(){
        // escucha para alimentar
        EatButton.addActionListener(e -> {
            int valueHambre = hambre.getValue(); // Obtengo el porcentaje de hambre
            if (valueHambre < hambre.getMaximum()) { // hambre menor al maximo
                if(valueHambre > hambre.getMinimum()) { // Hambre mayor al minimo
                    hambre.setValue(valueHambre - 10);
                    statusimagen.setIcon(img5);
                }
            }
        });

        // escucha para dormir
        DormirButton.addActionListener(e -> {
            int valueEnergia = energia.getValue();
            if (valueEnergia < energia.getMaximum()) {
                if(valueEnergia > energia.getMinimum()) {
                    energia.setValue(valueEnergia + 10);
                    statusimagen.setIcon(img4);
                }
            }
        });

        // escucha para jugar
        JugarButton.addActionListener(e -> {
            int valuefelicidad = felicidad.getValue();
            if (valuefelicidad < felicidad.getMaximum()) {
                if(valuefelicidad > felicidad.getMinimum()) {
                    felicidad.setValue(valuefelicidad + 10);
                    statusimagen.setIcon(img2);
                }
            }
        });
        // escucha para bañar
        BanarButton.addActionListener(e -> {
            int valueSuciedad = suciedad.getValue();
            if (valueSuciedad < suciedad.getMaximum()) {
                if(valueSuciedad > suciedad.getMinimum()) {
                    suciedad.setValue(valueSuciedad - 10);
                    statusimagen.setIcon(img3);
                }
            }
        });
        volverButton.addActionListener(e -> {
            String rutaActual = ruta.getRuta();
            guardarTamagochi();
            System.out.println(rutaActual);
            ruta.setRuta(rutaActual);
            TamagotchiMenu menu = new TamagotchiMenu();
            setVisible(false);
            menu.setVisible(true);
        });
    }

    @Override
    public void run() {
        Timer autoGuardado = new Timer(120000, e -> guardarTamagochi());
        autoGuardado.start();
    }
}