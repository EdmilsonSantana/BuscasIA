package simulador;

import agentes.AgenteObjetivo;
import buscas.cega.BuscaProfundidade;
import modelo.Puzzle;
import modelo.cores.CoresPuzzle;
import modelo.cores.Mapa;

public class SimuladorCores extends SimuladorPuzzle<Puzzle<Mapa>> {

	public SimuladorCores(AgenteObjetivo agentePuzzle) {
		super(agentePuzzle);
	}

	@Override
	Puzzle<Mapa> novoPuzzle() {
		return new CoresPuzzle();
	}

	public static void main(String[] args) {
		AgenteObjetivo agentePuzzle = new AgenteObjetivo(new BuscaProfundidade(true));

		SimuladorCores simuladorPuzzle = new SimuladorCores(agentePuzzle);

		System.out.println(simuladorPuzzle.simular(1));
	}

}
