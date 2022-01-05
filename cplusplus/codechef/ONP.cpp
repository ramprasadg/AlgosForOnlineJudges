/*http://www.codechef.com/problems/ONP
*Problem: to generate Reverse Polish Notation (RPN)
*/
#include <stdio.h>
#include <stack>
using namespace std;

void eval(char *str);
stack<char> mystack;

int main()
{
    int level;
    char str[400];
    
    int tests;
    scanf("%d",&tests);
    int i;
    for(i=0;i<tests;i++)
    {
	    scanf("%s",str);
	    eval(str);
        printf("\n");
    }
    return 0;
}

void eval(char *str)
{
    int i=0;
    int flag=0;
    while(str[i]!='\0')
    {
	if((str[i]>='a' && str[i]<='z')|| (str[i]>='A'&& str[i]<='Z')) //variables
	{
	    printf("%c",str[i]);
	    if(flag==1)
	    {
		    flag=0;
		    printf("%c",mystack.top());
		    mystack.pop();
	    }
	    else
	        flag=1;
	}
	else //operators
	{
	    if(str[i]=='(') {
            flag=0;
        }
        
        if(str[i]==')')
        {
	        while(!mystack.empty())
	        {
                char top = mystack.top();
                mystack.pop();
                
                if (top != '(')
	                printf("%c",top);
                else
                    break;
         	}
         } 
         else
    	    mystack.push(str[i]);
    }
    i++;
    }
    
    while(!mystack.empty())
    {
        char top = mystack.top();
        mystack.pop();
        printf("%c",top);
    }
}
