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
                <h1 id="homeHeading">College Cost</h1>
                <hr>
                <p>We analyzed your inputs and found these results.  If there is no result shown under the table that means no colleges were found based on your input.</p>
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

		int fee = Integer.parseInt(request.getParameter("Tuition_Fee"));
		String state = request.getParameter("State");
		int size = Integer.parseInt(request.getParameter("size"));
		int sat = Integer.parseInt(request.getParameter("SAT"));
		int actm = Integer.parseInt(request.getParameter("ACTM"));
		int actr = Integer.parseInt(request.getParameter("ACTR"));
		int actc = Integer.parseInt(request.getParameter("ACTC"));
		String in_out = request.getParameter("in_out_state");



		String str = "SELECT * FROM my_project1.all as a, Student_rato as s where a.College=s.College  ";
		if(fee != 0){
			if(in_out.equals("BOTH")){
				if(fee == 1000000){
					str += " and (TUITIONFEE_IN <=1000000 or TUITIONFEE_OUT<= 1000000)";
				}
				else if(fee == 5000){
					str += " and (TUITIONFEE_IN <=5000 or TUITIONFEE_OUT<= 5000)";
				}else{
					str += " and ((TUITIONFEE_IN <=" + fee + " and TUITIONFEE_IN >= " + (fee-5000) +  " ) or (TUITIONFEE_OUT <=" + fee + " and TUITIONFEE_OUT >= " + (fee-5000) +  " ))" ;
				}
			}
			else if(in_out.equals("IN")){
				if(fee == 1000000){
					str += " and TUITIONFEE_IN <=1000000 ";
				}
				else if(fee == 5000){
					str += " and TUITIONFEE_IN <=5000 ";
				}else{
					str += " and (TUITIONFEE_IN <=" + fee + " and TUITIONFEE_IN >= " + (fee-5000) +  " )" ;
				}
			}
			else if(in_out.equals("OUT")){
				if(fee == 1000000){
					str += " and TUITIONFEE_OUT <=1000000 ";
				}
				else if(fee == 5000){
					str += " and TUITIONFEE_OUT <=5000 ";
				}else{
					str += " and (TUITIONFEE_OUT <=" + fee + " and TUITIONFEE_OUT >= " + (fee-5000) +  " )" ;
				}
			}
		}

		
		if(state.equals("EMPTY")){
			
		}
		else if(!state.equals("ALL")){
			str += " and State = \"" + state + "\"";
		}


		if(size != 0)
			str += " and s.Total_Student <= " + size;
		if(sat != 0){
			str += " and (a.SAT_AVG <= " + sat + " and a.SAT_AVG >= " + (sat-100) + " ) ";
		}

		

		if(actm != 0){
			str += " and (a.ACT_MATH_MED <= " + actm + " and a.ACT_MATH_MED >= " + (actm-2) + " ) ";
		}

		if(actr != 0){
			str += " and (a.ACT_ENG_MED <= " + actr + " and a.ACT_ENG_MED >= " + (actr-2) + " ) ";
		}

		if(actc != 0){
			str += " and (a.ACT_CHEM_MED <= " + actc + " and a.ACT_CHEM_MED >= " + (actc-2) + " ) ";
		}

		ResultSet result = stmt.executeQuery(str);
		
		out.print("<form method=\"searchhh\" action=\"searchCollege.jsp\">");
		 //Make an HTML table to show the results in:
		out.print("<center><table border=" + "\"" + 5 + "\"" + ">");
		out.print("<col width=" + "\"" + 400 + "\"" + ">");
		out.print("<col width=" + "\"" + 50 + "\"" + ">");
		out.print("<col width=" + "\"" + 100 + "\"" + ">");
		out.print("<col width=" + "\"" + 100 + "\"" + ">");
		out.print("<col width=" + "\"" + 100 + "\"" + ">");
		out.print("<col width=" + "\"" + 100 + "\"" + ">");
		out.print("<col width=" + "\"" + 100 + "\"" + ">");
		//out.print("<col width=" + "\"" +  + "\"" + ">");

		//make a row
		out.print("<tr>");
		out.print("<td>");
		out.print("<center>College</center>");
		out.print("</td>");
		out.print("<td>");
		out.print("<center>State</center>");
		out.print("</td>");
		out.print("<td>");
		out.print("<center>Tuition in-state</center>");
		out.print("</td>");
		out.print("<td>");
		out.print("<center>Tuition out-state</center>");
		out.print("</td>");
		out.print("<td>");
		out.print("<center>SAT AVG</center>");
		out.print("</td>");
		out.print("<td>");
		out.print("<center>ACT MATH</center>");
		out.print("</td>");
		out.print("<td>");
		out.print("<center>ACT READ</center>");
		out.print("</td>");
		out.print("<td>");
		out.print("<center>ACT CHEM</center>");
		out.print("</td>");
		out.print("<td>");
		out.print("<center>SIZE</center>");
		out.print("</td>");
		out.print("</tr>");

		//out.print("<table>");
		while (result.next()){
			out.print("<tr>");
           out.print("<td height=\"50\">");

           out.print("<a href=\"searchCollege.jsp?item=");
           out.print(result.getString("College"));
           out.print("\"style=\"color: #FFFFFF\">");
           out.print(result.getString("College"));
           out.print("</td>");

           out.print("<td height=\"50\">");
           out.print(result.getString("State"));
           out.print("</td>");

           out.print("<td height=\"50\">");
           out.print(result.getString("TUITIONFEE_IN"));
           out.print("</td>");

           out.print("<td height=\"50\">");
           out.print(result.getString("TUITIONFEE_OUT"));
           out.print("</td>");

           out.print("<td height=\"50\">");
           out.print(result.getString("SAT_AVG"));
           out.print("</td>");

           out.print("<td height=\"50\">");
           out.print(result.getString("ACT_MATH_MED"));
           out.print("</td>");

           out.print("<td height=\"50\">");
           out.print(result.getString("ACT_ENG_MED"));
           out.print("</td>");

           out.print("<td height=\"50\">");
           out.print(result.getString("ACT_CHEM_MED"));
           out.print("</td>");

           out.print("<td height=\"50\">");
           out.print(result.getString("s.Total_Student"));
           out.print("</td>");


           out.print("</tr>");
       }
		out.print("</table></center>");
		out.print("</form>");




		con.close();

	} catch (Exception ex) {
		out.print("No Colleges were found based on your input.  Please try again.");
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
