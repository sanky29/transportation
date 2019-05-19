import java.util.AbstractQueue;
import java.io.*;
import javafx.util.Pair;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;
public class graph {
	
	public static class hashmap <E>{

		//just define hash as array
		public Pair <String,E>[] data;
		
		public int limit;
		
		//just define search function
		public E search ( String element) {
			
			int index = hash_cal(element);
			//also movable index as
			int i = 0;
			//search for it
			//just go for that index
			if(data[index] == null) {
				
				throw new IndexOutOfBoundsException("not found");
			}
			
			if (data[index].getKey().compareTo(element) == 0) {
				
				//just return the value as
				return(data[index].getValue());
			}
			//go for quadratic probing
			else {
				
				//run while loop
				while(data[index] != null && data[index].getKey().compareTo(element) == 0) {
					
					i++;
					index = (index + i*i) % limit;
				}
				if (data[index] != null) {
					
					return(data[index].getValue());
				}
				else {
					throw new IndexOutOfBoundsException("no found");
				}
			}
		}
		
		//define function to add
		public void add(E element, int hash_value, String name) {
			
			//define pair
			Pair<String,E> a = new Pair<>(name,element);
			int index = hash_value % limit;
			int i = 0;
			//just go for index
			if (data[index] == null) {
				data[index] = a;
			}
			else {
				//run while loop
				while(data[index] != null) {
					
					i++;
					index = (index + i*i) % limit;
				}
				data[index] = a;
			}
		}
		public hashmap(E element, String name, int hash_value) {
			Pair<String,E> a = new Pair<>(name,element);
			data = (Pair <String,E>[]) new Pair[1003];
			data[hash_value%1003] = a;
			limit = 1003;
			
		}
	}
	 
	//the node of graph
	public static class node {

		//define content of this as
		String name;
		
		LinkedList<node> neighbours;
		
		double dfs;
		
		float demand;
		
		int hash_value;
		
		int id;
		
		node parent;
		
		public node(String name1, float d) {
			name = name1;
			demand = d;
			neighbours = new LinkedList<>();
		}
	}

	public static class heap {

		int entry;
		
		public Pair <Double,Integer>[] d;
		
		public Pair<Double,Integer> show(){
			if (d[0] != null) {
				return(d[0]);
				}
			else {
				throw new IndexOutOfBoundsException("no element");
			}
		}
		public Pair<Double,Integer> pop(){
			if (d[0] != null) {
				Pair<Double,Integer> t = d[0];
				Pair<Double,Integer> t1 = d[0];
				d[0] = d[entry];
				d[entry] = null;
				entry--;
				int i = 0;
				while (i*2+1 < entry) {
					if (d[i*2 + 2] != null) {
						if (d[i*2 + 1].getKey() < d[i].getKey() && d[i*2 + 1].getKey() < d[i*2 + 2].getKey()) {
							t1 = d[i*2 + 1];
							d[i*2 + 1] = d[i];
							d[i] = t1;
							i = i*2 + 1;
						}
						else if (d[i*2 + 2].getKey() < d[i].getKey() && d[i*2 + 2].getKey() < d[i*2 + 1].getKey()) {
							t1 = d[i*2 + 2];
							d[i*2 + 2] = d[i];
							d[i] = t1;
							i = i*2 + 2;
						}
						else {
							i = entry;
						}
					}
					else {
						if(d[i*2 + 1].getKey() < d[i].getKey()) {
							t1 = d[i*2 + 1];
							d[i*2 + 1] = d[i];
							d[i] = t1;
							i = i*2 + 1;
						}
						else {
							i = entry;
						}
					}
				}
				return(t);
				}
			else {
				throw new IndexOutOfBoundsException("no element");
			}
		}
		public void add(Pair<Double,Integer> ele){
			entry++;
			d[entry] = ele;
		
			Pair<Double,Integer> t1 = d[0];
			int i = entry;
			while (i > 0) {
				if (d[i/2 - (i + 1)%2].getKey() > d[i].getKey()) {
					t1 = d[i/2 - (i + 1)%2];
					d[i/2 - (i + 1)%2] = d[i];
					d[i] = t1;
					i = i/2 - (i + 1)%2;
				}
				else {
					i = -1;
				}
			}
		}
		public heap(Integer z, Double t) {
			d = (Pair <Double,Integer>[]) new Pair[1003];
			entry = 0;
			d[0] = new Pair<Double,Integer>(t,z);
		}
		public heap() {
			d = (Pair <Double,Integer>[]) new Pair[1003];
			entry = -1;
		}
	}
	
	public static class max_heap {

		int entry;
		
		public Pair <Double,Integer>[] d;
		
