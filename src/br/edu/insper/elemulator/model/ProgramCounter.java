package br.edu.insper.elemulator.model;

public class ProgramCounter extends Register{
	

	
	public void execute (boolean[] register, boolean load, boolean reset) {
		if (reset) reset();
		else if (load) loadRegister(register, load);
		else increment();
	}
	
	private void increment() {
		boolean[] result = new boolean[16];
		String s = "";
		
		for (int i = result.length-1; i>=0; i--) {
			if (register[i] == false) s+='0';
      	  	else if (register[i] == true) s+='1';
		}
		int decimal = Integer.parseInt(s, 2);
		
		decimal++;
		
		String temp = Integer.toBinaryString(decimal);
		for (int j = 0; j<temp.length();j++) {
    		if (temp.charAt(j)=='0') register[temp.length()-1-j] = false;
    		else if (temp.charAt(j)=='1') register[temp.length()-1-j] = true;
    	}

	}
	
	private void reset () {
		for  (int i = 0; i<= register.length; i++) {
			register[i] = false;
		}
	}
}
