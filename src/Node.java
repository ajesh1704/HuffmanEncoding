import java.util.ArrayList;
import java.util.Collections;


public class Node implements Comparable {
	private String data;
	private Node left;
	private Node right;
	private int occurenceCount;
	private String binValue;
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public int getOccurenceCount() {
		return occurenceCount;
	}
	public void setOccurenceCount(int occurenceCount) {
		this.occurenceCount = occurenceCount;
	}
	public String getBinValue() {
		return binValue;
	}
	public void setBinValue(String binValue) {
		this.binValue = binValue;
	}
	
	public Node(String data, int occurenceCount){
		this.data = data;
		this.occurenceCount = occurenceCount;
		this.left = this.right = null;
	}
	@Override
	public int compareTo(Object compareNode) {
		return (((Node)compareNode).getOccurenceCount() - (this.getOccurenceCount()));
		// TODO Auto-generated method stub
//		return 0;
	}
	
	public static void main(String args[]){
		ArrayList<Node> nodeList = new ArrayList<Node>();
		nodeList.add(new Node("a",3));
		nodeList.add(new Node("b",4));
		nodeList.add(new Node("c",5));
		Collections.sort(nodeList);
		for(Node currentNode : nodeList){
			System.out.println(currentNode.data);
		}
		
	}
}
