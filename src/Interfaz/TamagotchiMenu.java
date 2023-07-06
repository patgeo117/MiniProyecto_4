package Interfaz;

import Filtro.RoundedBorder;
import PersistenciaDatos.Tamagotchi;
import PersistenciaDatos.Manejo_Archivos;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static PersistenciaDatos.Manejo_Archivos.crearArchivoBin;

public class TamagotchiMenu extends JFrame implements Runnable {
    // Iconos
    ImageIcon img1 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/IconosTamagotchi/IconoPrincipal.png")));
    ImageIcon img2 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/IconosTamagotchi/Goku_Normal_1.png")));


    // Botones
    private JButton CrearPartidaButton;

    // ComboBox
    private JComboBox CargarPartidaButton;

    // JLabel
    private JLabel imagenTamagotchi;

    // Ruta
    private static final String RUTA = "src/Archivos_Bin"; // Ruta donde se crearán los archivos

    // constructores
    Manejo_Archivos manejoArchivos = new Manejo_Archivos();
    Tamagotchi datos = new Tamagotchi();

    TamagotchiMenu(){
        // Configuración Ventana
        setTitle("Tamagotchi");
        setSize(300, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(229, 124, 35));
        setIconImage(img1.getImage());

        // Configuración Label
        imagenTamagotchi = new JLabel(img2);
        imagenTamagotchi.setBounds(20,20,250,220);
        imagenTamagotchi.setOpaque(true);
        imagenTamagotchi.setBackground(Color.white);
        imagenTamagotchi.setBorder(new RoundedBorder(10));

        // Configuración Botones
        CrearPartidaButton = new JButton("Crear Partida");
        CrearPartidaButton.setBounds(70,250,150,40);
        CrearPartidaButton.setBackground(Color.white);
        CrearPartidaButton.setForeground(Color.black);
        CrearPartidaButton.setBorder(new RoundedBorder(20));

        CargarPartidaButton = new JComboBox();
        CargarPartidaButton.addItem("Seleccionar");
        CargarPartidaButton.setBounds(70, 300, 150, 40);
        CargarPartidaButton.setBackground(Color.yellow);
        CargarPartidaButton.setForeground(Color.black);
        CargarPartidaButton.setEditable(false);
        CargarPartidaButton.setSelectedIndex(0);

        CrearPartidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearArchivoBin(RUTA);
                TamagotchiInterfaz tamagotchiInterface = new TamagotchiInterfaz();
                tamagotchiInterface.hambre.setValue(10); // valor por defecto de la barra de progreso
                tamagotchiInterface.felicidad.setValue(50);
                tamagotchiInterface.suciedad.setValue(10);
                tamagotchiInterface.energia.setValue(50);

                tamagotchiInterface.setVisible(true);
                setVisible(false);
            }
        });

        CargarPartidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String NombrePartida = (String) CargarPartidaButton.getSelectedItem(); // obtengo partida seleccionada
                int item = CargarPartidaButton.getSelectedIndex();

                if(Objects.equals(NombrePartida, "Seleccionar")){
                    CargarPartidaButton.setSelectedIndex(0); // Ignorar el ítem
                }
                if (item >= 1) {
                    String ruta = "src/Archivos_Bin/" + NombrePartida; // obtengo el archivo de la partida
                    datos.setRuta(ruta); ///enviamos la ruta a la instancia

                    Tamagotchi tamagotchi = manejoArchivos.leerDatos(ruta);

                    System.out.println(ruta);

                    TamagotchiInterfaz tamagotchiInterface = new TamagotchiInterfaz();

                    tamagotchiInterface.energia.setValue(tamagotchi.getValueEnergia());
                    tamagotchiInterface.hambre.setValue(tamagotchi.getValueHambre());
                    tamagotchiInterface.felicidad.setValue(tamagotchi.getValuefelicidad());
                    tamagotchiInterface.suciedad.setValue(tamagotchi.getValueSuciedad());
                    tamagotchiInterface.NivelLabel.setText(String.valueOf(tamagotchi.getLevel()));

                    tamagotchiInterface.setVisible(true);
                    setVisible(false);
                }
            }
        });

        // Agregar ambos botones al contenedor
        add(CrearPartidaButton);
        add(CargarPartidaButton);
        add(imagenTamagotchi);

        run();

    }
    // Método encargado de obtener todas las partidas guardadas
    public static String[] obtenerListaArchivosBin(){
        List<String> listaArchivos = new ArrayList<>();

        File directorio = new File(RUTA); // ruta de la carpeta
        if(!directorio.exists()){
            System.out.println("El directorio no existe.");
            String[] lista = listaArchivos.toArray(new String[0]);
            return lista;
        }
        File[] archivos = directorio.listFiles(); //listas de los archivos de directorio
        if (archivos != null) { // validó que la lista no sea nula
            for (File archivo : archivos) { // recorrer la lista
                // validó que le archivo abstracto sea un archivo y que esté en formato bin
                if (archivo.isFile() && archivo.getName().endsWith(".bin")) {
                    listaArchivos.add(archivo.getName());
                }
            }
        }
        String[] lista = listaArchivos.toArray(new String[0]);

        return lista;
    }

    @Override
    public void run() {
        Set<String> elementosAgregados = new HashSet<>();
        Timer timer = new Timer(1000, e -> {
            for (String elemento : obtenerListaArchivosBin()) {
                if (!elementosAgregados.contains(elemento)) {
                    CargarPartidaButton.addItem(elemento);
                    elementosAgregados.add(elemento);
                }
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        // Creo un hilo para asegurar que se ejecuten correctamente la interfaz de usuario
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TamagotchiMenu tamagotchiMenu = new TamagotchiMenu();
                tamagotchiMenu.setVisible(true);
            }
        });
    }
}
