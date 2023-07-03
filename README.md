## Section 23 Maven commands & Jenkins notes

Maven command to run each profile in pom.xml: 
	"mvn test -P<profile-id e.g. Regression> -e"
	
Maven command to run with specified browser:	
	"mvn test -PRegression -Dbrowser=firefox"
	
Jenkins instructions:
	"brew install jenkins-lts"
Start the Jenkins service: 
	"brew services start jenkins-lts"
Restart the Jenkins service: 
	'brew services restart jenkins-lts"
Update the Jenkins version: 
	"brew upgrade jenkins-lts"
	
Run jenkins using .war file.
1. Locate folder where .war file resides eg ~
2. Execute command:
	"java -jar jenkins.war --httpPort=9090"	
	
Jenkins First Admin User
shellymutu-grigg
-- see bitwarden

If in trouble delete .jenkins folder in /Users/shellymutu-grigg.  
WARNING you have to reinstall Jenkins plugins >12 mins

Maven command:
	"mvn test -PPurchase -Dbrowser=chromeheadless"
is the same as if run using same parameters in Jenkins

Jenkins scheduling:
Project > Configure > Build Triggers > Build periodically
1st parameter: MINUTES 	- minute of the hour to begin execution (0 - 59)
2nd parameter: HOURS 	- hour to begin execution (0 - 23)
3rd parameter: DAYMONTH - day in the month to begin execution (1 - 31)
4th parameter: MONTH 	- month to begin execution (1 - 12)
5th parameter: DAYWEEK 	- day of the week to begin execution (0 - 7) 0 & 7 are Sunday