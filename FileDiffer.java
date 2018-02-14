package testPack;

import java.util.*;
import javafx.util.*;

class DiscrepancySet {
	
	public Vector<Vector<String>> foundleftSet;
	public Vector<Vector<String>> foundrightSet;
	public Vector<Vector<Integer>> discrepancy;
	
	public DiscrepancySet() {
		this.foundleftSet = new Vector<>(); ;
		this.foundrightSet = new Vector<>();
		this.discrepancy = new Vector<>();
	}
	
	public void add(Vector<String> l, Vector<String> r, Vector<Integer> d) {
		foundleftSet.add(l);
		foundrightSet.add(r);
		discrepancy.add(d);
	}
}

public class FirstClass {

	public static void main(String[] args) {
		
	}
	
	public Pair<Integer, Vector<Integer>> compareVectors(Vector<String> l, Vector<String> r) {
		
		Vector<Integer> indices = new Vector<Integer>();
		
		int i = 0, j = 0, rank = 0, len = l.size() < r.size() ? l.size() : r.size();
		
		for(; i < len; i++) {
			if(l.get(i).trim().compareTo(r.get(i).trim()) != 0) {
				rank++;
				indices.add(i);
			}
		}
		
		j = i;
		while (j < l.size()) {
			indices.add(j++);
		}
		
		while(i < r.size()) {
			indices.add(i++);
		}
		
		return new Pair<>(rank, indices);
	}
	
	public void compareDataSet(Vector<Vector<String>> leftData, Vector<Vector<String>> rightData) {
		
		DiscrepancySet D = new DiscrepancySet();
		
		while (leftData.size() > 0) {
		
			Vector<String> l = new Vector<>(leftData.firstElement());
			Vector<String> r = new Vector<>();
			Vector<Integer> indices = new Vector<Integer>();
			
			leftData.removeElementAt(0);
			
			int len = rightData.size();
			Integer rank = Integer.MAX_VALUE;
			int lessDisc = 0;
			
			for(int i = 0; i < len; i++) {
				Vector<String> temp = rightData.get(i);
				Pair<Integer, Vector<Integer>> result = compareVectors(l, temp);
				if(result.getKey() < rank) {
					rank = result.getKey();
					lessDisc = i;
					r.clear();
					r.addAll(temp);
					indices.clear();
					indices.addAll(result.getValue());
				}
			}
			
			rightData.remove(lessDisc);
			
			D.add(l, r, indices);
			
			l.clear();
			r.clear();
			indices.clear();
		}
	}

}
