package PersistenciaDatos;

import javax.swing.*;
import java.io.Serializable;


public class Tamagotchi implements Serializable {
    private int valueHambre;
    private int valueSuciedad;
    private int valueEnergia;
    private int valuefelicidad;
    private String ruta;
    private String Level;

    public Tamagotchi(){

    }
    public Tamagotchi(int Hambre, int Energia, int Felicidad, int Suciedad,String level){
        this.valueHambre = Hambre;
        this.valueEnergia = Energia;
        this.valuefelicidad = Felicidad;
        this.valueSuciedad = Suciedad;
        this.Level = level;
    }

    public void setRuta(String ruta){
        this.ruta = ruta;
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
