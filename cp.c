/***************************************************************************************************
 * cp.c
 * By: Yousef Jarrar and Jose Perez
 * CSE 461 Lab 1
 **************************************************************************************************/
#include "types.h"
#include "stat.h"
#include "user.h"
#include "fcntl.h"

char buf[512];
int main(int argc, char *argv[]) {
  int fd0, fd1, fd2, n;
  if (argv <= 2) {
    printf(1, "Need 2 Arguements!\n");
    exit();
  }
  
  for (int i =2; i<=argc; i++){
    //opens README file
    if((fd0 = open(argv[1], O_RDONLY))<0) {
      printf(1, "cp: Cannot Open %s\n", argv[1]);
      exit();
    }

    //opens myFile1
    if((fd1 = open(argv[2], O_CREATE|O_RDWR))<0) {
      printf(1, "cp: Cannot Open %s\n", argv[2]);
      exit();
    }
    
    //opens the myFile2
    if((fd2 = open(argv[3], O_CREATE|O_RDWR))<0) {
      printf(1, "cp: Cannot Open %s\n", argv[3]);
      exit();
    }
    
    while((n = read ( fd0, buf, sizeof(buf)))>0){
      //writes onto the myFiles
      write (fd1, buf, n);
      write (fd2, buf, n);
    }
    //closes README File
    close(fd0);

    //closes myFile1 and myFile2
    close(fd1);
    close(fd2);
  }
  exit();
}
