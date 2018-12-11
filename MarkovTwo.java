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
public class MarkovTwo extends Markov
{
	public MarkovTwo()
	{
		super(null,0);
	}
	public MarkovTwo(String str)
	{
		super(str,0);
	}
	public MarkovTwo(int seed)
	{
		super(null,seed);
	}
	public MarkovTwo(String str,int seed)
	{
		super(str,seed);
	}
	@Override
	public String getRandomText(int charNumber)
	{
		StringBuilder sb=new StringBuilder();
		int index=randomObj.nextInt(trainText.length()-2);
		String seq=trainText.substring(index,index+2);
		sb.append(seq);
		ArrayList<Character> charArr=null;
		for(int i=0;i<charNumber -2;i++)
		{
			charArr=getFollowSet(seq);
			index=randomObj.nextInt(charArr.size());
			sb.append(Character.toString(charArr.get(index)));
			seq=seq.substring(1)+charArr.get(index);
		}
		return sb.toString();
	}
	@Override
	public String toString()
	{
		return "MarkovModel of order 2";
	}

}
