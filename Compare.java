import java.util.*;
import java.io.*;

public class Compare{
	public static void main(String[] argc){

	Set<String> ngrams = new HashSet<String>();
		BitSet features_vectors = null;
		BufferedReader is = null;
		char s;
		ArrayList<String> linesOfApkNgram = new ArrayList<String>();
		ArrayList<String> linesOfApkNgram1 = new ArrayList<String>();
		
		int asd=0;
		try{
			is = new BufferedReader(new FileReader(argc[0]));
			while((asd=is.read()) != -1) {
				linesOfApkNgram.add(String.valueOf((char)asd));
			}
			is.close();
			is = new BufferedReader(new FileReader(argc[1]));
			while((asd=is.read())!= -1){
				linesOfApkNgram1.add(String.valueOf((char)asd));
				
			}
			is.close();
			int countofapkngram=0, countofapkngramOverall=0;
			int maxapkngram = linesOfApkNgram.size() + linesOfApkNgram1.size();
			int j=0;

			if(linesOfApkNgram.size()<(maxapkngram/2)){
				j=linesOfApkNgram.size();
			}
			else{
				j=linesOfApkNgram1.size();

			}
			int z=0;
			for(int i=0;i<j;i++){
				if(linesOfApkNgram.get(i).equals(linesOfApkNgram1.get(i)) && linesOfApkNgram.get(i).equals("1")) {
					countofapkngram++;
				}
				if(linesOfApkNgram.get(i).equals("1") || linesOfApkNgram1.get(i).equals("1")){
					z++;
				}
				
			}		
			System.out.println("J:"+j + " - Z:" + z + " - Countofapkngram:" + countofapkngram);
			System.out.println((double)countofapkngram/(double)z);			

		}
		catch(Exception e){

		}
	}
}