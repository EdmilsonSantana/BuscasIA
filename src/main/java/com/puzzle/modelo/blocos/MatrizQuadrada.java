
package com.puzzle.modelo.blocos;

import com.puzzle.buscas.Estado;
import com.puzzle.modelo.Matriz;

public class MatrizQuadrada extends Matriz implements Cloneable {

	private int[] posicaoVazia;

	private final int valorVazio = 0;

	private MatrizQuadrada(int linhas, int colunas) {
		super(linhas, colunas);
	}

	public MatrizQuadrada(int linhas) {
		super(linhas, linhas);
		posicaoVazia = new int[2];
	}

	public void moverPosicaoVazia(int linha, int coluna) {

		int valor = this.get(linha, coluna);
		this.add(posicaoVazia[INDICE_LINHA], posicaoVazia[INDICE_COLUNA], valor);
		this.add(linha, coluna, valorVazio);
	}

	@Override
	public void add(int linha, int coluna, int valor) {
		if (valor == valorVazio) {
			setPosicaoVazia(linha, coluna);
		}
		super.add(linha, coluna, valor);
	}

	public int[] getPosicaoVazia() {

		return posicaoVazia;
	}

	public void setPosicaoVazia(int linha, int coluna) {

		this.posicaoVazia[INDICE_LINHA] = linha;
		this.posicaoVazia[INDICE_COLUNA] = coluna;
	}
	

	@Override
	public Double getHeuristica(Estado estado) {

		return 0.0;
	}

	@Override
	protected MatrizQuadrada clone() {
		MatrizQuadrada matrizQuadrada = new MatrizQuadrada(this.getLength());
		matrizQuadrada.matriz = this.getMatriz();
		matrizQuadrada.setPosicaoVazia(posicaoVazia[0], posicaoVazia[1]);
		return matrizQuadrada;
	}

}
