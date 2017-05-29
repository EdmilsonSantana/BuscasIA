package buscas.local;

import buscas.Estado;

public abstract class Otimizador {

	public abstract <T extends Estado> T otimizar(Otimizacao<T> otimizacao, int iteracoes);

	public <T extends Estado> T otimizar(Otimizacao<T> otimizacao) {
		return this.otimizar(otimizacao, 0);
	}

}
