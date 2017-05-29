package buscas.local;

import buscas.Estado;

public class SimulatedAnnealing extends Otimizador {

	private static Double FATOR_ARREFECIMENTO = 0.9;

	private static Double LIMITE_CUSTO_MAIOR = 0.05;

	private static Double TEMPERATURA_INICIAL = 1.0;

	@Override
	public <T extends Estado> T otimizar(Otimizacao<T> otimizacao, int iteracoes) {
		T estado = otimizacao.novoEstado();
		T melhorEstado = estado;
		Double temperatura = TEMPERATURA_INICIAL;
		Double custoAtual = otimizacao.getCusto(estado);

		while (iteracoes > 0) {
			temperatura *= FATOR_ARREFECIMENTO;
			iteracoes -= 1;

			estado = otimizacao.getVizinhoAleatorio(melhorEstado);
			Double novoCusto = otimizacao.getCusto(estado);

			if (novoCusto < custoAtual || aceitaComMaiorCusto(temperatura, novoCusto, custoAtual)) {
				custoAtual = novoCusto;
				melhorEstado = estado;
			}

		}
		return melhorEstado;
	}

	private Boolean aceitaComMaiorCusto(Double temperatura, Double novoCusto, Double custoAtual) {
		Double exp = ((-1.0 * (novoCusto - custoAtual)) / temperatura);

		return Math.pow(Math.E, exp) > LIMITE_CUSTO_MAIOR;
	}

}
