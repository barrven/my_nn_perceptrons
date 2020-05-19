package nn_perceptrons;
import nn_perceptrons.classes.*;

public class Main {

    public static void main(String[] args) {
        //training data
        double[][] tInput = new double[4][2];
        tInput[0][0] = 0;
        tInput[0][1] = 0;
        tInput[1][0] = 0;
        tInput[1][1] = 1;
        tInput[2][0] = 1;
        tInput[2][1] = 0;
        tInput[3][0] = 1;
        tInput[3][1] = 1;
        int[] cAnswer = {0,0,0,1};

        AndPerceptron a = new AndPerceptron(tInput, cAnswer, 0.1);
    }
}
