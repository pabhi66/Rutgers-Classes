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
    <link href="..\vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="..\vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>

    <!-- Plugin CSS -->
    <link href="..\vendor/magnific-popup/magnific-popup.css" rel="stylesheet">

    <!-- Theme CSS -->
    <link href="..\css/creative.min.css" rel="stylesheet">
    

    

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
                        <a class="page-scroll" href="..\index.jsp">Home</a>
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
                <h1 id="homeHeading">Ivy League Schools Cost Comparison</h1>
                <hr>
                <p>The Ivy League is a collegiate athletic conference comprising sports teams from eight private institutions of higher education in US. The eight institutions are Brown University, Columbia University, Cornell University, Dartmouth College, Harvard University, Princeton University, the University of Pennsylvania, and Yale University. The term Ivy League also has connotations of academic excellence, selectivity in admissions, and social elitism. <br></p>
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
		
		
		String str = "SELECT * FROM my_project1.acceptanceRate WHERE (College = \"Brown University\" or College = \"Yale University\" or College = \"Columbia University in the City of New York\" or College = \"Cornell University\" or  College = \"Dartmouth College\" or College = \"Harvard University\" or College = \"Princeton University\" or College = \"University of Pennsylvania\" );";

		ResultSet result = stmt.executeQuery(str);
		
		out.print("<form method=\"searchhh\" action=\"searchCollege.jsp\">");
		 //Make an HTML table to show the results in:
		out.print("<center><p>General Information</p></center>");
		out.print("<center><table border=" + "\"" + 5 + "\"" + ">");
		out.print("<col width=" + "\"" + 300 + "\"" + ">");
		out.print("<col width=" + "\"" + 100 + "\"" + ">");
		out.print("<col width=" + "\"" + 100 + "\"" + ">");
		out.print("<col width=" + "\"" + 100 + "\"" + ">");
		out.print("<col width=" + "\"" + 100 + "\"" + ">");

		//make a row
		out.print("<tr>");
		out.print("<td>");
		out.print("<center>College</center>");
		out.print("</td>");
		out.print("<td>");
		out.print("<center>Location</center>");
		out.print("</td>");
		out.print("<td>");
		out.print("<center>Acceptance rate</center>");
		out.print("</td>");
		out.print("<td>");
		out.print("<center>Yield Rate</center>");
		out.print("</td>");
		out.print("<td>");
		out.print("<center>Applicants</center>");
		out.print("</td>");
		out.print("</tr>");
		
		//out.print("<table>");
		while (result.next()){
			out.print("<tr>");
           out.print("<td height=\"50\">");
           out.print("<a href=\"..\\searchCollege.jsp?item=");
           out.print(result.getString("College"));
           out.print("\"style=\"color: #FFFFFF\">");
           out.print(result.getString("College"));
           out.print("</td>");
           
           
           out.print("<td height=\"50\">");
           out.print(result.getString("Addmitted"));
           out.print("</td>");
           
           out.print("<td height=\"50\">");
           out.print(result.getString("Acceptance_Rate"));
           out.print("</td>");
           
           out.print("<td height=\"50\">");
           out.print(result.getString("Yield_Rate"));
           out.print("</td>");
           
           out.print("<td height=\"50\">");
           out.print(result.getString("Applicants"));
           out.print("</td>");
           
           out.print("</tr>");
       }
		out.print("</table></center>");
		out.print("</form>");
		
		
		str = "SELECT * FROM my_project1.College_Cost WHERE (College = \"Brown University\" or College = \"Yale University\" or College = \"Columbia University in the City of New York\" or College = \"Cornell University\" or College = \"Dartmouth College\" or College = \"Harvard University\" or College = \"Princeton University\" or College = \"University of Pennsylvania\")";
		result = stmt.executeQuery(str);
		
		out.print("<form method=\"searchhh\" action=\"searchCollege.jsp\">");
		 //Make an HTML table to show the results in:
			 out.print("<center><p></p></center>");
		out.print("<center><p>Ive League Tuition & Living Cost</p></center>");
		out.print("<center><table border=" + "\"" + 5 + "\"" + ">");
		out.print("<col width=" + "\"" + 300 + "\"" + ">");
		out.print("<col width=" + "\"" + 100 + "\"" + ">");
		out.print("<col width=" + "\"" + 100 + "\"" + ">");
		out.print("<col width=" + "\"" + 100 + "\"" + ">");
		out.print("<col width=" + "\"" + 100 + "\"" + ">");

		//make a row
		out.print("<tr>");
		out.print("<td>");
		out.print("<center>College</center>");
		out.print("</td>");
		out.print("<td>");
		out.print("<center>Tuition cost in-state</center>");
		out.print("</td>");
		out.print("<td>");
		out.print("<center>Tuition cost out-state</center>");
		out.print("</td>");
		out.print("<td>");
		out.print("<center>Book cost</center>");
		out.print("</td>");
		out.print("<td>");
		out.print("<center>Living cost on-campus</center>");
		out.print("</td>");
		out.print("</tr>");
		
		//out.print("<table>");
		while (result.next()){
			out.print("<tr>");
          out.print("<td height=\"50\">");
          out.print("<a href=\"..\\searchCollege.jsp?item=");
          out.print(result.getString("College"));
          out.print("\"style=\"color: #FFFFFF\">");
          out.print(result.getString("College"));
          out.print("</td>");
          
          
          out.print("<td height=\"50\">");
          out.print(result.getString("Tuition_Cost_IN"));
          out.print("</td>");
          
          out.print("<td height=\"50\">");
          out.print(result.getString("Tuition_Cost_OUT"));
          out.print("</td>");
          
          out.print("<td height=\"50\">");
          out.print(result.getString("Books_Cost"));
          out.print("</td>");
          
          out.print("<td height=\"50\">");
          out.print(result.getString("Living_Cost_On"));
          out.print("</td>");
          
          out.print("</tr>");
      }
		out.print("</table></center>");
		out.print("</form>");
		
		con.close();

	} catch (Exception ex) {
		out.print("insert failed");
	}
