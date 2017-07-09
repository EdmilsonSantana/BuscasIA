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
		
		Utils.getLogger().info(String.format("Classificando com %d vizinhos e com distância %s",
				vizinhos, distancia));
		
		List<Classificacao> classificacoes = new ArrayList<>();
		
		for (Amostra teste : dataSet.getAmostrasTeste()) {
			classificacoes.add(this.getClassificacao(dataSet.getAmostrasTreino(), teste, vizinhos));
		}
		
		return classificacoes;
	}

	private Classificacao getClassificacao(List<Amostra> amostras, Amostra teste, Integer vizinhos) {

		Map<Amostra, Double> distanciaPorAmostra = new HashMap<>();

		for (Amostra amostra : amostras) {

			Double distanciaAmostra = distancia.getDistancia(amostra, teste);

			distanciaPorAmostra.put(amostra, distanciaAmostra);
		}
		return this.getClassificacaoVizinho(this.getVizinhosProximos(distanciaPorAmostra, vizinhos));
	}

	private List<Amostra> getVizinhosProximos(Map<Amostra, Double> distanciaPorAmostra, Integer vizinhos) {

		List<Entry<Amostra, Double>> distanciasOrdenadas = new ArrayList<>(distanciaPorAmostra.entrySet());
		
		Collections.sort(distanciasOrdenadas, new ComparadorDistancias());
		
		List<Amostra> vizinhosProximos = new ArrayList<>();
		
		while(vizinhos > 0) {
			Entry<Amostra, Double> vizinhoProximo = distanciasOrdenadas.get(vizinhos - 1);
			vizinhosProximos.add(vizinhoProximo.getKey());
			vizinhos -= 1;
		}
		
		return vizinhosProximos;
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

	class ComparadorDistancias implements Comparator<Entry<Amostra, Double>> {

		@Override
		public int compare(Entry<Amostra, Double> a, Entry<Amostra, Double> b) {
			return a.getValue().compareTo(b.getValue());
		}
	}

}
