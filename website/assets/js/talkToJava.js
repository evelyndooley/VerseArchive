// author: Andrew Berson (axb4069@rit.edu)
// filename: talkToJava.js
// This is a program that submits AJAX requests to a Java Web Servlet
// Dependencies: JavaServlet (NameTBD), JQuery, index.html
$(document).on("click", "#submit", function(){ // JQuery DOM Event Listener
	$.get("http://natedooley.com:8080/GiveItAString", function(data){ // AJAX get request
		$("#submit").text(data); //data holds the returned String,
	});// Which replaces the contents of the text field
}
);
