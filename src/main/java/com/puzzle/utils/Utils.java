package com.puzzle.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Utils {

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

	private static void fecharRecursos(BufferedReader bufferedReader) {
		if (bufferedReader != null) {
			try {
				bufferedReader.close();
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

}
