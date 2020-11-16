package com.migo.ahmed;

public class MathOperation
{
    public double sum(double num1, double num2)
    {
        return num1 + num2;
    }

    public double sub(double num1, double num2)
    {
        return num1 - num2;
    }

    public double mul(double num1, double num2)
    {
        return num1 * num2;
    }

    public double div(double num1, double num2) throws ArithmeticException
    {
        if(num2 == 0)
            throw new ArithmeticException("Cannot divide by 0");
        return num1 / num2;
    }
}
