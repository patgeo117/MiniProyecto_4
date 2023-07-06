package Interfaz;

import Filtro.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static PersistenciaDatos.Manejo_Archivos.crearArchivoBin;

public class TamagotchiMenu extends JFrame {
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

    TamagotchiMenu(){
        // Configuración Ventana
        setTitle("Tamagotchi");
        setSize(300, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLUE);
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
        CrearPartidaButton.setBackground(Color.blue);
        CrearPartidaButton.setForeground(Color.black);
        CrearPartidaButton.setBorder(new RoundedBorder(20));

        CargarPartidaButton = new JComboBox(obtenerListaArchivosBin()); // obtengo la lista de todas las partidas guardadas
        CargarPartidaButton.setBounds(70, 300, 150, 40);
        CargarPartidaButton.setBackground(Color.blue);
        CargarPartidaButton.setForeground(Color.black);
        CargarPartidaButton.setEditable(false);
        CargarPartidaButton.setSelectedIndex(0);

        CrearPartidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearArchivoBin(RUTA);
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
                    System.out.println(ruta);
                    TamagotchiInterfaz tamagotchiInterface = new TamagotchiInterfaz();
                    tamagotchiInterface.setVisible(true);
                }
            }
        });

        // Agregar ambos botones al contenedor
        add(CrearPartidaButton);
        add(CargarPartidaButton);
        add(imagenTamagotchi);

    }
    // Método encargado de obtener todas las partidas guardadas
    public String[] obtenerListaArchivosBin(){
        List<String> listaArchivos = new ArrayList<>();
        listaArchivos.add("Seleccionar");

        File directorio = new File(RUTA); // ruta de la carpeta
        if(!directorio.exists()){
            System.out.println("El directorio no existe.");
            String[] lista = listaArchivos.toArray(new String[0]);
            return lista;
        }
        File[] archivos = directorio.listFiles(); //listas de los archivos de directorio
        if (archivos != null) { // valido que la lista no sea nula
            for (File archivo : archivos) { // recorr la lista
                // valido que le archivo abstracto sea un archivo y que este en formato bin
                if (archivo.isFile() && archivo.getName().endsWith(".bin")) {
                    listaArchivos.add(archivo.getName());
                }
            }
        }
        String[] lista = listaArchivos.toArray(new String[0]);
        return lista;
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
