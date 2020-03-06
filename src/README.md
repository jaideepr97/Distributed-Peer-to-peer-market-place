#How to run

This system requires sufficient resources in terms of available memory, and cpu power. Please ensure these requirements are met on the testing platform. This program should ideally be run in isolation to ensure availability of specified ports for each node on all the machines in use

1) Ssh into EDLAB machines 1,2, 3 and 7
2) Clone the git repository: https://github.com/ds-umass/lab-1-rao-gupta.git
3) Navigate to the /test directory to find the test scripts.
4) The README file within the /test directory would include the details around the specific scripts that correspond to each test case and how they are to be executed 
5) Each test script can be executed by running ./file-name.sh for example. This would compile and run the entire program for the given test.
6) The program is set to run for a period of 2 minutes, after which all the generated processes are automatically terminated, and a set of output files should be generated within the same directory. Depending on the test that is executed, a different number of output files will be generated (one for each node in the topology that was executed)
7) The output files show the sequence of events that occurred with respect to a particular node and also include the time-based performance metrics for each node
