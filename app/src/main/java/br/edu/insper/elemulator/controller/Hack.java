package br.edu.insper.elemulator.controller;

import android.support.v7.app.AppCompatActivity;

import java.io.InputStream;
import java.util.Scanner;

import br.edu.insper.elemulator.model.CPU;
import br.edu.insper.elemulator.model.RAM;
import br.edu.insper.elemulator.model.ROM;
import br.edu.insper.elemulator.util.Converter;

public class Hack extends AppCompatActivity {
    boolean reset;
    RAM ram;


    ROM rom;
    CPU cpu;
    Converter converter;
    int current_line;
    int pc_value;

    public Hack(InputStream file) {
        this.ram = new RAM();
        this.rom = new ROM();
        this.cpu = new CPU();
        this.converter = new Converter();
        this.reset = false;

        this.current_line = 0;


            Scanner s = new Scanner(file);


            while (s.hasNext()){
                boolean[] instruction = converter.stringToBoolean(s.next());
                rom.setSelectedInstruction(instruction, current_line);
                current_line++;
            }
            s.close();

    }

    public void execute() {
        pc_value = converter.booleanToInt(cpu.getPcOut());

        //---------------------------------
        System.out.println("Instrução:");
        for (int i = 15; i>=0;i--) {
            if (rom.getSelectedInstruction(cpu.getPcOut())[i]) System.out.print("1");
            else System.out.print("0");
        }
        System.out.println("");
        //---------------------------------

        cpu.execute(ram.getSelectedValue(cpu.getAddressM()), rom.getSelectedInstruction(cpu.getPcOut()), reset);
        ram.setSelectedValue(cpu.getOutM(), cpu.getAddressM(), cpu.isWriteM());
        this.reset = false;

        System.out.println(" ");
        System.out.println(" ");
        pc_value = converter.booleanToInt(cpu.getPcOut());
    }



}
