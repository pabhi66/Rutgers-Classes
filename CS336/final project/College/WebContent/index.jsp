<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>College Cost</title>

    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>

    <!-- Plugin CSS -->
    <link href="vendor/magnific-popup/magnific-popup.css" rel="stylesheet">

    <!-- Theme CSS -->
    <link href="css/creative.min.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
    <script src="js/jquery.autocomplete.js"></script>	

</head>

<body id="page-top">

    <nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="#page-top">College Cost</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a class="page-scroll" href="#about">Search</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#services">Advance Search</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#portfolio">More queries</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#contact">About</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

    <header>
        <div class="header-content">
            <div class="header-content-inner">
                <h1 id="homeHeading">College Cost</h1>
                <hr>
                <p>Cutting through government data to find what College will cost</p>
                <a href="#about" class="btn btn-primary btn-xl page-scroll">Find Out More</a>
            </div>
        </div>
    </header>

    <section class="bg-primary" id="about">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 text-center">
                    <h2 class="section-heading">Search College by name</h2>
                    <hr class="light">
                    <p class="text-faded">Search College by its name and find all the information that you possibly need to about that college. That includes tuition cost, admission rate, SAT/ACT requirements, dorm/housing cost, books & supply cost, number of people in college by its race/male/female
            .  As well as find out how many people have the job right after college, and how may people have debt after college, and much more information that you possibly need about the college.</p>
            
            		<form method="searchh" action="selectCollege.jsp">
						<p>Enter College Name to Find all its information</p>
							<table>
								<tr> 
								<center><td>Enter College Name: &nbsp; &nbsp;</td><td><input type="text" name="college" STYLE="color: #000000; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #FFFFFF;" size="50" maxlength="100"></td>
								<td>&nbsp; &nbsp; <input type="submit" value="Search" <a href="#services" class="page-scroll btn btn-default btn-xl sr-button"></a>></input> </td></center>
								</tr>
							</table>
							<br>
					</form>
					
            		<!-- <a href="#services" class="page-scroll btn btn-default btn-xl sr-button">Search</a> --><br><br>
                    <a href="#services" class="page-scroll btn btn-default btn-xl sr-button">Do a Advance Search!</a>
                </div>
            </div>
        </div>
    </section>

    <section id="services">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">Advance Search</h2>
                    <hr class="primary">
                </div>
            </div>
        </div>
        
        <div class="container">
                <div class="col-lg-8 col-lg-offset-2">
                
                <center><form method="query" action="advanceQuery.jsp">
					<p><center>You can choose one or more entity from this search to find your dream college</center></p>
					<p>1) Choose the costs that is affordable to you <b class="section-heading">Cost</b><br>
						<select name="Tuition_Fee" size=1>
							<option value="0"></option>
							<option value="5000">$Less then 5K</option>
							<option value="10000">$5K - 10K</option>
							<option value="15000">$10K - 15K</option>
							<option value="20000">$15K - 20K</option>
							<option value="25000">$20K - 25K</option>
							<option value="100000">$25K and up</option>
							<option value="1000000">$ANY$$$</option>
						</select>
						
					 <p>2)Is your budget for <b class='accent'>in-state</b> or <b class='accent'>out-state</b><br>
						<select name="in_out_state" size=1>
							<option value=""></option>
							<option value="BOTH">both</option>
							<option value="IN">in-state</option>
							<option value="OUT">out-state</option>
						</select>
						
					<p>3) Click your <b class="accent">state</b>  or <b class='accent'>territory</b><br>
						<select name="State" size=1>
							<option value="EMPTY"></option>
							<option value="ALL">Select ALL</option>
							<option value="AL">Alabama</option>
							<option value="AK">Alaska</option>
							<option value="AZ">Arizona</option>
							<option value="AR">Arkansas</option>
							<option value="CA">California</option>
							<option value="CO">Colorado</option>
							<option value="CT">Connecticut</option>
							<option value="DE">Delaware</option>
							<option value="DC">Dist. of Columbia</option>
							<option value="FL">Florida</option>
							<option value="GA">Georgia</option>
							<option value="HI">Hawaii</option>
							<option value="ID">Idaho</option>
							<option value="IL">Illinois</option>
							<option value="IN">Indiana</option>
							<option value="IA">Iowa</option>
							<option value="KS">Kansas</option>
							<option value="KY">Kentucky</option>
							<option value="LA">Louisiana</option>
							<option value="ME">Maine</option>
							<option value="MD">Maryland</option>
							<option value="MA">Massachusetts</option>
							<option value="MI">Michigan</option>
							<option value="MN">Minnesota</option>
							<option value="MS">Mississippi</option>
							<option value="MO">Missouri</option>
							<option value="MT">Montana</option>
							<option value="NE">Nebraska</option>
							<option value="NV">Nevada</option>
							<option value="NH">New Hampshire</option>
							<option value="NJ">New Jersey</option>
							<option value="NM">New Mexico</option>
							<option value="NY">New York</option>
							<option value="NC">North Carolina</option>
							<option value="ND">North Dakota</option>
							<option value="OH">Ohio</option>
							<option value="OK">Oklahoma</option>
							<option value="OR">Oregon</option>
							<option value="PA">Pennsylvania</option>
							<option value="RI">Rhode Island</option>
							<option value="SC">South Carolina</option>
							<option value="SD">South Dakota</option>
							<option value="TN">Tennessee</option>
							<option value="TX">Texas</option>
							<option value="UT">Utah</option>
							<option value="VT">Vermont</option>
							<option value="VA">Virginia</option>
							<option value="WA">Washington</option>
							<option value="WV">West Virginia</option>
							<option value="WI">Wisconsin</option>
							<option value="WY">Wyoming</option>
						</select>
						
						
						<p>4) Select <b class='accent'>Institution size</b><br>
						<select name="size" size=1>
							<option value="0"></option>
							<option value="5000">Less then 5K</option>
							<option value="10000">Less then 10K</option>
							<option value="15000">Less then 15K</option>
							<option value="20000">Less then 20K</option>
							<option value="25000">Less then 25K</option>
							<option value="100000">25K and up</option>
						</select>
						
						<p>5) Select your <b class='accent'>SAT Score</b><br>
						<select name="SAT" size=1>
							<option value="0"></option>
							<option value="600">600</option>
							<option value="700">600-700</option>
							<option value="800">700-800</option>
							<option value="900">800-900</option>
							<option value="1000">900-1000</option>
							<option value="1100">1000-1100</option>
							<option value="1200">1100-1200</option>
							<option value="1300">1200-1300</option>
							<option value="1400">1300-1400</option>
							<option value="1500">1400-1500</option>
							<option value="1600">1500-1600</option>
						</select>
						
						<p>6) Select your <b class='accent'>Select ACT MATH Score</b><br>
						<select name="ACTM" size=1>
							<option value="0"></option>
							<option value="13">13</option>
							<option value="15">13-15</option>
							<option value="17">15-17</option>
							<option value="19">17-19</option>
							<option value="21">19-21</option>
							<option value="23">21-23</option>
							<option value="25">23-25</option>
							<option value="27">25-27</option>
							<option value="29">27-29</option>
							<option value="31">29-31</option>
							<option value="34">31-34</option>
							<option value="34">34-36</option>
						</select>
						
						<p>7) Select your <b class='accent'>Select ACT READING Score</b><br>
						<select name="ACTR" size=1>
							<option value="0"></option>
							<option value="13">13</option>
							<option value="15">13-15</option>
							<option value="17">15-17</option>
							<option value="19">17-19</option>
							<option value="21">19-21</option>
							<option value="23">21-23</option>
							<option value="25">23-25</option>
							<option value="27">25-27</option>
							<option value="29">27-29</option>
							<option value="31">29-31</option>
							<option value="34">31-34</option>
							<option value="34">34-36</option>
						</select>
						
						<p>8) Select your <b class='accent'>Select ACT CHEM Score</b><br>
						<select name="ACTC" size=1>
							<option value="0"></option>
							<option value="13">13</option>
							<option value="15">13-15</option>
							<option value="17">15-17</option>
							<option value="19">17-19</option>
							<option value="21">19-21</option>
							<option value="23">21-23</option>
							<option value="25">23-25</option>
							<option value="27">25-27</option>
							<option value="29">27-29</option>
							<option value="31">29-31</option>
							<option value="34">31-34</option>
							<option value="36">34-36</option>
						</select>
						
						
						 &nbsp;<br><br><center> <input type="submit" value="submit" class="btn btn-primary btn-xl page-scroll" role="button"></center>
						 
						 <p></p>
						 <center><a href="#portfolio" class="btn btn-primary btn-xl page-scroll">Click here for more Quick Queries</a></center>
				</form> 
                </div>
    </section>

    <section class="no-padding" id="portfolio">
        
        
        <aside class="bg-dark">
        <div class="container text-center">
            <div class="call-to-action">
                <h2>More Useful quick queries</h2>
                <p><a href="quickQueries/stateCostStatics.jsp" class="btn btn-default btn-xl sr-button">State Cost Statics!</a></p>
                <p><a href="quickQueries/selectState.jsp" class="btn btn-default btn-xl sr-button">Top 10 colleges by state</a></p>
                <p><a href="quickQueries/IveLeague.jsp" class="btn btn-default btn-xl sr-button">Ivy League Colleges Tuition Comparison</a></p>
                <p><a href="quickQueries/money.jsp" class="btn btn-default btn-xl sr-button">25 best colleges with average tuition cost of $$$</a></p>
                <p><a href="quickQueries/selectCareer-1.jsp" class="btn btn-default btn-xl sr-button">Find out what each Career will pay you after graduation</a></p>
                <p><a href="quickQueries/raceselect.jsp" class="btn btn-default btn-xl sr-button">Find out which college best fit for you base on your race </a></p>
                <p><a href="quickQueries/debt.jsp" class="btn btn-default btn-xl sr-button">Top 10 Colleges with the most/least debt after graduation and unemployment</a></p>
				
                
            </div>
        </div>
    	</aside>	

    </section>

    

    <section id="contact">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 text-center">
                    <h2 class="section-heading">About</h2>
                    <hr class="primary">
                    <p>This web-site collects college cost and admission data from US government and other useful web-site and lets students find the college that best fits their budget and other requirements</p>
					<p>Students can also find the tuition cost of different college, min SAT/ACT score required to getin to a college and other useful queries. "NOTE:" IF NO DATA WAS REPORTED BY SOME COLELGE THEN DEFAULT VALUE OF 0 OR NULL IS USED.</p>                
                	
                	<br>
               
                	
                	<ul class="text-center list-inline banner-social-buttons">
                	<h2 class="section-heading">Sources</h2>
                    <li>
                        <a href="https://collegescorecard.ed.gov/data" class="btn btn-default btn-lg"><i class="fa fa-graduation-cap fa-fw"></i> <span class="network-name">Government Data</span></a>
                    </li>
                    <li>
                        <a href="http://college-insight.org/" class="btn btn-default btn-lg"><i class="fa fa-graduation-cap fa-fw"></i> <span class="network-name">College Insights</span></a>
                    </li>
                    
                    <li>
                        <a href="http://www.collegetuitioncompare.com/" class="btn btn-default btn-lg"><i class="fa fa-graduation-cap fa-fw"></i> <span class="network-name">College Tuition Compare</span></a>
                    </li>
                </ul>
                	
                	<br>
                	<p><p>Copyright &copy; 2016 Abhishek Prajapati & Dhrumil Shah <br />
            					Principal and database management project.<p></p>
                </div>
            </div>
        </div>
    </section>

    <!-- jQuery -->
    <script src="vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
    <script src="vendor/scrollreveal/scrollreveal.min.js"></script>
    <script src="vendor/magnific-popup/jquery.magnific-popup.min.js"></script>

    <!-- Theme JavaScript -->
    <script src="js/creative.min.js"></script>
    
    <script>
	jQuery(function(){
		$("#country").autocomplete("list.jsp");
	});
</script>

</body>

</html>

