/*
 * after each closing brace 
 */
public class  BlockPretty implements Pretty {
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
	
	protected void writeCloseBrace(StringBuilder buf) {
		buf.append(replacement);
	}

	public String transform(String s){
		return s.replace("}", replacement);
	}

	public void reset(){
		//do nothing
	}


}