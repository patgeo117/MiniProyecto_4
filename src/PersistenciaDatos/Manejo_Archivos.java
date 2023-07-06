package PersistenciaDatos;

import java.io.*;

public class Manejo_Archivos {
    public void leerObjeto(String RUTA) {
        /*String rutaArchivo = "src/Archivos_Bin/Libros.bin";
        Object[][] objetos = null;
        try {

            FileInputStream fileInputStream = new FileInputStream(rutaArchivo);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            objetos = (Object[][]) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    public static void crearArchivoBin(String RUTA) {
        int contador = 1;
        File directorio = new File(RUTA);
        if (!directorio.exists()) {
            directorio.mkdirs(); // Crea el directorio si no existe
        }
        String nombreArchivo = "partida" + contador + ".bin";
        File archivo = new File(directorio, nombreArchivo);
        // creo un bucle para crear el archvi si ya existe
        while (archivo.exists()) {
            contador++;
            nombreArchivo = "partida" + contador + ".bin";
            archivo = new File(directorio, nombreArchivo);
        }

        try (FileOutputStream fos = new FileOutputStream(archivo)) {
            // Realizar operaciones de escritura en el archivo si es necesario
            System.out.println("Archivo creado: " + archivo.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }
}
