package br.edu.insper.elemulator.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

import br.edu.insper.elemulator.model.*;
import br.edu.insper.elemulator.util.Converter;

public class MainController {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		ROM rom = new ROM();
		RAM ram = new RAM();
		CPU cpu = new CPU();
		Converter converter = new Converter();
		Scanner s = new Scanner(new File("teste4.txt"));
		Screen screen = new Screen(ram);
		
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				int current_line = 0;
				
				while (s.hasNext()){
					boolean[] instruction = converter.stringToBoolean(s.next());
					rom.setSelectedInstruction(instruction, current_line);
					current_line++;
				}
				s.close();
				
				
				JFrame frame = new JFrame("Elemulator");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(false);
				frame.setContentPane(screen);
				frame.pack();
				frame.setVisible(true);
				
				
				int pc_value = converter.booleanToInt(cpu.getPcOut());
				
				while (pc_value+1 <= current_line) {
					
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
					
					System.out.println("");
					System.out.println("");	 

					pc_value = converter.booleanToInt(cpu.getPcOut());
				}
				
				screen.setRam(ram);
				screen.repaint();
            }
		});
	}
}
