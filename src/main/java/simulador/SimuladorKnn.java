package simulador;

import java.util.List;

import aprendizagem.DataSet;
import aprendizagem.knn.Classificacao;
import aprendizagem.knn.Knn;
import aprendizagem.knn.distancias.Distancia;
import aprendizagem.relatorio.Relatorio;
import aprendizagem.transformacoes.Limpeza;
import aprendizagem.transformacoes.Normalizacao;
import utils.Utils;

public class SimuladorKnn {

	public static void main(String[] args) {

		DataSet dataSet = new DataSet("ocupacao.csv", 7, 21.06, 6.33);
		// dataSet.converter(new ConversaoData("yyyy-MM-dd HH:mm:ss"), 2);
		dataSet.limpar(new Limpeza(), 1);
		dataSet.limpar(new Limpeza(), 1);
		dataSet.normalizar(new Normalizacao(), 1, 2, 3, 4, 5);

		for (int k = 1; k <= 11; k += 2) {

			classificar(k, Distancia.createInstance(Distancia.DISTANCIA_EUCLIDIANA), dataSet);
			classificar(k, Distancia.createInstance(Distancia.DISTANCIA_MANHATTAN), dataSet);
			classificar(k, Distancia.createInstance(Distancia.DISTANCIA_MINKOWSKI), dataSet);

		}
	}

	private static void classificar(Integer k, Distancia distancia, DataSet dataSet) {

		Knn knn = new Knn(distancia);

		List<Classificacao> classificacoes = knn.classificar(dataSet, k);

		String csv = Relatorio.gerarRelatorioCSV(classificacoes, dataSet);

		Utils.escreverCsv(csv, String.format("%s-%d.csv", distancia, k));
	}
}
