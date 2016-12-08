import java.util.*;
import java.io.*;

public class featureextractInstructions{
	public static void main(String[] argc){

		Set<String> ngrams = new HashSet<String>();
		BitSet features_vectors = null;
		BufferedReader is = null;
		String s;
		Integer asd=0;
		Integer h, featureIndex;
		Integer lenofVector;	
		String[] peritem;

		HashMap<Integer, Integer> binaryvector = new HashMap<Integer, Integer>();
		try{
			is = new BufferedReader(new FileReader(argc[0]));
			while((s=is.readLine())!=null){
				peritem = s.split(" ");
				for(String item: peritem){
					if(ngrams.contains(item)==true){
					ngrams.remove(item);
					}
					else{
						ngrams.add(item);
					}	
				}
				
			}
			lenofVector = ngrams.size();
			features_vectors = new BitSet(lenofVector);
			for(String item:ngrams){
				featureIndex=0;
				h = item.hashCode();
				featureIndex = (h % lenofVector + lenofVector) % lenofVector;
				features_vectors.set(featureIndex, true);

			}
			
			for(int i=0;i<lenofVector; i++){
				if(features_vectors.get(i)==true){
					binaryvector.put(i, 1);
				}
				else{
					binaryvector.put(i, 0);
				}
			
			}
			
		}
		catch(Exception e){

		}
		finally{
			for(Integer key:binaryvector.keySet()){
				System.out.print(binaryvector.get(key));
			}
			
		}

	}
}