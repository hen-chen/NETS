import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Wiki {
    
    String baseUrl; 
    Document currentDoc;
    
    static final String SOURCE_LINK = "https://wikipedia.org";
    
    public Wiki(String url) {
        this.baseUrl = url;
        try {
            this.currentDoc = Jsoup.connect(this.baseUrl).get();
        } catch (Exception e) {
            System.out.println("Could not reach URL");
        }
    }
    /**
     * Finds all current statuses for question 1
     * @return
     */
    List<String> q1() {
        List<String> result = new ArrayList<String>();
        String newUrl = redirectURL("conservation status", this);
        
        Wiki newWiki = new Wiki(newUrl);
        Elements h3s = newWiki.currentDoc.select("h3");
        for (Element h3 : h3s) {
            String header = h3.text();
            if (header.contains("IUCN")) { // look for IUCN header 
                Elements neighbors = h3.nextElementSiblings();
                for (Element n : neighbors) {
                    if (n.tagName().equals("ul")) {
                        Elements lis = n.select("li");
                        for (Element li : lis) {
                            result.add(li.text());
                        }
                        return result;
                    }
                }
            }
        }
        return result;
    }
    /**
     * Returns the nth criteria of a species
     * @param species - e.g. the critically endangered
     * @param criteria - e.g. 3
     * @return
     */
    String q2(String species, int criteria) {
        species = species.toLowerCase();
        String newUrl = redirectURL(species, this);
        
        Wiki newWiki = new Wiki(newUrl); //make new object with new wiki page
        Elements dds = newWiki.currentDoc.select("dd");
        int counter = 1;
        for (Element dd : dds) {
            if (counter == criteria) {
                String info = dd.text();
                return info;
            } else {
                counter++;
            }
        }
        return null;
    }
    /**
     * Find how many species are extinct or extinct in the wild.
     * @param species - e.g. mammals
     * @param status - e.g. extinct or extinct in the wild
     * @return
     */
    String q3(String species, String status) {
        status = status.toLowerCase();
        species = species.toLowerCase();
        String newUrl = redirectURLList(species, this);
        
        Wiki newWiki = new Wiki(newUrl);
        Elements lis = newWiki.currentDoc.select("li"); 
        for (Element li : lis) {
            String text = li.ownText().toLowerCase();
            if (text.contains(status)) {
                return text;
            }
        }
        return null;
    }
    /**
     * Get conservation status of a species as well as their scientific classification
     * @param species - e.g. white rhinoceros
     * @param classification - e.g. Class
     * @return
     */
    List<String> q4(String species, String classification) {
        species = species.toLowerCase();
        classification = classification.toLowerCase();
        
        List<String> result = new ArrayList<String>();
        String newUrl = redirectURL(species, this);

        Wiki newWiki = new Wiki(newUrl);
        Elements as = newWiki.currentDoc.select("a");
                
        boolean conservationStatus = false;
        
        for (Element a : as) { // get Conservation status first as result[0]
            String url = a.text().toLowerCase();
            if (conservationStatus) {
                String title = a.text();
                result.add(title);
                break;
            }
            if (url.contains("conservation status")) {
                conservationStatus = true; // the next link a will be the conservation status
            }
        }
        // now get the Scientific classification as result[1];
        
        boolean getClassification = false;
        Elements tds = newWiki.currentDoc.select("td");
        for (Element td : tds) {
            if (getClassification) {
                Element link = td.select("a").first();
                if (classification.contains("species")) {
                    link = td.select("i").first(); // species is not an a tag
                }
                String text = link.text();
                result.add(text);
                return result;    
            }
            String text = td.text().toLowerCase();
            if (text.contains(classification)) {
                getClassification = true; // next td tag will be the classification
            }
        }
        return result;
    }
    /**
     * Get the species of type animal that are endangered. In the example, species is
     * Endangered birds, and type is Parrots.
     * @param Endangered species
     * @param type animal
     * @return
     */
    List<String> q5(String species, String type) {
        type = type.toLowerCase();
        species = species.toLowerCase();
        List<String> result = new ArrayList<String>();
        String newUrl = redirectURLList(species, this); 
        
        Wiki newWiki = new Wiki(newUrl);

        Elements h2s = newWiki.currentDoc.select("h2");
        for (Element h2 : h2s) {
            String typeOfSpecies = h2.text(); // find header name

            if (typeOfSpecies.toLowerCase().contains(type)) { // look for header that has TYPE name
                Elements between = h2.nextElementSiblings(); // find all elements after this header
                for (Element elt : between) {
                    if (elt.tagName().equals("h2")) { // done since found new TYPE
                        return result;
                    }
                    if (elt.tagName().equals("ul")) {
                        Elements children = elt.children(); // all species found under li tag
                        for (Element li : children) {
                            String spec = li.text();
                            result.add(spec);
                        }
                    } else if (elt.tagName().equals("div")) { // some species under div
                        Elements lis = elt.select("li");
                        for (Element li : lis) {
                            String spec = li.text();
                            result.add(spec);
                        }
                    }
                }

            }
        }
        return result;
    }
    /**
     * Find list of recently extinct species in continent
     * (In the example, continent is Australia, species is mammals, and speciesPrefix
     * is recently extinct)
     * @param continent
     * @param species
     * @param speciesPrefix
     * @return
     */
    List<String> q6(String continent, String species, String speciesPrefix) {
        species = species.toLowerCase();
        speciesPrefix = speciesPrefix.toLowerCase();
        
        List<String> result = new ArrayList<String>();
        String newUrl = redirectURLList(species, this);
        Wiki newWiki = new Wiki(newUrl);
        
        newUrl = redirectURLList(speciesPrefix, newWiki);
        Wiki newerWiki = new Wiki(newUrl);

        Elements h2s = newerWiki.currentDoc.select("h2");
        for (Element h2 : h2s) {
            String hName = h2.text().toLowerCase(); // find header name
            if (hName.contains("extinct species")) { // look for table with "Extinct species"
                Elements between = h2.nextElementSiblings();
                for (Element elt : between) { 
                    if (elt.tagName().equals("table")) { // find table tag
                        Element tbody = elt.select("tbody").first(); // find table body
                        Elements trs = tbody.select("tr");
                        for (Element tr : trs) { // each tr tag is a row in the table
                            if (tr.text().contains(continent)) {
                                Element td = tr.child(0); // animal name in first col
                                result.add(td.text());
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
    /**
     * Returns list of animals of a certain type of a species that are in a certain place
     * @param species - e.g. amphibians
     * @param type - e.g. Lungless salamanders
     * @param place - e.g. United States
     * @return
     */
    Set<String> q7(String species, String type, String place) {
        type = type.toLowerCase();
        place = place.toLowerCase();
        species = species.toLowerCase();
        
        Set<String> result = new HashSet<String>();
        String newUrl = redirectURLList(species, this);
        Wiki newWiki = new Wiki(newUrl);
        
        Elements h3s = newWiki.currentDoc.select("h3");
        for (Element h3 : h3s) {
            Elements spans = h3.select("span");
            for (Element span : spans) {
                String noUnderscoresH3 = span.id().replaceAll("_", " ").toLowerCase();
                if (noUnderscoresH3.equals(type)) {
                    Elements between = h3.nextElementSiblings();
                    for (Element elt : between) {
                        if (elt.tagName().equals("div")) {
                            Element ul = elt.select("ul").first();
                            Elements lis = ul.select("li");
                            return this.q7Helper(result, lis, place, species);
                        }
                    }
                }
            }
        }
        return result;
    }
    /**
     * Helper that for q7 that returns the list of animals under the right criteria for q7.
     * @param result
     * @param lis
     * @param place
     * @param species
     * @return
     */
    Set<String> q7Helper(Set<String> result, Elements lis, String place, String species) {
        String[] words = species.split("\\s+"); // look out for any preceding words
        species = words[words.length - 1]; // get the species
        
        for (Element li : lis) {
            String name = li.text();
            
            Element aLi = li.select("a").first();
            String url = aLi.attr("href");
            
            String newUrl = SOURCE_LINK + url;
            Wiki newWiki = new Wiki(newUrl);

            Elements as = newWiki.currentDoc.select("a");
            for (Element a : as) {
                if (a.text().toLowerCase().equals("categories") 
                        && a.attr("title").toLowerCase().equals("help:category")) {
                    Element ul = a.nextElementSibling(); // assuming next elt is a ul tag
                    Elements newLis = ul.select("li");
                    for (Element newLi : newLis) {
                        String text = newLi.text().toLowerCase();
                        if (text.contains("endemic fauna") && text.contains(place)) {
                            result.add(name);
                        } else if (text.contains(species) && text.contains(place)) {
                            result.add(name);
                        }
                    }
                }
            }
        }
        return result;
    }
    /**
     * Return the ethnic groups of a country
     * @param newInfo - name of the country - e.g. united states
     * @return
     */
    Set<String> q8(String country) {
        country = country.toLowerCase();
        Set<String> result = new HashSet<String>();
       
        String newUrl = redirectURL(country, this);
        Wiki newWiki = new Wiki(newUrl);
        
        Elements ths = newWiki.currentDoc.select("th");
        for (Element th : ths) {
            String text = th.text().toLowerCase();
            if (text.contains("ethnic groups")) {
                Element parent = th.parent();
                Element infoBox = parent.select("td").first();
                Element race = infoBox.selectFirst("div");
                Elements groups = race.select("li");
                for (Element group : groups) {
                    result.add(group.text());
                }
            }
        }
        return result;
    }
    /**
     * Helper to get redirected URL from given endangered species wiki page
     * @param data
     * @return
     */
    private String redirectURL(String data, Wiki page) {
        String newUrl = "";
        
        Elements links = page.currentDoc.select("a");
        for (Element l : links) { // redirect new URL
            String linkName = l.text().toLowerCase();
            String url = l.attr("href").toLowerCase();
            if (linkName.equals(data)) {
                newUrl = SOURCE_LINK + url;
                break;
            }
        }
        return newUrl;
    }
    /**
     * Helper to get redirectURL that has List of + species
     * @param species
     * @return
     */
    private String redirectURLList(String species, Wiki page) {
        String newUrl = "";
        Elements links = page.currentDoc.select("a");
        for (Element l : links) { // redirect new URL
            String linkName = l.text();
            String url = l.attr("href");
            if (linkName.contains(species) && linkName.contains("List of")) {
                newUrl = SOURCE_LINK + url;
                break;
            }
        }
        return newUrl;
    }
}
