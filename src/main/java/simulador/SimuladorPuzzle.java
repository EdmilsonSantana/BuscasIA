
package simulador;

import agentes.AgenteObjetivo;
import buscas.Estado;
import modelo.Puzzle;

public abstract class SimuladorPuzzle<T extends Puzzle<? extends Estado>> {

	private AgenteObjetivo agentePuzzle;

	public SimuladorPuzzle(AgenteObjetivo agentePuzzle) {
		this.agentePuzzle = agentePuzzle;
	}

	public String simular(int quantidadeProblemas) {

		StringBuilder log = new StringBuilder();

		int count = 0;
		while (count < quantidadeProblemas) {

			Puzzle<?> puzzle = novoPuzzle();

			log.append(this.simular(puzzle));

			count += 1;
		}

		return log.toString();
	}

	public String simular(Puzzle<?> puzzle) {

		StringBuilder log = new StringBuilder();

		log.append("-----------------------------------------\n\n");

		long inicio = System.currentTimeMillis();

		String arvoreExplorada = agentePuzzle.resolverPuzzle(puzzle);

		long fim = System.currentTimeMillis();

		log.append(criarLogResultado(puzzle, fim - inicio));
		log.append("\n");
		log.append(arvoreExplorada);
		log.append("\n");

		return log.toString();
	}

	private String criarLogResultado(Puzzle<?> puzzle, long duracao) {

		StringBuilder log = new StringBuilder();
		log.append(puzzle.toString());
		log.append("Duração: ");
		log.append(duracao);
		log.append("\n");
		return log.toString();
	}

	abstract T novoPuzzle();

}
