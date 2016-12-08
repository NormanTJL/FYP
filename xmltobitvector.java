import java.util.*;
import java.io.*;


public class xmltobitvector{

	public static void main(String args[]){
		BufferedReader is = null;
		String s;
		String perline[];
		ArrayList<String> binaryvector = new ArrayList<String>();
		int outputvector[];
		ArrayList<String> usedpermissions = new ArrayList<String>();
		try{

			is = new BufferedReader(new FileReader(args[0]));
			while((s=is.readLine())!=null){
				if(s.contains("name=")){
					perline = s.split("name=");
					usedpermissions.add(perline[1].substring(1, perline[1].length()-1));
				
				}
			}

			is = new BufferedReader(new FileReader("permissions"));
			while((s=is.readLine())!=null){
				if(!binaryvector.contains(s)){
					binaryvector.add(s);	
				}
				
			}
			outputvector = new int[binaryvector.size()];
			for(int i=0;i<outputvector.length;i++){
				if(usedpermissions.contains(binaryvector.get(i))) {
					outputvector[i] = 1;
				}
				else{
					outputvector[i] = 0;
				}
			}
			for(int i=0;i<outputvector.length;i++){
				System.out.print(outputvector[i]);	
			}
		}
		catch(Exception e){

		}
	}
}