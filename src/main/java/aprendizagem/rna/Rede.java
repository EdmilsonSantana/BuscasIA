package aprendizagem.rna;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import aprendizagem.Amostra;
import aprendizagem.rna.ativacao.Ativacao;
import aprendizagem.rna.erro.Corretor;

public class Rede {

	private Collection<Neuronio> neuronios = new HashSet<>();

	private Ativacao ativacao;

	private Corretor corretor;

	public Rede(Ativacao ativacao, Corretor corretor) {
		this.ativacao = ativacao;
		this.corretor = corretor;
	}

	public void novoNeuronio(Integer sinapses) {

		this.neuronios.add(new Neuronio(sinapses, ativacao, corretor));
	}
	
	public void treinar(List<Amostra> amostras) {
		
		//this.neuronios.iterator().next().treinar(, )
	}

}
