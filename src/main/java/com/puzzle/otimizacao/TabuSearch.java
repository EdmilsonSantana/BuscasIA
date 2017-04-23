package com.puzzle.otimizacao;

import java.util.HashSet;
import java.util.List;

import com.puzzle.buscas.Estado;

public class TabuSearch extends Otimizador {

	@Override
	public <T extends Estado> T otimizar(Otimizacao<T> otimizacao, int iteracoes) {
		HashSet<T> tabu = new HashSet<>();

		T estado = otimizacao.novoEstado();
		Double custoEstado = otimizacao.getCusto(estado);

		T melhorEstado = estado;
		Double menorCusto = custoEstado;

		tabu.add(melhorEstado);

		while (iteracoes > 0) {
			iteracoes -= 1;

			estado = this.melhorSolucao(otimizacao, tabu, otimizacao.getVizinhos(estado));
			custoEstado = otimizacao.getCusto(estado);

			if (custoEstado < menorCusto) {
				melhorEstado = estado;
				menorCusto = custoEstado;

			}

			tabu.add(estado);

		}
		return melhorEstado;
	}

	public <T extends Estado> T melhorSolucao(Otimizacao<T> otimizacao, HashSet<T> tabu, List<T> vizinhos) {
		T menorVizinho = null;
		for (T vizinho : vizinhos) {
			if (menorVizinho == null) {
				menorVizinho = vizinho;
			} else {
				if (!tabu.contains(vizinho) && otimizacao.getCusto(vizinho) < otimizacao.getCusto(menorVizinho)) {
					menorVizinho = vizinho;
				}
			}
		}
		return menorVizinho;
	}

}
