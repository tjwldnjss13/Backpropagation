import java.util.Scanner;

public class Backpropagation {
    public static final double[][] NUMBER_SAMPLE = { 
        // 0
        {0,0,1,1,1,1,1,1,0,0,
        0,1,1,1,1,1,1,1,1,0,
        1,1,0,0,0,0,0,0,1,1,
        1,1,0,0,0,0,0,0,1,1,
        1,1,0,0,0,0,0,0,1,1,
        1,1,0,0,0,0,0,0,1,1,
        1,1,0,0,0,0,0,0,1,1,
        1,1,0,0,0,0,0,0,1,1,
        0,1,1,1,1,1,1,1,1,0,
        0,0,1,1,1,1,1,1,0,0},

        // 1
        {0,0,0,0,1,1,0,0,0,0,
       0,0,0,1,1,1,0,0,0,0,
       0,0,1,1,1,1,0,0,0,0,
       0,0,0,0,1,1,0,0,0,0,
       0,0,0,0,1,1,0,0,0,0,
       0,0,0,0,1,1,0,0,0,0,
       0,0,0,0,1,1,0,0,0,0,
       0,0,0,0,1,1,0,0,0,0,
       0,0,1,1,1,1,1,1,0,0,
       0,0,1,1,1,1,1,1,0,0},

       // 2
        {0,0,1,1,1,1,1,1,0,0,
        0,1,1,1,1,1,1,1,1,0,
        1,1,1,0,0,0,0,1,1,1,
        1,1,0,0,0,0,0,1,1,1,
        0,0,0,0,0,0,1,1,1,0,
        0,0,0,0,1,1,1,1,0,0,
        0,0,1,1,1,1,0,0,0,0,
        0,1,1,1,0,0,0,0,0,0,
        1,1,1,1,1,1,1,1,1,1,
        1,1,1,1,1,1,1,1,1,1},

        // 3
        {0,0,1,1,1,1,1,1,0,0,
        0,1,1,1,1,1,1,1,1,0,
        1,1,1,0,0,0,0,1,1,1,
        1,1,0,0,0,0,0,1,1,1,
        0,0,0,0,1,1,1,1,1,0,
        0,0,0,0,1,1,1,1,1,0,
        1,1,0,0,0,0,0,1,1,1,
        1,1,1,0,0,0,0,1,1,1,
        0,1,1,1,1,1,1,1,1,0,
        0,0,1,1,1,1,1,1,0,0},

        // 4
        {0,0,0,0,1,1,1,0,0,0,
        0,0,0,1,1,1,1,0,0,0,
        0,0,1,1,1,1,1,0,0,0,
        0,1,1,1,0,1,1,0,0,0,
        1,1,1,0,0,1,1,0,0,0,
        1,1,1,1,1,1,1,1,1,1,
        1,1,1,1,1,1,1,1,1,1,
        0,0,0,0,0,1,1,0,0,0,
        0,0,0,0,0,1,1,0,0,0,
        0,0,0,0,0,1,1,0,0,0},

        // 5
        {1,1,1,1,1,1,1,1,1,1,
        1,1,1,1,1,1,1,1,1,1,
        1,1,0,0,0,0,0,0,0,0,
        1,1,0,0,0,0,0,0,0,0,
        1,1,1,1,1,1,1,1,1,0,
        1,1,1,1,1,1,1,1,1,1,
        0,0,0,0,0,0,0,0,1,1,
        1,1,0,0,0,0,0,0,1,1,
        1,1,1,1,1,1,1,1,1,1,
        0,1,1,1,1,1,1,1,1,0},

        // 6
        {0,0,1,1,1,1,1,1,0,0,
       0,1,1,1,1,1,1,1,1,0,
       1,1,1,0,0,0,0,1,1,1,
       1,1,0,0,0,0,0,0,1,1,
       1,1,0,1,1,1,1,0,0,0,
       1,1,1,1,1,1,1,1,1,0,
       1,1,0,0,0,0,0,0,1,1,
       1,1,0,0,0,0,0,0,1,1,
       0,1,1,1,1,1,1,1,1,0,
       0,0,1,1,1,1,1,1,0,0},

       // 7
        {1,1,1,1,1,1,1,1,1,1,
         1,1,1,1,1,1,1,1,1,1,
         0,0,0,0,0,0,0,0,1,1,
         0,0,0,0,0,0,0,1,1,0,
         0,0,0,0,0,0,1,1,0,0,
         0,0,0,0,0,1,1,0,0,0,
         0,0,0,0,1,1,0,0,0,0,
         0,0,0,0,1,1,0,0,0,0,
         0,0,0,0,1,1,0,0,0,0,
         0,0,0,0,1,1,0,0,0,0},


        // 8
        {0,0,1,1,1,1,1,1,0,0,
         0,1,1,1,1,1,1,1,1,0,
         1,1,0,0,0,0,0,0,1,1,
         1,1,0,0,0,0,0,0,1,1,
         0,1,1,1,1,1,1,1,1,0,
         0,1,1,1,1,1,1,1,1,0,
         1,1,0,0,0,0,0,0,1,1,
         1,1,0,0,0,0,0,0,1,1,
         0,1,1,1,1,1,1,1,1,0,
         0,0,1,1,1,1,1,1,0,0},

        // 9
        {0,0,1,1,1,1,1,1,0,0,
        0,1,1,1,1,1,1,1,1,0,
        1,1,0,0,0,0,0,0,1,1,
        1,1,0,0,0,0,0,0,1,1,
        1,1,1,1,1,1,1,1,1,1,
        0,1,1,1,1,1,1,1,1,1,
        0,0,0,0,0,0,0,0,1,1,
        1,1,0,0,0,0,0,0,1,1,
        1,1,1,1,1,1,1,1,1,0,
        0,1,1,1,1,1,1,1,0,0} 
    };

