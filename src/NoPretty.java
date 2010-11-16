public class NoPretty extends BlockPretty{
	
	public NoPretty(){
		super(0);
	}
	
	//post: liefert kopie von s
	public String transform(String s){
		return new String(s);
	}
	
	//do nothing, no state
	public void reset(){
	}
}