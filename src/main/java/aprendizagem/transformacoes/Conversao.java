package aprendizagem.transformacoes;

import java.util.List;

import aprendizagem.Amostra;

public interface Conversao {

	void converter(List<Amostra> amostras, Integer coluna);
}
