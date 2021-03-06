/**
 * @author ${hisham_maged10}
 *https://github.com/hisham-maged10
 * ${DesktopApps}
 */
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Random;
import java.util.HashSet;
import java.util.ArrayList;
public class MarkovModel extends Markov
{
	private int n;
	public MarkovModel(int n)
	{
		super(null,0);
		this.n=n;
	}
	public MarkovModel(String str)
	{
		super(str,0);
	}
	public MarkovModel(String str,int seed)
	{
		super(str,seed);
	}
	@Override
	public String getRandomText(int charNumber)
	{
		StringBuilder sb=new StringBuilder();
		int index=randomObj.nextInt(trainText.length()-this.n);
		this.map=buildMap();
		String seq=trainText.substring(index,index+this.n);
		sb.append(seq);
		ArrayList<Character> charArr=null;
		for(int i=0;i<charNumber - this.n;i++)
		{
			charArr=this.map.get(seq);
			if(charArr.isEmpty())break;
			index=randomObj.nextInt(charArr.size());
			sb.append(Character.toString(charArr.get(index)));
			seq=seq.substring(1)+charArr.get(index);
		}
		return sb.toString();
	}
	@Override
	public String toString()
	{
		return "MarkovModel of order "+ this.n;
	}

}
