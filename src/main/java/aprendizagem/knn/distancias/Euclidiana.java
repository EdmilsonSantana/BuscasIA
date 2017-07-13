package aprendizagem.knn.distancias;

import aprendizagem.Amostra;

public class Euclidiana extends Distancia {

	@Override
	public Double getDistancia(Amostra a, Amostra b) {
		Double distancia = 0.0;
		for (int index = 0; index < a.getTamanho(); index++) {
			Double diferenca = Math.abs(a.getNumero(index) - b.getNumero(index));
			distancia += diferenca * diferenca;
		}
		return Math.sqrt(distancia);
	}

	@Override
	public String toString() {
		return "Euclidiana";
	}
}
