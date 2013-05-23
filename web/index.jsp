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
                <h1 class="h2_decoration ">Klasyfikator podań do przedszkola</h1>
            </div>
            <div id="pageInside">
                <h1 class="h2_decoration underline">Uzupełnij formularz</h1><br />
                <div class="wrap">
                    <form action="classifyNewInstance" method="post">
                        <!--                    parents        usual, pretentious, great_pret
                                            has_nurs       proper, less_proper, improper, critical, very_crit
                                            form           complete, completed, incomplete, foster
                                            children       1, 2, 3, more
                                            housing        convenient, less_conv, critical
                                            finance        convenient, inconv
                                            social         non-prob, slightly_prob, problematic
                                            health         recommended, priority, not_recom-->

                        <table>
                            <tr>
                                <td>parents:</td><td><select name="parents" >
                                        <option value="0">usual</option>
                                        <option value="1">pretentious</option>
                                        <option value="2">great_pret</option>
                                    </select></td>
                            </tr>
                            <!--<br />-->
                            <tr>
                                <td>has_nurs:</td>
                                <td><select name="has_nurs">
                                        <option value="0">proper</option>
                                        <option value="1">less_proper</option>
                                        <option value="2">improper</option>
                                        <option value="3">critical</option>
                                        <option value="4">very_crit</option>
                                    </select></td>
                            </tr>
                            <tr>
                                <td>form:</td>
                                <td><select name="form">
                                        <option value="0">complete</option>
                                        <option value="1">completed</option>
                                        <option value="2">incomplete</option>
                                        <option value="3">foster</option>
                                    </select>
                                </td>
                            </tr><!--<br />-->
                            <tr>
                                <td>children:</td>
                                <td><select name="children">
                                        <option value="0">1</option>
                                        <option value="1">2</option>
                                        <option value="2">3</option>
                                        <option value="3">more</option>
                                    </select>
                                </td>
                            </tr>
                            <!--<br />-->
                            <tr>
                                <td>housing:</td>
                                <td><select name="housing">
                                        <option value="0">convenient</option>
                                        <option value="1">less_conv</option>
                                        <option value="2">critical</option>
                                    </select></td>
                                <!--<br />-->
                            </tr>
                            <tr>                       <td>finance:</td>
                                <td><select name="finance">
                                        <option value="0">convenient</option>
                                        <option value="1">inconv</option>
                                    </select>
                                </td>
                            </tr>                      <!--<br />-->
                            <tr>                      <td>social:</td><td><select name="social">
                                        <option value="0">non-prob</option>
                                        <option value="1">slightly_prob</option>
                                        <option value="2">problematic</option>
                                    </select></td>
                            </tr>
                            <tr>                        <!--<br />-->
                                <td>health:</td><td><select name="health">
                                        <option value="0">recommended</option>
                                        <option value="1">priority</option>
                                        <option value="2">not_recom</option>
                                    </select></td>
                            </tr>
                            <!--<br />-->
                            <tr>                        <!--<br />-->
                                <td></td>
                                <td></td>
                            </tr>                        <!--<br />-->

                            <tr>                        <!--<br />-->
                                <td></td>
                                <td><input type="submit"  class="button" value="Sprawdź klasę"/></td>
                            </tr>                        <!--<br />-->

                        </table>
                    </form>
                    <br />

                </div>
            </div>
            <div id="footer">Autor: Paweł Parafiniuk</div>
        </div>
    </body>
</html>