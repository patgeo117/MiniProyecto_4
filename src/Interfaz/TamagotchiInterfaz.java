package Interfaz;

import Filtro.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class TamagotchiInterfaz extends JFrame {

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
    private JLabel NivelLabel;
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

    // JProgressBar
    private JProgressBar hambre;
    private JProgressBar felicidad;
    private JProgressBar suciedad;
    private JProgressBar energia;

    public TamagotchiInterfaz() {

        // Configuración Ventana
        setTitle("Tamagotchi");
        setSize(400, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLUE);
        setIconImage(img.getImage());

        //Configuración JProgressBar
        hambre = new JProgressBar(0,100);
        hambre.setForeground(Color.red);
        hambre.setBounds(20,20,130,20);
        hambre.setStringPainted(true); // habilitación para llenar la barra de progreso
        hambre.setValue(10); // valor por defecto de la barra de progreso

        felicidad = new JProgressBar(0,100);
        felicidad.setForeground(Color.green);
        felicidad.setBounds(20,60,130,20);
        felicidad.setStringPainted(true);
        felicidad.setValue(50);

        suciedad = new JProgressBar(0,100);
        suciedad.setForeground(Color.pink);
        suciedad.setBounds(220,20,130,20);
        suciedad.setStringPainted(true);
        suciedad.setValue(10);

        energia = new JProgressBar(0,100);
        energia.setForeground(Color.orange);
        energia.setBounds(220,60,130,20);
        energia.setStringPainted(true);
        energia.setValue(50);

        // Configuración Label
        NivelLabel = new JLabel("0");
        NivelLabel.setForeground(Color.white);
        NivelLabel.setBounds(175,20,40,40);
        NivelLabel.setFont(new Font("Arial", Font.BOLD, 44));

        statusimagen = new JLabel();
        statusimagen.setIcon(img1);
        statusimagen.setBounds(70,90,270,280);
        statusimagen.setOpaque(true);
        statusimagen.setBackground(Color.white);
        statusimagen.setBorder(new RoundedBorder(10));

        HambreLabel = new JLabel("Hambre");
        HambreLabel.setForeground(Color.white);
        HambreLabel.setBounds(60,1,130,20);

        felicidadLabel = new JLabel("Felicidad");
        felicidadLabel.setForeground(Color.white);
        felicidadLabel.setBounds(60,40,130,20);

        suciedadLabel = new JLabel("Suciedad");
        suciedadLabel.setForeground(Color.white);
        suciedadLabel.setBounds(260,1,130,20);

        energiaLabel = new JLabel("Energía");
        energiaLabel.setForeground(Color.white);
        energiaLabel.setBounds(260,40,130,20);

        LevelLabel = new JLabel("Nivel");
        LevelLabel.setForeground(Color.white);
        LevelLabel.setFont(new Font("Arial", Font.BOLD, 15));
        LevelLabel.setBounds(170,50,40,40);

        EatLabel = new JLabel("Comer");
        EatLabel.setForeground(Color.white);
        EatLabel.setBounds(30,365,40,40);

        DormirLabel = new JLabel("Dormir");
        DormirLabel.setForeground(Color.white);
        DormirLabel.setBounds(210,365,40,40);

        JugarLabel = new JLabel("Jugar");
        JugarLabel.setForeground(Color.white);
        JugarLabel.setBounds(120,365,40,40);

        BañarLabel = new JLabel("Bañar");
        BañarLabel.setForeground(Color.white);
        BañarLabel.setBounds(300,365,40,40);

        // Configuración Button
        EatButton = new JButton();
        EatButton.setBounds(35,400,40,40);
        EatButton.setBackground(Color.blue);
        EatButton.setForeground(Color.black);
        EatButton.setBorder(new RoundedBorder(100)); // Aquí se establece el radio del botón

        DormirButton = new JButton();
        DormirButton.setBounds(215,400,40,40);
        DormirButton.setBackground(Color.blue);
        DormirButton.setForeground(Color.black);
        DormirButton.setBorder(new RoundedBorder(100));

        JugarButton = new JButton();
        JugarButton.setBounds(125,400,40,40);
        JugarButton.setBackground(Color.blue);
        JugarButton.setForeground(Color.black);
        JugarButton.setBorder(new RoundedBorder(100));

        BañarButton = new JButton();
        BañarButton.setBounds(305,400,40,40);
        BañarButton.setBackground(Color.blue);
        BañarButton.setForeground(Color.black);
        BañarButton.setBorder(new RoundedBorder(100));

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

        tiempo();
        tiempo_Level();
    }

    public void tiempo(){
        // inicializo timer en 5000 ms
        Timer timer = new Timer(5000, e -> {
            // Obtengo los valores de los barras de progreso
            int valueHambre = hambre.getValue();
            int valueSuciedad = suciedad.getValue();
            int valueEnergia = energia.getValue();
            int valuefelicidad = felicidad.getValue();

            // condicón para morir
            if(valueHambre > 80 & valueSuciedad > 80 & valueEnergia < 20 & valuefelicidad < 20){
                statusimagen.setIcon(img6);
                EatButton.setEnabled(false);
                DormirButton.setEnabled(false);
                JugarButton.setEnabled(false);
                BañarButton.setEnabled(false);

            }else {
                statusimagen.setIcon(img1);

                //  Control de los evento de la barra de estado
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
        Timer timer = new Timer(12000, e ->{
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
}