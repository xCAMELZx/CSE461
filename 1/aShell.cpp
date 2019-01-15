#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>
#include <string.h>

int main () {
char cmd[100], command[100], *parameters[20];
//enviornment variables

char *envp[]={(char *) "PATH=/bin", 0 };
while ( 1 ) {
	type_prompt(); // Repeat Forever
	read_command ( command, parameters ); //Display Prompt on Screen
	if (fork() !=0)  //Parent
		wait(NULL)     //Wait on Child
	else{
		strcpy ( cmd, "/bin/");
		strcat ( cmd, command);
		execve ( cmd, parameters, envp );  //Execute Command
}

if ( strcmp ( command, "exit" ) == 0)
	break; 
