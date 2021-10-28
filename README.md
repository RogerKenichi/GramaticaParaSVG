# GramaticaParaSVG
 gramatica-para-svg

RA: 081190026

```
Σ : F, +, -, [, ]
n : 6
ω : F
δ : 32º
p1 : F → FF-[+F-F]+[-F+F]
```
# Como utilizar:
No arquivo "entrada.txt" entra a gramática.

O alfabeto ```Σ``` contém:

- ```+``` representa "virar para a direita"
- ```-``` representa "virar para a esquerda"
- ```F``` representa "ir para frente"
- ```[``` representa abrir uma ramificação
- ```]``` representa fechar uma ramificação

```n``` é o número de iterações

```c``` é o comprimento da linha

```ω``` é a condição inicial

```δ``` é o ângulo de virada (em graus, somente o número)

```p1```, ```p2```... são as regras de produção

É preciso que o arquivo esteja organizado nessa ordem.


**OBS:** Caso o desenho fique muito grande, é possível alterar ponto de início dele em DesenharComGramatica():
```
private double[] pontoInicio = {1000, 1000};
```
Na qual o primeiro valor é a posição x e o segundo é a posição y

Por padrão, o SVG gerado tem um tamanho de 2000x2000