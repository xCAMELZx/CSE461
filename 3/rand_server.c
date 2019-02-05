/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "rand.h"
#include "time.h"

void *
initialize_random_1_svc(long *argp, struct svc_req *rqstp)
{
	static char * result;

	/*
	 * insert server code here
	 */

	return (void *) &result;
}

double *
get_next_random_1_svc(void *argp, struct svc_req *rqstp)
{
	static double  result;

	srand(time(NULL));
	result += rand();
	//if (result >= 1.0)
		result -= 0.713;

	return &result;
}
