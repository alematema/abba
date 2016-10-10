# abba
This application tells either is possible or not to obtain a given string of characters A and B, from another string of characters A and B, through only two basic operations on initial string s.<br> Uses brute force to accomplish the task.<br> Has O(2^n), where n = target.length - initial.lenght. 

#The two allowed operations on initial string are <br>
 	*  adding an A to the end of the string - 0
 	*  reversing string and adding B to string's end - 1

# If it is possible obtaining the target string from the initial, then the application describes the sequence of operations on initial string that produces the target, as follows :<br>

 	 initial string : "BBBBABABBBBBBA"   <br>
	 target string : "BBBBABABBABBBBBBABABBBBBBBBABAABBBAA"
  
  
# The application outputs the following :
  
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