    public static final double[][] DESIRED_OUTPUT = { 
        {0,0,0,0},  // 0
        {0,0,0,1},  // 1
        {0,0,1,0},  // 2
        {0,0,1,1},  // 3
        {0,1,0,0},  // 4
        {0,1,0,1},  // 5
        {0,1,1,0},  // 6
        {0,1,1,1},  // 7
        {1,0,0,0},  // 8
        {1,0,0,1}   // 9
    }; 

    Scanner scan = new Scanner(System.in);
    double totalError = 0;
    int numHiddenLayer = 2;
    int numHiddenLayerNode = 10;
    int numUnit = Backpropagation.NUMBER_SAMPLE[0].length;
    int numNumberSample = Backpropagation.NUMBER_SAMPLE.length;
    int outputLength = Backpropagation.DESIRED_OUTPUT[0].length;
    int threshold = 0;
    int numIncorrect = 0;
    int iTestNumberSample = 0;
    //double learningRate1 = 0.2;
    double learningRate2 = 0.2;
    double[][] inputLayerWeight = new double[this.numUnit][this.numHiddenLayerNode];
    double[][][] hiddenLayerWeight = new double[this.numHiddenLayer-1][this.numHiddenLayerNode][this.numHiddenLayerNode];
    double[][] outputLayerWeight = new double[this.numHiddenLayerNode][this.outputLength];
    double[][] previousInputLayerWeight = new double[this.numUnit][this.numHiddenLayerNode];
    double[][][] previousHiddenLayerWeight = new double[this.numHiddenLayer-1][this.numHiddenLayerNode][this.numHiddenLayerNode];
    double[][] previousOutputLayerWeight = new double[this.numHiddenLayerNode][this.outputLength];
    double[] patternError = new double[this.numNumberSample];
    double[] inputLayerNET = new double[this.numHiddenLayerNode];
    double[][] hiddenLayerNET = new double[this.numHiddenLayer-1][this.numHiddenLayerNode];
    double[] outputLayerNET = new double[this.outputLength];
    double[][] actualOutput = new double[this.numNumberSample][this.outputLength];
    double[] outputLayerDelta = new double[this.outputLength];
    double[] hiddenLayerDelta = new double[this.numHiddenLayerNode];
    double[] previousHiddenLayerDelta = new double[this.numHiddenLayerNode];

