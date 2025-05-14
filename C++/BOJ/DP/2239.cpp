#include <iostream>
using namespace std;

int v[101];
long d[10001];
int n, k;

int main()
{
    cin.tie(0);
    cout.tie(0);
    ios::sync_with_stdio(false);

    cin >> n >> k;

    for (int i = 1; i <= n; i++)
    {
        cin >> v[i];
    }
    d[0] = 1;
    for(int i = 1; i <=n; i++){
        for(int j = v[i]; j <= k; j++){
            d[j] = d[j] + d[j-v[i]];
        }
    }
    cout << d[k];
}