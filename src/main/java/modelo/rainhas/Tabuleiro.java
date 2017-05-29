package modelo.rainhas;

import java.util.Random;

import buscas.Estado;
import modelo.Matriz;

public class Tabuleiro extends Matriz {

	private int rainhasEmAtaque = -1;

	private Tabuleiro(int linhas, int colunas) {
		super(linhas, colunas);
	}

	public Tabuleiro(int linhas) {
		super(linhas, 1);
	}

	public int getRainha(int linha) {
		return super.get(linha - 1, 0);
	}

	public int getLinha(int rainha) {
		int linha = -1;
		for (int i = 1; i <= this.getLength(); i++) {
			if (this.getRainha(i) == rainha) {
				linha = i;
				break;
			}
		}

		return linha;
	}

	public void addRainha(int linha, int rainha) {
		super.add(linha - 1, 0, rainha);
	}

	@Override
	public void inicializarValoresAleatorios() {
		int quantidade = this.getLength() * this.getQuantidadeColunas();

		int[] gerados = new int[quantidade];

		for (int i = 1; i <= this.getLength(); i++) {
			int aleatorio = new Random().nextInt(quantidade);
			while (gerados[aleatorio] == 1) {
				aleatorio += 1;
				aleatorio %= quantidade;
			}
			gerados[aleatorio] = 1;
			this.addRainha(i, aleatorio + 1);
		}
	}

	public void moverRainha(int linhaOrigem, int linhaDestino) {
		int rainhaOrigem = this.getRainha(linhaOrigem);
		int rainhaDestino = this.getRainha(linhaDestino);
		this.addRainha(linhaDestino, rainhaOrigem);
		this.addRainha(linhaOrigem, rainhaDestino);
		this.rainhasEmAtaque = calcularQuantidadeEmAtaque();
	}

	public int getRainhasEmAtaque() {
		if (rainhasEmAtaque == -1) {
			this.rainhasEmAtaque = calcularQuantidadeEmAtaque();
		}
		return rainhasEmAtaque;
	}

	public Boolean emAtaque() {
		return getRainhasEmAtaque() > 0;
	}

	private int calcularQuantidadeEmAtaque() {
		int emAtaque = 0;

		for (int linhaRainha = 1; linhaRainha <= getLength(); linhaRainha++) {
			emAtaque += contarAtaques(linhaRainha);
		}

		return emAtaque;
	}

	private int contarAtaques(int linhaRainha) {

		int linhaRainhaVizinha = linhaRainha + 1;

		int emAtaque = 0;

		while (linhaRainhaVizinha <= getLength()) {

			Boolean emDiagonal = this.emDiagonal(linhaRainha, linhaRainhaVizinha);

			if (emDiagonal) {
				emAtaque += 1;
			}

			linhaRainhaVizinha += 1;
		}
		return emAtaque;
	}

	private Boolean emDiagonal(int linhaRainha, int linhaRainhaVizinha) {
		Boolean emDiagonal = Boolean.FALSE;

		int colunaRainhaVizinha = getRainha(linhaRainhaVizinha);
		int colunaRainha = getRainha(linhaRainha);

		int distanciaLinha = Math.abs((linhaRainhaVizinha - linhaRainha));
		int distanciaColuna = Math.abs((colunaRainhaVizinha - colunaRainha));
		int diferenca = distanciaLinha - distanciaColuna;

		if (diferenca == 0) {
			emDiagonal = Boolean.TRUE;
		}

		return emDiagonal;

	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int linha = 1; linha <= matriz.length; linha++) {
			str.append("|");
			for (int coluna = 1; coluna <= matriz.length; coluna++) {
				if (this.getRainha(linha) == coluna) {
					str.append(" R |");
				} else {
					str.append(" 0 |");
				}
			}
			str.append("\n");
		}
		return str.toString();
	}

	@Override
	public Tabuleiro clone() {
		Tabuleiro tabuleiro = new Tabuleiro(this.getLength());
		tabuleiro.matriz = this.getMatriz();
		tabuleiro.rainhasEmAtaque = rainhasEmAtaque;
		return tabuleiro;
	}

	private Double distanciaEntreRainhas(Tabuleiro tabuleiro) {
		Double distancia = 0.0;
		for (int rainha = 1; rainha <= this.getLength(); rainha++) {
			distancia += Math.abs(this.distanciaEntreRainhas(rainha, tabuleiro));
		}
		return distancia;
	}

	private int distanciaEntreRainhas(int rainha, Tabuleiro tabuleiro) {
		return this.getLinha(rainha) - tabuleiro.getLinha(rainha);
	}

	@Override
	public Double getHeuristica(Estado objetivo) {
		Double custo = null;
		if (objetivo != null) {
			custo = distanciaEntreRainhas((Tabuleiro) objetivo);
		} else {
			custo = Integer.valueOf(this.getRainhasEmAtaque()).doubleValue();
		}
		return custo;
	}

}
