package br.org.ovelha;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request {

	public static void main(String[] args) {
		String CHARACTER_ENCODING = "UTF-8";
		HttpURLConnection servletConnection = null;
		URL url;
		OutputStreamWriter wr = null;
		GeraLog log = new GeraLog();
		

		try {
			//url = new URL(args[0]);
			url = new URL("http://sistema-ovelha.rhcloud.com");
			
			servletConnection = (HttpURLConnection) url.openConnection();
			servletConnection.setDoInput(true);
			servletConnection.setDoOutput(true);
			servletConnection.setUseCaches(false);
			servletConnection.setRequestMethod("POST");
			servletConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			wr = new OutputStreamWriter(servletConnection.getOutputStream(), CHARACTER_ENCODING);
			wr.flush();
			wr.close();

			if (((HttpURLConnection) servletConnection).getResponseCode() == HttpURLConnection.HTTP_OK) {
				log.LogTxt("Sucesso ", "Request");
				System.exit(0);//Sucesso
				
				
			} else {
				log.LogTxt("Erro ", "Request");
				System.exit(1);//Erro
				
			}
		} catch (Exception e) {
			log.LogTxt("Erro :"+e.getMessage(), "Request");
			System.exit(1);//Erro
			
		} finally {
			if (wr != null) {
				try {
					wr.flush();
					wr.close();
				} catch (IOException e) {
				}
			}
			if (servletConnection != null) {
				servletConnection.disconnect();
			}
		}


	}

}
