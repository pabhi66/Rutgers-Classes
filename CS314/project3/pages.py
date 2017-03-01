import subprocess
import re

# Calls the R system specifying that commands come from file commands.R
# The commands.R provided with this assignment will read the file named
# data and will output a histogram of that data to the file pageshist.pdf
def runR( ):
    res = subprocess.call(['R', '-f', 'commands.R'])

# log2hist analyzes a log file to calculate the total number of pages
# printed by each user during the period represented by this log file,
# and uses R to produce a pdf file pageshist.pdf showing a histogram
# of these totals.  logfilename is a string which is the name of the
# log file to analyze.
#
def log2hist(logfilename):
	#create user array
    users_array = {}
    #open the log file and store it it log file
    log_file = open(logfilename, 'r')

    print_regex = re.compile('user:\s*(?P<user>.*)\s*')
    print_pages = re.compile('printer:.*?pages:\s*(?P<pages>\d+)')

    #with file open
    with log_file as file:
    	#loop through the file and read line by line
        for line in file.readlines():
        	#split the lines and store it in tokens
            tokens = line.split()

            #search for user and pages
            z = print_regex.search(line)
            a = print_pages.search(line)

            #if there is "user" in the word then make a user and store all the related info
            if 'user:' in tokens:
            	#store user to user
            	x = tokens[tokens.index('user:')+1]
                #store user's pages in pages
                y = tokens[tokens.index('pages:')+1]

                #assign x and y to user and number of pages to be printed
                user = x
                pages = int(y)
                #add the use to user array
                users_array[user] = users_array.setdefault(user, 0) + pages

    #write the data to the file
    write = open('data', 'w')            
    with write as file:
        for pages in users_array.itervalues():
            file.write("%d\n" % pages)

    #close the file
    write.close()
    #run the R system command        
    runR()

if __name__ == '__main__':
    log2hist("log")