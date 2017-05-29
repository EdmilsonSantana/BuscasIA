package buscas;

import java.util.Collection;

import modelo.Puzzle;

public abstract class Busca {
	public abstract <T extends Estado> No<T> busca(Puzzle<T> puzzle);

	protected <T extends Estado> No<T> criarRaiz(Puzzle<T> puzzle) {

		No<T> no = new No<T>(puzzle.getEstadoInicial(), null, 0.0);
		return no;
	}

	protected <T extends Estado> No<T> criarNo(No<T> pai, Puzzle<T> puzzle, T estado) {

		No<T> no = new No<T>(estado, pai, puzzle.custoCaminho(pai, estado));
		return no;
	}

	protected <T extends Estado> void expandir(No<T> no, Puzzle<T> puzzle, Collection<No<T>> borda) {

		Collection<T> estados = puzzle.getSucessores(no.getEstado());

		for (T estado : estados) {
			borda.add(this.criarNo(no, puzzle, estado));
		}
	}

	protected <T extends Estado> Boolean isObjetivo(Puzzle<T> puzzle, No<T> no) {
		return puzzle.testeObjetivo(no.getEstado());
	}
}
