public class MorePretty extends VarPretty{
	
	public MorePretty(boolean longP, int depth){
		super(longP, depth);
	}
	
	//erzeugt longPretty
	public MorePretty(int depth){
		super(true, depth);
	}
	
	//gibt als ergebnis die anz d noch offenen klammerebenen zurueck
	public int open(){
		return openBraces;
	}
}