package com.puzzle.simulador;

import com.puzzle.buscas.AgentePuzzle;
import com.puzzle.buscas.BuscaUniforme;
import com.puzzle.modelo.Puzzle;
import com.puzzle.modelo.estacoes.Estacao;
import com.puzzle.modelo.estacoes.EstacoesPuzzle;

public class SimuladorEstacoes extends SimuladorPuzzle<Puzzle<Estacao>> {

	public SimuladorEstacoes(AgentePuzzle agentePuzzle) {
		super(agentePuzzle);
	}

	@Override
	Puzzle<Estacao> novoPuzzle() {
		EstacoesPuzzle estacoesPuzzle = new EstacoesPuzzle("E13", "E9");
		return estacoesPuzzle;
	}

	public static void main(String[] args) {
		AgentePuzzle agentePuzzle = new AgentePuzzle(new BuscaUniforme());

		SimuladorEstacoes simuladorPuzzle = new SimuladorEstacoes(agentePuzzle);

		System.out.println(simuladorPuzzle.simular(1));
	}
}
