package aprendizagem.knn.distancias;

import aprendizagem.Amostra;

public abstract class Distancia {

	public static final String DISTANCIA_EUCLIDIANA = "EUCLIDIANA";
	public static final String DISTANCIA_MANHATTAN = "MANHATTAN";
	public static final String DISTANCIA_MINKOWSKI = "MINKOWSKI";

	public abstract Double getDistancia(Amostra a, Amostra b);

	public static Distancia createInstance(String tipo) {
		Distancia distancia = null;
		if (DISTANCIA_EUCLIDIANA.equals(tipo)) {
			distancia = new Euclidiana();
		} else if (DISTANCIA_MANHATTAN.equals(tipo)) {
			distancia = new Manhattan();
		} else if (DISTANCIA_MINKOWSKI.equals(tipo)) {
			distancia = new Minkowski();
		}
		return distancia;
	}
}
