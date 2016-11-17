package br.edu.insper.elemulator.model;

public class RAM {
	private Register[] ram;
	
	public RAM () {
		this.ram = new Register[32768];
		this.ram[0] = new Register();
	}
	
	public boolean[] getSelectedValue (boolean[] index) {
		int decIndex = booleanToInt(index);
		if (this.ram[decIndex] == null) {
			this.ram[decIndex] = new Register();
		}
		return this.ram[decIndex].getRegister();
	}

	public void setSelectedValue(boolean[] register, boolean[] index, boolean load) {
		int decIndex = booleanToInt(index);
		if (load){
			System.out.println("guardando na memoria na posicao: " + decIndex);
			if (this.ram[decIndex] == null) this.ram[decIndex] = new Register();
			this.ram[decIndex].loadRegister(register, load);
			}
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
	
	public boolean[] getSelectedValueInt(int index) {
		if (this.ram[index] == null) {
			this.ram[index] = new Register();
		}
		return this.ram[index].getRegister();
	}
	
	
}
