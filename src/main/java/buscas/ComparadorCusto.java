package buscas;

import java.util.Comparator;

public class ComparadorCusto<T extends Estado> implements Comparator<No<T>> {

	private T objetivo;

	public ComparadorCusto() {
		this.objetivo = null;
	}

	public ComparadorCusto(T estado) {
		this.objetivo = estado;
	}

	@Override
	public int compare(No<T> a, No<T> b) {
		T estadoA = a.getEstado();
		T estadoB = b.getEstado();

		Double custoA = a.getCustoCaminho() + estadoA.getHeuristica(objetivo);
		Double custoB = b.getCustoCaminho() + estadoB.getHeuristica(objetivo);

		if (custoB > custoA) {
			return -1;
		} else if (custoB < custoA) {
			return 1;
		} else {
			return 0;
		}
	}

}