    public static void main(String[] args) {
        Backpropagation bp = new Backpropagation();
        boolean isError = true;
        boolean stopFlag = false;
        double previousError = 0;
        int numErrorStable = 1;
        int epoch = 0;
        char repeat = ' ';

        bp.initWeight();
        bp.initNET();
        bp.initPatternError();
        bp.initTotalError();
        bp.initActualOutput();

        while (isError) {
            bp.initPatternError();
            bp.initTotalError();
            //bp.numIncorrect = 0;
            epoch++;

            //System.out.println("---------------" + epoch + "번째 학습 시작---------------");

            for (int i=0; i < bp.numNumberSample; i++) {
                //System.out.println();
                //System.out.println("<패턴 " + (i) + " 학습 시작>");

                bp.initNET();

                bp.makeNETwith2Hidden(i);
                bp.makeActualOutput(i);
                //bp.printActualOutput();
                bp.calcPatternError(i);
                if (bp.patternError[i] != 0) {
                    if (bp.numHiddenLayer == 2)
                        bp.updateWeightWith2Hidden(i, epoch);
                }
                //bp.printReverseSigmoid();
            }
            
            bp.calcTotalError();
            //bp.printError();

            if (epoch > 1) {
                if (bp.totalError == previousError)
                    numErrorStable++;
                else 
                    numErrorStable = 1;
            }
            
            if (epoch % 100 == 0) {
                if (epoch % 1000 == 0)
                    System.out.print("/");
                else
                    System.out.print(".");
            }

            if (numErrorStable == 200) 
                isError = false;

            previousError = bp.totalError;

            //System.out.println();
            //System.out.println("---------------" + epoch + "번째 학습 완료---------------");
            //System.out.println();
        }
        System.out.println();
        System.out.println("<Desired Output>");
        for (int i=0; i<bp.numNumberSample; i++) {
            bp.printDesiredOutput(i);
        }
        System.out.println();
        System.out.println("<Actual Output>");
        for (int i=0; i<bp.numNumberSample; i++) {
            bp.printActualOutput(i);
        }

        System.out.println("--------------------학습 완료--------------------");
        
        /*
        System.out.println("--------------------테스트 시작--------------------");
        while (!stopFlag) {
            bp.testInput();

            System.out.print("Continue? (y/n) ");
            repeat = bp.scan.next().charAt(0);
            if (repeat == 'n' || repeat == 'N')
                stopFlag = true;
        }
        bp.scan.close();
        System.out.println("--------------------테스트 완료--------------------");
        */
    }
    
    void initInputLayerWeight() {
        for (int i=0; i<this.numUnit; i++) {
            for (int j=0; j<this.numHiddenLayerNode; j++) {
                this.inputLayerWeight[i][j] = Math.random();
            }
        }
    }

    void initHiddenLayerWeight() {
        for (int k=0; k<this.numHiddenLayer-1; k++) {
            for (int i=0; i<this.numHiddenLayerNode; i++) {
                for (int j=0; j<this.numHiddenLayerNode; j++) {
                    this.hiddenLayerWeight[k][i][j] = Math.random();
                }
            }
        }
    }

    void initOutputLayerWeight() {
        for (int i=0; i<this.numHiddenLayerNode; i++) {
            for (int j=0; j<this.outputLength; j++) {
                this.outputLayerWeight[i][j] = Math.random();
            }
        }
    }

    void initWeight() {
        this.initInputLayerWeight();
        this.initHiddenLayerWeight();
        this.initOutputLayerWeight();
    }

