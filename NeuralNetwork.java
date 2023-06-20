
class neuron extends NeuralNetwork {
    public double weight1 = 20;
    private double weight2 = 20;
    public double bias = -30;

    public double compute(double input1, double input2){
        double preActivation = (this.weight1 * input1) + (this.weight2 * input2) + bias;
        double output = sigmoid(preActivation);
        return output;
    }
}

public class NeuralNetwork {
    neuron neuron;

    NeuralNetwork(){

    }
    NeuralNetwork(neuron neuron){
        this.neuron = neuron;

    }

    public double predict(double input1, double input2){
        double neuron_output = neuron.compute(input1, input2);
        return neuron_output;
    }
    double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public static void main(String[] args) {
        neuron neuron = new neuron();
       NeuralNetwork network = new NeuralNetwork(neuron);

        // Test inputs
        System.out.println("Input is (1,1): "+ Math.round(network.predict(1,1)));
        System.out.println("Input is (1,0): "+ Math.round(network.predict(1,0)));
        System.out.println("Input is (0,0): "+ Math.round(network.predict(0,0)));
        System.out.println("Input is (0,1): "+ Math.round(network.predict(1,0)));

    }
}
