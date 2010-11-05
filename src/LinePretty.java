//haut gleich alle leerzeilen bzw. Zeilen umbrüche raus
//Ersetzungsmap: key: das zu ersetzende Zeichen, value: die einzufügenden Zeichen
//beeinhaltet die Logik zum ersetzen
//Mehode: addReplacement(regex pattern, String replacement)

import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;

abstract class LinePretty extends BlockPretty {
	LinePretty(int afterBlockLines) {
		super(afterBlockLines);
	}
	
	LinePretty() {
		super(0);
	}

	//post: returns String with whitespace stripped from lines, 
	//      then all newlines removed, then all ';' followed by a single newline,
	//      then calls BlockPretty transform for replacements of braces
	
	protected String getBraceContent(String input, int openBraces){
		return input;
	}
	
	public void reset() {
	}
}