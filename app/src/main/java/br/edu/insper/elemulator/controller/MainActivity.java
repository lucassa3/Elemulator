package br.edu.insper.elemulator.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import br.edu.insper.elemulator.R;
import br.edu.insper.elemulator.util.Converter;

public class MainActivity extends AppCompatActivity{

    Button runAllBtn, runBtn;
    TextView regA, regD, valueM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        runAllBtn = (Button) findViewById(R.id.runall);
        runBtn = (Button) findViewById(R.id.run);

        regA = (TextView) findViewById(R.id.rega);
        regD = (TextView) findViewById(R.id.regd);
        valueM = (TextView) findViewById(R.id.valuem);

        final Hack hack;
        final Converter converter = new Converter();
        try {
            hack = new Hack(getAssets().open("teste2.txt"));

            runAllBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    hack.execute();

                    int a = converter.booleanToInt(hack.cpu.registerA.getRegister());
                    regA.setText(Integer.toString(a));

                    int d = converter.booleanToInt(hack.cpu.registerD.getRegister());
                    regD.setText(Integer.toString(d));
                }
            });

            runBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    hack.singleExecute();

                    int a = converter.booleanToInt(hack.cpu.registerA.getRegister());
                    regA.setText(Integer.toString(a));

                    int d = converter.booleanToInt(hack.cpu.registerD.getRegister());
                    regD.setText(Integer.toString(d));

                    int m = converter.booleanToInt(hack.cpu.getOutM());
                    valueM.setText(Integer.toString(m));

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
