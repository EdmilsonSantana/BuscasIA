
package com.puzzle.buscas;

public class No<T extends Estado> {

	private No<T> pai;

	private T estado;

	private Double custoCaminho;

	public No(T estado, No<T> pai, Double custoCaminho) {
		this.estado = estado;
		this.pai = pai;
		this.custoCaminho = custoCaminho;
	}

	public No<T> getPai() {

		return pai;
	}

	public void setPai(No<T> pai) {

		this.pai = pai;
	}

	public Double getCustoCaminho() {

		return this.getPai() != null ? custoCaminho + this.pai.getCustoCaminho() : custoCaminho;
	}

	public void setCustoCaminho(Double custoCaminho) {

		this.custoCaminho = custoCaminho;
	}

	public T getEstado() {

		return estado;
	}

	public void setEstado(T estado) {

		this.estado = estado;
	}

	@Override
	public boolean equals(Object obj) {

		Boolean isEquals = Boolean.FALSE;

		if (obj != null && obj instanceof No) {
			@SuppressWarnings("unchecked")
			No<T> no = (No<T>) obj;
			if (this == obj || this.getEstado().equals(no.getEstado())) {
				isEquals = Boolean.TRUE;
			}
		}
		return isEquals;
	}

	@Override
	public String toString() {
		return estado.toString();
	}

	@Override
	public int hashCode() {
		return estado.hashCode();
	}

}
