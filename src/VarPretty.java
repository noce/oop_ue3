public class VarPretty extends LinePretty{
	
	//short- od longPretty
	private String tmpMode, mode;
	private int tmpDepth, depth;
	
	public VarPretty(String m, int d){
		super(d);
		mode = m;
	}
	
	public VarPretty(String m){
		super(0);
		mode = m;
	}
	
	public void setMode(String m){
		tmpMode = m;
	}
	
	public void setDepth(int d){
		tmpDepth = d;
	}
	
	//aenderungen werden erst nach reset geltend gemacht
	public void reset(){
		mode = tmpMode;
		depth = tmpDepth;
	}

	@Override
	protected void writeOpenBrace(StringBuilder buf) {
		// TODO Auto-generated method stub
		
	}
}