/**
 * ECSE 414 - Homework Assignment 4, Problem 4
 * Michael Rabbat
 * McGill University
 * michael.rabbat@mcgill.ca
 */

import java.io.File;

/**
 * This class only implements the main method for Problem 4 of HW Assignment 4.
 * 
 * @author michaelrabbat
 * 
 */
public class Hw4Prob5b {

	/**
	 * Main method that takes one command line argument, which is the name of
	 * the network file. After loading the network from file, carry out distance
	 * vector updates until no more messages are transmitted.
	 * 
	 * @param args
	 *            should only have one entry which is the name of the network
	 *            file
	 */
	public static void main(String[] args) {
		// Check for the right number of arguments
		if (args.length != 0) {
			System.out.println("Warning: Hw4Prob5b doesn't use any arguments.  Arguments ignored");
			System.out.println("\tUsage: java Hw4Prob5b");
		}
		
		String fileName = "testNetwork1.txt";
		try {
			// Load the network from file
			File networkFile = new File(fileName);
			Network network = new Network(networkFile);
			
			// Print initial distance vectors and forwarding tables
			System.out.println("===========================================================");
			System.out.println("T=0 (Before any messages are exchanged)");
			network.printRoutingInfo();
			
			// Repeat doing distance vector updates until no more messages are passed
			int t;
			for (t=1; network.hasNewMessages(); t++) {
				System.out.println("===========================================================");
				System.out.println("T=" + t);
				network.deliverMessages();
				network.doDistanceVectorUpdates();
			}
			
			// Change the cost of an edge
			System.out.println("===========================================================");
			System.out.println("T=" + (t++) + " (Increasing cost of link x-y from 4 to 60)");
			Node x = network.getNode("X");
			Node y = network.getNode("Y");
			x.changeCostToNeighbor(y, 60);
			y.changeCostToNeighbor(x, 60);
			network.printRoutingInfo();
			
			// Repeat distance vector updates after changes until no more messages are passed
			for (; network.hasNewMessages(); t++) {
				System.out.println("===========================================================");
				System.out.println("T=" + t);
				network.deliverMessages();
				network.doDistanceVectorUpdates();
			}
			
			// Continue doing distance vector updates until no more messages are passed
		} catch (Exception e) {
			System.out.println("Error loading the network from file: " + fileName);
			e.printStackTrace();
		}
		
	}

}
