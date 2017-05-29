package aprendizagem;

import java.util.ArrayList;
import java.util.List;

import utils.Utils;

public class DataSet {

	private static final String DATA_DIR = "./aprendizagem/";
	private List<Amostra> treino;
	private List<Amostra> teste;

	public DataSet(String arquivoTreino, String arquivoTeste, Integer indexClassificacao) {
		this.treino = inicializarAmostras(Utils.lerCSV(DATA_DIR + arquivoTreino), indexClassificacao);
		this.teste = inicializarAmostras(Utils.lerCSV(DATA_DIR + arquivoTeste), indexClassificacao);
	}

	private List<Amostra> inicializarAmostras(List<String[]> csv, Integer indexClassificacao) {
		List<Amostra> amostras = new ArrayList<>();
		for (String[] dados : csv) {
			amostras.add(Amostra.createInstance(dados, indexClassificacao));
		}
		return amostras;
	}

	public String compararClassificacoes(List<Amostra> classificacoes) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < classificacoes.size(); i++) {
			stringBuilder.append(String.format("%s -> Classificado como: %s\n", teste.get(i), 
					classificacoes.get(i).getClassificacao()));
		}
		return stringBuilder.toString();
	}

	public List<Amostra> getAmostrasTeste(Integer quantidade) {
		return teste.subList(0, quantidade);
	}

	public List<Amostra> getAmostrasTreino() {
		return treino;
	}
}
