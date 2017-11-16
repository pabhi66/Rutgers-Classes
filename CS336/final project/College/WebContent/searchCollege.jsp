<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!--Import some libraries that have classes that we need -->
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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

</head>
<body>

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
                        <a class="page-scroll" href="index.jsp">Home</a>
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
            <%
          //Create a connection string
    		String urll = "jdbc:mysql://collegecost.cuuw09qotyfh.us-east-2.rds.amazonaws.com:3306/my_project1";
    		//Load JDBC driver - the interface standardizing the connection procedure. Look at WEB-INF\lib for a mysql connector jar file, otherwise it fails.
    		Class.forName("com.mysql.jdbc.Driver");

    		//Create a connection to your DB
    		Connection conn = DriverManager.getConnection(urll, "sql336", "collegecost");

    		//Create a SQL statement
    		Statement stmtt = conn.createStatement();
    		
    		String collegee = request.getParameter("item");
    		out.print("<h1 id=\"homeHeading\">" + collegee +"</h1>");
            %>
                <!-- <h1 id="homeHeading">Searching College Based on you</h1> -->
                <hr>
                <p>Cutting through government data to find what College will cost</p>
                <a href="#about" class="btn btn-primary btn-xl page-scroll">Find Out More</a>
            </div>
        </div>
    </header>
    
    
    <section class="bg-primary" id="about">
        
        <%
	try {

		//Create a connection string
		String url = "jdbc:mysql://collegecost.cuuw09qotyfh.us-east-2.rds.amazonaws.com:3306/my_project1";
		//Load JDBC driver - the interface standardizing the connection procedure. Look at WEB-INF\lib for a mysql connector jar file, otherwise it fails.
		Class.forName("com.mysql.jdbc.Driver");

		//Create a connection to your DB
		Connection con = DriverManager.getConnection(url, "sql336", "collegecost");

		//Create a SQL statement
		Statement stmt = con.createStatement();
		
		String college = request.getParameter("item");
		
		String str = "SELECT * FROM my_project1.all WHERE College LIKE " + "'" + college + "%'";
		
		out.print("<p><center> " + college + "</center></p>");

		ResultSet result = stmt.executeQuery(str);

/////////////////////////////////////////////
		out.print("<h2 class=\"section-heading\">College Information</h2>");
		out.print("<table style=text-align:left;background-color:grey;border=1;\">");
		while (result.next()){
			out.print("<tr>");
			
			out.print("<td style=\"background-color:black;color:white;\">");
			out.print("College : ");
			out.print("</td>");
			out.print("<td>");
            out.print(result.getString("College"));
            out.print("</td>");
            out.print("</tr>");
            
            
            out.print("<tr>");
            out.print("<td style=\"background-color:black;color:white;\">");
    		out.print("City : ");
    		out.print("</td>");
    		out.print("<td>");
            out.print(result.getString("CITY"));
            out.print("</td>");
            out.print("</tr>");
            
            out.print("<tr>");
            out.print("<td style=\"background-color:black;color:white;\">");
            out.print("State : ");
            out.print("</td>");
            out.print("<td>");
            out.print(result.getString("State"));
            out.print("</td>");
            out.print("</tr>");
            
            out.print("<tr>");
            out.print("<td style=\"background-color:black;color:white;\">");
            out.print("Zip Code : ");
            out.print("</td>");
            out.print("<td>");
            out.print(result.getString("ZIP"));
            out.print("</td>");
            out.print("</tr>");
            
            out.print("<tr>");
            out.print("<td style=\"background-color:black;color:white;\">");
            out.print("Website :                     ");
            out.print("</td>");
            out.print("<td>");
            out.print(result.getString("Website"));
            out.print("</td>");
            out.print("</tr>");
        }
		out.print("</table>");
		
		out.print("<br>");
		out.print("<br>");
/////////////////////////////////////////////

 str = "SELECT * FROM my_project1.College_Cost WHERE College LIKE " + "'" + college + "%'";
	 result = stmt.executeQuery(str);
	 
	 
		out.print("<h2 class=\"section-heading\">Cost Information</h2>");
		out.print("<table style=text-align:left;background-color:grey;border=1;\">");
		while (result.next()){
			out.print("<tr>");
			
			out.print("<td style=\"background-color:black;color:white;\">");
			out.print("Tuition Cost In-State : ");
			out.print("</td>");
			out.print("<td>");
            out.print(result.getString("Tuition_Cost_IN"));
            out.print("</td>");
            out.print("</tr>");
            
            
            out.print("<tr>");
            out.print("<td style=\"background-color:black;color:white;\">");
    		out.print("Tuition Cost Out-State : ");
    		out.print("</td>");
    		out.print("<td>");
            out.print(result.getString("Tuition_Cost_OUT"));
            out.print("</td>");
            out.print("</tr>");
            
            out.print("<tr>");
            out.print("<td style=\"background-color:black;color:white;\">");
            out.print("Credit Cost In-State :        ");
            out.print("</td>");
            out.print("<td>");
            out.print(result.getString("Credit_Cost_IN"));
            out.print("</td>");
            out.print("</tr>");
            
            out.print("<tr>");
            out.print("<td style=\"background-color:black;color:white;\">");
            out.print("Credit Cost Out-State : ");
            out.print("</td>");
            out.print("<td>");
            out.print(result.getString("Credit_Cost_OUT"));
            out.print("</td>");
            out.print("</tr>");
            
            out.print("<tr>");
            out.print("<td style=\"background-color:black;color:white;\">");
            out.print("Books Cost : ");
            out.print("</td>");
            out.print("<td>");
            out.print(result.getString("Books_Cost"));
            out.print("</td>");
            out.print("</tr>");
            
            out.print("<tr>");
            out.print("<td style=\"background-color:black;color:white;\">");
            out.print("Living Cost On-Campus : ");
            out.print("</td>");
            out.print("<td>");
            out.print(result.getString("Living_Cost_On"));
            out.print("</td>");
            out.print("</tr>");
            
            out.print("<tr>");
            out.print("<td style=\"background-color:black;color:white;\">");
            out.print("Living Cost Off-Campus : ");
            out.print("</td>");
            out.print("<td>");
            out.print(result.getString("Living_Cost_Off"));
            out.print("</td>");
            out.print("</tr>");
        }
		out.print("</table>");
		
		out.print("<br>");
		out.print("<br>");
/////////////////////////////////////////////	

		 str = "SELECT * FROM my_project1.acceptanceRate WHERE College LIKE " + "'" + college + "%'";
			 result = stmt.executeQuery(str);
			 
			 
				out.print("<h2 class=\"section-heading\">Admission Information</h2>");
				out.print("<table style=text-align:left;background-color:grey;border=1;\">");
				while (result.next()){
					out.print("<tr>");
					
					out.print("<td style=\"background-color:black;color:white;\">");
					out.print("Applicants :                  ");
					out.print("</td>");
					out.print("<td>");
		            out.print(result.getString("Applicants"));
		            out.print("</td>");
		            out.print("</tr>");
		            
		            
		            out.print("<tr>");
		            out.print("<td style=\"background-color:black;color:white;\">");
		    		out.print("Addmitted : ");
		    		out.print("</td>");
		    		out.print("<td>");
		            out.print(result.getString("Addmitted"));
		            out.print("</td>");
		            out.print("</tr>");
		            
		            out.print("<tr>");
		            out.print("<td style=\"background-color:black;color:white;\">");
		            out.print("Enrolled : ");
		            out.print("</td>");
		            out.print("<td>");
		            out.print(result.getString("Enrolled"));
		            out.print("</td>");
		            out.print("</tr>");
		            
		            out.print("<tr>");
		            out.print("<td style=\"background-color:black;color:white;\">");
		            out.print("Acceptance Rate : ");
		            out.print("</td>");
		            out.print("<td>");
		            out.print(result.getString("Acceptance_Rate")+"%");
		            out.print("</td>");
		            out.print("</tr>");
		            
		            out.print("<tr>");
		            out.print("<td style=\"background-color:black;color:white;\">");
		            out.print("Yield Rate : ");
		            out.print("</td>");
		            out.print("<td>");
		            out.print(result.getString("Yield_Rate")+"%");
		            out.print("</td>");
		            out.print("</tr>");
		       
		        }
				out.print("</table>");
				
				out.print("<br>");
				out.print("<br>");
		/////////////////////////////////////////////	
		

				 str = "SELECT * FROM my_project1.Student_rato WHERE College LIKE " + "'" + college + "%'";
					 result = stmt.executeQuery(str);
					 
					 
						out.print("<h2 class=\"section-heading\">Enrollment Information</h2>");
						out.print("<table style=text-align:left;background-color:grey;border=1;\">");
						while (result.next()){
							out.print("<tr>");
							
							out.print("<td style=\"background-color:black;color:white;\">");
							out.print("Undergraduate Students :      ");
							out.print("</td>");
							out.print("<td>");
				            out.print(result.getString("Undergrad"));
				            out.print("</td>");
				            out.print("</tr>");
				            
				            
				            out.print("<tr>");
				            out.print("<td style=\"background-color:black;color:white;\">");
				    		out.print("Graduate Students : ");
				    		out.print("</td>");
				    		out.print("<td>");
				            out.print(result.getString("Grad"));
				            out.print("</td>");
				            out.print("</tr>");
				            
				            out.print("<tr>");
				            out.print("<td style=\"background-color:black;color:white;\">");
				            out.print("Male : ");
				            out.print("</td>");
				            out.print("<td>");
				            out.print(result.getString("Male"));
				            out.print("</td>");
				            out.print("</tr>");
				            
				            out.print("<tr>");
				            out.print("<td style=\"background-color:black;color:white;\">");
				            out.print("Female : ");
				            out.print("</td>");
				            out.print("<td>");
				            out.print(result.getString("Female"));
				            out.print("</td>");
				            out.print("</tr>");
				          
				        }
						out.print("</table>");
						
						out.print("<br>");
						out.print("<br>");
				/////////////////////////////////////////////	


						 str = "SELECT * FROM my_project1.all WHERE College LIKE " + "'" + college + "%'";
							 result = stmt.executeQuery(str);
							 
							 
								out.print("<h2 class=\"section-heading\">Diversity Information</h2>");
								out.print("<table style=text-align:left;background-color:grey;border=1;\">");
								while (result.next()){
									out.print("<tr>");
									
									out.print("<td style=\"background-color:black;color:white;\">");
									out.print("White                        :");
									out.print("</td>");
									out.print("<td>");
						            out.print(result.getString("UNDERGRADUATE_WHITE")+"%");
						            out.print("</td>");
						            out.print("</tr>");
						            
						            
						            out.print("<tr>");
						            out.print("<td style=\"background-color:black;color:white;\">");
						    		out.print("Black : ");
						    		out.print("</td>");
						    		out.print("<td>");
						            out.print(result.getString("UNDERGRADUATE_BLACK")+"%");
						            out.print("</td>");
						            out.print("</tr>");
						            
						            out.print("<tr>");
						            out.print("<td style=\"background-color:black;color:white;\">");
						            out.print("Hispanic : ");
						            out.print("</td>");
						            out.print("<td>");
						            out.print(result.getString("UNDERGRADUATE_HISPANIC")+"%");
						            out.print("</td>");
						            out.print("</tr>");
						            
						            out.print("<tr>");
						            out.print("<td style=\"background-color:black;color:white;\">");
						            out.print("Asian : ");
						            out.print("</td>");
						            out.print("<td>");
						            out.print(result.getString("UNDERGRADUATE_ASIAN")+"%");
						            out.print("</td>");
						            out.print("</tr>");
						            
						            out.print("<tr>");
						            out.print("<td style=\"background-color:black;color:white;\">");
						            out.print("Aian : ");
						            out.print("</td>");
						            out.print("<td>");
						            out.print(result.getString("UNDERGRADUATE_AIAN")+"%");
						            out.print("</td>");
						            out.print("</tr>");
						            
						            out.print("<tr>");
						            out.print("<td style=\"background-color:black;color:white;\">");
						            out.print("Nhpi : ");
						            out.print("</td>");
						            out.print("<td>");
						            out.print(result.getString("UNDERGRADUATE_NHPI")+"%");
						            out.print("</td>");
						            out.print("</tr>");
						            
						            out.print("<tr>");
						            out.print("<td style=\"background-color:black;color:white;\">");
						            out.print("Married : ");
						            out.print("</td>");
						            out.print("<td>");
						            out.print(result.getString("MARRIED")+"%");
						            out.print("</td>");
						            out.print("</tr>");
						            
						            out.print("<tr>");
						            out.print("<td style=\"background-color:black;color:white;\">");
						            out.print("Dependent : ");
						            out.print("</td>");
						            out.print("<td>");
						            out.print(result.getString("DEPENDENT")+"%");
						            out.print("</td>");
						            out.print("</tr>");
						            
						            out.print("<tr>");
						            out.print("<td style=\"background-color:black;color:white;\">");
						            out.print("Veteran : ");
						            out.print("</td>");
						            out.print("<td>");
						            out.print(result.getString("VETERAN")+"%");
						            out.print("</td>");
						            out.print("</tr>");
						            
						            out.print("<tr>");
						            out.print("<td style=\"background-color:black;color:white;\">");
						            out.print("First Generation : ");
						            out.print("</td>");
						            out.print("<td>");
						            out.print(result.getString("FIRST_GEN")+"%");
						            out.print("</td>");
						            out.print("</tr>");
						          
						        }
								out.print("</table>");
								
								out.print("<br>");
								out.print("<br>");
						/////////////////////////////////////////////	



								 str = "SELECT * FROM my_project1.all WHERE College LIKE " + "'" + college + "%'";
									 result = stmt.executeQuery(str);
									 
									 
										out.print("<h2 class=\"section-heading\">Requirement</h2>");
										out.print("<table style=text-align:left;background-color:grey;border=1;\">");
										while (result.next()){
											out.print("<tr>");
											
											out.print("<td style=\"background-color:black;color:white;\">");
											out.print("SAT Reading Median :      ");
											out.print("</td>");
											out.print("<td>");
								            out.print(result.getString("SAT_READING_MED"));
								            out.print("</td>");
								            out.print("</tr>");
								            
								            out.print("<td style=\"background-color:black;color:white;\">");
											out.print("SAT Math Median :      ");
											out.print("</td>");
											out.print("<td>");
								            out.print(result.getString("SAT_MATH_MED"));
								            out.print("</td>");
								            out.print("</tr>");
								            
								            out.print("<td style=\"background-color:black;color:white;\">");
											out.print("SAT Writing Median :      ");
											out.print("</td>");
											out.print("<td>");
								            out.print(result.getString("SAT_WRITING_MED"));
								            out.print("</td>");
								            out.print("</tr>");
								            
								            out.print("<tr>");
								            out.print("<td style=\"background-color:black;color:white;\">");
								    		out.print("ACT Chemistry Median : ");
								    		out.print("</td>");
								    		out.print("<td>");
								            out.print(result.getString("ACT_CHEM_MED"));
								            out.print("</td>");
								            out.print("</tr>");
								            
								            out.print("<tr>");
								            out.print("<td style=\"background-color:black;color:white;\">");
								    		out.print("ACT Chemistry Median : ");
								    		out.print("</td>");
								    		out.print("<td>");
								            out.print(result.getString("ACT_CHEM_MED"));
								            out.print("</td>");
								            out.print("</tr>");
								            
								            out.print("<tr>");
								            out.print("<td style=\"background-color:black;color:white;\">");
								    		out.print("ACT English Median : ");
								    		out.print("</td>");
								    		out.print("<td>");
								            out.print(result.getString("ACT_ENG_MED"));
								            out.print("</td>");
								            out.print("</tr>");
								            
								            out.print("<tr>");
								            out.print("<td style=\"background-color:black;color:white;\">");
								    		out.print("ACT Math Median : ");
								    		out.print("</td>");
								    		out.print("<td>");
								            out.print(result.getString("ACT_MATH_MED"));
								            out.print("</td>");
								            out.print("</tr>");
								            
								            out.print("<tr>");
								            out.print("<td style=\"background-color:black;color:white;\">");
								            out.print("SAT Avg : ");
								            out.print("</td>");
								            out.print("<td>");
								            out.print(result.getString("SAT_AVG"));
								            out.print("</td>");
								            out.print("</tr>");
								          
								        }
										out.print("</table>");
										
										out.print("<br>");
										out.print("<br>");
								/////////////////////////////////////////////	
				


										 str = "SELECT * FROM my_project1.all as a,my_project1.race as r  WHERE r.SCHOOL_NAME=a.College and College LIKE " + "'" + college + "%'";
											 result = stmt.executeQuery(str);
											 
											 
												out.print("<h2 class=\"section-heading\">Debt After Graduating</h2>");
												out.print("<table style=text-align:left;background-color:grey;border=1;\">");
												while (result.next()){
													out.print("<tr>");
													
													out.print("<td style=\"background-color:black;color:white;\">");
													out.print("Debt Median :      ");
													out.print("</td>");
													out.print("<td>");
										            out.print(result.getString("DEBT_MDN"));
										            out.print("</td>");
										            out.print("</tr>");
										            
										            out.print("<td style=\"background-color:black;color:white;\">");
													out.print("Graduate Student Debt Median :      ");
													out.print("</td>");
													out.print("<td>");
										            out.print(result.getString("GRAD_DEBT_MDN"));
										            out.print("</td>");
										            out.print("</tr>");
										            
										            out.print("<td style=\"background-color:black;color:white;\">");
													out.print("Withdraw Student Debt Median :      ");
													out.print("</td>");
													out.print("<td>");
										            out.print(result.getString("WDRAW_DEBT_MDN"));
										            out.print("</td>");
										            out.print("</tr>");
										            
										            out.print("<td style=\"background-color:black;color:white;\">");
													out.print("Low-Income Student Debt Median :      ");
													out.print("</td>");
													out.print("<td>");
										            out.print(result.getString("LO_INC_DEBT_MDN"));
										            out.print("</td>");
										            out.print("</tr>");
										            
										            out.print("<td style=\"background-color:black;color:white;\">");
													out.print("Medium-Income Student Debt Median :      ");
													out.print("</td>");
													out.print("<td>");
										            out.print(result.getString("MD_INC_DEBT_MDN"));
										            out.print("</td>");
										            out.print("</tr>");
										            
										            out.print("<td style=\"background-color:black;color:white;\">");
													out.print("Hi-Income Student Debt Median :      ");
													out.print("</td>");
													out.print("<td>");
										            out.print(result.getString("HI_INC_DEBT_MDN"));
										            out.print("</td>");
										            out.print("</tr>");
										            
										            out.print("<td style=\"background-color:black;color:white;\">");
													out.print("Female Student Debt Median :      ");
													out.print("</td>");
													out.print("<td>");
										            out.print(result.getString("FEMALE_DEBT_MDN"));
										            out.print("</td>");
										            out.print("</tr>");
										            
										            out.print("<td style=\"background-color:black;color:white;\">");
													out.print("Male-Income Student Debt Median :      ");
													out.print("</td>");
													out.print("<td>");
										            out.print(result.getString("MALE_DEBT_MDN"));
										            out.print("</td>");
										            out.print("</tr>");
										            
										            out.print("<td style=\"background-color:black;color:white;\">");
													out.print("Poverty Rate After Graduate :      ");
													out.print("</td>");
													out.print("<td>");
										            out.print(result.getString("POVERTY_RATE")+"%");
										            out.print("</td>");
										            out.print("</tr>");
										          
										            out.print("<td style=\"background-color:black;color:white;\">");
													out.print("Unemployment Rate After Graduate :      ");
													out.print("</td>");
													out.print("<td>");
										            out.print(result.getString("UNEMP_RATE")+"%");
										            out.print("</td>");
										            out.print("</tr>");
										        }
												out.print("</table>");
												
												out.print("<br>");
												out.print("<br>");
										/////////////////////////////////////////////	
										
		con.close();

	} catch (Exception ex) {
		out.print("insert failed");
	}
%>
        
        
        
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
</body>
</html>