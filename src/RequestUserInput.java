import java.util.Scanner;

public class RequestUserInput {

    Scanner scan;
    int numOfVertex;

    public RequestUserInput(Scanner scan, int numOfVertex) {
        this.scan = scan;
        this.numOfVertex = numOfVertex;
    }

    public int requestNumberOfAgents() {
        int numOfAgents = 0;
        System.out.println("Please enter number of agents");
        numOfAgents = scan.nextInt();
        while (numOfAgents <= 0) {
            System.out.println("number of agents should be positive integer. Try again");
            numOfAgents = scan.nextInt();
        }
        return numOfAgents;
    }

    public int requestAgentType(int agentIndex) {
        int agentType = 0;
        System.out.println("Please enter the type of agent " + (agentIndex + 1) + ":");
        System.out.println(" -For HUMAN agent press 1");
        System.out.println(" -For GREEDY agent press 2");
        System.out.println(" -For VANDAL agent press 3");
        System.out.println(" -For GREEDY-SEARCH agent press 4");
        System.out.println(" -For A-STAR agent press 5");
        System.out.println(" -For REAL-TIME-A-STAR agent press 6");
        agentType = scan.nextInt();
        while (agentType < 1 | agentType > 6) {
            System.out.println("type of agent should be 1-6. Try again");
            agentType = scan.nextInt();
        }
        return agentType;
    }

    public int requestAgentInitialPosition(int agentIndex) {
        int agentPosition = 0;
        System.out.println("Please enter the initial position of agent " + (agentIndex + 1)
                + " (natural number between 1-" + numOfVertex + ")");
        agentPosition = scan.nextInt() - 1;
        while (agentPosition >= numOfVertex | agentPosition < 0) {
            System.out
                    .println("position of agent should be positive integer smaller than number of vertices. Try again");
            agentPosition = scan.nextInt() - 1;
        }
        return agentPosition;
    }

    public double requestParameterK() {
        double parameterK = 0;
        System.out.println("Please enter the parameter k");
        parameterK = scan.nextDouble();
        while (parameterK < 0 | parameterK > 1) {
            System.out.println("k should be double between 0 and 1. Try again");
            parameterK = scan.nextDouble();
        }
        return parameterK;
    }

    public int requestParameterF() {
        int parameterF = 0;
        System.out.println("Please enter the parameter f");
        parameterF = scan.nextInt();
        return parameterF;
    }

    public boolean requestMode() {
        int isBonus = 0;
        System.out.println("For regular mode press 0, for bonus mode press 1:");
        isBonus = scan.nextInt();
        while (isBonus != 0 && isBonus != 1) {
            System.out.println("Try again. Enter 0 for regular mode or 1 for bonus mode");
            isBonus = scan.nextInt();
        }
        return isBonus == 1;
    }

}
