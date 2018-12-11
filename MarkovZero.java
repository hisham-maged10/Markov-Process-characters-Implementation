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
public class MarkovZero extends Markov
{
	public MarkovZero()
	{
		super(null,0);
	}
	public MarkovZero(String str)
	{
		super(str,0);
	}
	public MarkovZero(int seed)
	{
		super(null,seed);
	}
	public MarkovZero(String str,int seed)
	{
		super(str,seed);
	}
	@Override
	public String getRandomText(int charNumber)
	{
		StringBuilder sb=new StringBuilder();
		int index;
		int length=this.trainText.length();
		for(int i=0;i<charNumber;i++)
		{
			index=randomObj.nextInt(length);
			sb.append(this.trainText.charAt(index));
		}
		return sb.toString();
	}
	@Override
	public String toString()
	{
		return "MarkovModel of order 0";
	}

}
