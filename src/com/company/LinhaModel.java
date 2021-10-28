package com.company;

public class LinhaModel {
    private double[] ultimoPonto = {1000, 1000}; // Na qual [0] = x e [1] = y
    private double[] versor = {0, 1};  // Na qual [0] = x e [1] = y
    private double[] linhaCoordenadas = new double[4];

    public void setUltimoPonto(double x, double y) {
        this.ultimoPonto[0] = x;
        this.ultimoPonto[1] = y;
    }
    public void setVersor(double x, double y)
    {
        this.versor[0] = x;
        this.versor[1] = y;
    }
    public void setLinhaCoordenadas(double x1, double y1, double x2, double y2)
    {
        this.linhaCoordenadas[0] = x1;
        this.linhaCoordenadas[1] = y1;
        this.linhaCoordenadas[2] = x2;
        this.linhaCoordenadas[3] = y2;
    }

    public double[] getUltimoPonto()
    {
        return this.ultimoPonto;
    }
    public double[] getVersor()
    {
        return this.versor;
    }
    public double[] getLinhaCoordenadas()
    {
        return this.linhaCoordenadas;
    }
}
