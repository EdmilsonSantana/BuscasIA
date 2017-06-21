package aprendizagem.rna.ativacao;

public abstract class Ativacao {

	protected Double bias;

	public Ativacao(Double bias) {
		if(bias == null) {
			this.bias = 0.0;
		} else {
			this.bias = bias;
		}
	}
	
	public Ativacao() {
		this(null);
	}

	public abstract Double getEstadoAtivacao(Double processamento);

	public Double getBias() {
		return bias;
	}
	
	public Boolean biasEmOrigem() {
		return this.bias == 0.0;
	}
}
