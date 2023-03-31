import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

        // leitura da imagem 
        // InputStream inputStream = new FileInputStream(new File("entrada/filme-maior.jpg"));
        //InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@.jpg").openStream();
        BufferedImage  imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 400; 
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para a nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0 , null);

        // configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 100);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        // escrever uma frase na nova imagem 
        graphics.drawString("TOPZEIRA", 250, novaAltura - 150);

        // escrever a nova imagem em um arquivo 
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }
}
