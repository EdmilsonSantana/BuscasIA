package aprendizagem.rna;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aprendizagem.rna.ativacao.Ativacao;
import aprendizagem.rna.erro.Corretor;
import utils.Utils;

public class Neuronio {

	private List<Double> pesos = new ArrayList<>();

	private Ativacao ativacao;

	private Corretor corretor;

	public Neuronio(Integer sinapses, Ativacao ativacao, Corretor corretor) {

		this.inicializarPesos(sinapses);
		this.inicializarAtivacao(ativacao);
		this.corretor = corretor;
	}

	public Neuronio(List<Double> pesos, Ativacao ativacao, Corretor corretor) {

		this.pesos.addAll(pesos);
		this.inicializarAtivacao(ativacao);
		this.corretor = corretor;
	}

	private void inicializarPesos(Integer sinapses) {
		int i = 0;
		while (i < sinapses) {
			pesos.add(Utils.aleatorio().doubleValue());
			i += 1;
		}
	}

	private void inicializarAtivacao(Ativacao ativacao) {

		if (ativacao != null && ativacao.biasEmOrigem()) {
			pesos.add(Utils.aleatorio().doubleValue());
			this.ativacao = ativacao;
		}
	}

	public Boolean treinar(List<Double> entradas, Double saidaDesejada) {
		
		Boolean treinou = Boolean.TRUE;
		
		Double processamento = this.processarEntradas(entradas);
		Double estadoAtivacao = ativacao.getEstadoAtivacao(processamento);
		
		if (estadoAtivacao != saidaDesejada) {
			this.ajustarPesos(entradas, saidaDesejada, estadoAtivacao);
			treinou = Boolean.FALSE;
		}
		return treinou;
	}

	private Double processarEntradas(List<Double> entradas) {
		Double somatorio = 0.0;
		for (int index = 0; index < this.pesos.size(); index++) {
			somatorio += this.pesos.get(index) * entradas.get(index);
		}
		return somatorio;
	}

	private void ajustarPesos(List<Double> entradas, Double saidaDesejada, Double saidaObtida) {
		for (int index = 0; index < this.pesos.size(); index++) {

			Double peso = this.pesos.get(index);
			Double entrada = entradas.get(index);

			peso += corretor.corrigirErro(entrada, saidaDesejada, saidaObtida);

			this.pesos.add(peso);
		}
	}

	@Override
	public String toString() {
		return Arrays.toString(this.pesos.toArray());
	}

}
