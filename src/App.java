import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar os top 250 filmes
//        String url = "https://imdb-api.com/en/API/Top250Movies/k_o592cl93";
        String url = "https://imdb-api.com/en/API/MostPopularMovies/k_o592cl93";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam: (titulo, poster, classificação)
        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = jsonParser.parse(body);

        // exibir e manipular os dados
        for (int i = 0; i < 3; i++ ) {
            Map<String,String> filme = listaDeFilmes.get(i);
                System.out.println("\u001b[1mTitulo:\u001b[m " + filme.get("title"));
                System.out.println("\u001b[1mURL da Imagem:\u001b[m " + filme.get("image"));
                double classificacao = Double.parseDouble(filme.get("imDbRating"));
                int numeroEstrelinas = (int) classificacao;
                for (int n = 1; n <= numeroEstrelinas; n++) {
                    System.out.print("⭐ ");
                }
            System.out.println();
        }

    }
}
