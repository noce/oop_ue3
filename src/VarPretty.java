public class VarPretty extends LinePretty{
	
	private boolean tmpLongP, longPretty;
	private int tmpDepth, depth;
	
	public VarPretty(boolean mode, int d){
		super(d);
		longPretty = mode;
	}
	
	public VarPretty(int depth){
		this(true, depth);
	}
	
	public void setMode(boolean mode){
		tmpLongP = mode;
	}
	
	public void setDepth(int d){
		tmpDepth = d;
	}
	
	//post: änderungen an depth oder mode werden hier geltend gemacht
	public void reset(){
		longPretty = tmpLongP;
		depth = tmpDepth;
		super.reset();
	}

	//post: write open brace according to mode
	@Override
	protected void writeOpenBrace(StringBuilder buf) {
		if(longPretty){
			writeIndent(buf);
			buf.append('{');
		} else {
			buf.append(' ');
			buf.append("{");
		}
	}
}