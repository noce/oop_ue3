/* after each closing brace insert br newlines
 */
public class  BlockPretty implements Pretty {
	//inv: newline appendage constant, so precalc replacement
	private String replacement;

	//pre: i >= 0
	public BlockPretty(int br){
		if(br < 0){
			throw new IllegalArgumentException("invalid param");
		}
		StringBuilder buf = new StringBuilder("}");
		for(; br > 0; br--){
			buf.append('\n');
		}
		replacement = buf.toString();
	}
	
	//post: see first line :)
	protected void writeCloseBrace(StringBuilder buf) {
		buf.append(replacement);
	}

	public String transform(String s){
		return s.replace("}", replacement);
	}

	// do nothing, no state
	public void reset(){
	}


}