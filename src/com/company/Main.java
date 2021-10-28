package com.company;

public class Main {

    public static void main(String[] args) {
        DesenharComGramatica desenhar = new DesenharComGramatica("entrada.txt");
        desenhar.desenharGramatica();

        GeraHtml gerar = new GeraHtml();
        gerar.adicionarValores(desenhar.getLinhas());
        gerar.createFile();
    }
}