    void initPatternError() {
        for (int i=0; i<this.numNumberSample; i++) {
            this.patternError[i] = 0;   
        }
    }

    void initTotalError() {
        this.totalError = 0;
    }

    void initInputLayerNET() {
        for (int i=0; i<this.numHiddenLayerNode; i++) {
            this.inputLayerNET[i] = 0;
        }
    }

    void initHiddenLayerNET() {
        for (int k=0; k<this.numHiddenLayer-1; k++) {
            for (int i=0; i<this.numHiddenLayerNode; i++) {
                this.hiddenLayerNET[k][i] = 0;   
            }
        }
    }

    void initOutputLayerNET() {
        for (int i=0; i<this.outputLength; i++) {
            this.outputLayerNET[i] = 0;
        }
    }

    void initNET() {
        this.initInputLayerNET();
        this.initHiddenLayerNET();
        this.initOutputLayerNET();
    }

    void initActualOutput() {
        for (int i=0; i<this.numNumberSample; i++) {
            for (int j=0; j<this.outputLength; j++) {
                this.actualOutput[i][j] = 0;
            }
        }
    }

    void initDelta() {
        for (int i=0; i<this.outputLength; i++) {
            this.outputLayerDelta[i] = 0;
        }
        for (int i=0; i<this.numHiddenLayerNode; i++) {
            this.hiddenLayerDelta[i] = 0;
            this.previousHiddenLayerDelta[i] = 0;
        }
    }

    void makeNETwith2Hidden(int iNumberSample) {    // (node i) --> (node j)
        for (int k=0; k<this.numHiddenLayer+1; k++) {
            // Input layer
            if (k == 0) {
                for (int j=0; j<this.numHiddenLayerNode; j++) {
                    this.inputLayerNET[j] = 0;
                    for (int i=0; i<this.numUnit; i++) {
                        this.inputLayerNET[j] += Backpropagation.NUMBER_SAMPLE[iNumberSample][i] * this.inputLayerWeight[i][j];
                    }
                }
                //this.printNET(k);
            }
            // Hidden layer
            else if (k == 1) {   // k == 1
                for (int j=0; j<this.numHiddenLayerNode; j++) {
                    this.hiddenLayerNET[0][j] = 0;
                    for (int i=0; i<this.numHiddenLayerNode; i++) {
                        this.hiddenLayerNET[0][j] += this.sigmoid(this.inputLayerNET[i]) * this.hiddenLayerWeight[0][i][j];
                    }
                }
                //this.printNET(k);
            }
            // Output layer
            else if (k == 2) {     // k == 2
                for (int j=0; j<this.outputLength; j++) {
                    this.outputLayerNET[j] = 0;
                    for (int i=0; i<this.numHiddenLayerNode; i++) {
                        this.outputLayerNET[j] += this.sigmoid(this.hiddenLayerNET[0][i]) * this.outputLayerWeight[i][j];
                    }
                }
                //this.printNET(k);
            }
        }
    }

    void testNET(int iNumberSample) {

    }

    void makeActualOutput(int iNumberSample) {
        for (int i=0; i<this.outputLength; i++) {
            this.actualOutput[iNumberSample][i] = this.sigmoid(this.outputLayerNET[i]);
        }
    }

    
    void printNET(int iLayer) {
        if (iLayer == this.numHiddenLayer) {
            System.out.print("Output NET (" + iLayer + ") : [" );
            for (int i=0; i<this.outputLength; i++) {
                System.out.print(this.outputLayerNET[i]);
                if (i != this.outputLength - 1)
                    System.out.print(", ");
            }
            System.out.println("]");
        }
        else if ((iLayer < this.numHiddenLayerNode) && (iLayer > 0)) {
            System.out.print("Hidden NET (" + iLayer +  ") : [");
            for (int i=0; i<this.numHiddenLayerNode; i++) {
                System.out.print(this.hiddenLayerNET[iLayer-1][i]);
                if (i != this.numHiddenLayerNode - 1)
                    System.out.print(", ");
            }
            System.out.println("]");
        }
        else if (iLayer == 0) {
            System.out.print("Input NET  (" + iLayer + ") : [");
            for (int i=0; i<this.numHiddenLayerNode; i++) {
                System.out.print(this.inputLayerNET[i]);
                if (i != this.numHiddenLayerNode - 1)
                    System.out.print(", ");
            }
            System.out.println("]");
        }
    }

