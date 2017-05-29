package buscas.heuristica;

import java.util.Collection;
import java.util.PriorityQueue;

import buscas.Busca;
import buscas.ComparadorCusto;
import buscas.Estado;
import buscas.No;
import modelo.Puzzle;

public class BuscaA extends Busca {

	@Override
	public <T extends Estado> No<T> busca(Puzzle<T> puzzle) {
		PriorityQueue<No<T>> fila = new PriorityQueue<>(1, new ComparadorCusto<>(puzzle.getObjetivo()));

		No<T> noObjetivo = null;
		fila.add(criarRaiz(puzzle));

		Boolean achou = Boolean.FALSE;
		while (!fila.isEmpty() && !achou) {

			No<T> no = fila.poll();
			
			if (super.isObjetivo(puzzle, no)) {
				achou = Boolean.TRUE;
				noObjetivo = no;
			} else {
				expandir(no, puzzle, fila);
			}
		}
		return noObjetivo;
	}

	@Override
	protected <T extends Estado> void expandir(No<T> no, Puzzle<T> puzzle, Collection<No<T>> borda) {
		No<T> antecessor = no.getPai();

		Estado estadoAntecessor = null;
		if (antecessor != null) {
			estadoAntecessor = antecessor.getEstado();
		}

		Collection<T> estados = puzzle.getSucessores(no.getEstado());

		for (T estado : estados) {
			if (!estado.equals(estadoAntecessor)) {
				borda.add(this.criarNo(no, puzzle, estado));
			}
		}
	}

}
