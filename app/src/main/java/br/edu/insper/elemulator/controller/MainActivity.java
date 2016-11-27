package br.edu.insper.elemulator.controller;

import android.os.Handler;
import android.os.Message;
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

    Button runAllBtn, runBtn, kbdBtn, resetBtn, pauseBtn;
    TextView regA, regD, valueM;
    EditText keyboard;
    ListView fileList;
    ArrayAdapter adapter;


    /*private List<File> getListFiles(File parentDir) {
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
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        pauseBtn = (Button) findViewById(R.id.pause_btn);
        resetBtn = (Button) findViewById(R.id.reset_btn);
        kbdBtn  = (Button) findViewById(R.id.kbd_btn);
        fileList = (ListView) findViewById(R.id.file_list);
        keyboard = (EditText) findViewById(R.id.keyboard); //passo 2
        runAllBtn = (Button) findViewById(R.id.run_all);
        runBtn = (Button) findViewById(R.id.run);
        regA = (TextView) findViewById(R.id.rega);
        regD = (TextView) findViewById(R.id.regd);
        valueM = (TextView) findViewById(R.id.valuem);

        final Hack hack;
        final Converter converter = new Converter();





        try {
            hack = new Hack(getAssets().open("teste2.txt"));
           /* List<String> files = getListFileNames(new File("/"));
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, files);
            fileList.setAdapter(adapter);*/

            final Handler mHandler = new Handler() {
                public void handleMessage(Message msg) {
                    regA.setText(String.valueOf(converter.booleanToInt(hack.cpu.registerA.getRegister())));
                    regD.setText(String.valueOf(converter.booleanToInt(hack.cpu.registerD.getRegister())));
                    valueM.setText(String.valueOf(converter.booleanToInt(hack.cpu.getOutM())));
                }
            };

            final Thread thread = new Thread(){
                public void run(){
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            if (hack.pc_value <= hack.current_line-1) {
                                hack.execute();
                                mHandler.obtainMessage(1).sendToTarget();
                            }
                            sleep(300);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    if (hack.pc_value <= hack.current_line-1) {
                        hack.execute();
                        mHandler.obtainMessage(1).sendToTarget();
                    }
                }
            };



            runAllBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    thread.start();
                }
            });

            runBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    hack.execute();
                    mHandler.obtainMessage(1).sendToTarget();
                }
            });

            pauseBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (thread.isInterrupted()) {thread.resume();}
                    else thread.interrupt();
                }
            });

            resetBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                   hack.reset = true;

                }
            });






            kbdBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    char kbd = keyboard.getText().toString().charAt(0); //passo 3
                    int ascii = (int) kbd; //passo 4

                    hack.ram.setSelectedValue(converter.intToBoolean(ascii),  converter.intToBoolean(256), true); //passo 5
                    keyboard.setText(""); //passo 6

                    System.out.println(converter.booleanToInt(hack.ram.getSelectedValue(converter.intToBoolean(256))));

                }
            });







        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