    void printActualOutput(int iNumberSample) {
        System.out.print("Actual Output ( " + iNumberSample + ") : [");
        for (int i=0; i<this.outputLength; i++) {
            System.out.print(this.actualOutput[iNumberSample][i]);
            if (i != this.outputLength - 1)
                System.out.print(", ");
        }
        System.out.println("]");
    }
    
    void updateWeightWith2Hidden(int iNumberSample, int epoch) {   // (node i) <-- (node j) backpropagation
        double delta = 0;
        double delta_base = 0;
        double temp = 0;

        this.initDelta();

        for (int k=this.numHiddenLayer; k>=0; k--) {
            delta = 0;
            delta_base = 0;

            // Output Layer
            if (k == 2) {
                for (int l=0; l<this.outputLength; l++) {    // (node i) <-- (node j(l))
                    delta = this.sigmoidPrime(this.actualOutput[iNumberSample][l]) * (this.sigmoid(Backpropagation.DESIRED_OUTPUT[iNumberSample][l]) - this.actualOutput[iNumberSample][l]);
                    this.outputLayerDelta[l] = delta;
                }
                for (int j=0; j<this.outputLength; j++) {
                    for (int i=0; i<this.numHiddenLayerNode; i++) {
                        if (epoch == 1) {
                            this.outputLayerWeight[i][j] += this.learningRate2 * this.outputLayerDelta[j] * this.sigmoid(this.hiddenLayerNET[0][i]);
                        }
                        else if (epoch > 1) {
                            temp = this.outputLayerWeight[i][j];
                            this.outputLayerWeight[i][j] += this.learningRate2 * this.outputLayerDelta[j] * this.sigmoid(this.hiddenLayerNET[0][i]);
                            this.outputLayerWeight[i][j] += this.learningRate2 * (temp - this.previousOutputLayerWeight[i][j]);
                        }
                    }
                }
                for (int j=0; j<this.outputLength; j++) {
                    for (int i=0; i<this.numHiddenLayerNode; i++) {
                        this.previousOutputLayerWeight[i][j] = this.outputLayerWeight[i][j];
                    }
                }
            }
            // Hidden Layer
            else if (k == 1) {
                for (int l=0; l<this.numHiddenLayerNode; l++) {    // (node i) <-- (node j(l)) <-- (node m)
                    delta = 0;
                    delta_base = this.sigmoidPrime(this.sigmoid(this.hiddenLayerNET[0][l]));
                    for (int m=0; m<this.outputLength; m++) {
                        delta += delta_base * this.outputLayerDelta[m] * this.outputLayerWeight[l][m];
                    }
                    this.hiddenLayerDelta[l] = delta;
                    this.previousHiddenLayerDelta[l] = this.hiddenLayerDelta[l];
                }
                for (int j=0; j<this.numHiddenLayerNode; j++) {
                    for (int i=0; i<this.numHiddenLayerNode; i++) {
                        if (epoch == 1) {
                            this.hiddenLayerWeight[0][i][j] += this.learningRate2 * this.hiddenLayerDelta[j] * this.sigmoid(this.inputLayerNET[i]);
                        }
                        else if (epoch > 1) {
                            temp = this.hiddenLayerWeight[0][i][j];
                            this.hiddenLayerWeight[0][i][j] += this.learningRate2 * this.hiddenLayerDelta[j] * this.sigmoid(this.inputLayerNET[i]); 
                            this.hiddenLayerWeight[0][i][j] += this.learningRate2 * (temp - this.previousHiddenLayerWeight[0][i][j]);
                        }
                    }
                }
                for (int j=0; j<this.numHiddenLayerNode; j++) {
                    for (int i=0; i<this.numHiddenLayerNode; i++) {
                        this.previousHiddenLayerWeight[0][i][j] = this.hiddenLayerWeight[0][i][j];
                    }
                }
            }
            // Input Layer
            else if (k == 0) {     
                for (int l=0; l<this.numHiddenLayerNode; l++) {   // (node i) <-- (node j(l)) <-- (node m)
                    delta = 0;
                    delta_base = this.sigmoidPrime(this.sigmoid(this.inputLayerNET[l]));
                    for (int m=0; m<this.numHiddenLayerNode; m++) {
                        delta += delta_base * this.previousHiddenLayerDelta[m] * this.hiddenLayerWeight[0][l][m];
                    }
                    this.hiddenLayerDelta[l] = delta;
                }
                for (int j=0; j<this.numHiddenLayerNode; j++) {
                    for (int i=0; i<this.numUnit; i++) {
                        if (epoch == 1) {
                            this.inputLayerWeight[i][j] += this.learningRate2 * this.hiddenLayerDelta[j] * Backpropagation.NUMBER_SAMPLE[iNumberSample][i];
                        }
                        else if (epoch > 1) {
                            temp = this.inputLayerWeight[i][j];
                            this.inputLayerWeight[i][j] += this.learningRate2 * this.hiddenLayerDelta[j] * Backpropagation.NUMBER_SAMPLE[iNumberSample][i];
                            this.inputLayerWeight[i][j] += this.learningRate2 * (temp - this.previousInputLayerWeight[i][j]);
                        }
                    }
                }
                for (int j=0; j<this.numHiddenLayerNode; j++) {
                    for (int i=0; i<this.numUnit; i++) {
                        this.previousInputLayerWeight[i][j] = this.inputLayerWeight[i][j];
                    }
                }
            }
        }
    }

