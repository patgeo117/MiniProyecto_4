package Interfaz;

import Filtro.RoundedBorder;
import PersistenciaDatos.Tamagotchi;
import PersistenciaDatos.Manejo_Archivos;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

import static PersistenciaDatos.Manejo_Archivos.crearArchivoBin;

public class TamagotchiMenu extends JFrame implements Runnable {
    // Iconos
    ImageIcon img1 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/IconosTamagotchi/IconoPrincipal.png")));
    ImageIcon img2 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/IconosTamagotchi/Goku_Normal_1.png")));


    // Botones
    public JButton CrearPartidaButton;

    // ComboBox
    public JComboBox<String> CargarPartidaButton;

    // JLabel
    public JLabel imagenTamagotchi;

    // Ruta
    private static final String RUTA = "src/Archivos_Bin"; // Ruta donde se crearán los archivos

    // constructores
    Manejo_Archivos manejoArchivos = new Manejo_Archivos();
    Tamagotchi rutatamagochi = new Tamagotchi();

    public TamagotchiMenu() {
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
        imagenTamagotchi.setBounds(20, 20, 250, 220);
        imagenTamagotchi.setOpaque(true);
        imagenTamagotchi.setBackground(Color.white);
        imagenTamagotchi.setBorder(new RoundedBorder(10));

        // Configuración Botones
        CrearPartidaButton = new JButton("Crear Partida");
        CrearPartidaButton.setBounds(70, 250, 150, 40);
        CrearPartidaButton.setBackground(Color.white);
        CrearPartidaButton.setForeground(Color.black);
        CrearPartidaButton.setBorder(new RoundedBorder(20));

        CargarPartidaButton = new JComboBox<>();
        CargarPartidaButton.addItem("Seleccionar");
        CargarPartidaButton.setBounds(70, 300, 150, 40);
        CargarPartidaButton.setBackground(Color.yellow);
        CargarPartidaButton.setForeground(Color.black);
        CargarPartidaButton.setEditable(false);
        CargarPartidaButton.setSelectedIndex(0);

        CrearPartidaButton.addActionListener(e -> {
            crearArchivoBin(RUTA);
            TamagotchiInterfaz tamagotchiInterface = new TamagotchiInterfaz();
            tamagotchiInterface.hambre.setValue(10); // valor por defecto de la barra de progreso
            tamagotchiInterface.felicidad.setValue(50);
            tamagotchiInterface.suciedad.setValue(10);
            tamagotchiInterface.energia.setValue(50);
            tamagotchiInterface.NivelLabel.setText("0");
            String rutaActual = "src/Archivos_Bin/" + obtenerUltimoArchivoBin();
            rutatamagochi.setRuta(rutaActual);

            tamagotchiInterface.setVisible(true);
            setVisible(false);
        });

        CargarPartidaButton.addActionListener(e -> {
            String NombrePartida = (String) CargarPartidaButton.getSelectedItem();
            int item = CargarPartidaButton.getSelectedIndex();

            if (Objects.equals(NombrePartida, "Seleccionar")) {
                CargarPartidaButton.setSelectedIndex(0);
            }
            if (item >= 1) {
                String ruta = "src/Archivos_Bin/" + NombrePartida;
                rutatamagochi.setRuta(ruta);
                Tamagotchi tamagotchi = manejoArchivos.leerDatos(ruta);

                TamagotchiInterfaz tamagotchiInterface = new TamagotchiInterfaz();

                tamagotchiInterface.energia.setValue(tamagotchi.getValueEnergia());
                tamagotchiInterface.hambre.setValue(tamagotchi.getValueHambre());
                tamagotchiInterface.felicidad.setValue(tamagotchi.getValuefelicidad());
                tamagotchiInterface.suciedad.setValue(tamagotchi.getValueSuciedad());
                tamagotchiInterface.NivelLabel.setText(String.valueOf(tamagotchi.getLevel()));

                setVisible(false);
                tamagotchiInterface.setVisible(true);
            }
        });


        // Agregar ambos botones al contenedor
        add(CrearPartidaButton);
        add(CargarPartidaButton);
        add(imagenTamagotchi);

        run(); // cargar items del comboBox
    }

    // Método encargado de obtener todas las partidas guardadas
    public static String[] obtenerListaArchivosBin() {
        List<String> listaArchivos = new ArrayList<>();

        File directorio = new File(RUTA); // ruta de la carpeta
        if (!directorio.exists()) {
            System.out.println("El directorio no existe.");
            return listaArchivos.toArray(new String[0]);
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
        return listaArchivos.toArray(new String[0]); // Se agrega a la última posición
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
        Set<String> elementosAgregados = new HashSet<>(); // Creo una lista de items
        Timer timer = new Timer(1000, e -> { // se ejecuta cada segundo
            for (String elemento : obtenerListaArchivosBin()) {
                if (!elementosAgregados.contains(elemento)) {
                    CargarPartidaButton.addItem(elemento); // cargo los item de las partidas creadas al comboBox
                    elementosAgregados.add(elemento); // agrego el elemento
                }
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        // Creo un hilo para asegurar que se ejecuten correctamente la interfaz de usuario
        SwingUtilities.invokeLater(() -> {
            TamagotchiMenu tamagotchiMenu = new TamagotchiMenu();
            tamagotchiMenu.setVisible(true);
        });
    }
}
