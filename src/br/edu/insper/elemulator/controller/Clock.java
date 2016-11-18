package br.edu.insper.elemulator.controller;

import java.util.Timer;



public class Clock {
	public static void main(String[] args) throws InterruptedException {
	Timer tempo;
	int[] k = new int[]{0,1,1,0,1,1,0,1,0,1};
	for (int i = 0; i<10; i++){
		int x = i;
	 	tempo = new Timer();
		tempo.schedule(new Tempo() {
	    	@Override


		    public void run() {

	    	    System.out.println(x);

		    }
		}, 750);
	}
}
}


