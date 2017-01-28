package br.edu.insper.elemulator.model;

import br.edu.insper.elemulator.controller.DisplayDriver;
import br.edu.insper.elemulator.util.Converter;

public class RAM {
    private Register[] ram;
    private Converter converter = new Converter();
    private DisplayDriver displayDriver;

    public RAM (DisplayDriver displayDriver) {
        this.ram = new Register[32768];
        this.ram[0] = new Register();
        this.displayDriver = displayDriver;
    }

    public boolean[] getSelectedValue (boolean[] index) {
        int decIndex = converter.booleanToInt(index);
        if (this.ram[decIndex] == null) {
            this.ram[decIndex] = new Register();
        }

        return this.ram[decIndex].getRegister();
    }

    public void setSelectedValue(boolean[] register, boolean[] index, boolean load) { //o q vc quer gaurdar, onde e confirma
        int decIndex = converter.booleanToInt(index);

        if (load) {
            System.out.println("guardando na memoria na posicao: " + decIndex);
            if (this.ram[decIndex] == null) this.ram[decIndex] = new Register();
            this.ram[decIndex].loadRegister(register, load);
            if (converter.booleanToInt(index) >= 16384) {
                displayDriver.update(register, index);
            }
        }
    }


}
