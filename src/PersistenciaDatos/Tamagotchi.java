package PersistenciaDatos;

import java.io.Serializable;


public class Tamagotchi implements Serializable {
    private int valueHambre;
    private int valueSuciedad;
    private int valueEnergia;
    private int valuefelicidad;
    private static String ruta;
    private String Level;

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
