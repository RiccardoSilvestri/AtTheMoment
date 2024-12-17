package it.rizzoli.atthemoment.service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.zip.GZIPInputStream;

import it.rizzoli.atthemoment.model.principali.News;

public class CallAtm {
    public static InputStreamReader infoMezzo(String numero, int rotta) throws IOException {
        String link = "https://giromilano.atm.it/proxy.tpportal/api/tpportal/tpl/journeyPatterns/"+numero+"%7C"+rotta+"?alternativeRoutesMode=false";
        return fetchCall(link);
    }
    public static InputStreamReader infoFermata(String numero) throws IOException {
        String link = "https://giromilano.atm.it/proxy.tpportal/api/tpportal/geodata/pois/stops/"+numero;
        return fetchCall(link);
    }
    public static InputStreamReader listaMezzi() throws IOException{
        String link = "https://giromilano.atm.it/proxy.tpportal/api/tpPortal/tpl/journeyPatterns";
        return fetchCall(link);
    }

    public static InputStreamReader news() throws IOException{
        String link = "https://giromilano.atm.it/proxy.tpportal/api/tpPortal/tpl/atm/it";
        return fetchCall(link);
    }

    public static InputStreamReader infoAroundMe(double LocY, double LocX) throws IOException {
        String link = "https://giromilano.atm.it/proxy.tpportal/api/tpPortal/tpl/journeyPatterns/nearest?radius=500&Point.Y="+LocY+"&Point.X="+LocX;
        return fetchCall(link);
    }

    public static InputStreamReader fetchCall(String link) throws IOException {
        HttpURLConnection connection = null;
        URL url = new URL(link);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/18.1.1 Safari/605.1.15");
        connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
        connection.setRequestProperty("Accept-Language", "it-IT,it;q=0.9");
        connection.setRequestProperty("Sec-Fetch-Dest", "document");
        connection.setRequestProperty("Sec-Fetch-Mode", "navigate");
        connection.setRequestProperty("Sec-Fetch-Site", "none");
        return fixResponse(connection);
    }

    public static InputStreamReader fixResponse(HttpURLConnection connection) throws IOException {
        InputStream inputStream = new GZIPInputStream(connection.getInputStream());
        InputStreamReader fixedResponse = new InputStreamReader(inputStream, "UTF-8");
        return fixedResponse;
    }

    public static <T> T fixGzipResponse(InputStreamReader response, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(response, clazz);
        }

    public static List<News> newsFixGzipResponse(InputStreamReader response) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<News>>(){}.getType();
        return gson.fromJson(response, listType);
    }
}
