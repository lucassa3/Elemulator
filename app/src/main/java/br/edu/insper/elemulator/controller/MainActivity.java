package br.edu.insper.elemulator.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import br.edu.insper.elemulator.R;
import br.edu.insper.elemulator.model.CPU;
import br.edu.insper.elemulator.model.RAM;
import br.edu.insper.elemulator.model.ROM;
import br.edu.insper.elemulator.util.Converter;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView regA, regD, valueM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        regA = (TextView) findViewById(R.id.rega);
        regD = (TextView) findViewById(R.id.regd);
        valueM = (TextView) findViewById(R.id.valuem);



        final ROM rom = new ROM();
        final RAM ram = new RAM();
        final CPU cpu = new CPU();
        final Converter converter = new Converter();



        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int current_line = 0;
                try {

                    Scanner s = new Scanner(getAssets().open("teste2.txt"));


                    while (s.hasNext()){
                        boolean[] instruction = converter.stringToBoolean(s.next());
                        rom.setSelectedInstruction(instruction, current_line);
                        current_line++;
                    }
                    s.close();


                } catch (IOException e) {
                    e.printStackTrace();
                }

                int pc_value = converter.booleanToInt(cpu.getPcOut());
                int oi = 0;
                while (oi <= 1410) {

                    //---------------------------------
                    System.out.println("Instrução:");
                    for (int i = 15; i>=0;i--) {
                        if (rom.getSelectedInstruction(cpu.getPcOut())[i]) System.out.print("1");
                        else System.out.print("0");
                    }
                    System.out.println("");
                    //---------------------------------

                    cpu.execute(ram.getSelectedValue(cpu.getAddressM()), rom.getSelectedInstruction(cpu.getPcOut()), false);
                    ram.setSelectedValue(cpu.getOutM(), cpu.getAddressM(), cpu.isWriteM());

                    //regA.setText(converter.booleanToInt(cpu.registerA.getRegister()));
                    //regD.setText(converter.booleanToInt(cpu.registerD.getRegister()));

                    System.out.println("");
                    System.out.println("");
                    oi++;
                    pc_value = converter.booleanToInt(cpu.getPcOut());
                }
            }
        });




    }


}
