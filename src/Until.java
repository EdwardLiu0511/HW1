
import java.util.LinkedHashMap;
public class Until {
	public static void processToken(String str,
			LinkedHashMap<String, Integer> dic) {
		    str = str.toLowerCase()
		    		 .replaceAll("[0-9]+", "")
		    		 .replaceAll("[.|,|*|%|@|+|$|`|~|(|)]", "")
		    		 .replaceAll("<[^>]+>", "")
		    		 .replaceAll("[^\\w\\s-'.!;]+", "");

		if (str.endsWith("'s")) {
			str = str.replace("'s", "");
			if (str.length()>1)
		    	putToDic(str, dic);

		}  else if (str.contains("_")) {
			String[] newOnes = str.split("_");
			for (String newOne : newOnes) {
				if (newOne.length()>1)
				  putToDic(newOne, dic);
			}
		} else if (str.contains("-")) {
			String[] newOnes = str.split("-");
			for (String newOne : newOnes) {
				if (newOne.length()>1)
				  putToDic(newOne, dic);
			}
		} else if (str.contains("'")){
			String[] newOnes = str.split("'");
			for (String newOne : newOnes) {
				if (newOne.length()>1)
				  putToDic(newOne, dic);
			}
		}			
		 else {
			if (str.length()>1)
			  putToDic(str, dic);
		}
	}
	public static  void putToDic(String str, LinkedHashMap<String, Integer> dic) {
		if (str.length() > 0) {
			if (!dic.containsKey(str)) {
				dic.put(str, 1);
			} else {
				dic.put(str, dic.get(str) + 1);
			}
		}
	}
	public static void makeStem(char[] w, StemmerPorter sp,LinkedHashMap<String, Integer> dic_field_afterStem, String word) {
		while (true)
			{   
			int index=0;
			int ch = word.charAt(index);
			if (Character.isLetter((char) ch)) {
					int j = 0;
					while (true) {
						ch = Character.toLowerCase((char) ch);
						w[j] = (char) ch;
						if (j < 500)
							j++;
						
						index++;
						if (index<word.length())							
						   ch = word.charAt(index);
						if (index==word.length()) {						
							for (int c = 0; c < j; c++)
							     sp.add(w[c]);							
							sp.stem();
							{
								String u;
								u = sp.toString();
								Until.putToDic(u,dic_field_afterStem);
							}
							break;
						}
					}
					break;
				}
			else
			     break;
		}
	}
}
