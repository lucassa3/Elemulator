package br.edu.insper.elemulator.model;
public class ROM {
	private Register[] rom;
	
	public ROM () {
		this.rom = new Register[32768];
	}
	
	public void setSelectedInstruction(String instruction, int index) {
		boolean[] current_instruction = new boolean[16];
		for (int j = 0; j<instruction.length();j++) {
    		if (instruction.charAt(j)=='0') current_instruction[instruction.length()-1-j] = false;
    		else if (instruction.charAt(j)=='1') current_instruction[instruction.length()-1-j] = true;
    		}
		
		this.rom[index] = new Register();
		this.rom[index].loadRegister(current_instruction, true);
	}
	
	public boolean[] getSelectedInstruction(boolean[] index) {
		int decIndex = booleanToInt(index);
		return this.rom[decIndex].getRegister();
	}
	
	private int booleanToInt(boolean[] a) {
		String s = "";
		for (int i = a.length-1; i>=0; i--) {
			if (a[i] == false) s+='0';
      	  	else if (a[i] == true) s+='1';
		}
		int decimal = Integer.parseInt(s, 2);
		return decimal;
	}
}
