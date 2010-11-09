//hab die jez LongPretty erweitern lassen
public class ShortPretty extends LongPretty{
	
	public ShortPretty(int p, int d){
		super(p,d);
	}
	
	protected String getOpenBrace() {
		StringBuilder b = new StringBuilder("}");
		for(int i=0; i<pos; i++){
			b.append('\t');
		}
		pos += depth;
		b.append('{');
		b.append('\n');
		
		return b.toString();
	}
	
}