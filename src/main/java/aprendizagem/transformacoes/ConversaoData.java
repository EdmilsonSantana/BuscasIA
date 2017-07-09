package aprendizagem.transformacoes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import aprendizagem.Amostra;
import utils.Utils;

public class ConversaoData implements Conversao {

	private SimpleDateFormat formato;

	public ConversaoData(String formatoData) {
		this.formato = new SimpleDateFormat(formatoData);
	}

	@Override
	public void converter(List<Amostra> amostras, Integer coluna) {
		for (Amostra amostra : amostras) {
			try {
				Date data = formato.parse(amostra.getTexto(coluna - 1)
						.replaceAll("\"", ""));
				amostra.adicionarDado(String.valueOf(data.getTime()));
				amostra.removerDado(coluna - 1);
			} catch (ParseException e) {
				Utils.getLogger().warning(e.getMessage());
			}

		}

	}

}
