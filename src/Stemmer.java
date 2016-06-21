
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

class Stemmer {
	public static void main(String[] args) throws FileNotFoundException {
		StemmerPorter sp = new StemmerPorter();				
		if (args.length == 0)
			throw new RuntimeException("Please try to pass argument again!");
		HW_1 hw=new HW_1();
		hw.getTokens(args);
		LinkedHashMap<String, Integer> dic_field_afterStem=new LinkedHashMap<>();	
		for (String word:hw.dic_field.keySet()){
			for (int i=0;i<hw.dic_field.get(word);i++){				
				char[] chs = new char[501];
				Until.makeStem(chs, sp, dic_field_afterStem, word);						
			}									
		}				
		int stemsNum = 0;
		int wordsOnlyOne = 0;
		for (String key : dic_field_afterStem.keySet()) {
			stemsNum = stemsNum + dic_field_afterStem.get(key);
			if (dic_field_afterStem.get(key) == 1)
				wordsOnlyOne++;
		}
		System.out.println("----------------------------------------------------------------");	
		System.out.println("Stem's Number:" + stemsNum);
		System.out.println("Number of unique stem:" + dic_field_afterStem.keySet().size());
		System.out.println("Number of stem occur only once: " + wordsOnlyOne);
		System.out.println("Average number of stems per document :"+ (stemsNum / 1400));		
		//sort 
		List<Entry<String,Integer>> list=new ArrayList<>();
		for(Entry<String,Integer> entry:dic_field_afterStem.entrySet()){
			list.add(entry);
		}
		Collections.sort(list,new Comparator<Entry<String,Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1,Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}

		});	

		System.out.println("Most 30 frequency stems: ");
		for(int i=0;i<30;i++){
			System.out.println(list.get(i).getKey()+":"+list.get(i).getValue());
		}
		    		    
	}
 }
	

