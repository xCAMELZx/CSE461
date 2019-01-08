#include "type.s"
#include "stat.h"
#include "user.h"
#include "fcntl.h"

char buf[512];
 int main(int argc, char *argv[]) {
 int fd0, fd1, n;

  if (argv <= 2) {
    printf(1, "Need 2 Arguements!\n");
    exit();
    }

  if((fd0 = open(argv[1], O_RDONLY))<0) {
      printf(1, "cp: Cannot Open %s\n", argv[1]);
      exit();
    }
  if((fd1 = open(argv[2], O_CREATE|O_RDWR))<0) {
        printf(1, "cp: Cannot Open %s\n", argv[2]);
        exit();
      }
  while((n = read ( fd0, buf, sizeof(buf)))>0){
    write (fd1, buf, n);
  }
  close(fd0);
  close(fd1);

exit();
  }
