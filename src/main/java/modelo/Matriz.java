
package modelo;

import java.util.Arrays;
import java.util.Random;

import buscas.Estado;

public abstract class Matriz extends Estado {

	protected int[][] matriz;

	public static final int INDICE_LINHA = 0;

	public static final int INDICE_COLUNA = 1;

	public Matriz(int linhas, int colunas) {
		if (linhas <= 0 || colunas <= 0) {
			throw new RuntimeException("Estado inválido.");
		}
		this.matriz = new int[linhas][colunas];
	}

	public void add(int linha, int coluna, int valor) {
		matriz[linha][coluna] = valor;
	}

	public int get(int linha, int coluna) {

		return matriz[linha][coluna];
	}

	public int[] get(int linha) {

		return matriz[linha];
	}

	public int getLength() {

		return matriz.length;
	}

	public int getQuantidadeColunas() {
		return matriz[0].length;
	}

	protected int[][] getMatriz() {
		int[][] clone = new int[getLength()][getQuantidadeColunas()];
		for (int i = 0; i < clone.length; i++) {
			clone[i] = matriz[i].clone();
		}
		return clone;
	}

	private Boolean compararValores(Matriz m) {

		return Arrays.deepEquals(matriz, m.getMatriz());
	}

	@Override
	public boolean equals(Object obj) {

		Boolean isEquals = Boolean.FALSE;
		if (obj != null && obj instanceof Matriz) {

			if (this == obj || this.compararValores(((Matriz) obj))) {
				isEquals = Boolean.TRUE;
			}
		}
		return isEquals;
	}

	@Override
	public int hashCode() {

		return Arrays.deepHashCode(matriz);
	}

	@Override
	public String toString() {

		StringBuilder str = new StringBuilder();
		for (int i = 0; i < matriz.length; i++) {
			str.append(Arrays.toString(matriz[i]));
			str.append("\n");
		}
		return str.toString();
	}

	public void inicializarValoresAleatorios() {

		int quantidade = this.getLength() * this.getQuantidadeColunas();

		int[] gerados = new int[quantidade];

		for (int i = 0; i < this.getLength(); i++) {
			for (int j = 0; j < this.getQuantidadeColunas(); j++) {
				int aleatorio = new Random().nextInt(quantidade);
				while (gerados[aleatorio] == 1) {
					aleatorio += 1;
					aleatorio %= quantidade;
				}
				gerados[aleatorio] = 1;
				this.add(i, j, aleatorio);
			}
		}
	}

	public void inicializarValoresOrdenados() {

		int numeroOrdenado = 0;

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				this.add(i, j, numeroOrdenado);
				numeroOrdenado += 1;
			}
		}
	}

}
