package modelo.cores;

import buscas.Estado;
import modelo.Matriz;
import utils.Utils;

public class Mapa extends Matriz implements Cloneable {

	private static final int REGIOES_BRASIL = 5;

	private int regiao = 0;

	private int quantidadeCoresEmConflito = -1;

	private Mapa(int linhas, int colunas) {
		super(linhas, colunas);
	}

	public Mapa() {
		super(1, REGIOES_BRASIL);
	}

	public void pintar(Cor cor) {
		if (regiao < REGIOES_BRASIL && !Cor.BRANCO.equals(cor)) {
			super.add(0, regiao, cor.ordinal());
			regiao += 1;
			this.quantidadeCoresEmConflito = this.quantidadeCoresEmConflito();
		}
	}

	public int getCorRegiao(int regiao) {
		int cor = -1;
		if (regiao >= 1 && regiao <= REGIOES_BRASIL) {
			cor = super.get(0, regiao - 1);
		}
		return cor;
	}

	public Boolean pintado() {
		return regiao == REGIOES_BRASIL;
	}

	public int getQuantidadeRegioes() {
		return REGIOES_BRASIL;
	}

	public Boolean coresEmConflito() {
		return getQuantidadeCoresEmConflito() > 0;
	}

	public int getQuantidadeCoresEmConflito() {
		if (quantidadeCoresEmConflito == -1) {
			this.quantidadeCoresEmConflito = this.quantidadeCoresEmConflito();
		}
		return quantidadeCoresEmConflito;
	}

	private int quantidadeCoresEmConflito() {
		int conflitos = 0;

		for (int i = 1; i <= REGIOES_BRASIL; i++) {

			int cor = this.getCorRegiao(i);
			int corAnterior = this.getCorRegiao(i - 1);
			int proximaCorAnterior = this.getCorRegiao(i - 2);
			int corPosterior = this.getCorRegiao(i + 1);
			int proximaCorPosterior = this.getCorRegiao(i + 2);

			conflitos += Utils.booleanoParaNumero(cor == corAnterior);
			conflitos += Utils.booleanoParaNumero(cor == proximaCorAnterior);

			conflitos += Utils.booleanoParaNumero(cor == corPosterior);
			conflitos += Utils.booleanoParaNumero(cor == proximaCorPosterior);

		}

		return conflitos;
	}

	@Override
	protected Mapa clone() {
		Mapa mapa = new Mapa();
		mapa.matriz = this.getMatriz();
		mapa.regiao = this.regiao;
		mapa.quantidadeCoresEmConflito = this.quantidadeCoresEmConflito;
		return mapa;
	}

	@Override
	public String toString() {
		Cor[] cores = Cor.values();

		StringBuilder str = new StringBuilder();
		str.append("|");

		for (int i = 1; i <= REGIOES_BRASIL; i++) {
			str.append(cores[this.getCorRegiao(i)]);
			str.append("|");
		}
		str.append("\n");
		return str.toString();
	}

	@Override
	public Double getHeuristica(Estado objetivo) {

		return Integer.valueOf(getQuantidadeCoresEmConflito()).doubleValue();
	}

}
