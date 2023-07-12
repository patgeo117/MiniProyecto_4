package PersistenciaDatos;

import java.io.*;

public class Manejo_Archivos {
    public Tamagotchi leerDatos(String RUTA)  { // Método para leer los Datos
        Tamagotchi data = new Tamagotchi();
        try {
            FileInputStream fileInputStream = new FileInputStream(RUTA);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            data = (Tamagotchi) objectInputStream.readObject();

            fileInputStream.close();
            objectInputStream.close();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return data;
    }

    public void escribirDatos(Tamagotchi dato, String RUTA){ // Método para guardar los Datos en él .bin
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(RUTA);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(dato);

            fileOutputStream.close();
            objectOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void crearArchivoBin(String RUTA) { // Método para crear el archivo
        int contador = 1;
        File directorio = new File(RUTA);
        if (!directorio.exists()) {
            directorio.mkdirs(); // Crea el directorio si no existe
        }
        String nombreArchivo = "partida" + contador + ".bin";
        File archivo = new File(directorio, nombreArchivo);
        // creo un bucle para crear el archivo si ya existe
        while (archivo.exists()) {
            contador++;
            nombreArchivo = "partida" + contador + ".bin";
            archivo = new File(directorio, nombreArchivo);
        }

        try (FileOutputStream ignored = new FileOutputStream(archivo)) {
            // Realizar operaciones de escritura en el archivo si es necesario
            System.out.println("Archivo creado: " + archivo.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }
}
