package com.puzzle.otimizacao;

import com.puzzle.modelo.rainhas.RainhasPuzzle;
import com.puzzle.modelo.rainhas.Tabuleiro;

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
