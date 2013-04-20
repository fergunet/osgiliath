<%-- 
    Document   : images
    Created on : 10-mar-2013, 19:44:00
    Author     : rhgarcia
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashSet"%>
<%@page import="org.osgiliath.humanguidance.Helper"%>
<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Enumeration paramNames = request.getParameterNames();
    float value = 1;
    String ok = null;
    String[] ko = null;
    
    while (paramNames.hasMoreElements()) {
        String paramName = (String) paramNames.nextElement();
        if (paramName.equals("ok")) {
            String paramValue = request.getParameter(paramName);
            ok = paramValue;
        }
        if (paramName.equals("ko")) {
            String paramValue = request.getParameter(paramName);
            ko = paramValue.split(",");
        }
    }
    Helper.getInstance().vote(ok, ko);
    
%>

{
    "status": "ok"
}
