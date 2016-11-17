package br.edu.insper.elemulator.model;

public class InstruDec {
	private boolean muxIOsel, muxAMsel, zx, nx, zy, ny, f, no, loadA, loadD, loadM, loadPC;
	
	private boolean tempA, tempB, tempC, tempD, tempE;
	
	public void execute (boolean[] instruction, boolean zr, boolean ng) {
		
		muxIOsel = instruction[15];
		muxAMsel = instruction[12];
		zx = instruction[11];
		nx = instruction[10];
		zy = instruction[9];
		ny = instruction[8];
		f = !instruction[7];
		no = instruction[6];
		loadA = (!instruction[15]) || (instruction[15] && instruction[5]);
		loadD = instruction[4] && instruction[15];
		loadM = instruction[3] && instruction[15];
		
		tempA = instruction[2] && zr;
		tempB = instruction[1] && ng;
		tempC = tempA || tempB;
		tempD = (!zr) && (!ng);
		tempE = instruction[0] && tempD;
		
		loadPC = (tempC || tempE) && instruction[15];	
	}

	public boolean isMuxIOsel() {
		return muxIOsel;
	}

	public boolean isMuxAMsel() {
		return muxAMsel;
	}

	public boolean isZx() {
		return zx;
	}

	public boolean isNx() {
		return nx;
	}

	public boolean isZy() {
		return zy;
	}

	public boolean isNy() {
		return ny;
	}

	public boolean isF() {
		return f;
	}

	public boolean isNo() {
		return no;
	}

	public boolean isLoadA() {
		return loadA;
	}

	public boolean isLoadD() {
		return loadD;
	}

	public boolean isLoadM() {
		return loadM;
	}

	public boolean isLoadPC() {
		return loadPC;
	}
}
