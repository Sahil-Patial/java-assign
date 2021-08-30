package com.sahil.calc.calc.controller;

import com.sahil.calc.calc.SharedFunctions.Calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {
    
    @Autowired
    private Calculator calculator;

    @RequestMapping(value = "/sum", method = RequestMethod.POST)
    public int calculateSum(@RequestHeader (value = "n1") int n1, @RequestHeader (value = "n2") int n2){
        return(calculator.sum(n1, n2));
    }

    @RequestMapping(value = "/sub", method = RequestMethod.POST)
    public int calculateSub(@RequestHeader (value = "n1") int n1, @RequestHeader (value = "n2") int n2){
        return(calculator.sub(n1, n2));
    }

    @RequestMapping(value = "/mult", method = RequestMethod.POST)
    public int calculateMult(@RequestHeader (value = "n1") int n1, @RequestHeader (value = "n2") int n2){
        return(calculator.mult(n1, n2));
    }

    @RequestMapping(value = "/div", method = RequestMethod.POST)
    public double calculateDiv(@RequestHeader (value = "n1") double n1, @RequestHeader (value = "n2") double n2){
        return(calculator.div(n1, n2));
    }

}
