package com.puzzle.buscas;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import com.puzzle.modelo.Puzzle;

public class BuscaLargura extends Busca {

	@Override
	public <T extends Estado> No<T> busca(Puzzle<T> puzzle) {
		HashSet<No<T>> visitados = new HashSet<>();
		Queue<No<T>> fila = new LinkedList<>();

		fila.add(criarRaiz(puzzle));

		No<T> noObjetivo = null;
		Boolean encontrou = Boolean.FALSE;
		while (!fila.isEmpty() && !encontrou) {
			No<T> no = fila.poll();

			if (super.isObjetivo(puzzle, no)) {
				encontrou = Boolean.TRUE;
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
