
public class PrintInfo {

    // print edges weights
    private static void printGraph() {
        for (int i = 0; i < Main.vertexMatrix.length; i++) {
            for (int j = 0; j < Main.vertexMatrix.length; j++)
                System.out.print(Main.vertexMatrix[i][j] + " ");
            System.out.println();
        }
    }

    // print agents type, agents position, num of agents action, 
    // total time of agents action
    private static void printAgentsMovementInfo() {
        for (int i = 0; i < Main.numOfAgents; i++) {
            System.out.print("agent " + (i + 1) + " is from type " + Main.agents[i].agentType + " and ");
            System.out.println("in position " + (Main.agents[i].position + 1));
            System.out.print("agent " + (i + 1) + " do " + Main.agents[i].numberOfActionsDone);
            System.out.println(" actions in total time of " + Main.agents[i].totalAgentRunningTime);
        }
    }

    private static void printDeadlineInfo(double timeleft) {
        System.out.println("deadline will be in " + timeleft + " time units");
    }

    private static void printSheltersInfo() {
        System.out.print("shelters: ");
        for (int i = 0; i < Main.shelters.length; i++)
            System.out.print(Main.shelters[i] + " ");
        System.out.println();
    }

    private static void printPeopleInVertexPositionsInfo() {
        System.out.print("people to evacuate: ");
        for (int i = 0; i < Main.peopleToSave.length; i++)
            System.out.print(Main.peopleToSave[i] + " ");
        System.out.println();
    }

    private static void printPeopleInCarsInfo() {
        System.out.print("people in car: ");
        for (int i = 0; i < Main.numOfAgents; i++)
            System.out.print(Main.agents[i].peopleInCar + " ");
        System.out.println();
    }

    private static void printWorldState(double timeleft) {
        System.out.println("time: " + Main.time + "/" + Main.deadline);
        printGraph();
        printAgentsMovementInfo();
        printDeadlineInfo(timeleft);
        printSheltersInfo();
        printPeopleInVertexPositionsInfo();
        printPeopleInCarsInfo();
    }
    
    public static void printPrevWorldState(double timeleft) {
        System.out.println("The state of the world is:");
        printWorldState(timeleft);
    }
    
    public static void printNewWorldState(double timeleft) {
        System.out.println("The new state of the world is:");
        printWorldState(timeleft);
        System.out.println("");
    }
    
    public static void printDeadlineMessage() {
        System.out.println("__DEADLINE__");
    }
    
    

}
