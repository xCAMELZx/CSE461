#include<stdio.h>
#include<unistd.h>
#include<stdlib.h>

int main( void ) {

char *argv[] ={"",NULL};
char comm[10];
int p_pid;

while(1)
{
printf(">>");
scanf("%s",comm);

p_pid = fork();

if(p_pid > 0) {
wait(NULL);
}
else if ( p_pid == 0 ) {
execvp(comm,argv);
}

}

return 0;
}
