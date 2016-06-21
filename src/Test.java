
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class Test {
	
	public static void main(String args[]){
		 String str="?.......";
		  str = str.replaceAll("[.|?]?", "");	
		  StemmerPorter sp=new StemmerPorter();
		  char [] w=new char[501];
		  LinkedHashMap<String,Integer> dic_field_afterStem=new LinkedHashMap<>();
		  String word="of";
		  String word2="this";
		  List<String> ls=new ArrayList<>();
		  ls.add(word);
		  ls.add(word2);
		  for (String word5:ls){
			  Until.makeStem(w, sp, dic_field_afterStem, word5);
			  
		  }		
		  System.out.print(dic_field_afterStem);
	}

}
