package nn_perceptrons;
import nn_perceptrons.classes.*;
import nn_perceptrons.reference.*;
import java.util.Arrays;

public class Main {

    public static void testPerceptron(AndPerceptron ap, double[][] inputs){
        int numTestCases = inputs.length;
        System.out.println("Testing...");
        for (int i = 0; i < numTestCases; i++){
            int result = ap.activate(inputs[i]);
            System.out.println("Test" +(i+1)+ ": " +Arrays.toString(inputs[i])+ " --> " +result);
        }
    }

    public static double[][] trainingInputs1(){
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

        return trainingInputs;
    }

    public static double[] trainingOutputs1(){
        return new double[]{0,0,0,1};
    }

    public static int[] trainingOutputs2(){
        return new int[]{0,0,0,1};
    }

    static void runNNTest1(){
        double[] result;
        double[] testInput = new double[2];
        testInput[0] = 1;
        testInput[1] = 0;
        NeuralNetwork nn = new NeuralNetwork(2, 0.01);
        nn.addLayer(2);
        nn.addLayer(1);
        result = nn.forwardPass(testInput);
        System.out.println(Arrays.toString(result));
    }

    static void runNNTest2(){
        double [][] tInput = trainingInputs1();
        double[] cAnswer = trainingOutputs1();
        double sumSquared = 1;
        double[] result;
        double error1, error2, error3, error4;
        NeuralNetwork nn = new NeuralNetwork(2, 0.01);
        nn.addLayer(2);
        nn.addLayer(1);

        while (sumSquared >= 0.01){
            sumSquared = 0;

            result = nn.forwardPass(tInput[0]);
            System.out.println(result[0]);
            error1 = cAnswer[0] - result[0];
            sumSquared += error1 * error1;
            if (result[0] != cAnswer[0]){
                nn.trainThreeLayer(tInput[0], error1, result[0]);
            }

            result= nn.forwardPass(tInput[1]);
            System.out.println(result[0]);
            error2=cAnswer[1]-result[0];
            sumSquared+=error2*error2;
            if (result[0]!=cAnswer[1]){
                nn.trainThreeLayer(tInput[1], error2, result[0]);
            }

            result= nn.forwardPass(tInput[2]);
            System.out.println(result[0]);
            error3=cAnswer[2]-result[0];
            sumSquared+=error3*error3;
            if (result[0]!=cAnswer[2]){
                nn.trainThreeLayer(tInput[1], error3, result[0]);
            }

            result= nn.forwardPass(tInput[3]);
            System.out.println(result[0]);
            error4=cAnswer[3]-result[0];
            sumSquared+=error4*error4;
            if (result[0]!=cAnswer[3]){
                nn.trainThreeLayer(tInput[1], error4, result[0]);
            }
            System.out.println("------------------------------");
        }

        result= nn.forwardPass(tInput[0]);
        System.out.println(decide(result[0]));
        result= nn.forwardPass(tInput[1]);
        System.out.println(decide(result[0]));
        result= nn.forwardPass(tInput[2]);
        System.out.println(decide(result[0]));
        result= nn.forwardPass(tInput[3]);
        System.out.println(decide(result[0]));

    }

    public static int decide(double value){
        if (value>0.5){
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        AndPerceptron a = new AndPerceptron(trainingInputs1(), trainingOutputs2(), 0.1);
        testPerceptron(a, trainingInputs1());

//        runNNTest2();

    }




}
