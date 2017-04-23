package com.puzzle.otimizacao;

import java.util.List;

import com.puzzle.buscas.Estado;

public interface Otimizacao<T extends Estado> {

	Double getCusto(T estado);

	T novoEstado();

	T getVizinhoAleatorio(T estado);

	List<T> getVizinhos(T estado);

}
