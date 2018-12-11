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
import java.util.HashMap;
public class MarkovOne extends Markov
{
	public MarkovOne()
	{
		super(null,0);
	}
	public MarkovOne(String str)
	{
		super(str,0);
	}
	public MarkovOne(int seed)
	{
		super(null,seed);
	}
	public MarkovOne(String str,int seed)
	{
		super(str,seed);
	}
	@Override
	public String getRandomText(int charNumber)
	{
		StringBuilder sb=new StringBuilder();
		buildMap(charNumber);
		ArrayList<Character> charArr=null;
		int index=randomObj.nextInt(trainText.length()-1);
		char character=trainText.charAt(index);
		sb.append(character);
		for(int i=0;i<charNumber-1;i++)
		{
			charArr=getFollowSet(Character.toString(character));
			if(charArr.isEmpty())break;
			index=randomObj.nextInt(charArr.size());
			character=charArr.get(index);
			sb.append(character);
		}
		return sb.toString();
	}
	@Override
	public ArrayList<Character> getFollowSet(String character)
	{
		ArrayList<Character> charArr=new ArrayList<>();
		int index=-1;
		int maxLength=trainText.length();
		while((index=trainText.indexOf(character,index+1))!=-1)
		{
			if(index+1 >= maxLength)
				break;
			charArr.add(trainText.charAt(index+1));
		}
	//	System.out.println("Char numbers: "+charArr.size());
		return charArr;
	}
	@Override
	public String toString()
	{
		return "MarkovModel of order 1";
	}

}
