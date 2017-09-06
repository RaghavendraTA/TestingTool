#include<iostream>
#include<cstdio>
using namespace std;

int main()
{
   
	
	int d[100],N,A;
    

    cout<<"enter the Ammont\t";
    cin>>A;
   
    cout<<"enter the number of denominations\t";
    cin>>N;
   
   cout<<"enter the denominations\n";
   
   for(int i=1;i<N+1;i++)
      cin>>d[i];
    int C[A+1],S[A+1];
    
	C[0] = 0; 
	S[0] = 0; 
    
	int i, p, min, coin;
	for(p = 1; p <= A; p++) {
		min = 9999;
		for(i = 1; i <= N; i++) {
			if(d[i] <= p) {
				if(1 + C[p - d[i]] < min) {
					min = 1 + C[p - d[i]];
					coin = i;
				}

			}
		}
		C[p] = min;
		S[p] = coin;
	}
 
cout<<"min number of coins required is "<<C[A]<<endl;

	while(A > 0) {
		cout<<"Use coin of denomination:"<<d[S[A]]<<endl;
		A = A - d[S[A]];
    }
return 0 ;
}