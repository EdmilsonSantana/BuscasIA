package aprendizagem.relatorio;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import aprendizagem.Amostra;
import aprendizagem.DataSet;
import aprendizagem.knn.Classificacao;
import utils.Utils;

public class Relatorio {

	private static final String SEPARADOR_CSV = ",";

	private static final String CELULA_VAZIA = "-";

	private static final String QUEBRA_LINHA = "\n";

	public static String gerarRelatorioCSV(List<Classificacao> classificacoes, DataSet dataSet) {
		Utils.getLogger().info("Gerando matriz de confusão...");

		StringBuilder relatorio = new StringBuilder();

		Map<Classificacao, Map<Classificacao, Integer>> classificacoesMapeadas = mapearClassificacoes(classificacoes, dataSet);

		relatorio.append(gerarMatrizConfusao(classificacoesMapeadas));
		
		String taxaAcerto = calcularTaxaAcerto(classificacoesMapeadas, dataSet);
		Utils.getLogger().info(taxaAcerto);

		return relatorio.toString();
	}

	private static String calcularTaxaAcerto(Map<Classificacao, Map<Classificacao, Integer>> classificacoesMapeadas, DataSet dataSet) {

		Integer totalAcertos = 0;

		for (Entry<Classificacao, Map<Classificacao, Integer>> linha : classificacoesMapeadas.entrySet()) {
			Classificacao classificacao = linha.getKey();

			Map<Classificacao, Integer> contagemPorClassificacao = linha.getValue();
			Integer contagem = Utils.soma(contagemPorClassificacao.values());

			Integer acertos = contagemPorClassificacao.get(classificacao);
			totalAcertos += acertos;

			Utils.getLogger().info(String.format("Classificacao: %s - Total: %d - Acertou: %d - Errou %d", 
					classificacao, contagem, acertos, Math.abs(contagem - acertos)));
		}

		return String.format("\nTaxa de Acerto: %f\n", totalAcertos / dataSet.getQuantidadeTestes().doubleValue());
	}

	private static String gerarMatrizConfusao(Map<Classificacao, Map<Classificacao, Integer>> classificacoesMapeadas) {

		StringBuilder csv = new StringBuilder();

		csv.append(cabecalhoCsv(classificacoesMapeadas.keySet()));

		for (Entry<Classificacao, Map<Classificacao, Integer>> linha : classificacoesMapeadas.entrySet()) {

			Classificacao classificacao = linha.getKey();

			csv.append(classificacao);

			Map<Classificacao, Integer> contagem = linha.getValue();

			for (Entry<Classificacao, Integer> entry : contagem.entrySet()) {
				csv.append(SEPARADOR_CSV);
				csv.append(entry.getValue());
			}

			csv.append(QUEBRA_LINHA);
		}

		return csv.toString();
	}

	private static String cabecalhoCsv(Set<Classificacao> classificacoes) {
		StringBuilder cabecalhoCsv = new StringBuilder();

		cabecalhoCsv.append(QUEBRA_LINHA);
		cabecalhoCsv.append(CELULA_VAZIA);

		for (Classificacao classificacao : classificacoes) {
			cabecalhoCsv.append(SEPARADOR_CSV);
			cabecalhoCsv.append(classificacao.toString());

		}

		cabecalhoCsv.append(QUEBRA_LINHA);

		return cabecalhoCsv.toString();
	}

	private static Map<Classificacao, Map<Classificacao, Integer>> mapearClassificacoes(List<Classificacao> classificacoes,
			DataSet dataSet) {

		Map<Classificacao, Map<Classificacao, Integer>> classificacoesMapeadas = new TreeMap<>();

		Map<Classificacao, Integer> contagemPorClassificacao = null;

		List<Classificacao> classificacoesPossiveis = dataSet.getClassificacoesPossiveis();

		Integer incremento = 1;

		for (int index = 0; index < classificacoes.size(); index++) {

			Classificacao classificacao = classificacoes.get(index);
			Amostra amostra = dataSet.getAmostraTeste(index);

			if (!classificacoesMapeadas.containsKey(amostra.getClassificacao())) {
				contagemPorClassificacao = new TreeMap<>();
				inicializarContagem(classificacoesPossiveis, contagemPorClassificacao);
				classificacoesMapeadas.put(amostra.getClassificacao(), contagemPorClassificacao);
			} else {
				contagemPorClassificacao = classificacoesMapeadas.get(amostra.getClassificacao());
			}

			contagemPorClassificacao.put(classificacao, contagemPorClassificacao.get(classificacao) + incremento);
		}

		return classificacoesMapeadas;

	}

	private static void inicializarContagem(List<Classificacao> classificacoes, Map<Classificacao, Integer> contagemPorClassificacao) {
		for (Classificacao classificacao : classificacoes) {
			contagemPorClassificacao.put(classificacao, 0);
		}
	}

}
