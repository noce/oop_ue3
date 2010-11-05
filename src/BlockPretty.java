import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class  BlockPretty implements Pretty{
	private int lc; 
	private String transformed;
	private int openBraces = 0;
	
	protected String getOpenBrace() {
		return "{";
	}
	
	protected String getCloseBrace() {
		StringBuilder b = new StringBuilder("}");
		//TODO
		return b.toString();
	}
	
	// transforms a chunk of code without opening or closing braces
	protected String transformLineChunk(String in, int openBraces) {
		return in;
	}

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
		return transform(input);
	}
	
	public BlockPretty(int lineCount){
		lc = lineCount; 
	}
		
	//pre: source code is valid, in particular contains a closing brace for every opening brace
	public String transform(String s){
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
	
	public void reset(){}
}