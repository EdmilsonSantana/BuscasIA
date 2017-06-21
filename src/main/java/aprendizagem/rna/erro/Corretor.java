package aprendizagem.rna.erro;

public class Corretor {

	private Double taxaAprendizagem;

	public Corretor(Double taxaAprendizagem) {
		this.taxaAprendizagem = taxaAprendizagem;
	}

	public Double corrigirErro(Double entrada, Double saidaDesejada, Double saidaObtida) {
		return taxaAprendizagem * entrada * (saidaDesejada - saidaObtida);
	}
}
