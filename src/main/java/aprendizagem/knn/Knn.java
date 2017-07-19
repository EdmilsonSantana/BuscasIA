package aprendizagem.knn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import aprendizagem.Amostra;
import aprendizagem.DataSet;
import aprendizagem.knn.distancias.Distancia;
import utils.Utils;

public class Knn {

	private final Distancia distancia;

	public Knn(Distancia distancia) {
		this.distancia = distancia;
	}

	public List<Classificacao> classificar(DataSet dataSet, Integer vizinhos) {

		Utils.getLogger().info(String.format("Classificando com %d vizinhos e com distância %s", vizinhos, distancia));

		List<Classificacao> classificacoes = new ArrayList<>();
		List<Amostra> amostrasTeste = dataSet.getAmostrasTeste();
		for (Amostra teste : amostrasTeste) {
			classificacoes.add(this.getClassificacao(dataSet.getAmostrasTreino(), teste, vizinhos));
		}

		return classificacoes;
	}

	private Classificacao getClassificacao(List<Amostra> amostras, Amostra teste, Integer vizinhos) {

		Collections.sort(amostras, new ComparadorDistancias(teste));
		return this.getClassificacaoVizinho(amostras.subList(0, vizinhos));
	}

	private Classificacao getClassificacaoVizinho(List<Amostra> vizinhos) {

		Map<Classificacao, Integer> classificacoes = this.contarClassificacoes(vizinhos);

		Entry<Classificacao, Integer> classificacaoVizinho = null;

		for (Entry<Classificacao, Integer> classificacao : classificacoes.entrySet()) {

			if (classificacaoVizinho == null || classificacao.getValue() > classificacaoVizinho.getValue()) {
				classificacaoVizinho = classificacao;
			}
		}
		return classificacaoVizinho.getKey();
	}

	private Map<Classificacao, Integer> contarClassificacoes(List<Amostra> vizinhos) {

		Map<Classificacao, Integer> classificacoes = new HashMap<>();

		Integer valorInicial = 1;

		for (Amostra vizinho : vizinhos) {
			Classificacao classificacao = vizinho.getClassificacao();

			if (!classificacoes.containsKey(classificacao)) {
				classificacoes.put(classificacao, valorInicial);
			} else {
				classificacoes.put(classificacao, classificacoes.get(classificacao) + valorInicial);
			}
		}

		return classificacoes;
	}

	class ComparadorDistancias implements Comparator<Amostra> {

		private Amostra teste;

		private Map<Amostra, Double> cache = new HashMap<>();
		
		public ComparadorDistancias(Amostra teste) {
			this.teste = teste;
		}

		@Override
		public int compare(Amostra a, Amostra b) {
			Double distanciaA = cache.get(a);
			if(distanciaA == null){
				distanciaA = distancia.getDistancia(teste, a);
				cache.put(a, distanciaA);
			}
			Double distanciaB = cache.get(b);
			if(distanciaB == null){
				distanciaB = distancia.getDistancia(teste, b);
				cache.put(b, distanciaB);
			}
			return distanciaA.compareTo(distanciaB);
		}
	}

}
