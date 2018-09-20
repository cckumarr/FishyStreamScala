# Fishy Stream in scala

Is a goofy example for demonstrating how steaming works and what functionalities it can bring along with it in scala.

## The things that should be demonstrated are
  - [x] accept msgs from port
  - [x] filtering
  - [ ] aggregating
  - [x] counts  
more ambitious 
  - [ ] using some built in machine learning algo
  
## what does the application do
As a fish merchant, yes a fish merchant,  
we want to keep track all the fish caught and sold,  
this should include the different kinds of fish,  
we also want an upto date inventory of the fish we have.  
we also want to weed out all the other things caught in the sea, that we are not interested in.

and possibily:  
get a prediction on the number of fish that will be caught and sold in a particular time period,  
number of fish sold in the last x time period  
the fictional fish are caught on boats and we keep a count of them.
 
## FAQ:
why use a queue instead of directly hitting the flink port like most youtube/flink presentations ?
wanted to learn to use rabbitmq.


  