    void updateWeightWith3Hidden(int iNumberSample) {   // (node i) <-- (node j) backpropagation
        double delta = 0;
        double delta_base = 0;

        this.initDelta();

        for (int k=this.numHiddenLayer; k>=0; k--) {
            delta = 0;
            delta_base = 0;

            // Output Layer
            if (k == this.numHiddenLayer) {   // k == 2
                for (int l=0; l<this.outputLength; l++) {    // (node i) <-- (node j(l))
                    delta = this.actualOutput[iNumberSample][l] * (1 - this.actualOutput[iNumberSample][l]) * (this.sigmoid(Backpropagation.DESIRED_OUTPUT[iNumberSample][l]) - this.actualOutput[iNumberSample][l]);
                    this.outputLayerDelta[l] = delta;
                    this.outputLayerDelta[l] = this.restrictPoint(this.outputLayerDelta[l]);
                }
                for (int j=0; j<this.outputLength; j++) {
                    for (int i=0; i<this.numHiddenLayerNode; i++) {
                        this.outputLayerWeight[i][j] += this.learningRate1 * this.outputLayerDelta[j] * this.sigmoid(this.hiddenLayerNET[k-2][i]);
                        this.outputLayerWeight[i][j] = this.restrictPoint(this.outputLayerWeight[i][j]);
                    }
                }
            }
            else if ((k < this.numHiddenLayer) && (k > 0)) {
                for (int l=0; l<this.numHiddenLayerNode; l++) {
                    if (this.numHiddenLayer == 2) {
                        
                    }
                }
            }

            // Right-most Hidden Layer
            else if (k == this.numHiddenLayer - 1) {   // k == 1
                for (int l=0; l<this.numHiddenLayerNode; l++) {    // (node i) <-- (node j(l)) <-- (node m)
                    delta_base = this.sigmoid(this.hiddenLayerNET[k-1][l]) * (1 - this.sigmoid(this.hiddenLayerNET[k-1][l]));
                    for (int m=0; m<this.outputLength; m++) {
                        delta += delta_base * this.outputLayerDelta[m] * this.outputLayerWeight[l][m];
                    }
                    this.hiddenLayerDelta[l] = delta;
                    this.hiddenLayerDelta[l] = this.restrictPoint(this.hiddenLayerDelta[l]);

                    this.previousHiddenLayerDelta[l] = this.hiddenLayerDelta[l];
                }
                for (int j=0; j<this.numHiddenLayerNode; j++) {
                    for (int i=0; i<this.numHiddenLayerNode; i++) {
                        this.hiddenLayerWeight[k-1][i][j] += this.learningRate1 * this.hiddenLayerDelta[j] * this.sigmoid(this.hiddenLayerNET[k-1][i]); 
                        this.hiddenLayerWeight[k-1][i][j] = this.restrictPoint(this.hiddenLayerWeight[k-1][i][j]);
                    }
                }
            }
            // Remaining Hidden Layer
            else if ((k < this.numHiddenLayer - 1) && (k > 1)) {  
                for (int l=0; l<this.numHiddenLayerNode; l++) {     // (node i) <-- (node j(l)) <-- (node m)
                    delta_base = this.sigmoid(this.hiddenLayerNET[k][l]) * (1 - this.sigmoid(this.hiddenLayerNET[k][l]));
                    for (int m=0; m<this.numHiddenLayerNode; m++) {
                        delta += delta_base * this.previousHiddenLayerDelta[m] * this.hiddenLayerWeight[k][l][m];
                    }
                    this.hiddenLayerDelta[l] = delta;
                    this.hiddenLayerDelta[l] = this.restrictPoint(this.hiddenLayerDelta[l]);
                    
                    this.previousHiddenLayerDelta[l] = this.hiddenLayerDelta[l];
                }
                for (int j=0; j<this.numHiddenLayerNode; j++) {
                    for (int i=0; i<this.numHiddenLayerNode; i++) {
                        this.hiddenLayerWeight[k-1][i][j] += this.learningRate1 * this.hiddenLayerDelta[j] * this.sigmoid(this.hiddenLayerNET[k-1][i]); 
                        this.hiddenLayerWeight[k-1][i][j] = this.restrictPoint(this.hiddenLayerWeight[k-1][i][j]);
                    }
                }
            }
            // Left-most Hidden Layer
            else if ((k != this.numHiddenLayerNode - 1) && (k == 1)) {
                for (int l=0; l<this.numHiddenLayerNode; l++) {    // (node i) <-- (node j(l)) <-- (node m)
                    delta_base = this.inputLayerNET[l] * (1 - this.inputLayerNET[l]);
                    for (int m=0; m<this.numHiddenLayerNode; m++) {
                        delta += delta_base * this.previousHiddenLayerDelta[m] * this.hiddenLayerWeight[0][l][m];
                    }
                    this.hiddenLayerDelta[l] = delta;
                    this.hiddenLayerDelta[l] = this.restrictPoint(this.hiddenLayerDelta[l]);
                    this.previousHiddenLayerDelta[l] = this.hiddenLayerDelta[l];
                }
                for (int j=0; j<this.numHiddenLayerNode; j++) {
                    for (int i=0; i<this.numHiddenLayerNode; i++) {
                        this.hiddenLayerWeight[0][i][j] += this.learningRate1 * this.hiddenLayerDelta[j] * this.sigmoid(this.inputLayerNET[i]);
                        this.hiddenLayerWeight[0][i][j] = this.restrictPoint(this.hiddenLayerWeight[0][i][j]);
                    }
                }
            }
            // Input Layer
            else if (k == 0) {     
                for (int l=0; l<this.numHiddenLayerNode; l++) {   // (node i) <-- (node j(l)) <-- (node m)
                    delta_base = this.sigmoid(this.inputLayerNET[l]) * (1 - this.sigmoid(this.inputLayerNET[l]));
                    for (int m=0; m<this.numHiddenLayerNode; m++) {
                        delta += delta_base * this.previousHiddenLayerDelta[m] * this.hiddenLayerWeight[0][l][m];
                    }
                    this.hiddenLayerDelta[l] = delta;
                    this.hiddenLayerDelta[l] = this.restrictPoint(this.hiddenLayerDelta[l]);
                }
                for (int j=0; j<this.numHiddenLayerNode; j++) {
                    for (int i=0; i<this.numUnit; i++) {
                        this.inputLayerWeight[i][j] += this.learningRate1 * this.hiddenLayerDelta[j] * this.sigmoid(Backpropagation.NUMBER_SAMPLE[iNumberSample][i]);
                        this.inputLayerWeight[i][j] = this.restrictPoint(this.inputLayerWeight[i][j]);
                    }
                }
            }
        }
    }

