<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Java RSS web client</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/blog-home.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                	<c:if test = "${not empty sessionScope.infos}">
                    <li>
                        <a href="#" id="logoutLink">Logout from <c:out value = "${sessionScope.infos.email}" /></a>
                    </li>
                    </c:if>
                    <c:if test = "${empty sessionScope.infos}">
                    <li>
                        <a href="#" data-toggle="modal" data-target="#loginModal">Login</a>
                        <!-- Modal -->
                        <!-- IMPORTANT ! the CSS must be modified to contain ".modal-backdrop.in { z-index: auto;}"
                        for details: http://stackoverflow.com/questions/18580203/bootstrap-modal-window-is-grayed-out -->
                        <div id="loginModal" class="modal fade" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <form class="modal-content" action="login" method="post">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Login</h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label for="mail">Email:</label>
                                            <input type="text" class="form-control" id="maillogin" name="mail" placeholder="Enter mail">
                                        </div>
                                        <div class="form-group">
                                            <label for="pwd">Password:</label>
                                            <input type="password" class="form-control" id="pwdlogin" name="password" placeholder="Enter password">
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" id ="submitlogin" class="btn btn-default" data-dismiss="modal">Login</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <!-- End Modal -->
                    </li>
                    </c:if>
                    <c:if test = "${empty sessionScope.infos}">
                    <li>
                        <a href="#" data-toggle="modal" data-target="#registerModal">Register</a>
                        <!-- Modal -->
                        <!-- IMPORTANT ! the CSS must be modified to contain ".modal-backdrop.in { z-index: auto;}"
                        for details: http://stackoverflow.com/questions/18580203/bootstrap-modal-window-is-grayed-out -->
                        <div id="registerModal" class="modal fade" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <form class="modal-content" action="register" method="post">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Register</h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label for="email">Email:</label>
                                            <input type="email" class="form-control" id="mailregister" name="mail" placeholder="Enter email">
                                        </div>
                                        <div class="form-group">
                                            <label for="pwd">Password:</label>
                                            <input type="password" class="form-control" id="pwdregister" name="password" placeholder="Enter password">
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" id ="submitregister" class="btn btn-default" data-dismiss="modal">Register</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <!-- End Modal -->
                    </li>
                    </c:if>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">

        <div class="row">
            <div class="col-md-8">

                <h1 class="page-header">
                    Recents Articles
                </h1>

                <h2>
                    <a href="#">Mickaël va encore passer pour cette fois !</a>
                </h2>
                <p class="lead">
                    by <a href="index.php">Le Monde</a>
                </p>
                <p><span class="glyphicon glyphicon-time"></span> Posted on August 28, 2013 at 10:00 PM</p>
                <hr>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolore, veritatis, tempora, necessitatibus inventore nisi quam quia repellat ut tempore laborum possimus eum dicta id animi corrupti debitis ipsum officiis rerum.</p>
                <a class="btn btn-primary" href="#">Read More <span class="glyphicon glyphicon-chevron-right"></span></a>

                <hr>

            </div>

            <!-- Blog Sidebar Widgets Column -->
            <div class="col-md-4">

                <!-- Blog Search Well -->
                <div class="well">
                    <h4>Add an RSS feed</h4>
                    <div class="input-group">
                        <input type="text" class="form-control">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                <span class="glyphicon glyphicon-plus"></span>
                        </button>
                        </span>
                    </div>
                    <!-- /.input-group -->
                </div>

                <!-- Blog Categories Well -->
                <div class="well">
                    <h4>Your RSS feeds</h4>
                    <div class="row">
                        <table class="col-lg-6 table table-striped table-hover whiteBackground">
                            <tbody>
                            <c:if test = "${not empty requestScope.feeds}">
                            	<c:forEach items="${requestScope.feeds}" var="feed">
                            		<tr>
                                    	<td><c:out value = "${feed.title}" /></td>
                                    	<td><button type="button" class="close">&times;</button></td>
                                	</tr>
                            	</c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                        <!-- /.col-lg-6 -->
                    </div>
                    <!-- /.row -->
                </div>

            </div>

        </div>
        <!-- /.row -->

        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p></p>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
</body>

</html>
