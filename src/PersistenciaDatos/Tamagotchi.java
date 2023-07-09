package PersistenciaDatos;

import java.io.Serializable;


public class Tamagotchi implements Serializable {
    private static int valueHambre;
    private static int valueSuciedad;
    private static int valueEnergia;
    private static int valuefelicidad;
    private static String ruta;
    private static String Level;

    public Tamagotchi(){

    }
    public Tamagotchi(int Hambre, int Energia, int Felicidad, int Suciedad,String level){
        valueHambre = Hambre;
        valueEnergia = Energia;
        valuefelicidad = Felicidad;
        valueSuciedad = Suciedad;
        Level = level;
    }

    public void setRuta(String ruta){
        Tamagotchi.ruta = ruta;
    }

    public String getLevel(){
        return Level;
    }
    public String getRuta(){
        return ruta;
    }

    public int getValuefelicidad() {
        return valuefelicidad ;
    }

    public int getValueSuciedad() {
        return valueSuciedad;
    }
    public int getValueHambre() {
        return valueHambre;
    }

    public int getValueEnergia() {
        return valueEnergia ;
    }
}
