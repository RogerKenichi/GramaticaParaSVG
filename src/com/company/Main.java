package com.company;

public class Main {

    public static void main(String[] args) {
        DesenharComGramatica desenhar = new DesenharComGramatica();
        String itera = desenhar.aplicarRegras("F");// FF+[+F-F-F]-[-F+F+F]
        System.out.println("1: " + itera);

        for(int i = 2; i < 6; i++){
            itera = desenhar.aplicarRegras(itera);
            System.out.println(Integer.toString(i) +": " + itera);
        }


        GeraHtml g = new GeraHtml();

        //desenhar.lerString("F-+F-F+-F[++F-[-+F]-F+-F+]+F--+F-F+-F++F--+F-F+-F++F"); // -+F-F+-F++F--+F-F+-F++F--+F-F+-F++F--+F-F+-F++F
        desenhar.lerString(itera);
        g.adicionarValores(desenhar.getLinhas());

        System.out.println();

        g.createFile();
    }
}
