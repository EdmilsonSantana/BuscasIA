
package com.puzzle.buscas;

import com.puzzle.modelo.Puzzle;

public class AgentePuzzle {

	private Busca busca;

	public AgentePuzzle(Busca busca) {
		this.busca = busca;
	}

	public <T extends Estado> String resolverPuzzle(Puzzle<T> puzzle) {
		No<T> resultado = busca.busca(puzzle);
		String logProcessamento = "";
		if (resultado != null) {
			logProcessamento = this.explorar(resultado);
		} else {
			logProcessamento = "Não existe uma solução para este caso";
		}
		return logProcessamento;
	}

	private <T extends Estado> String explorar(No<T> no) {

		StringBuilder arvore = new StringBuilder();
		if (no != null) {
			arvore.append(explorar(no.getPai()));
			arvore.append(no.toString());
			arvore.append("\n\n");
			arvore.append("Custo: ");
			arvore.append(no.getCustoCaminho());
			arvore.append("\n\n");
		}
		return arvore.toString();
	}

}
