package br.edu.insper.elemulator.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.edu.insper.elemulator.R;
import br.edu.insper.elemulator.util.Converter;

public class MainActivity extends AppCompatActivity{

    Button runAllBtn, runBtn;
    TextView regA, regD, valueM;
    EditText keyboard;
    ListView fileList;
    ArrayAdapter adapter;

    private List<File> getListFiles(File parentDir) {
        ArrayList<File> inFiles = new ArrayList<File>();
        File[] files = parentDir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                inFiles.addAll(getListFiles(file));
            } else {
                if(file.getName().endsWith(".txt")){
                    inFiles.add(file);
                }
            }
        }
        return inFiles;
    }
    private List<String> getListFileNames(File parentDir) {
        ArrayList<String> inFiles = new ArrayList<String>();
        File[] files = parentDir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                //inFiles.addAll(getListFiles(file);
            } else {
                inFiles.add(file.getName());
                if(file.getName().endsWith(".txt")){

                }
            }
        }
        return inFiles;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        fileList = (ListView) findViewById(R.id.file_list);
        keyboard = (EditText) findViewById(R.id.keyboard); //passo 2
        runAllBtn = (Button) findViewById(R.id.runall);
        runBtn = (Button) findViewById(R.id.run);
        regA = (TextView) findViewById(R.id.rega);
        regD = (TextView) findViewById(R.id.regd);
        valueM = (TextView) findViewById(R.id.valuem);

        final Hack hack;
        final Converter converter = new Converter();




        try {
            List<String> files = getListFileNames(new File("/storage"));
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, files);
            fileList.setAdapter(adapter);


            hack = new Hack(getAssets().open("teste2.txt"));




            runAllBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    hack.execute();

                    int a = converter.booleanToInt(hack.cpu.registerA.getRegister());
                    regA.setText(Integer.toString(a));

                    int d = converter.booleanToInt(hack.cpu.registerD.getRegister());
                    regD.setText(Integer.toString(d));

                    int m = converter.booleanToInt(hack.cpu.getOutM());
                    valueM.setText(Integer.toString(m));
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

                    if (keyboard.getText().toString() != null) {
                        char kbd = keyboard.getText().toString().charAt(0); //passo 3
                        int ascii = (int) kbd; //passo 4

                        hack.ram.setSelectedValue(converter.intToBoolean(ascii),  converter.intToBoolean(256), true); //passo 5
                        keyboard.setText(""); //passo 6
                    }

                    System.out.println(converter.booleanToInt(hack.ram.getSelectedValue(converter.intToBoolean(256))));


                }
            });






        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
