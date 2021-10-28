package com.company;
import java.util.List;
import java.util.ArrayList;

public class DesenharComGramatica {
    public double anguloDeVirada = 34; // em graus, que será convertido para radianos.
    public double comprimentoLinha = 4;

    private List<LinhaModel> linhas = new ArrayList<LinhaModel>();
    private List<Integer> posicaoRamificacoesLinhas = new ArrayList<Integer>();
    private double[] pontoInicio = {1000, 1000}; // Na qual [0] = x e [1] = y

    public List<LinhaModel>  getLinhas()
    {
        return this.linhas;
    }

    public String aplicarRegras(String expressao)
    {
        String retorno = expressao;
        //retorno = retorno.replace("F", "FF+[+F-F-F]-[-F+F+F]");
        retorno = retorno.replace("F", "FF-[+F-F]+[-F+F]"); // FF-[+F-F]+[-F+F]      // FF-[-F-F+F]+[-F-F-F] // FF-[-F-F+F]+[+F-F-F-F]

        return retorno;
    }

    public void lerString(String expressao)
    {
        GeraHtml geradorHtml = new GeraHtml();
        posicaoRamificacoesLinhas.add(0); // A primeira ramificação é a linha principal e deve ser atualizada (setar a ultima posição) sempre que uma linha nova é feita mas não ramificada)

        for (int i = 0; i < expressao.length(); i++)
        {
            LinhaModel linhaAtual = new LinhaModel();
            int posicaoLinhaReferencia = posicaoRamificacoesLinhas.get(posicaoRamificacoesLinhas.size() - 1);

            if (expressao.charAt(i) == 'F') // Para frente é simplesmente comprimento * versor e adicionar os valores em relação a ultima posição (em x e y)
            {
                if (linhas.size() == 0){
                    linhaAtual.setVersor(0, 1);
                    linhaAtual.setUltimoPonto(pontoInicio[0], pontoInicio[1] + comprimentoLinha);
                    linhaAtual.setLinhaCoordenadas(pontoInicio[0], pontoInicio[1], pontoInicio[0], pontoInicio[1] - comprimentoLinha);
                    linhas.add(linhaAtual);
                    posicaoRamificacoesLinhas.set(posicaoRamificacoesLinhas.size() - 1, linhas.size() - 1);
                }else
                {
                    double pontoFinalX = (linhas.get(posicaoLinhaReferencia).getVersor()[0] * comprimentoLinha) + linhas.get(posicaoLinhaReferencia).getLinhaCoordenadas()[2];
                    double pontoFinalY = (linhas.get(posicaoLinhaReferencia).getVersor()[1] * comprimentoLinha)*-1 + linhas.get(posicaoLinhaReferencia).getLinhaCoordenadas()[3];

                    linhaAtual.setLinhaCoordenadas(linhas.get(posicaoLinhaReferencia).getUltimoPonto()[0], linhas.get(posicaoLinhaReferencia).getUltimoPonto()[1], pontoFinalX, pontoFinalY);
                    linhaAtual.setUltimoPonto(pontoFinalX, pontoFinalY);
                    linhaAtual.setVersor(linhas.get(posicaoLinhaReferencia).getVersor()[0], linhas.get(posicaoLinhaReferencia).getVersor()[1]);
                    linhas.add(linhaAtual);
                    posicaoRamificacoesLinhas.set(posicaoRamificacoesLinhas.size() - 1, linhas.size() - 1);
                }
            }else if (expressao.charAt(i) == '+') // Para a direita, precisa obter o valor do angulo, converter para radianos e recalcular um novo versor.
            {
                if (linhas.size() == 0){
                    //linhaAtual.setVersor(0, 1);
                    //linhaAtual.setUltimoPonto(pontoInicio[0], pontoInicio[1] + comprimentoLinha);
                    //linhaAtual.setLinhaCoordenadas(pontoInicio[0], pontoInicio[1], pontoInicio[0], pontoInicio[1] + comprimentoLinha);
                    double[] novoVersor = anguloParaVersor(90 - anguloDeVirada);
                    double pontoFinalX = (novoVersor[0] * comprimentoLinha) + pontoInicio[0];
                    double pontoFinalY = (novoVersor[1] * comprimentoLinha)*-1 + pontoInicio[1];
                    linhaAtual.setLinhaCoordenadas(linhas.get(posicaoLinhaReferencia).getLinhaCoordenadas()[2], linhas.get(posicaoLinhaReferencia).getLinhaCoordenadas()[3], pontoFinalX, pontoFinalY);
                    linhaAtual.setUltimoPonto(pontoFinalX, pontoFinalY);
                    linhaAtual.setVersor(novoVersor[0], novoVersor[1]);
                    linhas.add(linhaAtual);
                    posicaoRamificacoesLinhas.set(posicaoRamificacoesLinhas.size() - 1, linhas.size() - 1);
                }else
                {
                    double novoAngulo = getAnguloLinha(linhas.get(posicaoLinhaReferencia).getVersor()[0], linhas.get(posicaoLinhaReferencia).getVersor()[1]) - anguloDeVirada;
                    double[] novoVersor = anguloParaVersor(novoAngulo);

                    double pontoFinalX = (novoVersor[0] * comprimentoLinha) + linhas.get(posicaoLinhaReferencia).getLinhaCoordenadas()[2];
                    double pontoFinalY = (novoVersor[1] * comprimentoLinha)*-1 + linhas.get(posicaoLinhaReferencia).getLinhaCoordenadas()[3];
                    linhaAtual.setLinhaCoordenadas(linhas.get(posicaoLinhaReferencia).getLinhaCoordenadas()[2], linhas.get(posicaoLinhaReferencia).getLinhaCoordenadas()[3], pontoFinalX, pontoFinalY);
                    linhaAtual.setUltimoPonto(pontoFinalX, pontoFinalY);
                    linhaAtual.setVersor(novoVersor[0], novoVersor[1]);
                    linhas.add(linhaAtual);
                    posicaoRamificacoesLinhas.set(posicaoRamificacoesLinhas.size() - 1, linhas.size() - 1);
                }
            }else if (expressao.charAt(i) == '-') // Para a direita, precisa obter o valor do angulo, converter para radianos e recalcular um novo versor.
            {
                if (linhas.size() == 0){
                    //linhaAtual.setVersor(0, 1);
                    //linhaAtual.setUltimoPonto(pontoInicio[0], pontoInicio[1] + comprimentoLinha);
                    //linhaAtual.setLinhaCoordenadas(pontoInicio[0], pontoInicio[1], pontoInicio[0], pontoInicio[1] + comprimentoLinha);
                    double[] novoVersor = anguloParaVersor(90 + anguloDeVirada);
                    double pontoFinalX = (novoVersor[0] * comprimentoLinha) + pontoInicio[0];
                    double pontoFinalY = (novoVersor[1] * comprimentoLinha)*-1 + pontoInicio[1];
                    linhaAtual.setLinhaCoordenadas(linhas.get(posicaoLinhaReferencia).getLinhaCoordenadas()[2], linhas.get(posicaoLinhaReferencia).getLinhaCoordenadas()[3], pontoFinalX, pontoFinalY);
                    linhaAtual.setUltimoPonto(pontoFinalX, pontoFinalY);
                    linhaAtual.setVersor(novoVersor[0], novoVersor[1]);
                    linhas.add(linhaAtual);
                    posicaoRamificacoesLinhas.set(posicaoRamificacoesLinhas.size() - 1, linhas.size() - 1);
                    linhas.add(linhaAtual);
                    posicaoRamificacoesLinhas.set(posicaoRamificacoesLinhas.size() - 1, linhas.size() - 1);
                }else
                {
                    double novoAngulo = getAnguloLinha(linhas.get(posicaoLinhaReferencia).getVersor()[0], linhas.get(posicaoLinhaReferencia).getVersor()[1]) + anguloDeVirada;
                    double[] novoVersor = anguloParaVersor(novoAngulo);

                    double pontoFinalX = (novoVersor[0] * comprimentoLinha) + linhas.get(posicaoLinhaReferencia).getLinhaCoordenadas()[2];
                    double pontoFinalY = (novoVersor[1] * comprimentoLinha)*-1 + linhas.get(posicaoLinhaReferencia).getLinhaCoordenadas()[3];
                    linhaAtual.setLinhaCoordenadas(linhas.get(posicaoLinhaReferencia).getUltimoPonto()[0], linhas.get(posicaoLinhaReferencia).getUltimoPonto()[1], pontoFinalX, pontoFinalY);
                    linhaAtual.setUltimoPonto(pontoFinalX, pontoFinalY);
                    linhaAtual.setVersor(novoVersor[0], novoVersor[1]);
                    linhas.add(linhaAtual);
                    posicaoRamificacoesLinhas.set(posicaoRamificacoesLinhas.size() - 1, linhas.size() - 1);
                }
            }
            else if (expressao.charAt(i) == '[') // .
                posicaoRamificacoesLinhas.add(posicaoLinhaReferencia);
            else if (expressao.charAt(i) == ']') // .
                posicaoRamificacoesLinhas.remove(posicaoRamificacoesLinhas.size() - 1);
            else
                posicaoRamificacoesLinhas.set(posicaoRamificacoesLinhas.size() - 1, linhas.size() - 1);
        }
    }

