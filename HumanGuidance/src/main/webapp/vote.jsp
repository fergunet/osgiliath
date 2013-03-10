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
    float value = 1;
    String name = null;
    
    while (paramNames.hasMoreElements()) {
        String paramName = (String) paramNames.nextElement();
        if (paramName.equals("value")) {
            String paramValue = request.getParameter(paramName);
            value = Float.parseFloat(paramValue);
        }
        if (paramName.equals("name")) {
            String paramValue = request.getParameter(paramName);
            name = paramValue;
        }
    }
    if (name != null){
        Helper.getInstance().vote(name, value);
    }
%>

{
    "status": "ok"
}
