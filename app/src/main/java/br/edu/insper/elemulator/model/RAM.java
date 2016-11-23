package br.edu.insper.elemulator.model;

import br.edu.insper.elemulator.util.Converter;

public class RAM {
    private Register[] ram;
    private Converter converter = new Converter();

    public RAM () {
        this.ram = new Register[32768];
        this.ram[0] = new Register();
    }

    public boolean[] getSelectedValue (boolean[] index) {
        int decIndex = converter.booleanToInt(index);
        if (this.ram[decIndex] == null) {
            this.ram[decIndex] = new Register();
        }

        return this.ram[decIndex].getRegister();
    }

    public void setSelectedValue(boolean[] register, boolean[] index, boolean load) {
        int decIndex = converter.booleanToInt(index);

        if (load) {
            System.out.println("guardando na memoria na posicao: " + decIndex);
            if (this.ram[decIndex] == null) this.ram[decIndex] = new Register();
            this.ram[decIndex].loadRegister(register, load);
        }
    }

    public boolean[] getSelectedValueInt(int index) {
        if (this.ram[index] == null) {
            this.ram[index] = new Register();
        }

        return this.ram[index].getRegister();
    }


}
