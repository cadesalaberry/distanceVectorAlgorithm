/**
 * ECSE 414 - Homework Assignment 4, Problem 5
 * Michael Rabbat
 * McGill University
 * michael.rabbat@mcgillca
 */

import java.io.File;

/**
 * This class only implements the main method for Problem 4 of HW Assignment 4.
 * 
 * @author michaelrabbat
 * 
 */
public class Hw4Prob5a {

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
		if (args.length != 1) {
			System.out.println("Usage: java Hw4Prob5a networkFile");
			System.exit(1);
		}
		
		try {
			// Load the network from file
			File networkFile = new File(args[0]);
			Network network = new Network(networkFile);
			
			// Print initial distance vectors and forwarding tables
			System.out.println("===========================================================");
			System.out.println("T=0 (Before any messages are exchanged)");
			network.printRoutingInfo();
			
			// Repeat doing distance vector updates until no more messages are passed
			for (int t=1; network.hasNewMessages(); t++) {
				System.out.println("===========================================================");
				System.out.println("T=" + t);
				network.deliverMessages();
				network.doDistanceVectorUpdates();
			}
		} catch (Exception e) {
			System.out.println("Error loading the network from file: " + args[0]);
			e.printStackTrace();
		}
		
	}

}
