package modelo.cores;

import java.util.LinkedList;
import java.util.List;

import modelo.Puzzle;

public class CoresPuzzle extends Puzzle<Mapa> {

	private CoresPuzzle(Mapa estadoInicial) {
		super(estadoInicial);
	}

	public CoresPuzzle() {
		this(new Mapa());
	}

	@Override
	public List<Mapa> getSucessores(Mapa mapa) {
		List<Mapa> sucessores = new LinkedList<>();

		if (!mapa.pintado()) {
			for (Cor cor : Cor.values()) {
				if (!Cor.BRANCO.equals(cor)) {
					Mapa sucessor = mapa.clone();
					sucessor.pintar(cor);
					sucessores.add(sucessor);
				}
			}
		}

		return sucessores;
	}

	@Override
	public Boolean testeObjetivo(Mapa mapa) {

		return !mapa.coresEmConflito() && mapa.pintado();
	}

	/*
	 * @Override public Double getCusto(Mapa mapa) { return Integer.valueOf(mapa.getQuantidadeCoresEmConflito()).doubleValue(); }
	 * 
	 * @Override public Mapa novoEstado() { Mapa mapa = new Mapa(); Cor[] cores = Cor.values();
	 * 
	 * Random random = new Random();
	 * 
	 * for (int i = 0; i < mapa.getQuantidadeRegioes(); i++) { int cor = random.nextInt(cores.length); mapa.pintar(cores[cor]); } return
	 * mapa; }
	 */

}
