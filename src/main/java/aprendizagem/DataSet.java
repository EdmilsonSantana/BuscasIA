package aprendizagem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import aprendizagem.transformacoes.Normalizacao;
import aprendizagem.transformacoes.Transformacao;
import utils.Utils;

public class DataSet {
 //https://archive.ics.uci.edu/ml/datasets/abalone
	private static final String DATA_DIR = "./aprendizagem/";
	private List<Amostra> treino;
	private List<Amostra> teste;

	public DataSet(String arquivoTreino, String arquivoTeste, Integer indexClassificacao) {
		this.treino = inicializarAmostras(Utils.lerCSV(DATA_DIR + arquivoTreino), indexClassificacao);
		this.teste = inicializarAmostras(Utils.lerCSV(DATA_DIR + arquivoTeste), indexClassificacao);
	}

	public DataSet(String arquivoDados, Integer indexClassificacao, Integer porcentagemTreino) {
		this.treino = new ArrayList<>();
		this.teste = new ArrayList<>();
		agruparAmostras(inicializarAmostras(Utils.lerCSV(DATA_DIR + arquivoDados), indexClassificacao), porcentagemTreino);
	}

	private List<Amostra> inicializarAmostras(List<String[]> csv, Integer indexClassificacao) {
		List<Amostra> amostras = new ArrayList<>();
		for (String[] dados : csv) {
			amostras.add(Amostra.createInstance(dados, indexClassificacao));
		}
		return amostras;
	}

	public void normalizar(Normalizacao normalizacao, Integer... colunas) {

		Utils.getLogger().info("Realizando normalização utilizando valores máximos e mínimos...");

		List<Amostra> amostras = new ArrayList<>();
		amostras.addAll(this.teste);
		amostras.addAll(this.treino);

		for (Integer coluna : colunas) {
			normalizacao.normalizar(amostras, coluna);
		}
	}

	public void transformar(Transformacao transformacao, Integer... colunas) {

		Utils.getLogger().info("Transformando categóricos em númericos...");

		List<Amostra> amostras = new ArrayList<>();
		amostras.addAll(this.teste);
		amostras.addAll(this.treino);

		for (Integer coluna : colunas) {
			transformacao.transformar(amostras, coluna);
		}
	}

	public void agruparAmostras(List<Amostra> amostras, Integer porcentagemTreino) {

		Map<Classificacao, List<Amostra>> classificacoes = this.contarClassificacoes(amostras);

		for (Entry<Classificacao, List<Amostra>> entry : classificacoes.entrySet()) {

			List<Amostra> amostrasAgrupadas = entry.getValue();
			Classificacao classificacao = entry.getKey();

			Integer quantidade = amostrasAgrupadas.size();

			Integer indexDivisao = Double.valueOf(quantidade * (porcentagemTreino / 100.0)).intValue();

			this.treino.addAll(amostrasAgrupadas.subList(0, indexDivisao));
			this.teste.addAll(amostrasAgrupadas.subList(indexDivisao, quantidade));

			Utils.getLogger().info(String.format("Classificação: %s - Quantidade: %d - Teste: %d - Treino: %d", classificacao, quantidade,
					indexDivisao, quantidade - indexDivisao));

		}

		Utils.getLogger().info(String.format("%d amostras de treino", this.treino.size()));
		Utils.getLogger().info(String.format("%d amostras de teste", this.teste.size()));
	}

	public Map<Classificacao, List<Amostra>> contarClassificacoes(List<Amostra> amostras) {

		Map<Classificacao, List<Amostra>> classificacoes = new HashMap<>();
		List<Amostra> amostrasAgrupadas = null;

		for (Amostra amostra : amostras) {
			Classificacao classificacao = amostra.getClassificacao();

			if (!classificacoes.containsKey(classificacao)) {
				amostrasAgrupadas = new ArrayList<>();
				classificacoes.put(classificacao, amostrasAgrupadas);
			} else {
				amostrasAgrupadas = classificacoes.get(classificacao);
			}

			amostrasAgrupadas.add(amostra);
		}
		return classificacoes;
	}

	public List<Classificacao> getClassificacoesPossiveis() {
		List<Classificacao> classificacoes = new ArrayList<>();
		for (Amostra amostra : this.teste) {
			if (!classificacoes.contains(amostra.getClassificacao())) {
				classificacoes.add(amostra.getClassificacao());
			}
		}
		return classificacoes;
	}

	public List<Amostra> getAmostrasTeste() {
		return this.teste;
	}

	public List<Amostra> getAmostrasTreino() {
		return treino;
	}

	public Amostra getAmostraTeste(Integer index) {
		return this.teste.get(index);
	}

	public Integer getQuantidadeTestes() {
		return this.teste.size();
	}
}
