
Bissecção
-------------------------------------------
import java.lang.Math;

public class MyClass {
    public static void main(String args[]) {
        double erro = 0.0001; double intervalo[] = {2, 3}; int n = 1;
        do{
            double ptomedio = (intervalo[0] + intervalo[1]) / 2;
            if(calcF(intervalo[0]) * calcF(ptomedio) < 0){
                intervalo[1] = ptomedio;
                n++;
            }
            else{
                intervalo[0] = ptomedio;
                n++;
            }
        } while(Math.abs(intervalo[0] - intervalo[1]) > erro);
        double x = (intervalo[0] + intervalo[1]) / 2;
        System.out.println("x = " + x + " n = " + n);
    }
    static double calcF(double x){
        return x*Math.log10(x) - 1;
    }
}


Newton-Raphson
---------------------------------------
import java.lang.Math;

public class MyClass {
    public static void main(String args[]) {
        double erro = 0.0001; double x = 0; double x_ = 3; int n = 1;
        while(calcF(x_) > erro){
            x = x_ - (calcF(x_) / calcFL(x_));
            x_ = x;
            n++;
            }
        System.out.println("x = " + x + " n = " + n);
    }
    static double calcF(double x){
        return x*Math.log10(x) - 1;
    }
    static double calcFL(double x){
        double dx = 0.1;
        return (calcF(x + dx) - calcF(x)) / dx;
    }
}


Solução de sistema de equações f(x,y) e g(x,y) - Newton-Raphson
--------------------------------------------------------------------------------------
import java.lang.Math;

public class MyClass {
    public static void main(String args[]) {
        int n = 1;
        double erro = 0.00001; double[][] dx = new double[2][1]; double[][] v = new double[2][1]; double[] pto = {1.8, 0.5}; double[][] jac = { {0.5 , 1.8}, {2 * 1.8 , 2 * 0.5} };
        
       do{
           v = calcV(pto);
           double[][] multiplicado = mult(neg(MatInv(jac, 2)), v);
           dx[0][0] = multiplicado[0][0];
           dx[1][0] = multiplicado[1][0];
           pto[0] = pto[0] + dx[0][0];
           pto[1] = pto[1] + dx[1][0];
           n++;
       }while(Math.sqrt(Math.pow(dx[0][0], 2) + Math.pow(dx[1][0], 2)) > erro);
       System.out.println("x1 = " + pto[0] + " x2 = " + pto[1] + " n = " + n);
    }
    static double[][] calcV(double x[]){
        double[][] c = { {x[0] * x[1] - 1}, {Math.pow(x[0], 2) + Math.pow(x[1], 2) - 4} };
        return c;
    }
    static double[][] neg(double x[][]){
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                x[i][j] = x[i][j] * -1;
            }
        }
        return x;
    }
    static double[][] mult(double x[][], double y[][]){
        int n = 2; int m = 1; int l = 1; int j = 0;
        double[][] c = new double[n][m];
        for(int i = 0; i < n; i++){
                c[i][j] = x[i][j] * y[j][j] + x[i][l] * y[l][j];
        }
        return c;
    }
    static double[][] MatInv(double A[][], int n){
    	double con;
    	for (int k=0; k<n; k++) {
    		con = A[k][k];
    		A[k][k] = 1;
    		for (int j=0; j<n; j++)
    			A[k][j] = A[k][j] / con;
    			for (int i=0; i<n; i++) {
    				if (i!=k) {
    					con = A[i][k];
    					A[i][k] = 0.0;
    					for (int j=0; j<n; j++)
    						A[i][j] = A[i][j] - A[k][j]*con;
    				}
    			}
    	}
    	return A;
    }
}


Decomposição LU - dart
--------------------------------------------------------------------------------------
main() {
  List<List<double>> b = [[4], [7], [-1], [0]];
  List<List<double>> _u = [[1, 2, -2, 1], [2, 5, -2, 3], [-2, -2, 5, 3], [1, 3, 3, 2]];	//vai virar a matriz U.
  List<List<double>> _l = List<List<double>>(_u.length);

  criarMatrizL(_l, _u);
  definirMatrizLeU(_l, _u);
  List<List<double>> _Linvertida = inverterMatriz(_l, _l.length); //inverte _l tb n sei pq...
  List<List<double>> _y = mat_transpose(multiplicarMatrizes(_Linvertida, b));
  List<List<double>> _Uinvertida = inverterMatriz(_u, _u.length);
  List<List<double>> _x = mat_transpose(multiplicarMatrizes(_Uinvertida, _y));
  print(_x);
 


}

  void criarMatrizL(List<List<double>> l, List<List<double>> a){
    for(int i = 0; i < a.length; i++){
      l[i] = List<double>(a[0].length);
      for(int j = 0; j < a[0].length; j++){
        if(i == j){
          l[i][j] = 1;
        }
        else{
          l[i][j] = 0;
        }
      }
    }
  }

  void definirMatrizLeU(List<List<double>> l, List<List<double>> a){  
    for(int i = 0; i < a.length; i++){	//Anda na linha
      for(int k = i + 1; k < a.length; k++){	//Anda na linha debaixo kk
        for(int j = 0; j < a[0].length; j++){	//Anda nas colunas 
          if(k > i){
            l[k][i] = (a[i][k] / a[i][i]);
          }
          a[k][j] = a[k][j] - (a[i][k] / a[i][i])*a[i][j];
        }
      }
    }
  }

  List<List<double>> multiplicarMatrizes(List<List<double>> a, List<List<double>> b){
    List<double> vetor =  List<double>(a.length);
    List<List<double>> transposta =  List<List<double>>(b[0].length);
    double soma = 0;
    for(int k = 0; k < b[0].length; k++){
      for(int i = 0; i < a.length; i++){
        for(int j = 0; j < a[0].length; j++){
          soma = soma + a[i][j] * b[j][k];  
        }//dentro do j
        vetor[i] = soma;
        soma = 0;
      }//dentro do i 
      transposta[k] = vetor.toList();
    }//dentro do k
    return transposta;
  }

  List<List<double>> mat_transpose(a){
    int m = a.length, n = a[0].length; // m rows and n cols
    List<List<double>> b = List<List<double>>(n);
    for (int j = 0; j < n; ++j) b[j] = new List<double>(m);
    for (int i = 0; i < m; ++i)
      for (int j = 0; j < n; ++j)
        b[j][i] = a[i][j];
    return b;
  }

  List<List<double>> inverterMatriz(List<List<double>> A,int n){
    double con;
    for (int k=0; k<n; k++) {
      con = A[k][k];
      A[k][k] = 1;
      for (int j=0; j<n; j++)
        A[k][j] = A[k][j] / con;
      for (int i=0; i<n; i++) {
        if (i!=k) {
          con = A[i][k];
          A[i][k] = 0.0;
          for (int j=0; j<n; j++)
            A[i][j] = A[i][j] - A[k][j]*con;
        }
      }
    }
    return A;
  }

