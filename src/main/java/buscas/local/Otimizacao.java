package buscas.local;

import java.util.List;

import buscas.Estado;

public interface Otimizacao<T extends Estado> {

	Double getCusto(T estado);

	T novoEstado();

	T getVizinhoAleatorio(T estado);

	List<T> getVizinhos(T estado);

}
