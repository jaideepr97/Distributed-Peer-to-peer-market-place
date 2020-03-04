# Readme - Test

The directory contains ten test cases, each depicting a different network topology. Each testcase is a shell script, which can be run as follows:  
 <pre>
 chmod +x test1.sh
 ./test1.sh
</pre>
to produce output files of the format 
<pre>
"PeerID_Testcase_output.txt"
</pre>
Each output file will contain details about the peer -   
1. The role of the peer (buyer/seller/both)  
2. The product which the peer is buying/selling  
3. Transactions    
Its very easy to verify the correctness of the system. For each buy message in a buyer node, there should be a corresponding sell message in the seller node. We can easily find all matching pairs, and if there are no odd ones left (only buy no sell or vice versa) then the system works correctly. More details about the topology is given in the test document.


