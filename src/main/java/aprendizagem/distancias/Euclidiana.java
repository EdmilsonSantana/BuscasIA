package aprendizagem.distancias;

import java.util.List;

import aprendizagem.Amostra;

public class Euclidiana extends Distancia {

	private static final int QUADRATICA = 2;

	@Override
	public Double getDistancia(Amostra a, Amostra b) {
		List<Double> diferencas = a.diferenca(b);
		Double distancia = 0.0;
		for (Double diferenca : diferencas) {
			distancia += Math.pow(diferenca, QUADRATICA);
		}
		return Math.sqrt(distancia);
	}

}
