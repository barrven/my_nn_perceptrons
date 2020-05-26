package nn_perceptrons.reference;

public class Layer {
    int numNeurons;
    int numInputs;
    double[] inputs;
    Neuron[] neurons;

    public Layer(int numNodes, int numInputs){
        this.numNeurons = numNodes;
        this.numInputs = numInputs;
        neurons = new Neuron[numNeurons];
        createLayer();
    }

    private void createLayer(){
        for (int i = 0; i < numNeurons; i++){
            neurons[i] = new Neuron(numInputs);
        }
    }

    public double[] forwardPass(double[] input){
        inputs = input;
        double[] outputValues = new double[numNeurons];
        for (int i = 0; i < numNeurons; i++){
            outputValues[i] = neurons[i].forwardPass(input);
        }
        return outputValues;
    }

    //for training
    public double calcOutputGradients(double propError,double aOutput,double lr){
        double errorGradient;
        errorGradient=neurons[0].calcErrorGradient(propError,aOutput);
        neurons[0].calcDeltas(inputs,errorGradient,lr);
        return errorGradient;
    }

    public double[] calcInnerGradients(double propError,double[] outLayerInputWeights,double[] outLayerInput, double lr){
        double[] errorGradients= new double[numNeurons];
        for (int x=0;x<numNeurons;x++){
            errorGradients[x]=neurons[x].calcErrorGradient(propError*outLayerInputWeights[x],outLayerInput[x]);
            neurons[x].calcDeltas(inputs,errorGradients[x],lr);
        }
        return errorGradients;
    }

    public void updateLayerWeights() {
        for (int x = 0; x < numNeurons; x++) {
            neurons[x].updateWeights();
        }
    }
}
