package buscas.cega;

import java.util.Collection;
import java.util.HashSet;

import buscas.Busca;
import buscas.Estado;
import buscas.No;
import modelo.Puzzle;

public class BuscaProfundidade extends Busca {

	private Boolean iterativo;
	private int limite;
	private Boolean corte;
	@SuppressWarnings("rawtypes")
	private HashSet<No> visitados;

	public BuscaProfundidade() {
		this.limite = -1;
		this.iterativo = Boolean.FALSE;
	}

	public BuscaProfundidade(Boolean iterativo) {
		this.iterativo = iterativo;
	}

	public BuscaProfundidade(int limite) {
		this.limite = limite;
	}

	@Override
	public <T extends Estado> No<T> busca(Puzzle<T> puzzle) {
		No<T> noObjetivo = null;
		No<T> raiz = super.criarRaiz(puzzle);
		this.visitados = new HashSet<>();

		if (iterativo) {
			noObjetivo = this.buscaIterativa(raiz, puzzle);
		} else {
			noObjetivo = this.buscaEmProfundidade(raiz, puzzle, limite);
		}
		return noObjetivo;
	}

	private <T extends Estado> No<T> buscaIterativa(No<T> no, Puzzle<T> puzzle) {
		No<T> noObjetivo = null;
		int profundidade = 0;

		do {
			this.corte = Boolean.FALSE;
			this.visitados.clear();
			noObjetivo = this.buscaEmProfundidade(no, puzzle, profundidade);
			profundidade += 1;
		} while (noObjetivo == null && corte);

		return noObjetivo;
	}

	private <T extends Estado> No<T> buscaEmProfundidade(No<T> no, Puzzle<T> puzzle, int limite) {
		No<T> noObjetivo = null;

		if (super.isObjetivo(puzzle, no)) {
			noObjetivo = no;
		} else if (noObjetivo == null && limite == 0) {
			corte = Boolean.TRUE;
		} else if (!visitados.contains(no)) {
			visitados.add(no);
			noObjetivo = expandir(no, puzzle, limite);
			visitados.remove(no);
		}

		return noObjetivo;
	}

	private <T extends Estado> No<T> expandir(No<T> pai, Puzzle<T> puzzle, int limite) {
		No<T> noObjetivo = null;
		Collection<T> sucessores = puzzle.getSucessores(pai.getEstado());
		for (T sucessor : sucessores) {
			No<T> noSucessor = super.criarNo(pai, puzzle, sucessor);
			noObjetivo = this.buscaEmProfundidade(noSucessor, puzzle, limite - 1);
			if (noObjetivo != null) {
				break;
			}
		}
		return noObjetivo;
	}

}
