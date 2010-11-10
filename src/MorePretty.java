public class MorePretty extends VarPretty{
	
	public MorePretty(boolean longP, int i){
		super(longP, i);
	}
	
	//erzeugt longPretty
	public MorePretty(){
		super(true, 0);
	}
	
	//gibt als ergebnis die anz d noch offenen klammerebenen zurueck
	public int open(){
		return openBraces;
	}
}