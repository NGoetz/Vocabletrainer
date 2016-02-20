package vocabletrainer;

import java.util.Vector;

public class Vocable {
	private Vector<String>Lang1;//Every string contains a meaning in language 1/synonymes
	private Vector<String>Lang2;//Every string contains a possivle translation

	public Vocable(Vector<String>l1, Vector<String>l2) {
		Lang1=l1;
		Lang2=l2;
	}
public Vector<String> getLang1(){
	return Lang1;
}
public Vector<String> getLang2(){
	return Lang2;
}
public void setLang1(Vector<String> l){
	Lang1=l;
}
public void setLang2(Vector<String> l){
	Lang2=l;
}
public boolean check(int n, String s){//this function checks if the strong matches one possible translation
	boolean returner=false;
	if(n==0){
		for(int i=0; i<Lang1.size(); i++){
			if(s.equals(Lang1.elementAt(i)))
				returner=true;
		}
	}else{
		for(int i=0; i<Lang2.size(); i++){
			if(s.equals(Lang2.elementAt(i)))
				returner=true;
		}
	}
	return returner;
}
public void clear(){
	Lang1.removeAllElements();
	Lang2.removeAllElements();
}
}
//by Niklas Götz