%>

<center><div id="chart-under" data-highcharts-chart="0">
<div class="highcharts-container" id="highcharts-0" style="position: relative; overflow: hidden; width: 750px; height: 400px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);">
<svg version="1.1" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Arial, Helvetica, sans-serif;font-size:12px;" xmlns="http://www.w3.org/2000/svg" width="750" height="400">
<desc>Created with Highcharts 4.1.5</desc>
<defs><clipPath id="highcharts-1"><rect x="0" y="0" width="653" height="306"></rect></clipPath>
<linearGradient x1="0" y1="0" x2="0" y2="20" gradientUnits="userSpaceOnUse" id="highcharts-2"><stop offset="0.4" stop-color="#F7F7F7" stop-opacity="1"></stop>
<stop offset="0.6" stop-color="#E3E3E3" stop-opacity="1"></stop>
</linearGradient></defs><rect x="0.5" y="0.5" width="748" height="398" strokeWidth="1" fill="#fefefe" stroke="#eee" stroke-width="1" class=" highcharts-background">
</rect><g class="highcharts-grid" zIndex="1"></g><g class="highcharts-grid" zIndex="1">
<path fill="none" d="M 87 348.5 L 740 348.5" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path>
<path fill="none" d="M 87 297.5 L 740 297.5" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path>
<path fill="none" d="M 87 246.5 L 740 246.5" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path>
<path fill="none" d="M 87 195.5 L 740 195.5" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path>
<path fill="none" d="M 87 144.5 L 740 144.5" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path>
<path fill="none" d="M 87 93.5 L 740 93.5" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path>
<path fill="none" d="M 87 41.5 L 740 41.5" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path>
</g><g class="highcharts-axis" zIndex="2"><path fill="none" d="M 168.5 348 L 168.5 358" stroke="#C0D0E0" stroke-width="1" opacity="1"></path>
<path fill="none" d="M 249.5 348 L 249.5 358" stroke="#C0D0E0" stroke-width="1" opacity="1"></path>
<path fill="none" d="M 331.5 348 L 331.5 358" stroke="#C0D0E0" stroke-width="1" opacity="1"></path>
<path fill="none" d="M 413.5 348 L 413.5 358" stroke="#C0D0E0" stroke-width="1" opacity="1"></path>
<path fill="none" d="M 494.5 348 L 494.5 358" stroke="#C0D0E0" stroke-width="1" opacity="1"></path>
<path fill="none" d="M 576.5 348 L 576.5 358" stroke="#C0D0E0" stroke-width="1" opacity="1"></path>
<path fill="none" d="M 657.5 348 L 657.5 358" stroke="#C0D0E0" stroke-width="1" opacity="1"></path>
<path fill="none" d="M 740.5 348 L 740.5 358" stroke="#C0D0E0" stroke-width="1" opacity="1"></path>
<path fill="none" d="M 86.5 348 L 86.5 358" stroke="#C0D0E0" stroke-width="1" opacity="1"></path>
<path fill="none" d="M 87 348.5 L 740 348.5" stroke="#C0D0E0" stroke-width="1" zIndex="7" visibility="visible"></path>
</g><g class="highcharts-axis" zIndex="2"><text x="23.75" zIndex="7" text-anchor="middle" transform="translate(0,0) rotate(270 23.75 195)" class=" highcharts-yaxis-title" style="color:#707070;fill:#707070;" visibility="visible" y="195">
<tspan>USD ($)</tspan></text></g><g class="highcharts-series-group" zIndex="3">
<g class="highcharts-series highcharts-tracker" visibility="visible" zIndex="0.1" transform="translate(87,42) scale(1 1)" style="" clip-path="url(#highcharts-1)">
<rect x="18.5" y="52.5" width="20" height="247" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect>
<rect x="99.5" y="42.5" width="20" height="258" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect>
<rect x="181.5" y="52.5" width="20" height="249" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect>
<rect x="263.5" y="54.5" width="20" height="246" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect>
<rect x="344.5" y="89.5" width="20" height="212" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect>
<rect x="426.5" y="79.5" width="20" height="222" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect>
<rect x="508.5" y="76.5" width="20" height="224" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect>
<rect x="589.5" y="45.5" width="20" height="243" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect>
</g><g class="highcharts-markers" visibility="visible" zIndex="0.1" transform="translate(87,42) scale(1 1)" clip-path="none"></g>
<g class="highcharts-series highcharts-tracker" visibility="visible" zIndex="0.1" transform="translate(87,42) scale(1 1)" style="" clip-path="url(#highcharts-1)">
<rect x="18.5" y="299.5" width="20" height="7" stroke="#FFFFFF" stroke-width="1" fill="#434348" rx="0" ry="0"></rect>
<rect x="99.5" y="300.5" width="20" height="6" stroke="#FFFFFF" stroke-width="1" fill="#434348" rx="0" ry="0"></rect>
<rect x="181.5" y="301.5" width="20" height="5" stroke="#FFFFFF" stroke-width="1" fill="#434348" rx="0" ry="0"></rect>
<rect x="263.5" y="300.5" width="20" height="6" stroke="#FFFFFF" stroke-width="1" fill="#434348" rx="0" ry="0"></rect>
<rect x="344.5" y="301.5" width="20" height="5" stroke="#FFFFFF" stroke-width="1" fill="#434348" rx="0" ry="0"></rect>
<rect x="426.5" y="301.5" width="20" height="5" stroke="#FFFFFF" stroke-width="1" fill="#434348" rx="0" ry="0"></rect>
<rect x="508.5" y="300.5" width="20" height="6" stroke="#FFFFFF" stroke-width="1" fill="#434348" rx="0" ry="0"></rect>
<rect x="589.5" y="288.5" width="20" height="18" stroke="#FFFFFF" stroke-width="1" fill="#434348" rx="0" ry="0"></rect>
</g><g class="highcharts-markers" visibility="visible" zIndex="0.1" transform="translate(87,42) scale(1 1)" clip-path="none"></g>
<g class="highcharts-series highcharts-tracker" visibility="visible" zIndex="0.1" transform="translate(87,42) scale(1 1)" style="" clip-path="url(#highcharts-1)">
<rect x="42.5" y="52.5" width="20" height="247" stroke="#FFFFFF" stroke-width="1" fill="#90ed7d" rx="0" ry="0"></rect><rect x="124.5" y="42.5" width="20" height="258" stroke="#FFFFFF" stroke-width="1" fill="#90ed7d" rx="0" ry="0"></rect><rect x="206.5" y="52.5" width="20" height="249" stroke="#FFFFFF" stroke-width="1" fill="#90ed7d" rx="0" ry="0"></rect><rect x="287.5" y="54.5" width="20" height="246" stroke="#FFFFFF" stroke-width="1" fill="#90ed7d" rx="0" ry="0">
</rect><rect x="369.5" y="89.5" width="20" height="212" stroke="#FFFFFF" stroke-width="1" fill="#90ed7d" rx="0" ry="0"></rect>
<rect x="450.5" y="79.5" width="20" height="222" stroke="#FFFFFF" stroke-width="1" fill="#90ed7d" rx="0" ry="0"></rect>
<rect x="532.5" y="76.5" width="20" height="224" stroke="#FFFFFF" stroke-width="1" fill="#90ed7d" rx="0" ry="0"></rect>
<rect x="614.5" y="45.5" width="20" height="243" stroke="#FFFFFF" stroke-width="1" fill="#90ed7d" rx="0" ry="0"></rect>
</g><g class="highcharts-markers" visibility="visible" zIndex="0.1" transform="translate(87,42) scale(1 1)" clip-path="none">
</g><g class="highcharts-series highcharts-tracker" visibility="visible" zIndex="0.1" transform="translate(87,42) scale(1 1)" style="" clip-path="url(#highcharts-1)"><rect x="42.5" y="299.5" width="20" height="7" stroke="#FFFFFF" stroke-width="1" fill="#f7a35c" rx="0" ry="0"></rect><rect x="124.5" y="300.5" width="20" height="6" stroke="#FFFFFF" stroke-width="1" fill="#f7a35c" rx="0" ry="0"></rect><rect x="206.5" y="301.5" width="20" height="5" stroke="#FFFFFF" stroke-width="1" fill="#f7a35c" rx="0" ry="0"></rect><rect x="287.5" y="300.5" width="20" height="6" stroke="#FFFFFF" stroke-width="1" fill="#f7a35c" rx="0" ry="0"></rect><rect x="369.5" y="301.5" width="20" height="5" stroke="#FFFFFF" stroke-width="1" fill="#f7a35c" rx="0" ry="0"></rect><rect x="450.5" y="301.5" width="20" height="5" stroke="#FFFFFF" stroke-width="1" fill="#f7a35c" rx="0" ry="0"></rect><rect x="532.5" y="300.5" width="20" height="6" stroke="#FFFFFF" stroke-width="1" fill="#f7a35c" rx="0" ry="0"></rect><rect x="614.5" y="288.5" width="20" height="18" stroke="#FFFFFF" stroke-width="1" fill="#f7a35c" rx="0" ry="0"></rect></g><g class="highcharts-markers" visibility="visible" zIndex="0.1" transform="translate(87,42) scale(1 1)" clip-path="none"></g></g><text x="375" text-anchor="middle" class="highcharts-title" zIndex="4" style="color:#333333;font-size:14px;font-weight:bold;fill:#333333;width:686px;" y="21"><tspan>Ivy League - Undergraduate Tuition and Fees Chart</tspan></text><g class="highcharts-legend" zIndex="7" transform="translate(100,360)"><g zIndex="1"><g><g class="highcharts-legend-item" zIndex="1" transform="translate(8,3)"><text x="21" style="color:#333333;font-size:12px;font-weight:bold;cursor:pointer;fill:#333333;" text-anchor="start" zIndex="2" y="15"><tspan>Tuition &amp; Fees</tspan></text><rect x="0" y="4" width="16" height="12" zIndex="3" fill="#7cb5ec"></rect></g><g class="highcharts-legend-item" zIndex="1" transform="translate(137.953125,3)"><text x="21" y="15" style="color:#333333;font-size:12px;font-weight:bold;cursor:pointer;fill:#333333;" text-anchor="start" zIndex="2"><tspan>Books &amp; Supplies</tspan></text><rect x="0" y="4" width="16" height="12" zIndex="3" fill="#434348"></rect></g><g class="highcharts-legend-item" zIndex="1" transform="translate(285.3125,3)"><text x="21" y="15" style="color:#333333;font-size:12px;font-weight:bold;cursor:pointer;fill:#333333;" text-anchor="start" zIndex="2"><tspan>Tuition &amp; Fees</tspan></text><rect x="0" y="4" width="16" height="12" zIndex="3" fill="#90ed7d"></rect></g><g class="highcharts-legend-item" zIndex="1" transform="translate(415.265625,3)"><text x="21" y="15" style="color:#333333;font-size:12px;font-weight:bold;cursor:pointer;fill:#333333;" text-anchor="start" zIndex="2"><tspan>Books &amp; Supplies</tspan></text><rect x="0" y="4" width="16" height="12" zIndex="3" fill="#f7a35c"></rect></g></g></g></g><g class="highcharts-axis-labels highcharts-xaxis-labels" zIndex="7"></g><g class="highcharts-axis-labels highcharts-yaxis-labels" zIndex="7"><text x="72" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:238px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="353" opacity="1">0</text><text x="72" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:238px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="302" opacity="1"><tspan>10 000</tspan></text><text x="72" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:238px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="251" opacity="1"><tspan>20 000</tspan></text><text x="72" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:238px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="200" opacity="1"><tspan>30 000</tspan></text><text x="72" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:238px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="148" opacity="1"><tspan>40 000</tspan></text><text x="72" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:238px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="97" opacity="1"><tspan>50 000</tspan></text><text x="72" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:238px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="47" opacity="1"><tspan>60 000</tspan></text></g><g class="highcharts-tooltip" zIndex="8" style="cursor:default;padding:0;white-space:nowrap;" transform="translate(487,-9999)" opacity="0" visibility="visible"><path fill="none" d="M 3.5 0.5 L 235.5 0.5 C 238.5 0.5 238.5 0.5 238.5 3.5 L 238.5 57.5 C 238.5 60.5 238.5 60.5 235.5 60.5 L 125.5 60.5 119.5 66.5 113.5 60.5 3.5 60.5 C 0.5 60.5 0.5 60.5 0.5 57.5 L 0.5 3.5 C 0.5 0.5 0.5 0.5 3.5 0.5" isShadow="true" stroke="black" stroke-opacity="0.049999999999999996" stroke-width="5" transform="translate(1, 1)" width="238" height="60"></path><path fill="none" d="M 3.5 0.5 L 235.5 0.5 C 238.5 0.5 238.5 0.5 238.5 3.5 L 238.5 57.5 C 238.5 60.5 238.5 60.5 235.5 60.5 L 125.5 60.5 119.5 66.5 113.5 60.5 3.5 60.5 C 0.5 60.5 0.5 60.5 0.5 57.5 L 0.5 3.5 C 0.5 0.5 0.5 0.5 3.5 0.5" isShadow="true" stroke="black" stroke-opacity="0.09999999999999999" stroke-width="3" transform="translate(1, 1)" width="238" height="60"></path><path fill="none" d="M 3.5 0.5 L 235.5 0.5 C 238.5 0.5 238.5 0.5 238.5 3.5 L 238.5 57.5 C 238.5 60.5 238.5 60.5 235.5 60.5 L 125.5 60.5 119.5 66.5 113.5 60.5 3.5 60.5 C 0.5 60.5 0.5 60.5 0.5 57.5 L 0.5 3.5 C 0.5 0.5 0.5 0.5 3.5 0.5" isShadow="true" stroke="black" stroke-opacity="0.15" stroke-width="1" transform="translate(1, 1)" width="238" height="60"></path><path fill="rgba(249, 249, 249, .85)" d="M 3.5 0.5 L 235.5 0.5 C 238.5 0.5 238.5 0.5 238.5 3.5 L 238.5 57.5 C 238.5 60.5 238.5 60.5 235.5 60.5 L 125.5 60.5 119.5 66.5 113.5 60.5 3.5 60.5 C 0.5 60.5 0.5 60.5 0.5 57.5 L 0.5 3.5 C 0.5 0.5 0.5 0.5 3.5 0.5" stroke="#7cb5ec" stroke-width="1"></path><text x="8" zIndex="1" style="font-size:12px;color:#333333;fill:#333333;" transform="translate(0,20)"><tspan style="font-weight:bold">University of Pennsylvania</tspan><tspan dx="0"> (In-State)</tspan><tspan x="8" dy="15">Tuition &amp; Fees: $ 43 838</tspan><tspan x="8" dy="15">Total: $ 45 088</tspan></text></g><text x="740" text-anchor="end" zIndex="8" style="cursor:pointer;color:#909090;font-size:9px;fill:#909090;" y="395">Highcharts.com</text><rect x="0.5" y="0.5" width="23" height="19" strokeWidth="1" rx="3" ry="3" transform="translate(716,10)" fill="url(#highcharts-2)" zIndex="19" stroke="#B0B0B0" stroke-width="1"></rect><rect x="0.5" y="0.5" width="23" height="19" strokeWidth="1" rx="3" ry="3" transform="translate(690,10)" fill="url(#highcharts-2)" zIndex="19" stroke="#B0B0B0" stroke-width="1"></rect><path fill="#A8BF77" d="M 5.5 16.5 L 17.5 16.5 17.5 14.5 5.5 14.5 Z M 11.5 14.5 L 15.5 9.5 10.5 9.5 10.5 4.5 12.5 4.5 12.5 9.5 7.5 9.5 Z" transform="translate(716,10)" stroke="#A0A0A0" stroke-width="1" zIndex="20"></path><path fill="#B5C9DF" d="M 5.5 12.5 L 17.5 12.5 17.5 9.5 5.5 9.5 Z M 7.5 9.5 L 7.5 4.5 15.5 4.5 15.5 9.5 Z M 7.5 12.5 L 5.5 16.5 17.5 16.5 15.5 12.5 Z" transform="translate(690,10)" stroke="#A0A0A0" stroke-width="1" zIndex="20"></path><rect x="716" y="10" width="24" height="20" id="exportButton" fill="rgba(255, 255, 255, 0.001)" zIndex="21" style="cursor:pointer;"><title>Export to raster or vector image</title></rect><rect x="690" y="10" width="24" height="20" id="printButton" fill="rgba(255, 255, 255, 0.001)" zIndex="21" style="cursor:pointer;"><title>Print the chart</title></rect></svg></div></div></center>
        
        
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
    <script src="..\vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="..\vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
    <script src="..\vendor/scrollreveal/scrollreveal.min.js"></script>
    <script src="..\vendor/magnific-popup/jquery.magnific-popup.min.js"></script>

    <!-- Theme JavaScript -->
    <script src="..\js/creative.min.js"></script>
</body>
</html>