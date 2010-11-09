public class MorePretty extends VarPretty{
	
	public MorePretty(int i){
		super("", i);
	}
	
	public MorePretty(){
		super("", 0);
	}
	
	//gibt als ergebnis die anz d noch offenen klammerebenen zurueck
	public int open(){
		return openBraces;
	}
}