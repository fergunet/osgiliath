<%@page import="org.osgiliath.humanguidance.Helper"%>
<%@page import="java.util.Enumeration"%>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OsgiliART Human guidance interface</title>
        <script src="lib/jquery-1.9.1.js"></script>
        <script src="lib/bootstrap/js/bootstrap.js"></script>
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap-responsive.css" />
        <link rel="stylesheet" href="style/osgiliart.css" />
    </head>


    <body>
        <div id="header">
            <div class="container">
                <h1>OsgiliART Human Guidance</h1>
            </div>
        </div>

        <div class="container">
            <%
                Enumeration paramNames = request.getParameterNames();

                while (paramNames.hasMoreElements()) {
                    String paramName = (String) paramNames.nextElement();
                    if (paramName.equals("selected")){
                        String paramValue = request.getParameter(paramName);
                        Helper.getInstance().increaseVotes(paramValue);
                    }
                }

                String[] ret = org.osgiliath.humanguidance.Helper.
                        getInstance().getRandomFileNames(4);
            %>

            <h2>Please, choose the image you like the most</h2>
            <div class="row">
                <div class="span3">
                    <img src="<%=ret[0]%>" class="clickable_image" />
                </div>
                <div class="span3">
                    <img src="<%=ret[1]%>" class="clickable_image" />
                </div>
                <div class="span3">
                    <img src="<%=ret[2]%>" class="clickable_image" />
                </div>
                <div class="span3">
                    <img src="<%=ret[3]%>" class="clickable_image" />
                </div>
            </div>

            <div id="footer">

            </div>
    </body>

    <script>
        $(".clickable_image").click(function () {
            var url = this.getAttribute("src");
            var filename = url.substring(url.lastIndexOf('/')+1);
            window.location = "index.jsp?selected="+filename
        });
    </script>

</html>
