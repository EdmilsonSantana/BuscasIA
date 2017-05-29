package simulador;

import agentes.AgenteObjetivo;
import buscas.heuristica.BuscaA;
import modelo.Puzzle;
import modelo.estacoes.Estacao;
import modelo.estacoes.EstacoesPuzzle;

public class SimuladorEstacoes extends SimuladorPuzzle<Puzzle<Estacao>> {

	public SimuladorEstacoes(AgenteObjetivo agentePuzzle) {
		super(agentePuzzle);
	}

	@Override
	Puzzle<Estacao> novoPuzzle() {
		EstacoesPuzzle estacoesPuzzle = new EstacoesPuzzle("E7", "E8");
		return estacoesPuzzle;
	}

	public static void main(String[] args) {
		AgenteObjetivo agentePuzzle = new AgenteObjetivo(new BuscaA());

		SimuladorEstacoes simuladorPuzzle = new SimuladorEstacoes(agentePuzzle);

		System.out.println(simuladorPuzzle.simular(1));
	}
}
