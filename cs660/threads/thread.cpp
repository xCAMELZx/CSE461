/*
 * thread.cpp
*/
                                                                               
#include <SDL/SDL.h>
#include <SDL/SDL_thread.h>
#include <stdlib.h>
                                                                               
using namespace std;
                                                                               
bool value_consumed = true;
bool quit = false;
int a = 0, b = 0;
int thread1 ( void *data )
{
  char *tname = ( char * )data;
                                                                               
  while ( !quit ) {
    printf("I am %s. \n", tname );
                                                                               
    //delay for a random amount of time
    SDL_Delay ( rand() % 1000 );
  }
  printf("%s quits\n", tname );
                                                                               
  return 0;
}
                                                                               
int thread2 ( void *data )
{
  char *tname = ( char * )data;
                                                                               
  while ( !quit ) {
    printf("I am %s. \n", tname );
    //delay for a random amount of time
    SDL_Delay ( rand() % 1000 );
  }
  printf("%s quits\n", tname);
                                                                               
  return 0;
}
                                                                               
int main ()
{
  SDL_Thread *id1, *id2;                //thread identifiers
  char *tnames[2] = { "Arnold", "Groper" };     //names of threads
                                                                               
                                                                               
  //create the threads
  id1 = SDL_CreateThread ( thread1, tnames[0] );
  id2 = SDL_CreateThread ( thread2, tnames[1] );
                                                                               
  //experiment with 20 seconds
  for ( int i = 0; i < 10; ++i )
      SDL_Delay ( 2000 );
                                                                               
  quit = true;                  //signal the threads to return
                                                                               
  //wait for the threads to exit
  SDL_WaitThread ( id1, NULL );
  SDL_WaitThread ( id2, NULL );
                                                                               
  return 0;
}
   
