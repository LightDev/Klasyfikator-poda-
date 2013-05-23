<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>System wspomagania decyzji przyjmowania do żłobków</title>
        <link href="css/StyleSheet1.css" rel="stylesheet" type="text/css">
        <!--<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>-->
    </head>
    <body>
        <div id="page">
            <a href="admin_panel" style="float: right; text-decoration: none;padding-bottom: 7px;">Moduł administratora</a>
            <div id="header">
                <h1 class="h2_decoration "><a href="index.jsp">Klasyfikator podań do przedszkola</a></h1>
            </div>
            <div id="pageInside">
                <h1 class="h2_decoration underline">Rezultat klasyfikacji</h1>
                <pre  class="">${evaluation}</pre>
                <br />
                <p  class="">Przydzielenie do żłobka: ${class}</p>
                <br />
                <br />
            </div>
            <div id="footer">Autor: Paweł Parafiniuk</div>
        </div>
    </body>
</html>