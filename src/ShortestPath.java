import java.util.*;
import java.io.*;

public class ShortestPath
{
	private int col,row;
	private Node [][] map;
	private Node [][] solution;
	private PriorityQueue<Node> openNodes;
	private Node startNode;
	private Node endNode;
	
	private class Node
	{
		private int x,y;
		private char character;
		private int cost = 0;
		private int totalCost = 0;
		public boolean closed = false;
		private Node parent ;
		public Node(int i, int k, char c)
		{
			this.x = i;
			this.y = k;
			this.character = c;
			
		}
		public void setX(int x)
		{
			this.x = x;
		}
		public void setY(int y)
		{
			this.y = y;
		}
		public void setCharacter(char c)
		{
			this.character = c;
		}
		public void setCost(int cost)
		{
			this.cost = cost;
		}
		public void setTotalCost(int totalCost)
		{
			this.totalCost = totalCost;
		}
		public int getX()
		{
			return this.x;
		}
		public int getY()
		{
			return this.y;
		}
		public int getCost()
		{
			return this.cost;
		}
		public int getTotalCost()
		{
			return this.totalCost;
		}
		public char getCharacter()
		{
			return this.character;
		}
		public void setClosed()
		{
			this.closed = true;
		}
	}
	
	private void checkUpdate(Node temp, Node tmp,int cos)
	{
	
		if(tmp.getCharacter() == 'W' || tmp.closed)
		{
		
			return;
		}
		tmp.cost = Math.abs(tmp.x-endNode.x)+Math.abs(tmp.y-endNode.y);
		int finalCost = tmp.cost + cos;
		
		boolean inOpen = openNodes.contains(tmp);
		if(!inOpen || finalCost < tmp.totalCost)
		{
			tmp.totalCost = finalCost;
			tmp.parent = temp;
		
			if(!inOpen)
			{
				openNodes.add(tmp);
				if(temp != startNode){
				Node newNode = new Node(temp.x,temp.y,'"');
				solution[temp.x][temp.y] = newNode;
				}
					
			}
		}
	
	}
	public ShortestPath(String fileName,int x,int y)
	{
		row = x;
		col = y;
		map = new Node[x][y];
		solution = new Node[x][y];
		BufferedReader fileInput =null;
		try{
			fileInput = new BufferedReader(new FileReader(fileName));
			String s;
			int i = 0;
			int k = 0;
			Node temp = null;
			while ((s = fileInput.readLine()) != null) {
				for (char c : s.toCharArray())
				{
					if(!Character.isSpaceChar(c)){
					temp = new Node(i,k,c);
					map[i][k] = temp;
					solution[i][k] = temp;
					k++;
					if(c == 'S')
					{
						startNode = temp;
					}
					if(c == 'E')
					{
						endNode = temp;
					
					}
				}
				}
				i++;
				k =0;
				
			}
		}
		catch (IOException e) {

			e.printStackTrace();

		} 
		finally {

			try {
		fileInput.close();
			}catch (IOException ex) {

				ex.printStackTrace();

			}
		}
		
	}
	
	public void algorithm()
	{
		openNodes = new PriorityQueue<Node>((Object o1, Object o2) -> {
			Node  n1 = (Node )o1;
			Node  n2 = (Node )o2;

			return n1.totalCost<n2.totalCost?-1:
					n1.totalCost>n2.totalCost?1:0;
		});
		openNodes.add(startNode);
		Node temp;
		boolean done =false;
		while(!openNodes.isEmpty() && !done)
		{
			
			temp = openNodes.poll();
		
			if(temp.getCharacter() == 'W') 
			{
				
				break;
			}
			temp.closed =true;
			
			if(temp.x == endNode.x && temp.y== endNode.y)
			{
				done = true;
				endNode.closed = true;
		
			Node tem = endNode;
			while(tem.parent !=null)
			{
				if(tem!= endNode)
				{
					solution[tem.x][tem.y].setCharacter('*');
				}
				tem=tem.parent;
			}
			
		
				return;
			}
	
			Node tmp;
			
			if(temp.getX() - 1 >= 0)
			{
				tmp = map[temp.getX() - 1][temp.getY()];
				checkUpdate(temp,tmp,temp.totalCost+1);
			}
			if(temp.getY() -1>=0)
			{
				tmp = map[temp.getX()][temp.getY() -1];
				checkUpdate(temp,tmp,temp.totalCost+1);
			}	
			if(temp.getX() +1 < row)
			{
				tmp = map[temp.getX() + 1][temp.getY()];
				checkUpdate(temp,tmp,temp.totalCost+1);
			}
			if(temp.getY() +1 < col)
			{
				tmp = map[temp.getX()][temp.getY() +1];
				checkUpdate(temp,tmp,temp.totalCost+1);
			}
		
		}
		
		
	}
	public void print()
	{
		System.out.println("Map Sample");
		printMap(this.map);
		System.out.println("One possible path to E");
		printMap(this.solution);
	}
	private void printMap(Node [][] temp)
	{
		for(int i=0;i<row;++i)
		{
			for(int j=0;j<col;++j)
			{
				System.out.printf(temp[i][j].character + " ");
				
			}
			System.out.println();
		}
		System.out.println();
		
	}
}