		public Pair<Double,Integer> show(){
			if (d[0] != null) {
				return(d[0]);
				}
			else {
				throw new IndexOutOfBoundsException("no element");
			}
		}
		public Pair<Double,Integer> pop(){
			if (d[0] != null) {
				Pair<Double,Integer> t = d[0];
				Pair<Double,Integer> t1 = d[0];
				d[0] = d[entry];
				d[entry] = null;
				entry--;
				int i = 0;
				while (i*2+1 < entry) {
					if (d[i*2 + 2] != null) {
						if (d[i*2 + 1].getKey() > d[i].getKey() && d[i*2 + 1].getKey() > d[i*2 + 2].getKey()) {
							t1 = d[i*2 + 1];
							d[i*2 + 1] = d[i];
							d[i] = t1;
							i = i*2 + 1;
						}
						else if (d[i*2 + 2].getKey() > d[i].getKey() && d[i*2 + 2].getKey() > d[i*2 + 1].getKey()) {
							t1 = d[i*2 + 2];
							d[i*2 + 2] = d[i];
							d[i] = t1;
							i = i*2 + 2;
						}
						else {
							i= entry;
						}
					}
					else {
						if(d[i*2 + 1].getKey() > d[i].getKey()) {
							t1 = d[i*2 + 1];
							d[i*2 + 1] = d[i];
							d[i] = t1;
							i = i*2 + 1;
						}
						else {
							i = entry;
						}
					}
				}
				return(t);
				}
			else {
				throw new IndexOutOfBoundsException("no element");
			}
		}
		public void add(Pair<Double,Integer> ele){
			entry++;
			d[entry] = ele;
		
			Pair<Double,Integer> t1 = d[0];
			int i = entry;
			while (i > 0) {
				if (d[i/2 - (i + 1)%2].getKey() < d[i].getKey()) {
					t1 = d[i/2 - (i + 1)%2];
					d[i/2 - (i + 1)%2] = d[i];
					d[i] = t1;
					i = i/2 - (i + 1)%2;
				}
				else {
					i = -1;
				}
			}
		}
		public max_heap(Double z, int t) {
			d = (Pair <Double,Integer>[]) new Pair[1003];
			entry = 0;
			d[0] = new Pair<Double,Integer>(z,t);
		}
		public max_heap() {
			d = (Pair <Double,Integer>[]) new Pair[1003];
			entry = -1;
		}

	}

	//define hashmap
	node source;
	
	hashmap<node> nodes;
	
	float[][] distances;
	
	int nn;
	
	int ne;
	
	node[] idnode;
	
	max_heap shortest;
	
	public static AbstractQueue<node> q = new LinkedBlockingQueue<>();

	public static boolean[] visited = new boolean[1003];
	
