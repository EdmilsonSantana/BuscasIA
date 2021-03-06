package modelo.rainhas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import buscas.local.Otimizacao;
import modelo.Puzzle;

public class RainhasPuzzle extends Puzzle<Tabuleiro> implements Otimizacao<Tabuleiro> {

	public RainhasPuzzle(Tabuleiro estadoInicial) {
		super(estadoInicial);
	}

	public RainhasPuzzle(Tabuleiro estadoInicial, Tabuleiro objetivo) {
		super(estadoInicial, objetivo);
	}

	@Override
	public List<Tabuleiro> getSucessores(Tabuleiro tabuleiro) {
		List<Tabuleiro> tabuleiros = new ArrayList<>();

		Tabuleiro novoTabuleiro = null;
		for(int rainha = 1; rainha < tabuleiro.getLength(); rainha++) {
			for (int permutacao = rainha + 1; permutacao <= tabuleiro.getLength(); permutacao++) {

				int linha = tabuleiro.getLinha(rainha);
				int linhaVizinha = tabuleiro.getLinha(permutacao);

				novoTabuleiro = tabuleiro.clone();
				novoTabuleiro.moverRainha(linha, linhaVizinha);

				tabuleiros.add(novoTabuleiro);
			}
		}

		return tabuleiros;
	}

	@Override
	public Boolean testeObjetivo(Tabuleiro tabuleiro) {
		Boolean objetivo = Boolean.FALSE;
		if (this.estadoObjetivo != null) {
			objetivo = estadoObjetivo.equals(tabuleiro);
		} else {
			objetivo = !tabuleiro.emAtaque();
		}
		return objetivo;
	}

	@Override
	public Double getCusto(Tabuleiro estado) {
		return estado.getHeuristica();
	}

	@Override
	public Tabuleiro novoEstado() {
		return estadoInicial;
	}

	@Override
	public Tabuleiro getVizinhoAleatorio(Tabuleiro estado) {
		List<Tabuleiro> vizinhos = this.getVizinhos(estado);
		Tabuleiro aleatorio = null;
		if (vizinhos != null && !vizinhos.isEmpty()) {
			aleatorio = vizinhos.get(new Random().nextInt(vizinhos.size()));
		}
		return aleatorio;
	}

	@Override
	public List<Tabuleiro> getVizinhos(Tabuleiro estado) {
		return this.getSucessores(estado);
	}

}
