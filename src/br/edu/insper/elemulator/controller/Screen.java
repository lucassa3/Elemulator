package br.edu.insper.elemulator.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import javax.swing.JPanel;

import br.edu.insper.elemulator.model.RAM;

public class Screen extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	RAM ram;
	int count = 0;
	
	
	public Screen(RAM ram) throws IOException {
		setPreferredSize(new Dimension(512, 256));
		this.ram = ram;	
	}
	
	public void setRam(RAM ram) {
		this.ram = ram;
	}

	@Override
	public void paintComponent(Graphics g) {
		
		for (int j = 0; j<256; j++) {
			for (int i = 0; i<512; i+=16) {
				for (int k = 0; k<16; k++) {
					if (ram.getSelectedValueInt(16384+count)[k]) g.setColor(Color.BLACK);
					else g.setColor(Color.WHITE);
					g.fillRect(i+k, j, 1, 1);
				}
				count++;
			}
		}
    	getToolkit().sync();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}
