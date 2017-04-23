package com.puzzle.simulador;

import com.puzzle.buscas.AgentePuzzle;
import com.puzzle.buscas.BuscaProfundidade;
import com.puzzle.modelo.Puzzle;
import com.puzzle.modelo.cores.CoresPuzzle;
import com.puzzle.modelo.cores.Mapa;

public class SimuladorCores extends SimuladorPuzzle<Puzzle<Mapa>> {

	public SimuladorCores(AgentePuzzle agentePuzzle) {
		super(agentePuzzle);
	}

	@Override
	Puzzle<Mapa> novoPuzzle() {
		return new CoresPuzzle();
	}

	public static void main(String[] args) {
		AgentePuzzle agentePuzzle = new AgentePuzzle(new BuscaProfundidade(true));

		SimuladorCores simuladorPuzzle = new SimuladorCores(agentePuzzle);

		System.out.println(simuladorPuzzle.simular(1));
	}

}
