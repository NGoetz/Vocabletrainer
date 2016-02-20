package vocabletrainer;
//menu
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Vector;

public class Vocabletrainer {
	private Vector<Vocable>knowledge;

	public Vocabletrainer(Vector<Vocable>k) {
		knowledge=k;
	}
	public Vector<Vocable> getKnowledge(){
		return knowledge;
	}
	public void setKnowledge(Vector<Vocable> k){
		knowledge=k;
	}
	

	public void add (Vector<String>s1, Vector<String>s2, String Lan){//adds the vocable to the knowledge-base and saves them in file
		Vocable v=new Vocable(s1, s2);
		knowledge.add(v);
		ReadWrite.save(Lan, knowledge);
	}
	public void erase (int k,String Lan){//the same as above, but erases instead of adding
		if(!knowledge.isEmpty()){
		knowledge.removeElementAt(k-1);
		ReadWrite.save(Lan, knowledge);
		}
	}
	public void training (){//calls 10 random vocables in random language and asks them until answer was correct
		Random r=new Random();
		Vector<Integer> z= new Vector<Integer>();
		for(int i=0; i<10;i++){
			z.addElement(r.nextInt(knowledge.size()));
		}
		System.out.println("We start the training. Always give only one meaning of the given item!");
		try{while(!z.isEmpty()){
			int lan=r.nextInt(2);
			int voc=r.nextInt(z.size());
			InputStreamReader isr =new InputStreamReader(System.in);
			BufferedReader bur = new BufferedReader(isr);
			System.out.println("What means :");
			if(lan==0){
				for(int i=0; i<knowledge.elementAt(z.elementAt(voc)).getLang1().size();i++){//prints out all meanings of the word in one language
					System.out.print(knowledge.elementAt(z.elementAt(voc)).getLang1().elementAt(i)+" ");
				}

			}else if(lan==1){
				for(int i=0; i<knowledge.elementAt(z.elementAt(voc)).getLang2().size();i++){
					System.out.print(knowledge.elementAt(z.elementAt(voc)).getLang2().elementAt(i)+" ");
				}
			}
				System.out.println(" ?");
				String sLine = bur.readLine();
				if(knowledge.elementAt(z.elementAt(voc)).check(1-lan, sLine)){//calls check for the other language
					System.out.println("Correct!");
					z.removeElementAt(voc);//removes correct words from z - if z is empty, the training is over
				}else{
					System.out.println("Wrong!");
				}
			}
		System.out.println("You successfully ended the session");
			}catch(IOException eIO){
			System.out.println("Encountered error while trying to read input");
		}
	}
	public void help(){//just prints out hints
		System.out.println("H: Help");
		System.out.println("A: Add new vocables");
		System.out.println("P: Print out all your vocables");
		System.out.println("E: Erase existing vocables");
		System.out.println("T: Start training and get 10 random vocables tested");
		System.out.println("X: End session");
	}
	
	public static void main (String []args){//try it with Englisch.dat!
	System.out.println("Welcome to your personal vocable trainer. Please tell me which language you want to learn:");//gets language and, more important, file name
	InputStreamReader isr =new InputStreamReader(System.in);
	BufferedReader bur = new BufferedReader(isr);
	try{
		String language = bur.readLine();
		Vocabletrainer v=new Vocabletrainer(ReadWrite.getKnow(language));
		System.out.println("Let's start. You may enter:");
		v.help();
		boolean exit=false;
		while(!exit){
			String input=bur.readLine();
			if(input.equals("X")){
				exit=true;
			}
			if(input.equals("H")||input.equals("help")){
				v.help();
			}
			if(input.equals("P")){
				ReadWrite.print(v.knowledge);
			}
			if(input.equals("A")){//the user enters meanings, which are added to knowledge and saved
				Vector<String>v1=new Vector<String>();
				Vector<String>v2=new Vector<String>();
				boolean ready=false;
				while(!ready){
				System.out.println("Write an item in the first language. Click return if you finished with one meaning, enter 'ok' and return"
						+ " if you want to add the translation");
				String s1=bur.readLine();
				if(s1.equals("ok"))
					ready=true;
				else{
				String adder=new String(s1);
				v1.addElement(adder);}
				}
				ready=false;
				while(!ready){
					System.out.println("Write the translation. Click return if you finished with one meaning, enter 'ok' and return"
							+ " if you want to finish");
					String s2=bur.readLine();
					if(s2.equals("ok"))
						ready=true;
					else{
					String adder=new String(s2);
					v2.addElement(adder);}
					}
				v.add(v1, v2, language);
			}
			if(input.equals("E")){
				System.out.println("Which vocable do you want to erase? Enter its number");
				String n=bur.readLine();
				int nn=Integer.parseInt(n);
				v.erase(nn, language);
			}
			if(input.equals("T")){
				v.training();
			}
		}
		System.out.println("Thank you for using my program");
		System.exit(0);
	}catch(Exception eIO){System.out.println("Reading of input failed");}
	
	}
}
//by Niklas Götz