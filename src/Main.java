import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    // globals
    static Scanner scan = new Scanner(System.in);
    static int numOfAgents;
    static Agent[] agents;
    static double k_parameter;
    static double[][] vertexMatrix;
    static double deadline;
    static boolean[] shelters;
    static int[] peopleToSave;
    static double time = 0;
    static double mostHeavyEdge;
    static int f_parameter;
    static boolean isBonus = false;

    public static void main(String[] args) {
        FileParser parser = new FileParser();
        parser.parse("file.txt");

        vertexMatrix = parser.vertexMatrix;
        deadline = parser.deadline;
        shelters = parser.isShelter;
        peopleToSave = parser.people;

        getInputsFromUser();
        checkForPeopleInShelters();
        checkForAgentsWithPeopleInCar();
        getHeaviestEdge();

        File outfile = new File("results.txt");
        start(outfile);
    }

    private static void setAgent(int index, int type, int position) {
        switch (type) {
        case 1:
            agents[index] = new HumanAgent(type, position, 0);
            break;
        case 2:
            agents[index] = new GreedyAgent(type, position, 0);
            break;
        case 3:
            agents[index] = new VandalAgent(type, position, 0);
            break;
        case 4:
            agents[index] = new GreedySearchAgent(type, position, 0);
            break;
        case 5:
            agents[index] = new AStarAgent(type, position, 0);
            break;
        case 6:
            agents[index] = new RealtimeAStarAgent(type, position, 0);
            break;
        }
    }

    private static void getInputsFromUser() {
        RequestUserInput userInput = new RequestUserInput(scan, vertexMatrix.length);

        numOfAgents = userInput.requestNumberOfAgents();
        agents = new Agent[numOfAgents];

        // get types and positions of agents
        for (int i = 0; i < numOfAgents; i++) {
            int type = userInput.requestAgentType(i);
            int pos = userInput.requestAgentInitialPosition(i);
            setAgent(i, type, pos);
        }

        k_parameter = userInput.requestParameterK();
        f_parameter = userInput.requestParameterF();

        // Bonus
        if (numOfAgents == 2) {
            if ((agents[0].agentType == 3 && agents[1].agentType > 3)
                    || (agents[1].agentType == 3 && agents[0].agentType > 3)) {
                isBonus = userInput.requestMode();
            }
        }
    }

    private static void printMoveInfoToFile(FileWriter writer, int i, int vertexToMoveTo) throws IOException {
        writer.write("time is " + time + "/" + deadline + "\n");
        if (agents[i].agentType != 3) { // if not vandal agent
            writer.write("agent " + (i + 1) + " moved to vertex " + (vertexToMoveTo + 1) + "\n");
            writer.write("agent " + (i + 1) + " has " + agents[i].peopleInCar + " people in car\n");
            writer.write("agent " + (i + 1) + " do " + agents[i].numberOfActionsDone);
            writer.write(" actions in total time of " + agents[i].totalAgentRunningTime + "\n");
            writer.write("agents " + (i + 1) + " score is " + agents[i].score + "\n\n");
        } else { // if vandal
            writer.write("vandal " + (i + 1) + " moved to vertex " + (vertexToMoveTo + 1) + "\n\n");
        }
    }

    private static void printNotEnoughTimeMessageToFile(FileWriter writer, int i, double timeForAction,
            int vertexToMoveTo) throws IOException {
        writer.write("the last action doesn't have enough time :( \n");
        writer.write("agent " + (i + 1) + " tried to go to vertex " + (vertexToMoveTo + 1));
        writer.write(" but this action takes " + timeForAction + " time units\n\n");
    }
    
    private static void printAgentsPerformanceToFile(FileWriter writer) throws IOException {
        for (int i = 0; i < numOfAgents; i++) {
            if (agents[i].agentType >= 3)
                writer.write("agents " + (i + 1) + " performance is "
                        + ((agents[i].score) * f_parameter + agents[i].totalNumOfExpands) + "\n");
        }
    }
    
    private static void performAgentsStrategies(FileWriter writer) throws IOException {
        for (int i = 0; i < numOfAgents; i++) {
            if (time < deadline) {
                PrintInfo.printPrevWorldState(Main.deadline - Main.time);
                double timeForAction = agents[i].timeForNextAction();

                if (time + timeForAction <= deadline) {
                    int vertexToMoveTo = agents[i].doAction();
                    agents[i].totalAgentRunningTime += timeForAction;
                    agents[i].numberOfActionsDone++;
                    printMoveInfoToFile(writer, i, vertexToMoveTo);
                    time += timeForAction; // update time 
                    PrintInfo.printNewWorldState(deadline - time);
                } else {
                    int vertexToMoveTo = agents[i].doAction(); // to where we try to go (get info for print message)
                    time += timeForAction; // update time
                    printNotEnoughTimeMessageToFile(writer, i, timeForAction, vertexToMoveTo);
                }
            } else {
                break;
            }
        }
    }

    private static void start(File outfile) {
        try {
            outfile.createNewFile();
            FileWriter writer = new FileWriter(outfile);
            boolean valid = true;
            while (valid) {
                performAgentsStrategies(writer);
                valid = time < deadline;
            }
            PrintInfo.printDeadlineMessage();
            printAgentsPerformanceToFile(writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkForPeopleInShelters() {
        // if there are people in vertices with shelters - delete them
        for (int i = 0; i < vertexMatrix.length; i++) {
            if (shelters[i])
                peopleToSave[i] = 0;
        }
    }

    public static void checkForAgentsWithPeopleInCar() {
        // if there are agents in vertices with people - add to people in car
        for (int i = 0; i < numOfAgents; i++) {
            agents[i].peopleInCar += peopleToSave[agents[i].position];
            peopleToSave[agents[i].position] = 0;
        }
    }

    public static void getHeaviestEdge() {
        double heavy = 0;
        for (int i = 0; i < vertexMatrix.length; i++)
            for (int j = i + 1; j < vertexMatrix.length; j++)
                heavy = Math.max(heavy, vertexMatrix[i][j]);
        mostHeavyEdge = heavy * deadline;

    }

}