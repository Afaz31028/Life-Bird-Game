#include <bits/stdc++.h>
#define ll long long
#define pi pair<int, int>
#define pl pair<ll, ll>
#define yes cout << "YES" << endl
#define no cout << "NO" << endl
#define endl '\n'
#define loop for (int i = 0; i < n; i++)
#define code() ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
using namespace std;
void Afaz(){
    int n;
    cin>>n;
    int a,b;
    map<int,int>m;
    int arr[n][n-1];
    vector<int>v;
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n-1;j++)
        {
            cin>>arr[i][j];
            int x=arr[i][j];
            if(j==(n-2))
            {
                m[x]++;
            }
        }
    }
    for(auto it=m.begin();it != m.end();it++)
    {
        if(it->second==1)
            a=it->first;
        else
            b=it->first;
    }
    int c;
    for(int i=0;i<n;i++)
    {
        if(arr[i][n-2]==a)
            c=i;
    }
    for(int j=0;j<n-1;j++)
    {
        v.push_back(arr[c][j]);
    }
    v.push_back(b);
    for(int val :v)
        cout<<val<<" ";

}
int main()
{
    int t;
    cin>>t;
    while(t--){
        Afaz();
        cout<<endl;
    }
    return 0;
}