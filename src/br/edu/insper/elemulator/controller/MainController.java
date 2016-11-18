package br.edu.insper.elemulator.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

import br.edu.insper.elemulator.model.*;

public class MainController {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		ROM rom = new ROM();
		RAM ram = new RAM();
		CPU cpu = new CPU();
		Scanner s = new Scanner(new File("teste4.txt"));
		
		
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Screen screen;
				try {
					int current_line = 0;
					
					
					while (s.hasNext()){
						rom.setSelectedInstruction(s.next(), current_line);
						current_line++;
					}
					s.close();
					
					screen = new Screen(ram);
					JFrame frame = new JFrame("Portas Logicas");
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame.setResizable(false);
	                frame.setContentPane(screen);
	                frame.pack();
	                frame.setVisible(true);
	                
	                String s = "";
	        		
	        		for (int i = cpu.getPcOut().length-1; i>=0; i--) {
	        			if (cpu.getPcOut()[i] == false) s+='0';
	              	  	else if (cpu.getPcOut()[i] == true) s+='1';
	        		}
	        		int pc_value = Integer.parseInt(s, 2);
	                
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
	        			
	        			String sa = "";
		        		
		        		for (int i = cpu.getPcOut().length-1; i>=0; i--) {
		        			if (cpu.getPcOut()[i] == false) sa+='0';
		              	  	else if (cpu.getPcOut()[i] == true) sa+='1';
		        		}
		        		pc_value = Integer.parseInt(sa, 2);
	        		}
	        		
	        		screen.setRam(ram);
        			screen.repaint();
	        		
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
		});
	}
}
