package aprendizagem.rna.ativacao;

public class Limiar extends Ativacao {

	public Limiar(Double bias) {
		super(bias);
	}

	@Override
	public Double getEstadoAtivacao(Double processamento) {
		Double estado = 0.0;

		if (processamento >= bias) {
			estado = 1.0;
		}
		return estado;
	}

}
