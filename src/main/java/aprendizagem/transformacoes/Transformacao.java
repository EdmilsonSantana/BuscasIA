package aprendizagem.transformacoes;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import aprendizagem.Amostra;

public class Transformacao {

	private static final String ATIVO = "1";
	private static final String INATIVO = "0";

	public void transformar(List<Amostra> amostras, Integer coluna) {
		Collection<String> categorias = this.getCategorias(amostras, coluna);

		for (Amostra amostra : amostras) {
			this.adicionarColunas(categorias, amostra, coluna);
			amostra.removerDado(coluna - 1);
		}
	}

	public void adicionarColunas(Collection<String> categorias, Amostra amostra, Integer coluna) {
		for (String categoria : categorias) {

			String dado = amostra.getTexto(coluna - 1);

			if (categoria.equals(dado)) {
				amostra.adicionarDado(ATIVO);
			} else {
				amostra.adicionarDado(INATIVO);
			}
		}
	}

	public Collection<String> getCategorias(List<Amostra> amostras, Integer coluna) {
		Collection<String> valores = new HashSet<>();

		for (Amostra amostra : amostras) {
			valores.add(amostra.getTexto(coluna - 1));
		}

		return valores;
	}
}
