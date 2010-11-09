public class VarPretty extends LinePretty{
	
	//longPretty: mode = true;
	private boolean tmpLongP, longPretty;
	private int tmpDepth, depth;
	
	public VarPretty(boolean mode, int d){
		super(d);
		longPretty = mode;
	}
	
	public VarPretty(boolean mode){
		super(0);
		longPretty = mode;
	}
	
	public void setMode(boolean mode){
		tmpLongP = mode;
	}
	
	public void setDepth(int d){
		tmpDepth = d;
	}
	
	//aenderungen werden erst nach reset geltend gemacht
	public void reset(){
		longPretty = tmpLongP;
		depth = tmpDepth;
	}

	@Override
	protected void writeOpenBrace(StringBuilder buf) {
		// TODO Auto-generated method stub
		if(longPretty){
			writeIndent(buf);
			buf.append('{');
		} else {
			buf.append(afterTokenWhitespace);
			buf.append("{");
		}
	}
}