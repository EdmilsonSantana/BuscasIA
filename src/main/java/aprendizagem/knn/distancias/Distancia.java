package aprendizagem.knn.distancias;

import aprendizagem.Amostra;

public abstract class Distancia {

	public static final String DISTANCIA_EUCLIDIANA = "DISTANCIA_EUCLIDIANA";
	public static final String DISTANCIA_MANHATTAN = "DISTANCIA_MANHATTAN";

	public abstract Double getDistancia(Amostra a, Amostra b);

	public static Distancia createInstance(String tipo) {
		Distancia distancia = null;
		if (DISTANCIA_EUCLIDIANA.equals(tipo)) {
			distancia = new Euclidiana();
		} else if (DISTANCIA_MANHATTAN.equals(tipo)) {
			distancia = new Manhattan();
		}
		return distancia;
	}
}
