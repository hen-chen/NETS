NOTE: INCLUDE the JSOUP.jar to run Main!


Each question corresponds to a certain method (e.g. question 1 refers to q1()).

backend code is in Wiki.java

Simply run Main and type the question and inputs into the console.

## Algorithms:
Q1: go to link with conservation status text. Iterate h3 tags and check for h3 tags with "IUCN".
Iterate the IUCN h3 tag next elements and pick the ul tag and its lis tags. Add each li tag to
the List of statuses.

Q2: Go to input species link. Select all dd tags. have a counter and if counter == input int
criteria, return text of dd tag. Else increment counter.

Q3: Go to List of (input species). Iterate through li tags and look for li tags that have text
that contains the status (extinct or extinct in the wild).

Q4: Look for link that has animal in its text. Iterate through a tags. If the a tag text contains 
"conservation status", then next a tag will be the conservation status of the input animal. Next, 
select all td tags. Iterate through td tags. 
if text of td contains the input classification, then next td tag
will have an a tag that holds the classification.

Q5: Look for link that has List of input species. Iterate through h2 tags. Check if h2 tags
have header than has the input type of animal (e.g. parrots). If so, iterate through
h2 tag next elements. If the next element is an h2 tag, that means we're done. If the next element
is an ul or div tag, iterate through their li tags and add them to result list.

Q6: Go to Link that has List of Mammals, then go to recently extinct mammals link. Iterate
through h2 tags, find h2 tag that contains "extinct species". Iterate through the next elements
of the h2 tag. find the table and then the first tbody tag. Iterate through tr tags.
If tr contains the input place, then add the first column that holds the animal to result list.

Q7: Go to link that has list of input species. Iterate through h3 tags that have text 
that is the input animal type (e.g. Lungless salamanders). Iterate through h3 next elements
and look for a div tag. Iterate through li tags and go to each link from the a tag. Look for 
the categories table at the bottom of the page and look for text that has "endemic fauna" or text 
that has both the input species and input place in the table. Add the animal to result list
if either of these are true.

Q8: Find input country on main page and go to that link. Iterate through th tags. look for th
tag that has "ethnic groups". Find parent of th tag and iterate through li tags. Add each text of
the li tag to the result list.
 

## Assumptions:

Q1: I assumed there was a link with name "conservation status" to a new wiki page. I assumed
there was some header h3 that has IUCN for determining the status of a species. I assumed
there would be a ul tag that has each of the statuses in an li tag.

Q2: I assumed that the criteria would be the first section of the wikipedia page that would have the 
dd html tags. I looked through the html page, and it was indeed true that the criteria section
was the only section that had dd tags. I also assumed that the redirect to the new wiki page
would be within the first few Critically Endangered links.

Q3: I assumed the list of endangered species would be a link "List of" + "Species"
I assumed that the necessary information would be in an li tag with a text containing:
"extinct or extinct in the wild".

Q4: I assumed the animal would be on the wiki page of endangered species as a link. I then 
assumed that the Conservation Status of the animal would be a link under the "Conservation status"
link. 

For the classification, I assumed that the Class will have a td tag and that the td will have text
that contains the class. For the species class, I assumed that it would be italicized 
with the i tag.

Q5: Assume that the name of the endangered species is on the website given to us. The link
to the endangered species website is in a form "List of" + name of species. I assumed that
the header, h2, would contain the name of the TYPE of species (Parrots in the example). I assumed
that h2 would always be a new section of TYPEs. From there, I assumed that the species we want to
find are in ul tags or div tags, where then all the species are found in li tags. 

Q6: I assumed the species (e.g. mammals) would be on the main page under "List of" + "species". I 
then went to this link. I then assumed that the prefix (e.g. recently extinct) 
would be on the new page as "List of" + "prefix". I then went to this page. I then assumed that
the header would be h2 and under this header would have a table of extinct species. I 
then assumed that the tr tag would hold a row for each animal. I then assumed that the first td
tag in the row would hold the animal name. I also assumed if the row had the text "place" 
(e.g. Australia), that the animal was from that "place".

Q7: I assumed the species (e.g. amphibians) would be in the IUCN Red List on the main page in 
the form of "List of" + "species" as a link. In this link, I assumed that there was a header, h3,
with the type (e.g. Lungless salamanders) of species. I assumed that all animals that fit under 
this type would be in the first div tag and in the div tag there would be a ul tag with a 
bunch of lis with links to learn if the animal is from the designated place (e.g. United States). 

