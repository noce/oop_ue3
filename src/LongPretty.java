//addreplacement() wird von den subklassen aufgerufen
//wir einfach f�r jedes zu ersetzende zeichen aufgerufen

public class LongPretty extends LinePretty{
	
	public LongPretty(int depth){
		super(depth);
	}

	@Override
	protected void writeOpenBrace(StringBuilder buf) {
		writeIndent(buf);
		buf.append('{');
	}
}