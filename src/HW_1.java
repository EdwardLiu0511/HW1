import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class HW_1 {	
	public LinkedHashMap<String, Integer> dic_field=new LinkedHashMap<>();		
	public  void getTokens(String[] args) {
		if (args.length == 0)
			throw new RuntimeException("Please try to pass argument again!");
		File[] files= new File(args[0]).listFiles();		
		BufferedReader br = null;	
		int numDoc = 0;
		long start = System.currentTimeMillis();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.isFile()) {
				numDoc++;
				try {
					br = new BufferedReader(new FileReader(file));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				getWords(br);
         			}
		}		
		int tokenNum = 0;
		int wordsOnlyOne = 0;
		for (String key : dic_field.keySet()) {
			tokenNum = tokenNum + dic_field.get(key);
			if (dic_field.get(key) == 1)
				wordsOnlyOne++;
		}	
		long end = System.currentTimeMillis();	
		System.out.println("----------------------------------------------------------------");	
		System.out.println("Time required to acquire characteristic: "+ ((end - start) / 1000));
		System.out.println("Token's Number:" + tokenNum);
		System.out.println("Number of unique words:" + dic_field.keySet().size());
		System.out.println("Number of words occur only once: " + wordsOnlyOne);
		System.out.println("Number of doc scanned:" + numDoc);
		System.out.println("Average number of tokens per document:"+ (tokenNum / numDoc));					
		sortAndGetTop30();				
	}
	private void getWords(BufferedReader br) {
		String line=null;
		try {
			while ((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, " ");
				while (st.hasMoreTokens()) {
					String string = st.nextToken().trim();
					if (string.startsWith("<TEXT>")
							| string.startsWith("<TITLE>")
							| string.startsWith("<AUTHOR>"))
						break;
					if (string.startsWith("</TEXT>")
							| string.startsWith("</TITLE>")
							| string.startsWith("</AUTHOR>"))
						break;				
					Until.processToken(string, dic_field);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private void sortAndGetTop30() {
		//sort 
		List<Entry<String,Integer>> list=new ArrayList<>();
		for(Entry<String,Integer> entry:dic_field.entrySet()){
			list.add(entry);
		}
		Collections.sort(list,new Comparator<Entry<String,Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1,Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});		
		System.out.println("Most 30 frequency words: ");
		for(int i=0;i<30;i++){
			System.out.println(list.get(i).getKey()+":"+list.get(i).getValue());
		}
	}	
}
