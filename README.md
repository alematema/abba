# abba
This application tells either is possible or not to obtain a given target string of characters A and B, from another given initial string of characters A and B, through only two basic operations on initial string s. Uses brute force for accomplish the task. Has O(2^n), where n = target.length - initial.lenght. 

#The allowed operations on initial string are <br>
 	*  adding an A to the end of the string <br>
 	*  reversing string and adding B to string's end

# If is possible obtain target string from the initial, then the application describes how operates on initial string to obtain the target string , as follows :<br>

 	 initial string : "BBBBABABBBBBBA"   <br>
	 target string : "BBBBABABBABBBBBBABABBBBBBBBABAABBBAA"
  
  
# The applications outputs the following :
  
  -------------------------------------------------------------------------------------------------------------------------------------------------

describing operation 1111011010110011111100 on initial string BBBBABABBBBBBA to get target string BBBBABABBABBBBBBABABBBBBBBBABAABBBAA -------------------------------------

	0/1 		meaning				results			target

	 1	reverses s and adds B to  s' end  	ABBBBBBABABBBBB			BBBBABABBABBBBBBABABBBBBBBBABAABBBAA
	 1	reverses s and adds B to  s' end  	BBBBBABABBBBBBAB			BBBBABABBABBBBBBABABBBBBBBBABAABBBAA
	 1	reverses s and adds B to  s' end  	BABBBBBBABABBBBBB			BBBBABABBABBBBBBABABBBBBBBBABAABBBAA
	 1	reverses s and adds B to  s' end  	BBBBBBABABBBBBBABB			BBBBABABBABBBBBBABABBBBBBBBABAABBBAA
	 0	adds  A to the end of the string 	BBBBBBABABBBBBBABBA			BBBBABABBABBBBBBABABBBBBBBBABAABBBAA
	 1	reverses s and adds B to  s' end  	ABBABBBBBBABABBBBBBB			BBBBABABBABBBBBBABABBBBBBBBABAABBBAA
	 1	reverses s and adds B to  s' end  	BBBBBBBABABBBBBBABBAB			BBBBABABBABBBBBBABABBBBBBBBABAABBBAA
	 0	adds  A to the end of the string 	BBBBBBBABABBBBBBABBABA			BBBBABABBABBBBBBABABBBBBBBBABAABBBAA
	 1	reverses s and adds B to  s' end  	ABABBABBBBBBABABBBBBBBB			BBBBABABBABBBBBBABABBBBBBBBABAABBBAA
	 0	adds  A to the end of the string 	ABABBABBBBBBABABBBBBBBBA			BBBBABABBABBBBBBABABBBBBBBBABAABBBAA
	 1	reverses s and adds B to  s' end  	ABBBBBBBBABABBBBBBABBABAB			BBBBABABBABBBBBBABABBBBBBBBABAABBBAA
	 1	reverses s and adds B to  s' end  	BABABBABBBBBBABABBBBBBBBAB			BBBBABABBABBBBBBABABBBBBBBBABAABBBAA
	 0	adds  A to the end of the string 	BABABBABBBBBBABABBBBBBBBABA			BBBBABABBABBBBBBABABBBBBBBBABAABBBAA
	 0	adds  A to the end of the string 	BABABBABBBBBBABABBBBBBBBABAA			BBBBABABBABBBBBBABABBBBBBBBABAABBBAA
	 1	reverses s and adds B to  s' end  	AABABBBBBBBBABABBBBBBABBABABB			BBBBABABBABBBBBBABABBBBBBBBABAABBBAA
	 1	reverses s and adds B to  s' end  	BBABABBABBBBBBABABBBBBBBBABAAB			BBBBABABBABBBBBBABABBBBBBBBABAABBBAA
	 1	reverses s and adds B to  s' end  	BAABABBBBBBBBABABBBBBBABBABABBB			BBBBABABBABBBBBBABABBBBBBBBABAABBBAA
	 1	reverses s and adds B to  s' end  	BBBABABBABBBBBBABABBBBBBBBABAABB			BBBBABABBABBBBBBABABBBBBBBBABAABBBAA
	 1	reverses s and adds B to  s' end  	BBAABABBBBBBBBABABBBBBBABBABABBBB			BBBBABABBABBBBBBABABBBBBBBBABAABBBAA
	 1	reverses s and adds B to  s' end  	BBBBABABBABBBBBBABABBBBBBBBABAABBB			BBBBABABBABBBBBBABABBBBBBBBABAABBBAA
	 0	adds  A to the end of the string 	BBBBABABBABBBBBBABABBBBBBBBABAABBBA			BBBBABABBABBBBBBABABBBBBBBBABAABBBAA
	 0	adds  A to the end of the string 	BBBBABABBABBBBBBABABBBBBBBBABAABBBAA			BBBBABABBABBBBBBABABBBBBBBBABAABBBAA

-------------------------------------------------------------------------------------------------------------------------------------------------

Possible to obtain  BBBBABABBABBBBBBABABBBBBBBBABAABBBAA from BBBBABABBBBBBA	


