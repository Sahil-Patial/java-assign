package com.sahil.calc.calc.SharedFunctions;

import org.springframework.stereotype.Service;

@Service
public class Calculator {

    public Calculator() {
        System.out.print("Calculator initialized ");
    }

    public int sum(int n1, int n2) {
        return (n1 + n2);
    }

    public int sub(int n1, int n2){
        return (n1 - n2);
    }

    public int mult(int n1, int n2) {
        return (n1 * n2);
    }

    public double div(double n1, double n2){
        return (n1 / n2);
    }
    
}