    void printDesiredOutput(int iNumberSample) {
        System.out.print("Desired Output (" + iNumberSample + ") : [");
        for (int i=0; i<this.outputLength; i++) {
            System.out.print(this.sigmoid(Backpropagation.DESIRED_OUTPUT[iNumberSample][i]));
            if (i != this.outputLength - 1)
                System.out.print(", ");    
        }
        System.out.println("]");
        //System.out.println();
    }

    double patternBitError(double target, double actualOutput) {
        return target - actualOutput;
    }
    
    void calcPatternError(int iNumberSample) {
        for (int i=0; i<this.outputLength; i++) {
            this.patternError[iNumberSample] += 0.5 * this.square(this.sigmoid(Backpropagation.DESIRED_OUTPUT[iNumberSample][i]) - this.actualOutput[iNumberSample][i]);
        }
    }

    void calcTotalError() {
        for (int i=0; i<this.numNumberSample; i++) {
            this.totalError += this.patternError[i];
        }
        this.totalError = this.restrictPoint(this.totalError);
    }

    void printError() {
        System.out.println();
        System.out.println("Total Error : " + this.totalError);
    }

    void printInput(int iTestNumberSample) {
        System.out.println();
        for (int i=0; i<this.numUnit; i++) {
            if (Backpropagation.NUMBER_SAMPLE[iTestNumberSample][i] == 1)
                System.out.print("O");
            else
                System.out.print(" ");
            if ((i + 1) % 10 == 0)
                System.out.println();
        }
        System.out.println();
    }

