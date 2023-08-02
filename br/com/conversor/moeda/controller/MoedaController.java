package br.com.conversor.moeda.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import br.com.conversor.moeda.helper.Constants;

public class MoedaController {

	public MoedaController() throws IOException {
	}

	public Double acessaApiAwesomeAtual(String moedaDePara) throws IOException {

		URL url = new URL(Constants.getUrlAwesomeApi() + moedaDePara);
		URLConnection connection = url.openConnection();
		InputStream inputStream = connection.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		StringBuilder stringBuilder = new StringBuilder();

		StringBuilder jsonValorMoeda = new StringBuilder();

		String valor;
		while ((valor = bufferedReader.readLine()) != null) {
			jsonValorMoeda.append(valor);
		}

		String valorCotacao = jsonValorMoeda.toString();

		valorCotacao = valorCotacao.split("\"ask\":")[1].split(",")[0];
		valorCotacao = valorCotacao.replaceAll("[^\\d.]", "");

		return Double.parseDouble(valorCotacao);
	}
}
