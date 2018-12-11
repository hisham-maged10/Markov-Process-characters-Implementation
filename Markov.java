/**
 * @author ${hisham_maged10}
 *https://github.com/hisham-maged10
 * ${DesktopApps}
 */
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Random;
import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.ArrayList;
import java.util.HashMap;
public abstract class Markov
{
	protected String trainText;
	protected Random randomObj;
	protected HashMap<String,ArrayList<Character>> map;
	public Markov()
	{
		this(null,0);
	}
	public Markov(String str)
	{
		this(str,0);
	}
	public Markov(int seed)
	{
		this(null,seed);
	}
	public Markov(String str,int seed)
	{
		this.randomObj=seed==0?new Random():new Random(seed);
		this.trainText=str==null?getText(null):str;
	}

	public void setTrainText(String trainText)
	{
		this.trainText=trainText;
	}
	public void setRandom(int seed)
	{
		this.randomObj.setSeed(seed);
	}
	protected File getFile()
	{
		JFileChooser chooser=new JFileChooser();
		FileNameExtensionFilter filter=new FileNameExtensionFilter("Text Files","txt","TXT");
		chooser.setFileFilter(filter);
		chooser.setCurrentDirectory(new File("."));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		File file=null;
		try{
		do
		{
			System.out.println("Choose a text file to train on!");
			chooser.showOpenDialog(null);
		}while((file=chooser.getSelectedFile())==null);
		}catch(NullPointerException ex){System.out.println("Incorrect Response!"); return getFile();}
		return file;
		
	}
	protected ArrayList<Character> getFollowSet(String seq)
	{
		ArrayList<Character> charArr=new ArrayList<>();
		int index=trainText.indexOf(seq);
		int seqLength=seq.length();
		int maxLength=trainText.length()-1;
		while(index!=-1)
		{
			if(index+seqLength >= maxLength)
				break;
			charArr.add(trainText.charAt(index+seqLength));
			index=trainText.indexOf(seq,index+seqLength);
		}
		return charArr;
	}
	protected String getText(File file)
	{
		//file=file==null?this.getFile():file;
		FileResource fr=new FileResource();
		String st=fr.asString();
		st=st.trim();
		st=st.replace('\n',' ');
		return st;
	
		/*StringBuilder sb=new StringBuilder();
		try(BufferedReader reader=new BufferedReader(new FileReader(file));)
		{
		String line=null;
			while((line=reader.readLine())!=null)
			{
				sb.append(line+'\n');
			}
		}
		catch(IOException ex){System.out.println("Error with file"); ex.printStackTrace();}
		String ret=sb.toString();
	//	ret=ret.replaceAll("\\s+"," ");
		ret=ret.trim();
		ret=ret.replace('\n',' ');
		//ret=ret.substring(0,ret.length()-1);
		return ret;*/
	}
	//public abstract HashMap<String,ArrayList<Character>> buildMap();
	protected HashMap<String,ArrayList<Character>> buildMap()
	{	
		HashMap<String,ArrayList<Character>> builtMap=new HashMap<>();
		String sub=(sub=this.toString()).substring(sub.length()-1);
		int order=Integer.parseInt(sub);
		String seq;
		for(int i=0,n=this.trainText.length();i<n;i++)
		{
			if(i+order>n)
				break;
			seq=trainText.substring(i,i+order);
			if(!builtMap.containsKey(seq))
				builtMap.put(seq,getFollowSet(seq));
			//seq=seq.substring(1)+trainText.substring();
			//seq=seq.substring(1)+builtMap.get(seq).get(index);
		}
		int maxSize=0;
		for(String e:builtMap.keySet())
		{
			//System.out.println("Key: "+e+" set: "+builtMap.get(e));
			if(maxSize<builtMap.get(e).size())
				maxSize=builtMap.get(e).size();
		}
		System.out.println("number of keys: "+builtMap.size());
		System.out.println("Largest value in the HashMap: "+maxSize);
		/*System.out.println("Keys that have the largest values:-");
		for(String e:builtMap.keySet())
		{
			if(maxSize==builtMap.get(e).size())
			System.out.println("Key: "+e);
		}
		*/
		return builtMap;
	}

	public abstract String getRandomText(int charNumber);
	

}
