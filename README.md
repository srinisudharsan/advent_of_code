# Advent of Code
Advent of code posts questions here: https://adventofcode.com/.

# Folder Structure
- All the code is inside src/main/java.
- The input files are under resources/input<day>.txt
- Questions are inside com/adventofcode/day<daynum>/question.txt

# How to run
1. Install latest java
2. Go to src/main/java
3. Run `javac -d . com/adventofcode/day<daynum>/part<num>/Main.java`
4. Run `java com.adventofcode.day<daynum>.part<num>.Main`

# Approach 
No reason to choose a particular programming language for a question unless stated otherwise. I have used several approaches like using a ThreadPool Executor for reading each line of code and some complicated algorithms which are not strictly required or might not even be the most optimal given the size of input files. I still made these decisions to take these as a learning opportunity. To try out such experiments, I did not bother to abstract common code. I also did not care much about class design, rather focused on programming language constructs and algorithms.

# Notes on each problem:

## Day1
### Part1
Does basic char comparison. Could have used Character.isDigit(), but it is not going to do any better.

### Part2
Basic approach is to use a List with all strings, look for each string in the main string and find the one that appears first and do the same for the one that appears last. This is O(n^2). This would have been sufficient for this problem, but just for exploration, applied **Aho-Corasick** algorithm to find all the strings in the main string. This is O(n+m) runtime where n is the length of main string and M is the number of matches found. This would almost always be faster than O(n^2). Also used a threadpool to parallelize. Golang or C# would have made this much easier due to goroutines and async/await. Note that AhoCorasick is only used to find the text part. We still use isDigit to see if a number has gotten ahead. Reason for this is to keep the AhoCorasick part simple. We can easily make "1", "2"... as separate strings and adjust the alphabet size and values to accomodate for this change.
