import java.util.*;
import java.io.*;
import java.nio.file.*;

public class Main
{
	public static void main(String [] arg)
	{
		int y = 0;
		int x =0;
		try 
		{
			long countOfLines = Files.lines(Paths.get(new File(arg[0]).getPath())).count();
			x = (int)countOfLines;
			FileReader fr = new FileReader(arg[0]);
			LineNumberReader lineReader = new LineNumberReader(fr);
			String line = lineReader.readLine();
			int t = line.length();
			char[] ch = line.toCharArray();
			for(int i = 0;i < t; i++)
			{
				if(!Character.isSpaceChar(ch[i]))
				{
					y++;
				}
			}
	
		}
		catch (IOException e) 
		{
			System.out.println("No File Found");
		}
		
		ShortestPath object = new ShortestPath(arg[0],x,y);
		object.algorithm();
		object.print();
	}
}