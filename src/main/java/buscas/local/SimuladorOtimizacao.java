package buscas.local;

import modelo.rainhas.RainhasPuzzle;
import modelo.rainhas.Tabuleiro;

public class SimuladorOtimizacao {

	public static void main(String[] args) {
		Otimizador otimizador = new HillClimbling();
		//Otimizador otimizador = new TabuSearch();

		Tabuleiro tabuleiro = new Tabuleiro(8);

		tabuleiro.inicializarValoresAleatorios();
		
		System.out.println(tabuleiro);
		System.out.println(tabuleiro.getHeuristica());
		
		Tabuleiro otimizado = otimizador.otimizar(new RainhasPuzzle(tabuleiro), 10);
		System.out.println(otimizado);
		System.out.println(otimizado.getHeuristica());
	}
}
