import 'package:flutter/material.dart';
import 'package:metodonewton/config.dart' as config;
import 'package:vector_math/vector_math.dart';
import 'dart:math';

void main() => runApp(new MaterialApp(
  home: newton(),
));

class newton extends StatefulWidget {
  @override
  _newtonState createState() => _newtonState();
}

class _newtonState extends State<newton> {
  final GlobalKey<FormState> _formKey = new GlobalKey<FormState>();
  int nUI;
  double x1UI, x2UI;

  @override
  Widget build(BuildContext context) {
    double height = MediaQuery.of(context).size.height;
    double width = MediaQuery.of(context).size.width;
    return Scaffold(
        appBar: AppBar(
          backgroundColor: config.COLOR_DEFAULT_PRIMARY,
          title: Text('Método de Newton'),
        ),
        backgroundColor: config.COLOR_DEFAULT_BD,
        body: Container(
          child: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                Container(
                  height: 250,
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: <Widget>[ 
                      Image.asset('images/enunciadoNewton.png', width: 0.92*width,),
                      Text((nUI == null) ? 'Quantidade de iterações: 0' : 'Quantidade de iterações: $nUI'),
                      Text((x1UI == null) ? 'Resultado: ': 'Resultado: x1 = $x1UI, x2 = $x2UI'),
                      
                      Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: <Widget>[
                          RaisedButton(
                        onPressed: (){
                          resultado(1.8, 0.5);
                          //jacobiano(1, 3, );
                        },
                        color: config.COLOR_DEFAULT_PRIMARY_LIGHT,
                        child: Text('Calcular'), textColor: config.COLOR_DEFAULT_TEXT_PRIMARY,),    

                      Padding(
                        padding: const EdgeInsets.all(16.0),
                        child: RaisedButton(
                          onPressed: (){
                            x1UI = null;
                            nUI = null;
                            setState(() {
                              
                            });
                          },
                          color: config.COLOR_DEFAULT_PRIMARY_LIGHT,
                          child: Text('Zerar'), textColor: config.COLOR_DEFAULT_TEXT_PRIMARY,),
                      )  
                        ],
                      )                   
                    ],
                  ),
                ),
              ],
            ),
          ),
          
        ),

    );
  }

    Vector2 resultado(double x1, double x2){
      double epsilon = 0.00001;
      bool entrar = true; //para entrar no loop
      int n = 1; //numero de iterações
      while(entrar){
      Matrix2 jac = Matrix2(x2, 2*x1, x1, 2*x2);  //matriz do jacobiano, mostrada abaixo.
      /* (x1,x2)
        x2    x1
        2*x1  2*x2
      */ 
      var a = (1 / jac.determinant());  //usado na fórmula para cálcular a inversa da matriz do jacobiano, só olhar na net que a inversa de uma matrix quadrada 2x2, por exemplo, matriz de A = {[a b] / [c d]} é A^-1 = 1/detA * {[d -b] / [-c a]}
      Matrix2 jacInvertido = Matrix2(2*x2*a, -2*x1*a, -x1*a, x2*a); //Matriz do jacobiano já invertida
      Vector2 vetorFG = Vector2(-1 * ((x1 * x2) - 1), -1 * (pow(x1, 2) + pow(x2, 2) - 4)); //Esse é o vetor Vfg = (a, b), onde vc pega a = f(x1,x2) e b = g(x1,x2), onde x1,x2 é o ponto inicial, no caso x1 = 1.8 e x2 = 0.5
      Vector2 coluna1 = jacInvertido.getColumn(0); //pega uma coluna da matriz do jacobiano invertido, ela é uma matriz 2x2, dai aqui pega a primeira coluna e transforma em um vetor v = (x, y)
      Vector2 coluna2 = jacInvertido.getColumn(1); //mesma coisa de cima só que pra coluna 2
      Vector2 deltaX = Vector2((coluna1.x * vetorFG.x) + (coluna2.x * vetorFG.y), (coluna1.y * vetorFG.x) + (coluna2.y * vetorFG.y)); // Multiplicação normal de duas matrizes, sendo linha por coluna. como eu tenho acesso a cada elemento da matriz jacobiano e a cada elemento das colunas acima, daí é só multiplicar normal.
      x1 = x1 + deltaX.x; //melhorando o x1, somando com o valor lá encontrado após a multiplicação de matrizes
      x2 = x2 + deltaX.y; //mesma coisa de cima, mas com x2
      n++; //incrementa, usado para contar as iterações
      if(deltaX.x < epsilon || deltaX.y < epsilon){ //aqui eu me enganei, era pra pegar o módulo do vetor solução, peguei cada componente separada... mas ele disse que não importa muito.
        entrar = false; //usado para sair do loop quando for necessário kk
        nUI = n; //coisas de código pra aparecer no aplicativo, irrelevante....
        x1UI = x1; //mesma coisa de cima
        x2UI = x2; //mesma coisa de cima
        setState(() {});  //ignora pq isso é funcão da linguagem, irrelevante...
      }
      }
  }
}