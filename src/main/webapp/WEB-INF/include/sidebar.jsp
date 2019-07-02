<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

  <head>
  
    <!-- Bootstrap core CSS-->
    <link href="/res/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="/res/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Page level plugin CSS-->
    <link href="/res/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/res/css/sb-admin.css" rel="stylesheet">

  </head>

  <body id="page-top">
    <!-- Sidebar -->
      <ul class="sidebar navbar-nav">
        <li class="nav-item ${param.pageName=='index' ? 'active' : '' }">
          <a class="nav-link" href="/infraboard/home">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span>
          </a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fas fa-fw fa-folder"></i>
            <span>Pages</span>
          </a>
          <div class="dropdown-menu" aria-labelledby="pagesDropdown">
            <h6 class="dropdown-header">Login Screens:</h6>
            <a class="dropdown-item" href="login.html">Login</a>
            <a class="dropdown-item" href="register.html">Register</a>
            <a class="dropdown-item" href="forgot-password.html">Forgot Password</a>
            <div class="dropdown-divider"></div>
            <h6 class="dropdown-header">Other Pages:</h6>
            <a class="dropdown-item" href="404.html">404 Page</a>
            <a class="dropdown-item" href="blank.html">Blank Page</a>
          </div>
        </li>
        
        <li class="nav-item ${param.pageName=='board' ? 'active' : '' }">
          <a class="nav-link" href="/infraboard/board">
            <i class="fas fa-fw fa-chart-area"></i>
            <span>Board</span></a>
        </li>
        <li class="nav-item ${param.pageName=='calendar' ? 'active' : '' }">
          <a class="nav-link" href="/infraboard/calendar">
            <i class="fas fa-fw fa-table"></i>
            <span>Calendar</span></a>
        </li>
      </ul>
  </body>

</html>
