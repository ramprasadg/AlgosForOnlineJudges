/*
http://www.codechef.com/problems/PALIN
*/
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
int main()
{
  char a[1000002];
  int test, i, l, mid; 
  bool all9s, greaterThanGiven ;
  scanf("%d\n",&test);
  while(test--)
  {
    gets(a);
    l=strlen(a);
    
    greaterThanGiven=false;
    
    all9s=true;
    for(i=0;i<l;i++)
    {
      if(a[i]!='9')
      {
        all9s=0;
        break;
      }
    }
    
    if(all9s==1)//answer will be of the form 10...01
    {
      a[0]='1';
      for(i=1; i<l; i++)
        a[i]='0';
      a[l]='1';
      
      a[l+1]='\0';
      greaterThanGiven=1;
    }
    
    if(greaterThanGiven != 1)
    {
      for(i=0;i<l/2;i++)
      {
        if(a[i]<a[l-1-i])
          greaterThanGiven = 0;
        else if(a[i]>a[l-1-i])
          greaterThanGiven = 1;
        a[l-1-i]=a[i];
      }
      
      if(l%2==0)
        mid=l/2-1;
      else 
        mid=l/2;

      if(greaterThanGiven != 1)
      {
        i=0;
        while(a[mid-i]=='9')
        {
          a[mid-i]='0';
          a[l-1-mid+i]='0';
          i++;
        }
        a[mid-i]++;
        a[l-1-mid+i]=a[mid-i];
      }
    }
    printf("%s\n",a);
  }
return 0;
}
