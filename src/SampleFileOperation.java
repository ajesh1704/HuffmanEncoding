import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class SampleFileOperation {
	public static void main(String args[]){
		FileOutputStream fop = null;
		File file;
		BufferedOutputStream bos = null;
		byte myByteArray[] =  new byte[256];
		for(int i=-128;i<=127;i++){
			myByteArray[i+128] = (byte)i;
		}

		try {

			file = new File("paathname_new.txt");
			fop = new FileOutputStream(file);
			bos = new BufferedOutputStream(fop);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
//			for(int i=0;i<=255;i++){
				bos.write(228);
				bos.write(163);
				bos.write(64);
				bos.write(109);
//			}
//	        bos.write(myByteArray, 0, myByteArray.length);
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

	}
}
