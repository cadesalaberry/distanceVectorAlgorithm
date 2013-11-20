/**
 * ECSE 414 - Homework Assignment 4, Problem 4
 * Michael Rabbat
 * McGill University
 * michael.rabbat@mcgillca
 * 24 October 2009
 */

import java.util.HashMap;

/**
 * @author michaelrabbat
 * 
 */
public class PoisonedReverseNode extends Node {
	public PoisonedReverseNode(String name) {
		super(name);
	}

	@Override
	protected void notifyNeighbors() {
		// Step 3: Fill in this method

		HashMap<Node, Float> hashMap = new HashMap<Node, Float>();

		for (Node neighbor : getNeighbors()) {
			for (Node destination : getDestinations()) {

				// Constructs messages according to the poisoned reverse rule.
				if (neighbor.equals(this.getNextHopTo(destination)))
					hashMap.put(destination, Float.POSITIVE_INFINITY);
				else {
					float cost = getCostToDestination(destination);
					hashMap.put(destination, cost);
				}
			}

			// Sends these messages to each neighbor.
			Message message = new Message(this, hashMap);
			neighbor.sendMessage(message);
		}
	}
}
