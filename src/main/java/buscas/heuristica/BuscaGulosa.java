package buscas.heuristica;

import java.util.HashSet;
import java.util.PriorityQueue;

import buscas.Busca;
import buscas.ComparadorCusto;
import buscas.Estado;
import buscas.No;
import modelo.Puzzle;

public class BuscaGulosa extends Busca {

	@Override
	public <T extends Estado> No<T> busca(Puzzle<T> puzzle) {
		PriorityQueue<No<T>> fila = new PriorityQueue<>(1, new ComparadorCusto<>(puzzle.getObjetivo()));
		HashSet<No<T>> visitados = new HashSet<>();

		No<T> noObjetivo = null;
		fila.add(criarRaiz(puzzle));

		Boolean achou = Boolean.FALSE;
		while (!fila.isEmpty() && !achou) {

			No<T> no = fila.poll();

			if (super.isObjetivo(puzzle, no)) {
				achou = Boolean.TRUE;
				noObjetivo = no;
			} else {
				if (!visitados.contains(no)) {
					expandir(no, puzzle, fila);
					visitados.add(no);
				}
			}
		}
		return noObjetivo;
	}

}
