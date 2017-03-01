##########################################
#                                        #
# Schema                                 #
#                                        #
# Beers(name, manf)                      #
# Bars(name, license, city, phone, addr) #
# Drinkers(name, city, phone, addr)      #
# Likes(drinker, beer)                   #
# Sells(bar, beer, price)                #
# Frequents(drinker, bar)                #
#                                        #
##########################################



# Bars which sell Michelob Amber Bock or Budweiser
SELECT DISTINCT bar
FROM sells
WHERE beer = 'Michelob Amber Bock' OR beer = 'Budweiser'

# Drinkers who like some beers other than Budweiser (they do not have to like Budweiser)
SELECT DISTINCT drinker
FROM likes
WHERE beer != 'Budweiser'

# Beers other than Hefeweizen which are served by Gecko Grill bar
SELECT DISTINCT beer
FROM sells
WHERE bar = 'Gecko Grill' AND beer != 'Hefeweizen'

# Bars who serve some beers which John likes
SELECT DISTINCT s.bar
FROM sells s, likes l
WHERE l.drinker = 'John' AND l.beer = s.beer

# Drinkers who frequent bars which serve Original Premium Lager Dog
SELECT DISTINCT f.drinker
FROM frequents f, sells s
WHERE f.bar = s.bar AND s.beer = 'Original Premium Lager Dog'

# Bars which are frequented by John or Rebecca but not by both of them
SELECT DISTINCT f.bar
FROM frequents f
WHERE (f.drinker = 'John' OR f.drinker = 'Rebecca') 
    AND NOT EXISTS (SELECT f1.bar
                    FROM frequents f1, frequents f2
                    WHERE f1.drinker = 'John' AND f2.drinker = 'Rebecca' 
                        AND f.bar = f1.bar AND f1.bar = f2.bar
                   )

# Drinkers who frequent at least three bars
## using a triple join
SELECT DISTINCT f1.drinker
FROM frequents f1, frequents f2, frequents f3
WHERE f1.drinker = f2.drinker AND f2.drinker = f3.drinker AND f1.bar != f2.bar 
	AND f2.bar != f3.bar AND f1.bar != f3.bar
## using COUNT function
SELECT DISTINCT f1.drinker
FROM frequents f1
WHERE (SELECT COUNT(DISTINCT bar)
       FROM frequents f2
       WHERE f1.drinker = f2.drinker) >= 3

# Drinkers who frequent bars which sell some beer they like
SELECT DISTINCT f.drinker
FROM frequents f, likes l, sells s
WHERE l.drinker = f.drinker AND s.beer = l.beer AND f.bar = s.bar

# Beers which are served by bars which John frequents
SELECT DISTINCT s.beer
FROM frequents f, sells s
WHERE f.drinker = 'John' AND f.bar = s.bar

# Bars which serve some beers which John or Rebecca like and which are cheaper than $5
SELECT DISTINCT s.bar
FROM likes l, sells s
WHERE l.beer = s.beer AND (l.drinker = 'John' OR l.drinker = 'Rebecca') AND s.price < 5

# Bars which serve some beers which Mike likes or which John likes
SELECT DISTINCT s.bar
FROM likes l, sells s
WHERE l.beer = s.beer AND (l.drinker = 'John' OR l.drinker = 'Mike')

# Drinkers who like both Hefeweizen and Killian's
SELECT DISTINCT l1.drinker
FROM likes l1, likes l2
WHERE l1.drinker = l2.drinker AND l1.beer = 'Hefeweizen' AND l2.beer = 'Killian''s'

# List all bars, beers they sell and beer prices in euros  
SELECT bar, beer, price*.89 AS priceInEuros
FROM sells

# Bars with names containing "The"
SELECT DISTINCT name
FROM bars
WHERE name LIKE '%The%'

# Drinkers who like Killian's and who also frequent Gecko Grill
SELECT DISTINCT l.drinker
FROM likes l, frequents f
WHERE l.drinker = f.drinker AND l.beer = 'Killian''s' AND f.bar = 'Gecko Grill'
