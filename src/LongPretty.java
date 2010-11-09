//addreplacement() wird von den subklassen aufgerufen
//wir einfach für jedes zu ersetzende zeichen aufgerufen

public class LongPretty extends LinePretty{
	
	public LongPretty(int p, int d){
		super(0);
		depth = d;	
	}
	
	protected String getOpenBrace() {
		StringBuilder b = new StringBuilder("}");
		b.append('\n');
		for(int i=0; i<pos; i++){
			b.append('\t');
		}
		pos += depth;
		b.append('{');
		b.append('\n');
		
		return b.toString();
	}
	
	protected String getCloseBrace() {
		StringBuilder b = new StringBuilder("}");
		b.append('\n');
		for(int i=0; i<pos; i++){
			b.append('\t');
		}
		pos -= depth;
		b.append('{');
		
		return b.toString();
	}
}