import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class CreateTree {
	private Node root;
	private int[] occurenceArray =  new int[256];
	private StringBuffer encodedString=new StringBuffer();
	private StringBuffer decodedString=new StringBuffer();
	private HashMap<String,String> translationtoBinaryMap = new HashMap<String, String>();
	private HashMap<String,String> translationtoStringMap = new HashMap<String, String>();
	public void parseInput(String input){
		for(int i = 0; i<input.length(); i++){
			occurenceArray[input.charAt(i)]++;
		}
	}
	
	public void createTree(String input){
		parseInput(input);
		ArrayList<Node> nodeVector = new ArrayList<Node>();
		for(int i = 0; i<occurenceArray.length;i++){
			if(occurenceArray[i]>0) nodeVector.add(new Node(String.valueOf((char)i),occurenceArray[i]));
		}
		while(nodeVector.size()>1){
			Collections.sort(nodeVector);
			Node lowestNode = nodeVector.remove(nodeVector.size()-1);
			Node secondLowestNode = nodeVector.remove(nodeVector.size()-1);
			Node combinedNode = new Node(lowestNode.getData()+secondLowestNode.getData(),lowestNode.getOccurenceCount()+secondLowestNode.getOccurenceCount());
			combinedNode.setLeft(secondLowestNode);
			combinedNode.setRight(lowestNode);
			nodeVector.add(combinedNode);
		}
		root = nodeVector.get(0); 

		setBinaryValues(root.getLeft(), "0");
		setBinaryValues(root.getRight(), "1");
//		breadthFirstTraversal(root);
		createTranslationTable(root);
//		displayMap(translationtoBinaryMap);
		translateToBinary(input);
		System.out.println(encodedString);
		translateBinaryToString(translationtoStringMap, encodedString.toString());
	}
	
	public void translateBinaryToString(HashMap<String,String> translationTable,String encodedString){
		System.out.println(translationTable.get("1000"));
		for(int i=1; i<=encodedString.length();i++){
			String tempString = encodedString.substring(0, i);
			if(translationTable.containsKey(tempString)){
				decodedString.append(translationTable.get(tempString));
				encodedString = encodedString.substring(i); i=1;
			}
//			count ++;
		}
		System.out.println("Decoded String = "+decodedString);
	}
	
	public void setBinaryValues(Node currentNode, String value){
		if(currentNode == null)  return;
		currentNode.setBinValue(value);
		if(currentNode.getLeft()!=null) setBinaryValues(currentNode.getLeft(), value+"0");
		if(currentNode.getRight()!=null) setBinaryValues(currentNode.getRight(), value+"1");
		
	}
	
	public void displayOccurenceArray(){
		for(int i=0;i<256;i++){
			if(occurenceArray[i]>0) System.out.println((char)i+":"+occurenceArray[i]);
		}
	}
	
	public void createTranslationTable(Node currentNode){
		if(currentNode == null) return;
		if((currentNode.getLeft() == null) && (currentNode.getRight() ==null)){
			translationtoBinaryMap.put(currentNode.getData(), currentNode.getBinValue());
			translationtoStringMap.put(currentNode.getBinValue(), currentNode.getData());
//			System.out.println(currentNode.getData()+":"+currentNode.getOccurenceCount()+":"+currentNode.getBinValue());
		}
		else{
			if(currentNode.getLeft() != null) createTranslationTable(currentNode.getLeft()); 
			if(currentNode.getRight() !=null) createTranslationTable(currentNode.getRight());
		}
	}
	
	public void translateToBinary(String input){
		for(int i=0;i<input.length();i++){
			System.out.print(input.charAt(i));
			encodedString.append(translationtoBinaryMap.get(String.valueOf(input.charAt(i))));
		}
	}
	public void breadthFirstTraversal(Node root){
		Queue<Node> bfQ = new LinkedList<Node>();
		if(root == null) return;
		bfQ.add(root);
		while(!bfQ.isEmpty()){
			Node currentNode = bfQ.poll();
			if(currentNode!=null){
				if(currentNode.getLeft()!=null) bfQ.add(currentNode.getLeft());
				if(currentNode.getRight()!=null) bfQ.add(currentNode.getRight());
				System.out.println(currentNode.getData()+":"+currentNode.getOccurenceCount());
			}
		}
		
	}
	
	public void displayMap(HashMap tableMap){
	      Set set = tableMap.entrySet();
	      Iterator i = set.iterator();
	      while(i.hasNext()) {
	         Map.Entry me = (Map.Entry)i.next();
	         System.out.print(me.getKey() + ": ");
	         System.out.println(me.getValue());
	      }
	}
	
	public static void main(String args[]){
		CreateTree ct = new CreateTree();
		ct.createTree("mississippi river");
	}
}
