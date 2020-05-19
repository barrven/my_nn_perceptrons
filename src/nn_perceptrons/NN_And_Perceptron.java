package nn_perceptrons;


public class NN_And_Perceptron {

    public static void initializeWeights(double[] weights){
        for (int x = 0; x < weights.length; x++){
            weights[x] = Math.random()-0.5;
        }
    }

    //learning happens here
    public static double updateWeight(double weight, double input, double error, double learningRate){
        return weight + learningRate * input * error;
    }

    //"neuron" function
    public static int activate(double[] inputs, double[] weights, double theta){
        double sum = 0;

        //sum the weight of all the synapses (inputs)
        for(int x = 0; x < inputs.length; x++){
            sum += inputs[x] * weights[x];
        }
        return activationFunction(sum + theta);
    }

    // uses step function
    public static int activationFunction(double sum){
        if (sum >= 0){
            return 1;
        }
        return 0;
    }


    public NN_And_Perceptron() {

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

        double[] weights = new double[2];
        double[] inputs = new double[2];
        double theta = Math.random()-0.5; // is like a bias
        double learningRate = 0.1; // hyper parameter --> determines the size of each step. if too high will overshoot
        initializeWeights(weights);

        boolean done = false;
        int correct;

        //training
        while(!done){
            correct = 0;
            for (int rows = 0; rows < 4; rows++){
                inputs[0] = tInput[rows][0];
                inputs[1] = tInput[rows][1];
                int y = activate(inputs, weights, theta);
                int error = cAnswer[rows]-y;

                //update weights
                if (error != 0){
                    for(int w = 0; w < weights.length; w++){
                        weights[w] = updateWeight(weights[w], inputs[w], error, learningRate);
                    }
                    theta = updateWeight(theta,1, error, learningRate);
                    System.out.println("Weight updated... w1: " + weights[0] + " w2: " + weights[1] + " theta: " + theta);
                }
                else{
                    correct++;
                }
            }
            if (correct == 4){
                done = true;
            }
        }

        System.out.println("Training Done:");
        System.out.println("Testing...");
        inputs[0] = tInput[0][0];
        inputs[1] = tInput[0][1];
        System.out.println("Activate with 0 0 -->" + activate(inputs, weights, theta));
        inputs[0] = tInput[1][0];
        inputs[1] = tInput[1][1];
        System.out.println("Activate with 0 1 -->" + activate(inputs, weights, theta));
        inputs[0] = tInput[2][0];
        inputs[1] = tInput[2][1];
        System.out.println("Activate with 1 0 -->" + activate(inputs, weights, theta));
        inputs[0] = tInput[3][0];
        inputs[1] = tInput[3][1];
        System.out.println("Activate with 1 1 -->" + activate(inputs, weights, theta));

    }

}


