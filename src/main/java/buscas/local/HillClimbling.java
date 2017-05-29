package buscas.local;

import java.util.List;

import buscas.Estado;

public class HillClimbling extends Otimizador {

	@Override
	public <T extends Estado> T otimizar(Otimizacao<T> otimizacao, int iteracoes) {
		T estadoAtual = otimizacao.novoEstado();
		Boolean perda = Boolean.FALSE;
		
		while (!perda) {
			List<T> vizinhos = otimizacao.getVizinhos(estadoAtual);
			T vizinho = melhorSolucao(otimizacao, vizinhos);
			if (otimizacao.getCusto(vizinho) < otimizacao.getCusto(estadoAtual)) {
				estadoAtual = vizinho;
			} else {
				perda = Boolean.TRUE;
			}

		}
		return estadoAtual;
	}

	private <T extends Estado> T melhorSolucao(Otimizacao<T> otimizacao, List<T> vizinhos) {
		T melhor = null;

		for (T vizinho : vizinhos) {
			if (melhor == null) {
				melhor = vizinho;
			} else {
				if (otimizacao.getCusto(vizinho) < otimizacao.getCusto(melhor)) {
					melhor = vizinho;
				}
			}
		}
		return melhor;
	}

}
