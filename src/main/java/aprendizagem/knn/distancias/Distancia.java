package aprendizagem.knn.distancias;

import aprendizagem.Amostra;

public abstract class Distancia {

	public static final String DISTANCIA_EUCLIDIANA = "EUCLIDIANA";
	public static final String DISTANCIA_MANHATTAN = "MANHATTAN";
	public static final String DISTANCIA_MINKOWSKI = "MINKOWSKI";

	protected Integer fator;

	protected static final Integer FATOR_EUCLIDIANA = 2;
	protected static final Integer FATOR_MANHATTAN = 1;
	protected static final Integer FATOR_MINKOWSKI = 3;

	public Distancia(Integer fator) {
		this.fator = fator;
	}

	public abstract Double getDistancia(Amostra a, Amostra b);

	public static Distancia createInstance(String tipo) {
		Distancia distancia = null;
		if (DISTANCIA_EUCLIDIANA.equals(tipo)) {
			distancia = new Minkowski(FATOR_EUCLIDIANA);
		} else if (DISTANCIA_MANHATTAN.equals(tipo)) {
			distancia = new Minkowski(FATOR_MANHATTAN);
		} else if (DISTANCIA_MINKOWSKI.equals(tipo)) {
			distancia = new Minkowski(FATOR_MINKOWSKI);
		}
		return distancia;
	}
}
