import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDaNasa implements ExtratorDeConteudo {

    public List<Conteudo> extraiConteudos(String json) {

        // extrair só os dados que interessam

        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = jsonParser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();
        
        // popular a lista de conteudos 

        for (Map<String, String> atributos : listaDeAtributos) {
            String titulo = atributos.get("title");
            String urlImage = atributos.get("url");
            var conteudo = new Conteudo(titulo, urlImage);

            conteudos.add(conteudo);
        }
        return conteudos;
    }
}
