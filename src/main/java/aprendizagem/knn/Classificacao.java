package aprendizagem.knn;

public class Classificacao implements Comparable<Classificacao> {

	private String descricao;

	public Classificacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public boolean equals(Object obj) {
		Boolean equals = Boolean.FALSE;
		if (obj != null && obj instanceof Classificacao) {
			Classificacao classificacao = (Classificacao) obj;
			if (this == obj || this.getDescricao().equals(classificacao.getDescricao())) {
				equals = Boolean.TRUE;
			}
		}
		return equals;
	}

	@Override
	public String toString() {
		return this.descricao;
	}

	@Override
	public int hashCode() {
		return this.descricao.hashCode();
	}

	@Override
	public int compareTo(Classificacao o) {
		return this.descricao.compareTo(o.descricao);
	}

}
