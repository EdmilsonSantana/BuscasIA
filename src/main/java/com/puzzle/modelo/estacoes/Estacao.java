package com.puzzle.modelo.estacoes;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.puzzle.buscas.Estado;

public class Estacao extends Estado {

	private String nome;

	private HashSet<Linha> linhas = new HashSet<>();

	private Map<Estacao, Double> distancias = new HashMap<>();

	private Map<Estacao, Double> vizinhos = new HashMap<>();

	public Estacao(String nome) {
		this.nome = nome;
	}

	public void adicionarVizinho(Estacao estacao, Double custo) {
		this.vizinhos.put(estacao, custo);
	}

	public Collection<Estacao> getVizinhos() {
		return this.vizinhos.keySet();
	}

	public Double getCustoVizinho(Estacao estacao) {
		return this.vizinhos.get(estacao);
	}

	public Double getDistancia(Estacao estacao) {
		return this.distancias.get(estacao);
	}

	public void adicionarDistancia(Estacao estacao, Double distancia) {
		this.distancias.put(estacao, distancia);
	}

	public void adicionarLinha(Linha linha) {
		linhas.add(linha);
	}

	public Boolean possuiLinha(Linha linha) {
		return this.linhas.contains(linha);
	}

	public Boolean possuiLinhas(Set<Linha> linhas) {

		return getLinhaEmComum(linhas) != null;
	}

	public Linha getLinhaEmComum(Set<Linha> linhas) {
		Linha linhaEmComum = null;
		for (Linha linha : linhas) {
			if (this.linhas.contains(linha)) {
				linhaEmComum = linha;
				break;
			}
		}
		return linhaEmComum;
	}

	public Set<Linha> getLinhas() {
		return this.linhas;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public int hashCode() {

		return nome.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Boolean isEquals = Boolean.FALSE;
		if (obj != null && obj instanceof Estacao) {
			if (obj == this || ((Estacao) obj).getNome().equals(this.getNome())) {
				isEquals = Boolean.TRUE;
			}
		}
		return isEquals;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public Double getHeuristica(Estado estado) {

		return this.getDistancia((Estacao) estado);
	}

}