    void testInput() {
        int iminError = 0;
        double temp = 10000;

        this.initNET();
        this.initPatternError();
        this.initActualOutput();

        System.out.println();
        System.out.print("테스트할 숫자 패턴 : ");
        this.iTestNumberSample = this.scan.nextInt();

        this.printInput(this.iTestNumberSample);

        this.makeNETwith2Hidden(this.iTestNumberSample);
        this.makeActualOutput(this.iTestNumberSample);

        //this.printNET(this.numHiddenLayer);
        this.printActualOutput(this.iTestNumberSample);
        //this.printReverseSigmoid();
        
        for (int i=0; i<this.numNumberSample; i++) {
            this.calcPatternError(i);
            System.out.print(this.restrictPoint(this.patternError[i]) + " ");
        }
        System.out.println();

        for (int j=0; j<this.numNumberSample; j++) {
            if (this.patternError[j] < temp) {
                iminError = j;
                temp = this.patternError[j];
            }
        }
        
        System.out.println("패턴 " + iminError + "에 가장 수렴");
    }

    // Activation Function
    double sigmoid(double input) {
        return 1.0 / (Double) (1 + Math.exp(-1 * input));
    }

    double sigmoidPrime(double input) {
        return input * (1 - input);
    }

    double square(double a) {
        return a * a;
    } 

    double restrictPoint(double a) {
        return Double.parseDouble(String.format("%7f", a));
    }
}