	public static int hash_cal(String s) {
		int ans = 0;
		for (char ch: s.toCharArray()) {
			ans = (((ans%1003)*(13%1003))%1003 + ((int) ch)%1003)%1003;
		}
		return(ans);
	}
	public void add_node (String h) {
		String[] temp = h.split(" "); 
		node add1 = new node(temp[0], 0);
		node connect = nodes.search(temp[1]);
		add1.dfs = distances[0][nn-1];
		connect.neighbours.add(add1);
		add1.neighbours.add(connect);
		float distance = Float.valueOf(temp[2]);
		nodes.add(add1, hash_cal(add1.name), add1.name);
		nn++;
		add1.id = nn - 1;
		ne++;
		for (int i = 0 ; i < nn ; i ++) {
			distances[nn-1][i] = Float.MAX_VALUE;
			distances[i][nn-1] = Float.MAX_VALUE;
		}
		distances[nn-1][connect.id] = distance; 
		distances[connect.id][nn-1] = distance;
		add1.parent = connect;
		idnode[add1.id] = add1;
		distances[add1.id][add1.id] = 0;
	}
	public void add_edge(String h) {
		String[] temp = h.split(" "); 
		node x = nodes.search(temp[0]);
		node y = nodes.search(temp[1]);
		float w = Float.valueOf(temp[2]);
		x.neighbours.add(y);
		y.neighbours.add(x);
		distances[x.id][y.id] = w;
		distances[y.id][x.id] = w;
		ne++;
		if (x.dfs > y.dfs + w) {
			x.parent = y;
			x.dfs = y.dfs + w;
		}
		if (y.dfs > x.dfs + w) {
			y.parent = x;
			y.dfs = x.dfs + w;	
		}
	}
	public static void update_distances(float[][] x , int y) {
		for (int k = 0 ; k < y ; k ++) {
			for (int j = 0 ; j < y ; j ++) {
				for (int i = 0 ; i < y ; i ++) {
					if (x[i][j] > x[i][k] + x[k][j]) {
						x[i][j] = x[i][k] + x[k][j];
					}
				}
			}
		}	
	}
	public graph (node a) {
		source = a;
		nodes = new hashmap<>(a, a.name , hash_cal(a.name));
		distances = new float[1003][1003];
		a.id = 0;
		nn = 1;
		ne = 0;
		idnode = (node[]) new node[1003];
		shortest = new max_heap(0.0 , 0);
		idnode[0] = a;
	}
	public static void printl (LinkedList<Pair<node,Double>> z, PrintStream x) {
		String u = "";
		for (Pair<node,Double> t: z) {
			u = u + t.getKey().name + "(" + Double.toString(t.getValue()) + ") ----" ;
		}
		try {
		x.print(u + '\n');
	}
		catch(Exception e) {
			System.out.println("not ok");
		}
	}
	public void allocate_truck(PrintStream bw) {
		update_distances(distances,nn);
		for (int i = 1 ; i < nn ; i++ ) {
			shortest.add(new Pair<Double,Integer>(new Double(distances[0][i]),i));
		}
		node t;
		node t1;
		node s;
		max_heap z =new max_heap();
		heap a = new heap();
		int d;
		LinkedList<Pair<node,Double>> l ;
		float p = (float) 0.0;
	
		while(shortest.d[0] != null) {
			t1 = idnode[shortest.pop().getValue()];
			t =t1;
			d = (int) t.demand;
			
			if (d > 0) {
				l= new LinkedList<Pair<node,Double>>();
				l.add(new Pair<>(t,1.0*30));
				t = t.parent;	
				
				while(t != source) {
					l.add(new Pair<>(t,0.0));
					t = t.parent;
				}
	
				l.add(new Pair<>(t,0.0));
			
				for(int i = 0 ; i < d; i++) {
						printl(l,bw);
				}
			}
			t1.demand =(t1.demand  -  (float) d);
			z.add(new Pair<>(new Double (distances[0][t1.id]),t1.id));
		}
		d = 0;
	
		while(d < nn) {
			
			l = new LinkedList<Pair<node,Double>>();
			t = idnode[z.pop().getValue()];
			t1 = t;
			p = 1 - t.demand;	
			l.add(new Pair<>(t,new Double(t.demand*30)));
			if (t.demand == 0) {
				
			}
			
			else {
				
				t.demand = 0;
				while(t != source) {	
					a = new heap();
				
					for(int i = 0 ; i < nn ; i ++) {
			
						if (i != 0 && i != t.id) {
							a.add(new Pair<>(new Double (distances[t.id][i]),i));
							
						}
					}
					s = idnode[a.show().getValue()];
					while(a.entry >= 0 && (s.demand == 0.0 || (s.demand > p && s != t.parent)|| a.show().getKey() > t.dfs )) {
						
						a.pop();
						if (a.entry >= 0) {
						s = idnode[a.show().getValue()];}	
					}
					
					if (a.entry < 0) {
						
						l.add(new Pair<>(source,0.0));
						t =source;
					}
					
					else {
						
						t = s;
					
						if (t.demand - p >= 0) {
							l.add(new Pair<>(t,new Double(p)*30));
							t.demand = (float) 0.0;
							
							if (t.demand - p < 0.00005 ) {
								l.add(new Pair<>(source,0.0));
								t = source;
							}
							
							else {
								t.demand = t.demand - p;
							}
						}
						else if (t.demand - p < 0) {
			
							if (p - t.demand < 0.00005) {
								l.add(new Pair<>(t,new Double(p)*30));
								if (t != source) {
									l.add(new Pair<>(source,0.0));
								}
								t = source;
							}
							else {
								
								l.add(new Pair<>(t,new Double(t.demand)*30));
								p = p - t.demand;
								
							}
							t.demand = 0;
						}
					}
					
				}
				printl(l,bw);
			}
		d++;}
	}
	public static void main(String args[]) {
		try {
			
		FileInputStream input = new FileInputStream("map.txt");
	
		Scanner scan = new Scanner(input);
		int j = Integer.valueOf(scan.nextLine());
		
	
		node a = new node(scan.nextLine().split(" ")[1], 0);
		graph g = new graph(a);
		for(int i = 0 ; i < 17; i++) {
		
			
			g.add_node(scan.nextLine());
		}
		j =Integer.valueOf(scan.nextLine());
		for(int i = 0 ; i < j; i++) {
			g.add_edge(scan.nextLine());
		}
		input.close();
		scan.close();
		FileInputStream input2 = new FileInputStream("demand.txt");
		Scanner scan2 = new Scanner(input2);
		j = Integer.valueOf(scan2.nextLine());
		String[] temp;
		for(int i = 0 ; i < j; i++) {
			temp = scan2.nextLine().split(" ");
			
			g.nodes.search(temp[0]).demand = g.nodes.search(temp[0]).demand + Float.valueOf(temp[1])/30;
			System.out.println(i + " " + temp[0] + " " + temp[1]);
		}
		scan2.close();

		FileOutputStream output = new FileOutputStream("solution.txt");
		PrintStream bw = new PrintStream(output);
		g.allocate_truck(bw);
		
		output.close();
		}
		catch (Exception FileNotFounException) {
			System.out.println("got a error101");
		}
	}
}
