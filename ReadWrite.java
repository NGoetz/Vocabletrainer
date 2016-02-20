package vocabletrainer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Vector;

public class ReadWrite {

	public static Vector<Vocable> getKnow(String f){ //tries to read data from a language-file(filename given by languagename). if none exists, new is initialised
		String s=f+".dat";//transform languagename into filename
		Vector<Vocable>know=new Vector<Vocable>();
		try {
			int k=0;
			Vector<String>l1=new Vector<String>();
			Vector<String>l2=new Vector<String>();
			FileInputStream fis = new FileInputStream(s);//try to read out file
			InputStreamReader isr = new InputStreamReader (fis);
			BufferedReader bur =new BufferedReader(isr);
			String sLine="nothing";
			while (sLine != null){//upload a line, parse it into data
				k++;
				sLine=bur.readLine();
				if(sLine!=null){
					Scanner sc = new Scanner(sLine).useDelimiter("\\s*,\\s*");

					while(sc.hasNext()){
						if(k%2==1)
							l1.addElement(sc.next());
						if(k%2==0)
							l2.addElement(sc.next());

					}
					if(k%2==0){
						Vector<String>buffer1=new Vector<String>();
						Vector<String>buffer2=new Vector<String>();
						for(int i=0;i<l1.size();i++){//copy the string
							String backup=new String(l1.elementAt(i));
							buffer1.addElement(backup);
						}
						;
						for(int n=0;n<l2.size();n++){
							String backup=new String(l2.elementAt(n));
							buffer2.addElement(backup);
						}
						Vocable v=new Vocable(buffer1,buffer2);
						know.addElement(v);//add it into the programs knowledge
						l1.removeAllElements();//clean the container structure
						l2.removeAllElements();

					}
					sc.close();}
			};
			bur.close();
			ReadWrite.save(f, know);
		} catch (IOException eIO){//no file existed. we create a now one and fill it with the minimum ammount of vocables
			System.out.println("Language does not exist. Creating new file with language data");
			File file=new File(s);
			try{file.createNewFile();
			System.out.println("File created. Please enter 10 new vocables to start. First enter an item in the first language, different meanings separated by commas,\n"
					+ "then the translation, different meanings separated by commas. After that, enter another item.");
			InputStreamReader isr =new InputStreamReader(System.in);
			BufferedReader bur = new BufferedReader(isr);
			Vector<String>l1=new Vector<String>();
			Vector<String>l2=new Vector<String>();
			int k=0;
			for(int i=1; i<=20;i++){//the same procedure as at the top - the input is given to the program, writing will be done by calling the save-method
				k++;
				String sLine = bur.readLine();
				Scanner sc = new Scanner(sLine).useDelimiter("\\s*,\\s*");

				while(sc.hasNext()){
					if(k%2==1)
						l1.addElement(sc.next());
					if(k%2==0)
						l2.addElement(sc.next());

				}
				if(k%2==0){
					Vector<String>buffer1=new Vector<String>();
					Vector<String>buffer2=new Vector<String>();
					for(int i1=0;i1<l1.size();i1++){
						String backup=new String(l1.elementAt(i1));
						buffer1.addElement(backup);
					}
					
					for(int n=0;n<l2.size();n++){
						String backup=new String(l2.elementAt(n));
						buffer2.addElement(backup);
					}
					Vocable v=new Vocable(buffer1,buffer2);
					know.addElement(v);
					l1.removeAllElements();
					l2.removeAllElements();
				}
			}
			save(f, know);//save the data into the file
			} catch (IOException e) {
				System.out.println("Tried to create file for new language - Error occured");
			}
			}
			return know;
		}
		public static void save (String f, Vector<Vocable>v){//get the program's data and save it into the file (which name is given by the choosen language
			String s=f+".dat";
			File file =new File(s);
			try{
				FileWriter writer=new FileWriter(file);
				for(int i=0; i<v.size();i++){
					for(int k=0; k<v.elementAt(i).getLang1().size();k++){
						writer.write(v.elementAt(i).getLang1().elementAt(k)+", ");//we use separator-signs to secure the correct handling of data
					}
					writer.write(System.getProperty("line.separator"));

					for(int m=0; m<v.elementAt(i).getLang2().size();m++){
						writer.write(v.elementAt(i).getLang2().elementAt(m)+", ");
					}
					writer.write(System.getProperty("line.separator"));
				}
				writer.flush();
				writer.close();
			}catch(IOException eIO){
				System.out.println("Error. Saving failed!");
			}
		}

		public static void print (Vector<Vocable>v){//just a printout method, prints the current data in our knowledge-base
			for(int i=0; i<v.size();i++){
				System.out.println((i+1)+": ");
				for(int k=0;k<v.elementAt(i).getLang1().size();k++){
					System.out.print(v.elementAt(i).getLang1().elementAt(k)+"  ");

				}
				System.out.println();
				for(int m=0; m<v.elementAt(i).getLang2().size();m++){
					System.out.print(v.elementAt(i).getLang2().elementAt(m)+"  ");
				}
				System.out.println();
				System.out.println();
			}

		}
	}
//by Niklas Götz

