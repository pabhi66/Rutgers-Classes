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


# Drinkers who like only Bud
## using exists
SELECT l1.drinker
FROM likes l1
WHERE l1.beer = 'Bud'
    AND NOT EXISTS(SELECT l2.drinker
                   FROM likes l2
                   WHERE l1.drinker = l2.drinker AND l2.beer <> 'Bud')

## using in
SELECT l1.drinker
FROM likes l1
WHERE l1.beer = 'Bud'
    AND l1.drinker NOT IN(SELECT l2.drinker
                          FROM likes l2
                          WHERE l2.beer <> 'Bud')


# Beers which every drinker who frequents Cabana likes
SELECT DISTINCT l.beer
FROM frequents f, likes l
WHERE f.drinker = l.drinker
    AND f.bar = 'Cabana'
    AND NOT EXISTS(SELECT f2.drinker
                   FROM frequents f2
                   WHERE f2.bar = 'Cabana'
                       AND l.beer NOT IN(SELECT l2.beer
                                         FROM likes l2
                                         WHERE l2.drinker = f2.drinker))


# Bars which serve only beer(s) which Joe likes
SELECT s.bar
FROM sells s, likes l
WHERE l.drinker = 'Joe' 
    AND NOT EXISTS(SELECT s2.beer
                   FROM sells s2
                   WHERE s.bar = s2.bar 
                       AND s2.beer NOT IN(SELECT l2.beer
                                          FROM likes l2
                                          WHERE l.drinker = l2.drinker))


# Bars which every drinker frequents
SELECT DISTINCT f.bar
FROM frequents f
WHERE NOT EXISTS(SELECT d.name
                 FROM drinkers d
                 WHERE d.name NOT IN(SELECT f2.drinker
                                     FROM frequents f2
                                     WHERE f.bar = f2.bar))


# Drinkers who frequent exactly one bar of bar(s) which Joe frequents
SELECT DISTINCT f.drinker
FROM frequents f
WHERE f.bar IN (SELECT f2.bar
                FROM frequents f2
                WHERE f2.drinker = 'Joe')
                    AND NOT EXISTS(SELECT f3.bar
                                   FROM frequents f3
                                   WHERE f.drinker = f3.drinker
                                       AND f.bar <> f3.bar
                                       AND f3.bar IN (SELECT f2.bar
                                                      FROM frequents f2
                                                      WHERE f2.drinker = 'Joe'))


# Bars which no drinker frequents
SELECT DISTINCT b.name
FROM bars b
WHERE b.name NOT IN(SELECT f.bar
                    FROM frequents f
                    WHERE f.drinker IS NOT NULL)
