import requests
from bs4 import BeautifulSoup

x = 2
for x in xrange(2,356):
	url ="http://www.collegetuitioncompare.com/search/?program=01.000004.000005.000026.000052.000009.000011.000010.000046.000013.000014.000015.000023.000016.000051.000054.000043.000044.000022.000024.000025.000027.000047.000029.000030.000003.000031.000012.000038.000040.000048.000042.000041.000045.000039.000049.000050.000019.0000&page=" + str(x)
	r = requests.get(url)

	soup = BeautifulSoup(r.content)

	links = soup.find_all("a")

	for link in soup.find_all("a"):
		print link.text