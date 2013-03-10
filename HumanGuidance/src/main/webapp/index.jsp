<%@page import="java.util.HashSet"%>
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

                HashSet<String> ignoreList = new HashSet<String>();

                while (paramNames.hasMoreElements()) {
                    String paramName = (String) paramNames.nextElement();
                    if (paramName.equals("selected")) {
                        String paramValue = request.getParameter(paramName);
                        Helper.getInstance().vote(paramValue, 1);
                        ignoreList.add(paramValue);
                    }
                }

                String[] ret = org.osgiliath.humanguidance.Helper.
                        getInstance().getRandomFileNames(4, ignoreList);
            %>

            <h2>Please, choose the image you like the most</h2>
            <div class="row" id="imagediv">
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
            var index = $(".clickable_image").index(this)
            var url = this.getAttribute("src");
            var filename = url.substring(url.lastIndexOf('/')+1);
            var t_url = "vote.jsp?name="+filename+"&value=1"
            $.ajax({
                url: t_url,
                context: document.body
            }).done(function() {
                
                //$(this).addClass("done");
                var t_url2 = "images.jsp?number=3&ignored="+filename
                $.ajax({
                    url: t_url2,
                    context: document.body
                }).done(function(data) {
                    //$(this).addClass("done");
                    //alert("data: "+data)
                    var obj = jQuery.parseJSON(data);
                    
                    var children = $("#imagediv").children();
                    for (var i = 0, ind=0; i<children.length; i++){
                        if (index != i){
                            var element = children.eq(i).children().eq(0)
                            element.attr("src_alt", obj.urls[ind])
                            element.fadeOut(200, function(){
                                var src = $(this).attr("src_alt")
                                $(this).attr("src", src)
                                $(this).fadeIn(500)
                            })
                            ind++
                        }
                    }
                });
                
            });
        });
        
    </script>

</html>
