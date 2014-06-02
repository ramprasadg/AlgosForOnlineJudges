#include<iostream>
#include <list>

const int MOD = 10007;
const int MAXN = 100010;

using namespace std;

int num_of_ways_optimal[MAXN];
int num_of_ways_optimal_cnt[MAXN];
int num_of_ways_with[MAXN];
int num_of_ways_with_cnt[MAXN];

void dfs(list<int> graph[], int x, int parent) {
  list<int> x_adj = graph[x];
  int withNode = 1, withNodeCnt = 1, withoutNode = 0, withoutNodeCnt = 1;
  for (std::list<int>::iterator y = x_adj.begin(); y != x_adj.end(); y++) {
    if(*y != parent) {
      dfs(graph, *y, x);
      withNode += num_of_ways_optimal[*y];
      withNodeCnt = (withNodeCnt * num_of_ways_optimal_cnt[*y]) % MOD;
      
      withoutNode += num_of_ways_with[*y];
      withoutNodeCnt = (withoutNodeCnt * num_of_ways_with_cnt[*y]) % MOD;
    }
  }
  
  num_of_ways_with[x] = withNode;
  num_of_ways_with_cnt[x] = withNodeCnt;
  
  if(withNode < withoutNode) {
    num_of_ways_optimal[x] = withNode;
    num_of_ways_optimal_cnt[x] = withNodeCnt;
  }
  else if(withNode > withoutNode) {
    num_of_ways_optimal[x] = withoutNode;
    num_of_ways_optimal_cnt[x] = withoutNodeCnt;
  }
  else{
    num_of_ways_optimal[x] = withNode;
    num_of_ways_optimal_cnt[x] = (withoutNodeCnt + withNodeCnt) % MOD;
  }
}

int main() {
  int tests;
  cin>>tests;
  for(int test = 0; test < tests; test++) {
    int n;
    cin>>n;
    
    list<int> graph[n+1];
    int a, b;
    for(int i=0; i < n-1; i++) {
      cin>>a>>b;
      graph[a].push_back(b);
      graph[b].push_back(a);
    }
    
    int root = 1;
    dfs(graph, root, -1);
    cout<<num_of_ways_optimal[root]<<" "<<num_of_ways_optimal_cnt[root]<<"\n";
  }
}
