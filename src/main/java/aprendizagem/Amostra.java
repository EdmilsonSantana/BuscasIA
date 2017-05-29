package aprendizagem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Amostra {

	private List<String> dados;

	private final Integer indexClassificacao;

	private Amostra(String[] dados, Integer indexClassificacao) {
		this.dados = Arrays.asList(dados);
		this.indexClassificacao = indexClassificacao;
	}

	protected Double getDado(int index) {
		Double dado = null;
		if (index >= 0 && index < this.dados.size()) {
			dado = Double.valueOf(this.dados.get(index));
		}
		return dado;
	}

	public List<Double> diferenca(Amostra amostra) {

		List<Double> diferencas = new ArrayList<>();

		for (int i = 0; i < this.dados.size(); i++) {
			if (i != indexClassificacao) {
				diferencas.add(this.getDado(i) - amostra.getDado(i));
			}
		}
		return diferencas;
	}

	public String getClassificacao() {
		return this.dados.get(indexClassificacao);
	}

	@Override
	public String toString() {

		return String.format("%s - %s", Arrays.toString(dados.toArray()), this.getClassificacao());
	}

	public static Amostra createInstance(String[] dados, int indexClassificacao) {
		return new Amostra(dados, indexClassificacao);
	}

}