Integração Numérica (Método dos Trapézios) - dart
--------------------------------------------------------------------------------------

import 'dart:math' as m;

main() {
  num n = 1001; // Número de subdivisões. PS.: Quero 1000 divisões, porém, inicio com 1001 pois quero trabalhar dentro dos loops com i > 0.
  List<num> vetorY = List(n);  // Declaro o vetor que irá armazenar todos os f(x).
  num h = (7 - 1) / (n - 1);  // (intervalo final - intervalo inicial) / número de subdivisões.
  num k = (h + 1);  // Necessário para implementar os subintervalos Xi's
  vetorY[1] = m.pow(m.e, m.cos(1)); // Armazena o primeiro valor de f(x), -> f(1), ou seja, a função aplicada ao ponto x = 1.
  for (var i = 2; i < n; i++) { // Entra dentro do vetor que armazena todos os f(x) e armazena seus respectivos valores. PS.: Inicio o loop com 2 pois f(0) não me interessa e f(1) já foi preenchido. Vai até n que é 1001 pois o loop para em 1000.
    vetorY[i] = k * m.pow(m.e, m.cos(k)); 
    k = k + h;  // Adiciona h ao valor anterior de X. No caso, a variável independente X é chamada de k.
  }
  num s = (h / 2) * (vetorY[1] + 2 * somaYi(vetorY, n) + vetorY[n - 1]);  // Efetua a soma através da fórmula para o método do trapézio.
  print(s);
}
num somaYi(List<num> vetor, int l){ // Devolve o somatório interno da fórmula do método dos trapézios.
  num s = 0;
  for (var i = 2; i < l - 2; i++) {
    s = s + vetor[i];
  }
  return s;
}


---
    import 'dart:math' as m;

main() {
  num n = 1001; // Número de subdivisões. PS.: Quero 1000 divisões, porém, inicio com 1001 pois quero trabalhar dentro dos loops com i > 0.
  List<num> vetorY = List(n);  // Declaro o vetor que irá armazenar todos os f(x).
  num h = (7 - 1) / (n - 1);  // (intervalo final - intervalo inicial) / número de subdivisões.
  num k = (h + 1);  // Necessário para implementar os subintervalos Xi's
  vetorY[1] = m.pow(m.e, m.cos(1)); // Armazena o primeiro valor de f(x), -> f(1), ou seja, a função aplicada ao ponto x = 1.
  for (var i = 2; i < n; i++) { // Entra dentro do vetor que armazena todos os f(x) e armazena seus respectivos valores. PS.: Inicio o loop com 2 pois f(0) não me interessa e f(1) já foi preenchido. Vai até n que é 1001 pois o loop para em 1000.
    vetorY[i] = k * m.pow(m.e, m.cos(k)); 
    k = k + h;  // Adiciona h ao valor anterior de X. No caso, a variável independente X é chamada de k.
  }
  num s = (h / 2) * (vetorY[1] + 2 * somaYi(vetorY, n) + vetorY[n - 1]);  // Efetua a soma através da fórmula para o método do trapézio.
  //print(s);
  
  num hSimpson = n / 2;
  num sSimpson = (hSimpson / 3) * (vetorY[1] + 4*somatorios(vetorY, n, false) + 2*somatorios(vetorY, n, true) + vetorY[n - 1]);
  
  print(sSimpson);
  
} //fim  Main



num somaYi(List<num> vetor, int l){ // Devolve o somatório interno da fórmula do método dos trapézios.
  num s = 0;
  for (var i = 2; i < l - 2; i++) {
    s = s + vetor[i];
  }
  return s;
}


num somatorios(List<num> vetor, int l /*tamanho do subintervalo*/, bool j){
  num s = 0;
  print(j);
  if(j){	//Se for true, é par.
    for(var i = 1; i < (((l - 1) / 2)) - 1; i++){
      if(pegarPar(i)){
        s = s + vetor[i];
        print(s.toString() + ' entrou no par');        
      }
    }  
  }
  else if(!j){//Se for false, é impar.
    for(var i = 1; i < ((l - 1) / 2); i++){
      if(!pegarPar(i)){
        s = s + vetor[i];
        print(s.toString() + ' entrou no impar'); 
      }
    }
  }
 	print(s.toString() + ' fora dos loops'); 
  return s;
}

bool pegarPar(int i) => (i / 2 == 0) ? true : false;
