"""
author: Andrew Berson (axb4069@rit.edu)
This python script uses the Gutenberg API to grab a large known range
of poems from the Gutenberg database.
Stores results in a plaintext file called 'allpoems.txt'
"""
import gutenberg
outfile = open("allpoems.txt","w")
from gutenberg.acquire import load_etext
from gutenberg.cleanup import strip_headers
#this loop was stolen from stackOverflow
id_list = [12924, 16786] # Gutenberg ID numbers for all poems
for id_nr in id_list: # loop over list
    text = strip_headers(load_etext(id_nr)).strip()
    # Write all of the lines of Gutenberg poetry to the outfile
    outfile.write(text)
outfile.flush()
outfile.close()
