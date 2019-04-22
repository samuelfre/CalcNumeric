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

Bissecção
-------------------------------------------

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

Newton-Raphson
---------------------------------------

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

Solução de sistema de equações f(x,y) e g(x,y) - Newton-Raphson
--------------------------------------------------------------------------------------
