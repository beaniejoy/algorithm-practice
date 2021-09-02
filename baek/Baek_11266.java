import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Baek_11266 {
	
	private static class Graph {
		int[] dfn;
		int[] low;
		boolean[] visited;
		Node[] adjList;
		int dfnCount;
		
		Graph(int maxSize) {
			adjList = new Node[maxSize + 1];
			dfn = new int[maxSize + 1];
			low = new int[maxSize + 1];
			visited = new boolean[maxSize + 1];
			dfnCount = 1;
			
			for(int i = 0; i <= maxSize; i++) {
				this.dfn[i] = -1;
				this.low[i] = -1;
			}
		}
		
		void insertVertex(int v) {
			if(adjList[v] != null)
				return;
			
			adjList[v] = new Node(v, null);
		}
		
		boolean insertEdge(int u, int v) {
			insertVertex(u);
			insertVertex(v);
			
			// (u, v)
			Node nextU = adjList[u];
			while(nextU.getNext() != null) {
				nextU = nextU.getNext();
				
				if(nextU.getVertex() == v)
					return false;
			}
			
			nextU.setNext(new Node(v, null));
			
			// (v, u)
			Node nextV = adjList[v];
			while(nextV.getNext() != null) {
				nextV = nextV.getNext();
				
				if(nextV.getVertex() == u) {
					nextU.setNext(null);
					return false;
				}
			}
			
			nextV.setNext(new Node(u, null));
			return true;
		}
		
		void dfnlow(int u, int v) {
			dfn[u] = low[u] = dfnCount++;
			
			Node child = adjList[u].getNext();
			int w;
			for(; child != null; child = child.getNext()) {
				w = child.getVertex();
				if(dfn[w] < 0) {
					dfnlow(w, u);
					low[u] = min(low[u], low[w]);
				} else if(w != v) {
					low[u] = min(low[u], dfn[w]);
				}
			}
		}
		
		void dfs(int u) {
			if(visited[u])
				return;
			
			visited[u] = true;
			
			Node next = adjList[u].getNext();
			for(; next != null; next = next.getNext()) {
				int nextVertex = next.getVertex();
				if(!visited[nextVertex]) {
					dfs(nextVertex);
				}
			}
		}
		
		boolean isArticulation(int u) {
			// root
			if(dfn[u] == 0)
				return isRootArticulation(u);
				
			// non-root
			Node next = adjList[u].getNext();
			int v;
			for(; next != null; next = next.getNext()) {
				v = next.getVertex();
				
				// 자식노드에만 관심, 부모노드일시 패스
				if(dfn[u] > dfn[v])
					continue;
				
				if(dfn[u] <= low[v])
					return true;
			}
			
			return false;
		}
		
		boolean isRootArticulation(int root) {
			visited[root] = true;
			
			int childCount = 0;
			Node child = adjList[root].getNext();
			for(; child != null; child = child.getNext()) {
				int childVertex = child.getVertex();
				if(!visited[childVertex]) {
					childCount++;
					dfs(childVertex);
				}
			}
			
			return childCount >= 2;
		}
		
		private int min(int a, int b) {
			return a < b ? a: b;
		}
		
	}
	
	private static class Node {
		int vertex;
		Node next;
		
		Node(int vertex, Node next) {
			this.vertex = vertex;
			this.next = next;
		}
		
		public int getVertex() {
			return vertex;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int V = Integer.parseInt(st.nextToken());
    int E = Integer.parseInt(st.nextToken());
    
    int u;
    int v;
    Graph graph = new Graph(V);
    
    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());
        u = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        
        graph.insertEdge(u, v);
    }
    
    int artCount = 0;
    List<Integer> result = new ArrayList<>();
    for(int i = 1; i <= V; i++) {
      if(graph.dfn[i] < 0) {
        graph.dfnCount = 1;
        graph.dfnlow(i, i);
        
        for(int j = 1; j <= V; j++) {
          if(graph.dfn[j] >= 0 && graph.isArticulation(j)) {
            
            if(!result.contains(j)) {
              result.add(j);
              artCount++;        					
            }
            
          }
        }
      }
    }
    StringBuffer sb = new StringBuffer();
    
    Collections.sort(result);
    for(int i = 0; i < result.size(); i++) {
      if(i != 0)
        sb.append(" ");
      
      sb.append(result.get(i));
    }
    
    bw.write(String.valueOf(artCount));
    if(artCount > 0) {
      bw.newLine();
      bw.write(sb.toString());
    }
    bw.flush();

    bw.close();
    br.close();
	}
}