    private double[] getVersorDoVetor(double vetorx, double vetory)
    {
        // versor = vetor / módulo do vetor
        double modulo = Math.sqrt((vetorx * vetorx) + (vetory * vetory));
        double componenteX = vetorx / modulo;
        double componenteY = vetory / modulo;

        double[] versorRetorno = {componenteX, componenteY};
        return  versorRetorno;
    }
    private double[] getVetorDaLinha(double x1, double y1, double x2, double y2)
    {
        double componenteX = x2 - x1;
        double componenteY = y2 - y1;
        double[] vetorLinha = {componenteX, componenteY};
        return  vetorLinha;
    }

    private double getAnguloLinha(double versorx, double versory)
    {
        double anguloCos = Math.toDegrees(Math.acos(versorx));
        double angulo;

        if (versory < 0){
            angulo = 360 - anguloCos;
        }else
            angulo = anguloCos;

        if (angulo < 0)
            angulo += 360;
        else if (angulo > 0)
            angulo -= 360;

        return angulo;
    }

    private double[] anguloParaVersor(double anguloGraus)
    {
        // O próprio valor de x e y fazem um versor
        double radiano = Math.toRadians(anguloGraus);
        double componenteX = Math.cos(radiano);
        double componenteY = Math.sin(radiano);

        double[] retorno = {componenteX, componenteY};
        return retorno;
    }
}