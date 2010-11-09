/*
 * after each closing brace 
 */
public class  BlockPretty implements Pretty{

	private int breaks;

	//pre: i >= 0
	public BlockPretty(int br){
		if(br < 0){
			throw new IllegalArgumentException("invalid param");
		}
		breaks = br;
	}

	public String transform(String s){
		String transformed = new String(s);
		String replacement = "}";
		for(int i = breaks; i > 0; i--){
			replacement += "\n";
		}
		transformed.replace("}", replacement);
		return transformed;
	}

	public void reset(){
		//do nothing
	}


}