package simulador;

import agentes.AgenteObjetivo;
import buscas.cega.BuscaLargura;
import modelo.Puzzle;
import modelo.blocos.BlocosPuzzle;
import modelo.blocos.MatrizQuadrada;

public class SimuladorBlocos extends SimuladorPuzzle<Puzzle<MatrizQuadrada>> {

	public SimuladorBlocos(AgenteObjetivo agentePuzzle) {
		super(agentePuzzle);
	}

	@Override
	Puzzle<MatrizQuadrada> novoPuzzle() {

		MatrizQuadrada estadoInicial = new MatrizQuadrada(3);
		estadoInicial.inicializarValoresAleatorios();

		MatrizQuadrada objetivo = new MatrizQuadrada(3);
		objetivo.inicializarValoresOrdenados();

		return new BlocosPuzzle(estadoInicial, objetivo);
	}

	public static void main(String[] args) {
		AgenteObjetivo agentePuzzle = new AgenteObjetivo(new BuscaLargura());

		SimuladorBlocos simuladorPuzzle = new SimuladorBlocos(agentePuzzle);

		System.out.println(simuladorPuzzle.simular(problemaSemSolucao()));
		System.out.println(simuladorPuzzle.simular(casoAleatorio1()));
		System.out.println(simuladorPuzzle.simular(casoAleatorio2()));
		System.out.println(simuladorPuzzle.simular(casoAleatorio3()));
	}

	private static BlocosPuzzle problemaSemSolucao() {
		MatrizQuadrada estadoInicial = new MatrizQuadrada(3);

		estadoInicial.add(0, 0, 4);
		estadoInicial.add(0, 1, 6);
		estadoInicial.add(0, 2, 2);

		estadoInicial.add(1, 0, 8);
		estadoInicial.add(1, 1, 1);
		estadoInicial.add(1, 2, 3);

		estadoInicial.add(2, 0, 7);
		estadoInicial.add(2, 1, 5);
		estadoInicial.add(2, 2, 0);

		MatrizQuadrada objetivo = new MatrizQuadrada(3);

		objetivo.add(0, 0, 1);
		objetivo.add(0, 1, 2);
		objetivo.add(0, 2, 3);

		objetivo.add(1, 0, 4);
		objetivo.add(1, 1, 5);
		objetivo.add(1, 2, 6);

		objetivo.add(2, 0, 7);
		objetivo.add(2, 1, 8);
		objetivo.add(2, 2, 0);

		return new BlocosPuzzle(estadoInicial, objetivo);
	}

	private static BlocosPuzzle casoAleatorio1() {
		MatrizQuadrada estadoInicial = new MatrizQuadrada(3);

		estadoInicial.add(0, 0, 8);
		estadoInicial.add(0, 1, 6);
		estadoInicial.add(0, 2, 1);

		estadoInicial.add(1, 0, 7);
		estadoInicial.add(1, 1, 0);
		estadoInicial.add(1, 2, 5);

		estadoInicial.add(2, 0, 4);
		estadoInicial.add(2, 1, 3);
		estadoInicial.add(2, 2, 2);

		MatrizQuadrada objetivo = new MatrizQuadrada(3);

		objetivo.add(0, 0, 1);
		objetivo.add(0, 1, 2);
		objetivo.add(0, 2, 3);

		objetivo.add(1, 0, 4);
		objetivo.add(1, 1, 5);
		objetivo.add(1, 2, 6);

		objetivo.add(2, 0, 7);
		objetivo.add(2, 1, 8);
		objetivo.add(2, 2, 0);

		return new BlocosPuzzle(estadoInicial, objetivo);
	}

	private static BlocosPuzzle casoAleatorio2() {
		MatrizQuadrada estadoInicial = new MatrizQuadrada(3);

		estadoInicial.add(0, 0, 1);
		estadoInicial.add(0, 1, 4);
		estadoInicial.add(0, 2, 5);

		estadoInicial.add(1, 0, 6);
		estadoInicial.add(1, 1, 0);
		estadoInicial.add(1, 2, 8);

		estadoInicial.add(2, 0, 7);
		estadoInicial.add(2, 1, 3);
		estadoInicial.add(2, 2, 2);

		MatrizQuadrada objetivo = new MatrizQuadrada(3);

		objetivo.add(0, 0, 1);
		objetivo.add(0, 1, 2);
		objetivo.add(0, 2, 3);

		objetivo.add(1, 0, 4);
		objetivo.add(1, 1, 5);
		objetivo.add(1, 2, 6);

		objetivo.add(2, 0, 7);
		objetivo.add(2, 1, 8);
		objetivo.add(2, 2, 0);

		return new BlocosPuzzle(estadoInicial, objetivo);
	}

	private static BlocosPuzzle casoAleatorio3() {
		MatrizQuadrada estadoInicial = new MatrizQuadrada(3);

		estadoInicial.add(0, 0, 4);
		estadoInicial.add(0, 1, 6);
		estadoInicial.add(0, 2, 8);

		estadoInicial.add(1, 0, 5);
		estadoInicial.add(1, 1, 0);
		estadoInicial.add(1, 2, 7);

		estadoInicial.add(2, 0, 2);
		estadoInicial.add(2, 1, 3);
		estadoInicial.add(2, 2, 1);

		MatrizQuadrada objetivo = new MatrizQuadrada(3);

		objetivo.add(0, 0, 1);
		objetivo.add(0, 1, 2);
		objetivo.add(0, 2, 3);

		objetivo.add(1, 0, 4);
		objetivo.add(1, 1, 5);
		objetivo.add(1, 2, 6);

		objetivo.add(2, 0, 7);
		objetivo.add(2, 1, 8);
		objetivo.add(2, 2, 0);

		return new BlocosPuzzle(estadoInicial, objetivo);
	}

}
