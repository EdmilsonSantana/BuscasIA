package aprendizagem;

import java.util.ArrayList;
import java.util.List;

import aprendizagem.distancias.Distancia;

public class Knn {

	private final Distancia distancia;

	private Knn(Distancia distancia) {
		this.distancia = distancia;
	}

	public List<Amostra> getVizinhos(DataSet dataSet, Integer vizinhos) {
		List<Amostra> classificacoes = new ArrayList<>();
		List<Amostra> amostrasTeste = dataSet.getAmostrasTeste(2);
		for (Amostra teste : amostrasTeste) {
			classificacoes.add(this.getVizinhoMaisProximo(dataSet.getAmostrasTreino(), teste, vizinhos));
		}
		
		return classificacoes;
	}

	private Amostra getVizinhoMaisProximo(List<Amostra> amostras, Amostra teste, Integer vizinhos) {

		Amostra vizinhoProximo = amostras.remove(0);
		Double maisProximo = distancia.getDistancia(vizinhoProximo, teste);

		for (Amostra amostra : amostras) {
			
			Double distanciaAmostra = distancia.getDistancia(amostra, teste);
			
			if (distanciaAmostra < maisProximo) {
				vizinhoProximo = amostra;
				maisProximo = distanciaAmostra;
			}
		}
		return vizinhoProximo;
	}

	public static void main(String[] args) {
		DataSet dataSet = new DataSet("dota-treino.csv", "dota-teste.csv", 0);
		Knn knn = new Knn(Distancia.createInstance(Distancia.DISTANCIA_EUCLIDIANA));

		System.out.println(dataSet.compararClassificacoes(knn.getVizinhos(dataSet, 1)));
	}

}
