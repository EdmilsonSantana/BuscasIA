package simulador;

import java.util.List;

import aprendizagem.DataSet;
import aprendizagem.knn.Classificacao;
import aprendizagem.knn.Knn;
import aprendizagem.knn.distancias.Distancia;
import aprendizagem.relatorio.Relatorio;
import aprendizagem.transformacoes.Normalizacao;
import aprendizagem.transformacoes.Transformacao;
import utils.Utils;

public class SimuladorKnn {

	public static void main(String[] args) {
		
		DataSet dataSet = new DataSet("abalone.csv", 8, 70);

		dataSet.transformar(new Transformacao(), 1);
		dataSet.normalizar(new Normalizacao(), 2, 3, 4, 5, 6, 7, 8);

		Distancia distancia = Distancia.createInstance(Distancia.DISTANCIA_EUCLIDIANA);
		Knn knn = new Knn(distancia);

		List<Classificacao> classificacoes = knn.classificar(dataSet, 1);
		
		String csv = Relatorio.gerarRelatorioCSV(classificacoes, dataSet);
		
		Utils.escreverCsv(csv, String.format("abalone-%d-%s.csv", 1, distancia));
	}
}
