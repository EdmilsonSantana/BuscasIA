
package com.puzzle.modelo.blocos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.puzzle.modelo.Puzzle;

public class BlocosPuzzle extends Puzzle<MatrizQuadrada> {

	public BlocosPuzzle(MatrizQuadrada estadoInicial, MatrizQuadrada objetivo) {
		super(estadoInicial, objetivo);
	}

	private List<Movimento> getAcoes(MatrizQuadrada estado) {
		List<Movimento> acoes = new LinkedList<>();

		int[] posicao = estado.getPosicaoVazia();

		int linha = posicao[MatrizQuadrada.INDICE_LINHA];
		int coluna = posicao[MatrizQuadrada.INDICE_COLUNA];

		if ((linha + 1) < estado.getLength()) {
			acoes.add(Movimento.BAIXO);
		}
		if ((linha - 1) >= 0) {
			acoes.add(Movimento.CIMA);
		}
		if ((coluna + 1) < estado.getLength()) {
			acoes.add(Movimento.DIREITA);
		}
		if ((coluna - 1) >= 0) {
			acoes.add(Movimento.ESQUERDA);
		}

		return acoes;
	}

	private MatrizQuadrada getNovoEstado(MatrizQuadrada estado, Movimento movimento) {
		int[] posicao = estado.getPosicaoVazia();

		int linha = posicao[MatrizQuadrada.INDICE_LINHA];
		int coluna = posicao[MatrizQuadrada.INDICE_COLUNA];

		MatrizQuadrada novoEstado = estado.clone();

		if (Movimento.BAIXO.equals(movimento)) {
			linha += 1;
		}
		if (Movimento.CIMA.equals(movimento)) {
			linha -= 1;
		}
		if (Movimento.DIREITA.equals(movimento)) {
			coluna += 1;
		}
		if (Movimento.ESQUERDA.equals(movimento)) {
			coluna -= 1;
		}

		novoEstado.moverPosicaoVazia(linha, coluna);

		return novoEstado;
	}

	private enum Movimento {
		BAIXO, CIMA, DIREITA, ESQUERDA
	}

	@Override
	public List<MatrizQuadrada> getSucessores(MatrizQuadrada estado) {
		List<MatrizQuadrada> sucessores = new ArrayList<>();
		List<Movimento> movimentos = this.getAcoes(estado);
		for (Movimento movimento : movimentos) {
			sucessores.add(this.getNovoEstado(estado, movimento));
		}
		return sucessores;
	}

	@Override
	public Boolean testeObjetivo(MatrizQuadrada estado) {

		return estado.equals(estadoObjetivo);
	}

}
