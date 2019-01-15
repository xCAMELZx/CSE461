#include <iostream>
#include <stdlib.h>

using namespace std;

int succ ( int k )
{
  int nActive = 9;
  int activeNodes [] = {1, 4, 9, 11, 14, 18, 20, 21, 28 };
  int m = 5;

  for ( int i = 0; i < nActive; i++ ) {
    if ( k <= activeNodes[i] )
      return activeNodes[i];  
  }

  return activeNodes[0];
}

int two2n ( int n )
{
   int p = 1;

   for ( int i = 1; i <= n; i++ )
     p = p * 2;

   return p;
}

int printFT ( int p,  int m )
{
  int k;
  for ( int i = 1; i <= m; i++ ){
    k = p + two2n ( i - 1 );
    int m2 = two2n ( m );
    if ( k >= m2 ) k -= m2;
    cout << i << "\t " << succ ( k ) << endl;
  }
}

int main()
{
  int x = 0;
  while ( 1 ) {
    cout << "Enter active node number: ";
    cin >> x;
    if ( x < 0 ) break;
    printFT ( x, 5 );
    cout  << endl;;
  }
 
  return 0;
}
