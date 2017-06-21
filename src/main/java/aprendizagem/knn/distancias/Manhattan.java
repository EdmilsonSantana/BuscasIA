package aprendizagem.knn.distancias;

import aprendizagem.Amostra;

public class Manhattan extends Distancia {

	@Override
	public Double getDistancia(Amostra a, Amostra b) {
		Double distancia = 0.0;
		for (int index = 0; index < a.getTamanho(); index++) {
			distancia = Math.abs(a.getNumero(index) - b.getNumero(index));
		}
		return distancia;
	}

	@Override
	public String toString() {
		return "Manhattan";
	}

}
