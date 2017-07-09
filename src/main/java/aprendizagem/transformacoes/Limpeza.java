package aprendizagem.transformacoes;

import java.util.List;

import aprendizagem.Amostra;

public class Limpeza {

	public void remover(List<Amostra> amostras, Integer coluna) {
		for (Amostra amostra : amostras) {
			amostra.removerDado(coluna - 1);
		}
	}
}
