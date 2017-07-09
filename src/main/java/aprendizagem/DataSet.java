package aprendizagem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import aprendizagem.knn.Classificacao;
import aprendizagem.transformacoes.Conversao;
import aprendizagem.transformacoes.Limpeza;
import aprendizagem.transformacoes.Normalizacao;
import aprendizagem.transformacoes.Transformacao;
import utils.Utils;

public class DataSet {

	private static final String DATA_DIR = "./aprendizagem/";
	private List<Amostra> treino;
	private List<Amostra> teste;

	public DataSet(String arquivoTreino, String arquivoTeste, Integer indexClassificacao) {
		this.treino = inicializarAmostras(Utils.lerCSV(DATA_DIR + arquivoTreino), indexClassificacao);
		this.teste = inicializarAmostras(Utils.lerCSV(DATA_DIR + arquivoTeste), indexClassificacao);
		Utils.getLogger().info(String.format("Treino: %d - Teste: %d", treino.size(), teste.size()));
	}

	public DataSet(String arquivoDados, Integer indexClassificacao, Double... porcentagemPorClassificacao) {
		this.treino = new ArrayList<>();
		this.teste = new ArrayList<>();
		agruparAmostras(inicializarAmostras(Utils.lerCSV(DATA_DIR + arquivoDados), indexClassificacao), porcentagemPorClassificacao);
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

	public void limpar(Limpeza limpeza, Integer coluna) {

		Utils.getLogger().info("Realizando limpeza dos dados...");

		List<Amostra> amostras = new ArrayList<>();
		amostras.addAll(this.teste);
		amostras.addAll(this.treino);

		limpeza.remover(amostras, coluna);
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

	public void converter(Conversao conversao, Integer... colunas) {

		Utils.getLogger().info("Convertendo valores...");

		List<Amostra> amostras = new ArrayList<>();
		amostras.addAll(this.teste);
		amostras.addAll(this.treino);

		for (Integer coluna : colunas) {
			conversao.converter(amostras, coluna);
		}
	}

	public void agruparAmostras(List<Amostra> amostras, Double... porcentagemPorClassificacao) {

		Map<Classificacao, List<Amostra>> classificacoes = this.contarClassificacoes(amostras);
		Integer indexClassificacao = 0;
		for (Entry<Classificacao, List<Amostra>> entry : classificacoes.entrySet()) {

			List<Amostra> amostrasAgrupadas = entry.getValue();
			Classificacao classificacao = entry.getKey();

			Integer quantidade = amostrasAgrupadas.size();

			Integer indexDivisao = Double.valueOf(quantidade * (porcentagemPorClassificacao[indexClassificacao] / 100.0)).intValue();

			this.treino.addAll(amostrasAgrupadas.subList(0, indexDivisao));
			this.teste.addAll(amostrasAgrupadas.subList(indexDivisao, quantidade));

			Utils.getLogger().info(String.format("Classificação: %s - Quantidade: %d - Treino: %d - Teste: %d", classificacao, quantidade,
					indexDivisao, quantidade - indexDivisao));

			indexClassificacao += 1;

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

	public void gerarCsvTreino() {

		Utils.escreverCsv(this.gerarCsv(this.treino), "dataset-treino.csv");
	}

	public void gerarCsvTeste() {

		Utils.escreverCsv(this.gerarCsv(this.teste), "dataset-teste.csv");
	}

	private String gerarCsv(List<Amostra> amostras) {

		StringBuilder csv = new StringBuilder();

		for (Amostra amostra : amostras) {
			csv.append(amostra.toString()
					.replace("[", "").replace("]", ""));
			csv.append(",");
			csv.append(amostra.getClassificacao());
			csv.append("\n");
		}

		return csv.toString();
	}
}
