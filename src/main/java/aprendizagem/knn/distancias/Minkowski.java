package aprendizagem.knn.distancias;

import aprendizagem.Amostra;

public class Minkowski extends Distancia {

	public Minkowski(Integer fator) {
		super(fator);
	}

	@Override
	public Double getDistancia(Amostra a, Amostra b) {
		Double distancia = 0.0;
		for (int index = 0; index < a.getTamanho(); index++) {
			Double diferenca = Math.abs(a.getNumero(index) - b.getNumero(index));
			distancia += Math.pow(diferenca, fator);
		}
		return Math.pow(distancia, 1.0 / fator);
	}

	@Override
	public String toString() {
		String str = "";
		if (FATOR_EUCLIDIANA.equals(fator)) {
			str = DISTANCIA_EUCLIDIANA.toLowerCase();
		} else if (FATOR_MANHATTAN.equals(fator)) {
			str = DISTANCIA_MANHATTAN.toLowerCase();
		} else if (FATOR_MINKOWSKI.equals(fator)) {
			str = DISTANCIA_MINKOWSKI.toLowerCase();
		}
		return str;
	}

}
