package br.edu.insper.elemulator.model;

public class Register {
	protected boolean[] register;
	
	public Register() {
		this.register = new boolean[16];
	}
	
	public boolean[] getRegister () {
		return this.register;
	}

	public void loadRegister(boolean[] register, boolean load) {
		if (load) {
			for (int i = 0; i<register.length; i++) this.register[i] = register[i];
		}
	}
}