After going to the link of the animal, I looked for a Categories label in an a tag. I then 
assumed that the following tag would be a ul tag where I could get the li tags. (I assumed this 
since it seems most wikipedia pages have a categories box at the bottom of the page). I assumed
that the place of the animal would be in the form of "Endemic fauna" + "place" and/or
"species" + "place". 

Q8: Get the ethnic groups/races of a country given as an input (a country). I assumed that each
wiki valid country would have a "ethnic groups" section on a table under a th tag. 
I assumed that the parent of the th tag would have a div tag that would contain li tags that
have information of the percentage of a certain ethnic group/race. I also assumed the country
could be found on the main given page.



## ANSWERS:

Q1: 
Extinct (EX) – No known living individuals
Extinct in the wild (EW) – Known only to survive in captivity, or as a naturalized population 
outside its historic range
Critically endangered (CR) – Extremely high risk of extinction in the wild
Endangered (EN) – High risk of extinction in the wild
Vulnerable (VU) – High risk of endangerment in the wild
Near threatened (NT) – Likely to become endangered in the near future
Conservation Dependent (CD) – Low risk; is conserved to prevent being near threatened, certain 
events may lead it to being a higher risk level
Least concern (LC) – Lowest risk; does not qualify for a higher risk category. Widespread and 
abundant taxa are included in this category.
Data deficient (DD) – Not enough data to make an assessment of its risk of extinction
Not evaluated (NE) – Has not yet been evaluated against the criteria.

Q2: C: Population Decline The population must decline to less than 250 MI and either: 
A decline of 25% over 3G/10Y Extreme fluctuations, or over 90% of MI in a single subpopulation, 
or no more than 50 MI in any one subpopulation.

Q3: 81 to 83 are extinct or extinct in the wild:

Q4: Status: Near Threatened
Classification: Mammalia

Q5: New Zealand kaka
Kea
White cockatoo
Baudin's black cockatoo
Carnaby's black cockatoo
Black-billed amazon
Yellow-naped amazon
Lilac-crowned amazon
Yellow-headed amazon
Vinaceous-breasted amazon
Red-crowned amazon
Lear's macaw
Sun parakeet
Grey-cheeked parakeet
Chatham parakeet
Coxen's fig-parrot
Red-and-blue lory
Purple-naped lory
Night parrot
Green-thighed parrot
Green racket-tail
Golden-shouldered parrot
Echo parakeet
Grey parrot
Timneh parrot
Santarem parakeet
Perija parakeet (Pyrrhura caeruleiceps)
Azuero parakeet (Pyrrhura eisenmanni)
El Oro parakeet
Pfrimer's parakeet
Santa Marta parakeet
Thick-billed parrot
Maroon-fronted parrot
Scarlet-breasted lorikeet
Kuhl's lorikeet

Q6: Broad-faced potoroo
Eastern hare wallaby
Lake Mackay hare-wallaby
Desert rat-kangaroo
Thylacine, or Tasmanian wolf/tiger
Toolache wallaby
Desert bandicoot
Lesser bilby, or Yallara
Pig-footed bandicoot
Crescent nail-tail wallaby
Nullarbor dwarf bettong
Bramble Cay melomys
Big-eared hopping mouse
Darling Downs hopping mouse
White-footed rabbit-rat
Capricorn rabbit rat
Short-tailed hopping mouse
Long-tailed hopping mouse
Great hopping mouse
Gould's mouse
Plains rat, or Palyoora
Lesser stick-nest rat, or white-tipped stick-nest rat
Blue-gray mouse
Dusky flying fox, or Percy Island flying fox
Lord Howe long-eared bat

Q7: Georgetown salamander (Eurycea naufragia)
Jollyville Plateau salamander (Eurycea tonkawae)
Berry Cave salamander (Gyrinophilus gulolineatus)
Weller's salamander (Plethodon welleri)
West Virginia spring salamander (Gyrinophilus subterraneus)
Inyo Mountains salamander (Batrachoseps campi)
Red Hills salamander (Phaeognathus hubrichti)
Siskiyou Mountains salamander (Plethodon stormi)

Q8: What are the ethnic groups and their percentages of the United States 
(underlined is United States)?
0.2% Pacific Islander
2.8% Multiracial
5.9% Asian
13.4% Black
76.3% White
1.3% Native American




