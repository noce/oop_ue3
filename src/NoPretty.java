public class NoPretty implements Pretty{
	
	//liefert kopie von s
	public String transform(String s){
		return new String(s);
	}
	
	public void reset(){
		//do nothing
	}
}