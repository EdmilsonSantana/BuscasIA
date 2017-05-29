
package agentes;

import buscas.Busca;
import buscas.Estado;
import buscas.No;
import modelo.Puzzle;

public class AgenteObjetivo {

	private Busca busca;

	public AgenteObjetivo(Busca busca) {
		this.busca = busca;
	}

	public <T extends Estado> String resolverPuzzle(Puzzle<T> puzzle) {
		No<T> resultado = busca.busca(puzzle);
		String logProcessamento = "";
		if (resultado != null) {
			logProcessamento = this.explorar(resultado);
		} else {
			logProcessamento = "Não existe uma solução para este caso";
		}
		return logProcessamento;
	}

	private <T extends Estado> String explorar(No<T> no) {

		StringBuilder arvore = new StringBuilder();
		if (no != null) {
			arvore.append(explorar(no.getPai()));
			arvore.append(no.toString());
			arvore.append("\n\n");
			arvore.append("Custo: ");
			arvore.append(no.getCustoCaminho());
			arvore.append("\n\n");
		}
		return arvore.toString();
	}

}
