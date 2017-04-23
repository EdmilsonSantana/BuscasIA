package com.puzzle.modelo.estacoes;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puzzle.buscas.No;
import com.puzzle.modelo.Puzzle;
import com.puzzle.utils.Utils;

public class EstacoesPuzzle extends Puzzle<Estacao> {

	public static final String CSV_DISTANCIAS = "distancias.csv";

	public static final String CSV_MAPA = "mapa.csv";

	public static final String CSV_LINHAS = "linhas.csv";

	private static final int INDEX_CABECALHO_COLUNA_CSV = 0;

	private static final int INDEX_CABECALHO_LINHA_CSV = 0;

	private static final Double CUSTO_PADRAO_TROCA_LINHA = 5.0;

	private static final Double VELOCIDADE_PADRAO_METRO = 0.5;

	private Double custoTrocaLinha;

	private Double velocidadeMetro;

	private Map<String, Estacao> estacaoPorNome = new HashMap<>();

	public EstacoesPuzzle(String estacaoInicial, String estacaoObjetivo) {
		super(null);
		inicializarEstacoes(estacaoInicial, estacaoObjetivo);

	}

	private void inicializarEstacoes(String estacaoInicial, String estacaoObjetivo) {

		this.velocidadeMetro = VELOCIDADE_PADRAO_METRO;
		this.custoTrocaLinha = CUSTO_PADRAO_TROCA_LINHA;

		criarEstacoes();

		this.estadoInicial = estacaoPorNome.get(estacaoInicial);
		this.estadoObjetivo = estacaoPorNome.get(estacaoObjetivo);

		if (this.estadoInicial == null || this.estadoObjetivo == null) {
			throw new RuntimeException("Nomes das estações são inválidos.");
		}
	}

	private void criarEstacoes() {

		adicionarEstacoesMapa();
		adicionarDistanciasEstacoes();
		adicionarLinhasEstacoes();

	}

	private void adicionarDistanciasEstacoes() {
		List<String[]> distanciasEstacoes = Utils.lerCSV(CSV_DISTANCIAS);

		String[] cabecalho = distanciasEstacoes.remove(INDEX_CABECALHO_COLUNA_CSV);

		for (String[] distancias : distanciasEstacoes) {

			Estacao estacaoOrigem = this.obterEstacao(distancias[INDEX_CABECALHO_LINHA_CSV]);

			for (int i = 1; i < distancias.length; i++) {

				Estacao estacaoDestino = this.obterEstacao(cabecalho[i]);
				Double custoDistancia = Double.valueOf(distancias[i]);

				estacaoOrigem.adicionarDistancia(estacaoDestino, Utils.calcularIntervaloTempo(custoDistancia, velocidadeMetro));
			}
		}
	}

	private void adicionarLinhasEstacoes() {

		List<String[]> linhasEstacoes = Utils.lerCSV(CSV_LINHAS);

		String[] cabecalho = linhasEstacoes.remove(INDEX_CABECALHO_COLUNA_CSV);

		for (String[] linhas : linhasEstacoes) {

			Estacao estacao = this.obterEstacao(linhas[INDEX_CABECALHO_LINHA_CSV]);

			for (int i = 1; i < linhas.length; i++) {

				if (Utils.numeroParaBooleano(linhas[i])) {

					String descricaLinha = cabecalho[i].toUpperCase();
					estacao.adicionarLinha(Linha.valueOf(descricaLinha));
				}
			}
		}
	}

	private void adicionarEstacoesMapa() {

		List<String[]> mapa = Utils.lerCSV(CSV_MAPA);

		for (String[] caminho : mapa) {

			String no = caminho[0];
			String vizinho = caminho[1];
			String custo = caminho[2];

			Estacao estacaoNo = null;
			Estacao estacaoVizinha = null;

			estacaoNo = this.obterEstacao(no);

			estacaoVizinha = this.obterEstacao(vizinho);

			estacaoNo.adicionarVizinho(estacaoVizinha, Utils.calcularIntervaloTempo(Double.valueOf(custo), velocidadeMetro));
			estacaoVizinha.adicionarVizinho(estacaoNo, Utils.calcularIntervaloTempo(Double.valueOf(custo), velocidadeMetro));

		}
	}

	private Estacao obterEstacao(String nome) {
		Estacao estacao = null;

		if (!estacaoPorNome.containsKey(nome)) {
			estacao = new Estacao(nome);
			estacaoPorNome.put(nome, estacao);
		} else {
			estacao = estacaoPorNome.get(nome);
		}

		return estacao;
	}

	@Override
	public Collection<Estacao> getSucessores(Estacao estacao) {
		return estacao.getVizinhos();
	}

	@Override
	public Boolean testeObjetivo(Estacao estado) {
		return estadoObjetivo.equals(estado);
	}

	@Override
	public Double custoCaminho(No<Estacao> pai, Estacao estacao) {

		Double custo = this.getCustoEstacao(pai.getEstado(), estacao);

		No<Estacao> noEstacaoAntecessora = pai.getPai();
		if (noEstacaoAntecessora != null) {
			Estacao estacaoAntecessora = noEstacaoAntecessora.getEstado();
			Estacao estacaoAtual = pai.getEstado();

			Linha linhaAtual = estacaoAtual.getLinhaEmComum(estacaoAntecessora.getLinhas());
			custo += this.getCustoTrocaLinha(estacao, linhaAtual);
		}
		return custo;
	}

	private Double getCustoEstacao(Estacao estacao, Estacao vizinho) {
		return estacao.getCustoVizinho(vizinho);
	}

	private Double getCustoTrocaLinha(Estacao proximaEstacao, Linha linhaAtual) {
		Boolean trocouLinha = !proximaEstacao.possuiLinha(linhaAtual);
		return custoTrocaLinha * Utils.booleanoParaNumero(trocouLinha);
	}

}
