package nn_perceptrons;
import nn_perceptrons.classes.*;

public class Main {

    public static void testPerceptron(AndPerceptron ap, double[][] inputs, int[] answers){

    }

    public static void main(String[] args) {
        //training data inputs
        double[][] trainingInputs = new double[4][2];
        trainingInputs[0][0] = 0;
        trainingInputs[0][1] = 0;
        trainingInputs[1][0] = 0;
        trainingInputs[1][1] = 1;
        trainingInputs[2][0] = 1;
        trainingInputs[2][1] = 0;
        trainingInputs[3][0] = 1;
        trainingInputs[3][1] = 1;
        // training data answers
        int[] trainingAnswers = {0,0,0,1};

        AndPerceptron a = new AndPerceptron(trainingInputs, trainingAnswers, 0.1);
    }
}
