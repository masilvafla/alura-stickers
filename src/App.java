import java.io.InputStream;
import java.net.URI;
import java.net.URL;
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

        for (Map<String,String> filme : listaDeFilmes) {
            String titulo = filme.get("title");
            String rating = filme.get("imDbRating");
            String urlImage = filme.get("image");

            InputStream inputStream = new URL(urlImage).openStream();
            String nomeArquivo = titulo + ".png";
            var geradora = new GeradoraDeFigurinhas();
            geradora.cria(inputStream, nomeArquivo);
            System.out.println(titulo);
            System.out.println();
        }
       
/*        
        for (int i = 0; i < 6; i++ ) {
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
*/ 

    }
}
