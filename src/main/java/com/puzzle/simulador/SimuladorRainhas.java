package com.puzzle.simulador;

import com.puzzle.buscas.AgentePuzzle;
import com.puzzle.buscas.BuscaGulosa;
import com.puzzle.modelo.Puzzle;
import com.puzzle.modelo.rainhas.RainhasPuzzle;
import com.puzzle.modelo.rainhas.Tabuleiro;

public class SimuladorRainhas extends SimuladorPuzzle<Puzzle<Tabuleiro>> {

	public SimuladorRainhas(AgentePuzzle agentePuzzle) {
		super(agentePuzzle);
	}

	@Override
	Puzzle<Tabuleiro> novoPuzzle() {
		Tabuleiro tabuleiro = new Tabuleiro(8);

		tabuleiro.inicializarValoresAleatorios();

		return new RainhasPuzzle(tabuleiro);
	}

	public static void main(String[] args) {

		AgentePuzzle agentePuzzle = new AgentePuzzle(new BuscaGulosa());

		SimuladorRainhas simuladorRainhas = new SimuladorRainhas(agentePuzzle);

		System.out.println(simuladorRainhas.simular(criarAleatorio1()));
		System.out.println(simuladorRainhas.simular(criarAleatorio2()));
	}

	private static RainhasPuzzle criarAleatorio1() {

		Tabuleiro tabuleiro = new Tabuleiro(8);

		tabuleiro.addRainha(1, 1);
		tabuleiro.addRainha(2, 2);
		tabuleiro.addRainha(3, 5);
		tabuleiro.addRainha(4, 3);
		tabuleiro.addRainha(5, 4);
		tabuleiro.addRainha(6, 8);
		tabuleiro.addRainha(7, 6);
		tabuleiro.addRainha(8, 7);

		return new RainhasPuzzle(tabuleiro);
	}

	private static RainhasPuzzle criarAleatorio2() {

		Tabuleiro tabuleiro = new Tabuleiro(8);

		tabuleiro.addRainha(1, 7);
		tabuleiro.addRainha(2, 1);
		tabuleiro.addRainha(3, 5);
		tabuleiro.addRainha(4, 2);
		tabuleiro.addRainha(5, 8);
		tabuleiro.addRainha(6, 4);
		tabuleiro.addRainha(7, 6);
		tabuleiro.addRainha(8, 3);

		return new RainhasPuzzle(tabuleiro);
	}
	

}
