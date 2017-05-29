
package modelo;

import java.util.Collection;

import buscas.Estado;
import buscas.No;

public abstract class Puzzle<T extends Estado> {

	protected T estadoInicial;

	protected T estadoObjetivo;

	public Puzzle(T estadoInicial, T objetivo) {
		this.estadoInicial = estadoInicial;
		this.estadoObjetivo = objetivo;
	}

	public Puzzle(T estadoInicial) {
		this.estadoInicial = estadoInicial;
	}

	public abstract Collection<T> getSucessores(T estado);

	public abstract Boolean testeObjetivo(T estado);

	public Double custoCaminho(No<T> pai, T estado) {
		return 1.0;
	}

	public T getEstadoInicial() {
		return estadoInicial;
	}

	public T getObjetivo() {
		return estadoObjetivo;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Estado Inicial: \n");
		str.append(this.estadoInicial);
		str.append("\n");
		str.append("Estado final: \n");
		if (this.estadoObjetivo == null) {
			str.append("Não possui um estado objetivo definido.");
		} else {
			str.append(this.estadoObjetivo);
		}
		str.append("\n\n");
		return str.toString();
	}

}
