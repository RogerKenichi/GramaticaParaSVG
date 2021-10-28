package com.company;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GeraHtml {

    private List<String> linhasPontosSVG = new ArrayList<String>();

    public void createFile()
    {
        try {
            Writer escreverArquivo = new FileWriter("desenho.html", false);
            escreverArquivo.write("<!DOCTYPE html>\n");
            escreverArquivo.write("<html lang=\"pt-br\">\n");
            escreverArquivo.write("<head>\n");
            escreverArquivo.write("<title>Gramática para SVG - 081190026</title>\n");
            escreverArquivo.write(" <meta charset=\"utf-8\">\n");
            escreverArquivo.write("</head>\n");
            escreverArquivo.write("<body>\n");

            // Estrutura do svg aqui
            escreverArquivo.write("<p>RA: 081190026 - EC6</p>\n");
            escreverArquivo.write("<svg width=\"2000\" height=\"2000\">\n");
            for (int i = 0; i < this.linhasPontosSVG.size(); i++)
                escreverArquivo.write(linhasPontosSVG.get(i));

            escreverArquivo.write("</svg>\n");

            escreverArquivo.write("</body>\n");
            escreverArquivo.write("</html>\n");
            escreverArquivo.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro.");
            e.printStackTrace();
        }
    }

    public String toLinhaSvgHtml(double x1, double y1, double x2, double y2)
    {
        return "<line x1=\"" + Double.toString(x1) + "\" y1=\"" + Double.toString(y1) +
                "\" x2=\"" + Double.toString(x2) + "\" y2=\"" + Double.toString(y2) +
                "\" style=\"stroke:rgb(110,180,0);stroke-width:2\" />\n";
    }

    public void adicionarValores(List<LinhaModel> linhas)
    {
        for(int i = 0; i < linhas.size(); i++)
        {
            double[] posicoes = linhas.get(i).getLinhaCoordenadas();
            this.linhasPontosSVG.add(toLinhaSvgHtml(posicoes[0], posicoes[1], posicoes[2], posicoes[3]));
        }
    }
}
