import java.util.Random;

public class NeuralNetwork {
    private double[][] weights;
    private double[] biases;
    private int[] layers;
    private int numLayers;

    public NeuralNetwork(int[] layers) {
        this.layers = layers;
        this.numLayers = layers.length;
        this.weights = new double[numLayers - 1][];
        this.biases = new double[numLayers - 1];

        Random random = new Random();

        // Initialize weights and biases with random values
        for (int i = 0; i < numLayers - 1; i++) {
            weights[i] = new double[layers[i] * layers[i + 1]];
            biases[i] = random.nextDouble() - 0.5;
            for (int j = 0; j < weights[i].length; j++) {
                weights[i][j] = random.nextDouble() - 0.5;
            }
        }
    }

    private double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public double feedForward(double[] input) {
        double[] activations = input.clone();

        for (int i = 0; i < numLayers - 1; i++) {
            double[] nextActivations = new double[layers[i + 1]];

            for (int j = 0; j < layers[i + 1]; j++) {
                double weightedSum = biases[i];

                for (int k = 0; k < layers[i]; k++) {
                    weightedSum += weights[i][j * layers[i] + k] * activations[k];
                }

                nextActivations[j] = sigmoid(weightedSum);
            }

            activations = nextActivations;
        }

        return activations[0]; // Assuming a single output node for the AND gate
    }

    public static void main(String[] args) {
        int[] layers = {2, 2, 1}; // 2 input nodes, 2 hidden nodes, 1 output node
        NeuralNetwork network = new NeuralNetwork(layers);

        // Test inputs
        double[][] testInputs = {
                {0, 0},
                {0, 1},
                {1, 0},
                {1, 1}
        };

        // Feed inputs to the network and print the results
        for (double[] input : testInputs) {
            double output = network.feedForward(input);
            System.out.println("Input: [" + input[0] + ", " + input[1] + "] => Output: " + output);
        }
    }
}
