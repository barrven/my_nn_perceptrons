package nn_perceptrons.reference;
import java.util.ArrayList;

public class NeuralNetwork {
    double learningRate;
    int numInputs;
    ArrayList<Layer> layerList;

    public NeuralNetwork(int numInputs, double learningRate){
       this.numInputs = numInputs;
       this.learningRate = learningRate;
       layerList = new ArrayList<Layer>();
    }

    public void addLayer(int numNodes){
        int prevLayer = layerList.size()-1;
        if (prevLayer >= 0){
            int numPrevLayerOutputs = layerList.get(prevLayer).numNeurons;
            layerList.add(new Layer(numNodes, numPrevLayerOutputs));
        }
        else{
            layerList.add(new Layer(numNodes, numInputs));
        }
    }

    public double[] forwardPass(double[] input){
        double[] layerOutput = input;
        for (int i = 0; i < layerList.size(); i++){
            layerOutput = layerList.get(i).forwardPass(layerOutput);
        }
        return layerOutput;
    }

    //for training
    public void trainThreeLayer(double[] input, double error, double actualOutput){
        int outerLayer = 1;
        double errorGradient;
        Layer out=layerList.get(outerLayer);
        //calc output layer error gradient/s
        errorGradient=out.calcOutputGradients(error,actualOutput,learningRate);
        //propagate backward
        layerList.get(0).calcInnerGradients(errorGradient,out.neurons[0].weights,out.inputs,learningRate);
        layerList.get(1).updateLayerWeights();
        layerList.get(0).updateLayerWeights();
    }


}
