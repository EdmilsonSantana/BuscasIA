package aprendizagem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aprendizagem.knn.Classificacao;

public class Amostra {

	private List<String> dados;

	private final Classificacao classificacao;

	private Amostra(String[] dados, Integer indexClassificacao) {
		this.dados = new ArrayList<>(Arrays.asList(dados));
		this.classificacao = new Classificacao(this.dados.remove(indexClassificacao.intValue()));
	}

	public Double getNumero(Integer index) {

		return Double.valueOf(this.getTexto(index));
	}

	public String getTexto(Integer index) {
		String dado = null;
		if (index >= 0 && index < this.dados.size()) {
			dado = this.dados.get(index);
		}
		return dado;
	}

	public void setDado(Double dado, Integer index) {
		this.setDado(String.valueOf(dado), index);
	}

	public void setDado(String dado, Integer index) {
		if (index >= 0 && index < this.dados.size()) {
			this.dados.set(index, dado);
		}
	}
	
	public void adicionarDado(String dado) {
		this.dados.add(dado);
	}
	
	public void removerDado(Integer index) {
		this.dados.remove(index.intValue());
	}

	public Integer getTamanho() {
		return this.dados.size();
	}

	public Classificacao getClassificacao() {
		return this.classificacao;
	}

	@Override
	public String toString() {

		return Arrays.toString(dados.toArray());
	}

	@Override
	public boolean equals(Object obj) {
		Boolean equals = Boolean.FALSE;
		if (obj != null && obj instanceof Amostra) {
			Amostra amostra = (Amostra) obj;
			if (this == obj || this.toString().equals(amostra.toString())) {
				equals = Boolean.TRUE;
			}
		}
		return equals;
	}

	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

	public static Amostra createInstance(String[] dados, int indexClassificacao) {
		return new Amostra(dados, indexClassificacao);
	}

}
