package aprendizagem.transformacoes;

import java.util.List;

import aprendizagem.Amostra;

public class Normalizacao {

	private static Integer INDEX_MAXIMO = 1;

	private static Integer INDEX_MINIMO = 0;

	public void normalizar(List<Amostra> amostras, Integer coluna) {
		Double[] minMax = this.getMinMax(amostras, coluna);

		Double minimo = minMax[INDEX_MINIMO];
		Double maximo = minMax[INDEX_MAXIMO];

		for (Amostra amostra : amostras) {

			Double dado = amostra.getNumero(coluna - 1);
			dado = (dado - minimo) / (maximo - minimo);
			amostra.setDado(dado, coluna - 1);
		}
	}

	private Double[] getMinMax(List<Amostra> amostras, Integer coluna) {
		Double maximo = null;
		Double minimo = null;

		for (Amostra amostra : amostras) {
			Double dado = amostra.getNumero(coluna - 1);

			if (maximo == null || dado > maximo) {
				maximo = dado;
			}

			if (minimo == null || dado < minimo) {
				minimo = dado;
			}

		}
		return new Double[] { minimo, maximo };
	}
}
