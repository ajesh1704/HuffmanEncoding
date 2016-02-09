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
	public HashMap<String, String> getTranslationtoBinaryMap() {
		return translationtoBinaryMap;
	}

	public void setTranslationtoBinaryMap(
			HashMap<String, String> translationtoBinaryMap) {
		this.translationtoBinaryMap = translationtoBinaryMap;
	}

	public HashMap<String, String> getTranslationtoStringMap() {
		return translationtoStringMap;
	}

	public void setTranslationtoStringMap(
			HashMap<String, String> translationtoStringMap) {
		this.translationtoStringMap = translationtoStringMap;
	}

	private HashMap<String,String> translationtoStringMap = new HashMap<String, String>();
	private void parseInput(String input){
		for(int i = 0; i<input.length(); i++){
			occurenceArray[input.charAt(i)]++;
		}
	}
	
//public String decode(String encodedInput, HashMap<String,String>binToStringTable){
//	
//}
	public String encode(String input){
		parseInput(input);
		createTree();
		setBinaryValues(root.getLeft(), "0");
		setBinaryValues(root.getRight(), "1");
//		breadthFirstTraversal(root);
		createTranslationTable(root);
//		displayMap(translationtoBinaryMap);
		translateToBinary(input);
		System.out.println(encodedString);
		return encodedString.toString();
//		translateBinaryToString(translationtoStringMap, encodedString.toString());
	}
	
//	public String decode(HashMap<String,String> String encodedString)
	
	
	private void createTree(){
		
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
	}
	
	public String decode(HashMap<String,String> translationTable,int[] byteArray){
//		System.out.println(translationTable.get("1000"));
		String binaryEncodedString = new String(); 
		String tempBinaryString;
		for(int i=0;i<byteArray.length;i++){
//			char tempChar = encodedString.charAt(i);
//			int tempInt = (int)tempChar;
			tempBinaryString = Integer.toBinaryString(byteArray[i]);
			int length = tempBinaryString.length();
			for(int j=0;j<8-length;j++){
				tempBinaryString = "0"+tempBinaryString;
			}
			binaryEncodedString+=tempBinaryString;
		}
		System.out.println("Binary String = "+binaryEncodedString);
//		String encodedString = Integer.toBinaryString(encodedString);
		for(int i=1; i<=binaryEncodedString.length();i++){
			String tempString = binaryEncodedString.substring(0, i);
			if(translationTable.containsKey(tempString)){
				decodedString.append(translationTable.get(tempString));
				binaryEncodedString = binaryEncodedString.substring(i); i=1;
			}
//			count ++;
		}
//		System.out.println("Decoded String = "+decodedString);
		return decodedString.toString();
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
		System.out.println("inside encode = "+encodedString);
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
	
	public int[] convertBinarytoBytes(String binaryString){
		int arrayLength = (int) Math.ceil((float)binaryString.length()/8);
		int[] encodedBytes = new int[arrayLength];
		StringBuffer asciiString = new StringBuffer();
		int index = 0;
		while(index < binaryString.length()){
			String currentBinary = binaryString.substring(index, Math.min(index + 8,binaryString.length()));
			encodedBytes[index/8] = (Integer.parseInt(currentBinary, 2));
//			System.out.println(encodedBytes[index/8]);
//			asciiString.append((char)Integer.parseInt(currentBinary, 2));
			index +=8;
		}
		return encodedBytes;
	}
	
	public String convertAsciiToBinary(String asciiString){
		StringBuffer binaryString = new StringBuffer();
		byte[] bytes = asciiString.getBytes();
		int index = 0;
		for(byte b:bytes){
		     int val = b;
		     binaryString.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
		}
		return binaryString.toString();
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
		String binaryString  = ct.encode("mississippi river");
		int[] encodedBytes = ct.convertBinarytoBytes(binaryString);
		for(int b:encodedBytes){
			System.out.print(b  +" ");
		}
		System.out.println();
//		System.out.println("ascii = " +asciiString);
//		System.out.println(ct.convertAsciiToBinary(asciiString));	
	}
}
