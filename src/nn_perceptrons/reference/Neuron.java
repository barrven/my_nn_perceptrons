package nn_perceptrons.reference;

public class Neuron {
    int numInputs;
    double[] weights;
    double bias;
    //for training
    double[] deltaWeights;
    double deltaBias;

    public Neuron(int numInputs){
        this.numInputs = numInputs;
        weights = new double[numInputs];
        initializeWeights();
        deltaWeights = new double[numInputs];
    }

    //use if network has preset values (ie already trained)
    public Neuron(double[] weightsAndBias){
        numInputs = weightsAndBias.length-1;
        for (int i = 0; i < numInputs; i++){
            weights[i] = weightsAndBias[i];
        }
        bias = weightsAndBias[numInputs];
    }

    private void initializeWeights(){
        for (int i = 0; i < numInputs; i++){
            weights[i] = Math.random()-0.5;
        }
        bias = Math.random()-0.5;
    }

    public double activate(double sum){
        return 1.0/(1 + Math.exp(sum * -1));
    }

    public double forwardPass(double[] input){
        double sum = 0;
        for (int i = 0; i < numInputs; i++){
            sum += input[i] * weights[i];
        }
        return activate(sum + bias);
    }
    //for training
    public double calcErrorGradient(double propError, double output){
        return propError * activateSigmoid(output);
    }

    private static double activateSigmoid(double sum){
        return 1.0/(1 + Math.exp(sum*-1));
    }

    public void calcDeltas(double[] inputs,double errorGradient,double lr){
        for (int x=0;x<numInputs;x++){
            deltaWeights[x] = lr*errorGradient*inputs[x];
        }
        deltaBias=lr*errorGradient*-1;
    }

    public void updateWeights(){
        for (int x=0;x<numInputs;x++){
            weights[x]+=deltaWeights[x];
        }
    }
}
