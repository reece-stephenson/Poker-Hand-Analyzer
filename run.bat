ECHO Building...
CALL mvn package
echo Build finished, now running program
cd target
java -jar Poker-Hand-Analyzer-1.0.jar
cd ../ 
pause