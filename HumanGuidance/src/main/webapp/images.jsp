<%-- 
    Document   : images
    Created on : 10-mar-2013, 19:44:00
    Author     : rhgarcia
--%>

<%@page import="java.util.HashSet"%>
<%@page import="org.osgiliath.humanguidance.Helper"%>
<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Enumeration paramNames = request.getParameterNames();
    int number = 4;
    HashSet<String> ignored = new HashSet<String>();
    
    while (paramNames.hasMoreElements()) {
        String paramName = (String) paramNames.nextElement();
        if (paramName.equals("number")) {
            String paramValue = request.getParameter(paramName);
            number = Integer.parseInt(paramValue);
        }
        if (paramName.equals("ignored")) {
            String paramValue = request.getParameter(paramName);
            ignored.add(paramValue);
        }
    }
    String[] files = Helper.getInstance().getRandomFileNames(number, ignored);
%>

{
    "urls": [
        <%
        for (int i=0; i<files.length-1; i++){
        %>
        "<%=files[i]%>",
        <%
        }
        
        for (int i=files.length-1; i<files.length; i++){
        %>
        "<%=files[i]%>"
        <%
        }
        %>
    ]
}



