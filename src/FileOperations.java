import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class FileOperations {
	public static String readFile(String filePath){

		BufferedReader br = null;
		StringBuffer fileContent = new StringBuffer();
		try {
			br = new BufferedReader(new FileReader(filePath));
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				fileContent.append(currentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return fileContent.toString();

	}
	
public static void WriteFile(int[] myByteArray){
	FileOutputStream fop = null;
	File file;
	BufferedOutputStream bos = null;

	try {

		file = new File("paathname_new.txt");
		fop = new FileOutputStream(file);
		bos = new BufferedOutputStream(fop);

		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		// get the content in bytes
		for(int i=0;i<myByteArray.length;i++)
			bos.write(myByteArray[i]);
        bos.flush();
		fop.close();

		System.out.println("Done");

	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			if (fop != null) {
				fop.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//	FileOutputStream fos;
//	try {
//		File file = new File("pathname_new.txt");
//		if (!file.exists()) {
//			file.createNewFile();
//		}
//		FileWriter fw = new FileWriter(file.getAbsoluteFile());
//		BufferedWriter bw = new BufferedWriter(fw);
//		for(int tempInt:myByteArray)
//			bw.write((char)tempInt);
//		bw.close();
//
//	} catch (FileNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}

//	FileOutputStream fos;
//	try {
//		fos = new FileOutputStream("pathname.txt");
//		for(int temp:myByteArray)
//			fos.write(temp);
//		fos.close();
//
//	} catch (FileNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
}
	
public static void WriteKey(HashMap<String,String> translationTable){
//	FileOutputStream fos;
//	CreateTree ct = new CreateTree();
//	try {
//		fos = new FileOutputStream("key.txt");
//		Iterator mapIterator = translationTable.entrySet().iterator();
//		while(mapIterator.hasNext()){
//	        Map.Entry pair = (Map.Entry)mapIterator.next();
//////	        byte value = (byte)ct.convertBinarytoBytes(pair.getValue().toString())[0];
////	        fos.write(pair.getValue());
////	        fos.write(" = ".getBytes());
////	        fos.write(String.valueOf(pair.getKey()).getBytes());
////	        fos.write('\n');
//	        bw.write(String.valueOf((char)value) + " = " + pair.getValue());
//	        bw.newLine();
//		}
//		
////		for(int temp:myByteArray)
////			fos.write(temp);
//		fos.close();
//
//	} catch (FileNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}

	CreateTree ct = new CreateTree();
	try {
		File file = new File("key.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		Iterator mapIterator = translationTable.entrySet().iterator();
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		while(mapIterator.hasNext()){
	        Map.Entry pair = (Map.Entry)mapIterator.next();
//	        int value = ct.convertBinarytoBytes(pair.getValue().toString())[0];
	        bw.write(pair.getValue() + " = " + pair.getKey());
	        bw.newLine();
		}
		bw.close();

	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static void WriteDecodedFile(String content){
	System.out.println("Decoded COntent = "+content);
	FileOutputStream fos;
	try {
		File file = new File("decoded.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
		bw.close();

	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static HashMap<String,String> readKey(String fileName){
	 String  thisLine = null;
	 HashMap<String,String> translationToString = new HashMap<String,String>();
		      try{
		         // open input stream test.txt for reading purpose.
		         BufferedReader br = new BufferedReader((new FileReader(fileName)));
		         while ((thisLine = br.readLine()) != null) {
		        	translationToString.put(thisLine.split(" = ")[0],thisLine.split(" = ")[1]);
//		            System.out.println(thisLine);
		         }       
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		      return translationToString;
}

public static int[] readBinaryFile(String path){
	 File file = new File("paathname_new.txt");
     BufferedInputStream bin = null;
     ArrayList<Integer> byteList = new ArrayList<Integer>();
     
     try
     {
             FileInputStream fin = new FileInputStream(file);
             bin = new BufferedInputStream(fin);
             while( bin.available() > 0 ){
            	 byteList.add(bin.read());
//                     System.out.print((char)bin.read());
             }
     }
     catch(FileNotFoundException e)
     {
             System.out.println("File not found" + e);
     }
     catch(IOException ioe)
     {
             System.out.println("Exception while reading the file " + ioe); 
     }
     finally
     {
             try{
                     if(bin != null)
                             bin.close();
                            
             }catch(IOException ioe)
             {
                     System.out.println("Error while closing the stream : " + ioe);
             }
     }
//     byte[] byteArray = ;
     int[] byteArray = new int[byteList.size()];
     for(int i=0;i<byteList.size();i++){
    	 byteArray[i] = byteList.get(i);
     }
     return (byteArray);
}
public static void main(String args[]){

		String path = new File("SampleText.txt").getAbsolutePath();
//		String path = new File("pathname.txt").getAbsolutePath();
		System.out.println("File = "+FileOperations.readFile(path));		
		CreateTree ct = new CreateTree();
		String encodedString = ct.encode(FileOperations.readFile(path));
		int byteString[] = ct.convertBinarytoBytes(encodedString);
//		ct.displayMap(ct.getTranslationtoBinaryMap());
		FileOperations.WriteFile(byteString);
		FileOperations.WriteKey(ct.getTranslationtoBinaryMap());
		HashMap<String,String> translationToString = FileOperations.readKey("key.txt");
//		ct.displayMap(translationToString);
		path = new File("paathname_new.txt").getAbsolutePath();		
		WriteDecodedFile(ct.decode(translationToString,FileOperations.readBinaryFile(path)));
//		System.out.println("Encoded String = "+ct.encode(FileOperations.readFile(path)));
	}
	
}
