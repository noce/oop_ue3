import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class  BlockPretty implements Pretty{
	private int lc; 
//	private String transformed;
//	private int openBraces = 0;
//	private int middle = 0;
	protected static int pos = 0;
	protected static int depth = 0;
	
	private StringBuffer transf  = new StringBuffer();
//	private StringBuffer orig = new StringBuffer();
	private String remaining;
	
	
	protected String getOpenBrace() {
		return "{";
	}
	
	protected String getCloseBrace() {
		StringBuilder b = new StringBuilder("}");
		for(int i=0; i<lc;i++){//leere Zeilen je nach vorgabe
			b.append('\n');
		}
		return b.toString();
	}
	
	//returns the next position to put the code in the output String (quasi "die Mitte")
	private int getPos(){
		if(transf.toString().contains("}")) return transf.indexOf("}");
		else if(transf.length() == 0) return 0;
		else return transf.length();
	}
	
	//cuts the code around the current {} out (including {})
	//and adds them to the output Stringbuilder
	protected String undressToBrace(String in) {
		int begin = in.indexOf("{");

		
//		System.out.println(in);
		if(!in.startsWith("{")){
			transf.insert(this.getPos(),in.substring(0, begin));//wenns nicht mit { beginnt
			in = in.substring(begin+1);
		}else{
			in = in.substring(1);
		}
		int end = in.lastIndexOf("}");
		if(!in.endsWith("}")){
			transf.insert(this.getPos(), in.substring(end));//nach derKlammer
			in = in.substring(0, end-1);
		}else{
			in = in.substring(0, end-1);
		}

		return in;
	}

	public BlockPretty(int lineCount){
		lc = lineCount; 
	}
	
	//pre: source code is valid, in particular contains a closing brace for every opening brace
	
	//also transform soll einfach nur undress ausführen, dass die der Code Block
	//bis auf die äusseren klammern trimmt, und transform dann die klammern actions macht
	//und eben rekursiv von aussen nach innen in den Code geht
	public String transform(String s){
		remaining = this.undressToBrace(s);
//		System.out.println("tansf:"+transf.toString());
//		System.out.println("remaining"+remaining); 
		while(remaining.contains("{") && remaining.contains("}")){
			transf.insert(this.getPos(),this.getOpenBrace());//Open Brache Actions
			transf.insert(this.getPos(),this.getCloseBrace());//Close Brace Actions
			
			this.transform(remaining);//recursion

		}
		transf.insert(this.getPos(),'\n'+remaining);
		return transf.toString();
	}
	
/*
		StringBuilder ret = new StringBuilder();
		if (s.startsWith("{") && s.endsWith("}")) {
			try {
				openBraces++;
				ret.append(this.getOpenBrace());
				ret.append(this.getBraceContent(s.substring(1, s.length() - 1), openBraces));
				ret.append(this.getCloseBrace());
				for(int i=0; i<lc;i++){
					ret.append('\n');
				}
			} finally {
				openBraces --;
			}
		} else {
			
		}
		return ret.toString();
	}
*/
	
	
	
	/*
	Pattern brace = Pattern.compile("\\{|\\}");
	
	//pre: gets source code String between an opening and closing brace, before inner braces are replaced
	//post: returns fully tranformed String
	protected String getBraceContent(String input, int openBraces){
		int pos = 0;
		do {
			Matcher match = brace.matcher(input);
		//	pos = match.pos();
			// if (match.openBrace(), closeBrace()) --> openBraces 
			
			//klamernpaare finden die auf der selben ebene sind 
		} while (true); // TODO not end
	//	return transform(input);
	}
*/	
	
	public void reset(){}
}