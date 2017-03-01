#Abhishek Prajapati
#CS336 HW 1

#Query 1 - Bars which sell Michelob Amber Bock or Budweiser
select bar 
from BarBeerDrinker.sells 
where beer = 'Michelob Amber Bock' OR beer = 'Budweiser';

#Query 2 - Drinkers who like some beers other than Budweiser (they do not have to like Budweiser)
select distinct drinker 
from BarBeerDrinker.likes 
where beer <> 'Budwiser';

#Query 3 -  Beers other than Hefeweizen which are served by Gecko Grill bar
select beer 
from BarBeerDrinker.sells 
where bar = 'Gecko Grill' AND beer <> 'Hefeweizen';

#Query 4 - Bars who serve some beers which John likes
select distinct bar 
from BarBeerDrinker.sells S 
join (select beer from BarBeerDrinker.likes where drinker = 'john')J 
on S.beer = J.beer;
#select distinct bar from BarBeerDrinker.sells, BarBeerDrinker.likes where ( likes.drinker = 'John' AND sells.beer = likes.beer);

#Query 5 - Drinkers who frequent bars which serve Original Premium Lager Dog
select distinct 
	drinker 
from 
	BarBeerDrinker.frequents S 
join
	(select bar from BarBeerDrinker.sells where beer = 'Original Premium Lager Dog')J 
on 
	S.bar = J.bar;

#Query 6 - Bars which are frequented by John or Rebecca but not by both of them
select 
	f.bar 
from 
	BarBeerDrinker.frequents f 
where 
	(drinker = 'John' OR drinker = 'Rebecca')
		AND NOT EXISTS 
			(select f1.bar 
				from BarBeerDrinker.frequents f1, BarBeerDrinker.frequents f2 
					where
   					 (f1.drinker = 'john' AND f2.drinker = 'Rebecca') AND f.bar = f1.bar AND f.bar = f2.bar);
   
#Query 7 - Drinkers who frequent at least three bars       
select distinct
	drinker
from
	BarBeerDrinker.frequents d
where exists
	(select distinct bar from BarBeerDrinker.frequents where drinker = d.drinker
		HAVING COUNT(distinct bar ) >= 3);
         
#Query 8 - Drinkers who frequent bars which sell some beer they like
select distinct f1.drinker
from BarBeerDrinker.frequents f1, BarBeerDrinker.likes l1, BarBeerDrinker.sells s1
where (f1.drinker = l1.drinker) and f1.bar = s1.bar and l1.beer = s1.beer;

#Query 9 - Beers which are served by bars which John frequents
select distinct 
	beer 
from 
	BarBeerDrinker.sells S 
join
	(select bar from BarBeerDrinker.frequents where drinker = 'John')J
on 
	S.bar = J.bar;

#Query 10 - Bars which serve some beers which John or Rebecca like and which are cheaper than $5
select distinct 
	bar 
from 
	BarBeerDrinker.sells S 
join
	( select beer from BarBeerDrinker.likes where (drinker = 'john' OR drinker = 'Rebecca'))J 
on 
	S.beer = J.beer
where price < 5;

#Query 11 - Bars which serve some beers which Mike likes or which John likes
select distinct 
	bar 
from 
	BarBeerDrinker.sells S 
join
	( select beer from BarBeerDrinker.likes where (drinker = 'john' OR drinker = 'Mike'))J 
on 
	S.beer = J.beer;
    
#Query 12 - Drinkers who like both Hefeweizen and Killian's
select 
	drinker 
from 
	BarBeerDrinker.likes 
where 
	beer = 'Hefeweizen' AND beer = ('Killian''s');

#Query 13 - List all bars, beers they sell and beer prices in euros  
SELECT * FROM BarBeerDrinker.sells ;
select sells.bar, sells.beer, sells.price AS price_EUR FROM BarBeerDrinker.sells ;

#Query 14 - Bars with names containing "The"
select 
	bars.name 
from 
	BarBeerDrinker.bars 
where 
	bars.name LIKE '%The%';

#Query 15 - Drinkers who like Killian's and who also frequent Gecko Grill
select 
	L.drinker
from BarBeerDrinker.likes AS L where L.beer = 'Killian''s'
UNION
	select F.drinker
		from BarBeerDrinker.frequents AS F where F.bar = 'Gecko Grill';
        


    









