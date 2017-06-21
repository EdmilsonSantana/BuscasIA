package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Utils {

	private static Logger LOG;

	private static final String LOG_NAME = "IA";

	public static List<String[]> lerCSV(String nomeArquivo) {
		BufferedReader buffer = null;
		String linha = "";
		String separador = ",";
		List<String[]> csv = new ArrayList<>();

		try {
			URL url = Utils.class.getClassLoader().getResource(nomeArquivo);
			buffer = new BufferedReader(new FileReader(url.getPath()));

			while ((linha = buffer.readLine()) != null) {
				String[] colunas = linha.split(separador);
				csv.add(colunas);
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			fecharRecursos(buffer);
		}

		return csv;
	}

	public static void escreverCsv(String csv, String nomeArquivo) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(nomeArquivo));
			writer.write(csv);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			fecharRecursos(writer);
		}

	}

	public static Integer soma(Collection<Integer> valores) {
		Integer soma = 0;
		for (Integer valor : valores) {
			soma += valor;
		}
		return soma;
	}

	private static void fecharRecursos(Closeable recurso) {
		if (recurso != null) {
			try {
				recurso.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	}

	public static Boolean numeroParaBooleano(String str) {
		Boolean booleano = null;
		if (str != null) {
			str = str.trim();
			if ("1".equals(str)) {
				booleano = Boolean.TRUE;
			} else if ("0".equals(str)) {
				booleano = Boolean.FALSE;
			}
		}
		return booleano;
	}

	public static Integer booleanoParaNumero(Boolean booleano) {
		Integer numero = -1;
		if (booleano != null) {
			if (booleano) {
				numero = 1;
			} else {
				numero = 0;
			}
		}
		return numero;
	}

	public static Double calcularIntervaloTempo(Double distancia, Double velocidade) {
		Double intervalo = -1.0;
		if (velocidade > 0) {
			intervalo = distancia / velocidade;
		}
		return intervalo;
	}

	public static Logger getLogger() {
		try {
			if (LOG == null) {
				FileHandler fileHandler = new FileHandler(String.format("./%s.log", LOG_NAME));

				SimpleFormatter formatter = new SimpleFormatter();
				fileHandler.setFormatter(formatter);

				LOG = Logger.getLogger(LOG_NAME);
				LOG.addHandler(fileHandler);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return LOG;
	}
	
	public static Integer aleatorio() {
		Random random  = new Random();
		return random.nextInt();
	}

}
