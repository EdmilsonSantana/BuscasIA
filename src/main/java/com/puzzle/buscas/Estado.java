package com.puzzle.buscas;

public abstract class Estado {
	public abstract Double getHeuristica(Estado objetivo);

	public Double getHeuristica() {
		return getHeuristica(null);
	}
}
