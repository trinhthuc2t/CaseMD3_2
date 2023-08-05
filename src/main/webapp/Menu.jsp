<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--begin of menu-->

<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
  <div class="container">
    <a class="navbar-brand" href="home">Nhà Hàng 3 Miền</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
            aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
      <ul class="navbar-nav m-auto">
        <c:if test="${sessionScope.acc.isAdmin == 1}">
          <li class="nav-item">
            <a class="nav-link" href="#">Quản Lý Tài Khoản</a>
          </li>
        </c:if>

        <c:if test="${sessionScope.acc.isSell == 1}">
          <li class="nav-item">
            <a class="nav-link" href="managerProduct">Quản Lý Sản Phẩm</a>
          </li>
        </c:if>

        <c:if test="${sessionScope.acc != null}">
          <li class="nav-item">
            <a class="nav-link" href="#">Xin Chào ${sessionScope.acc.user}</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="logout">Đăng Xuất</a>
          </li>
        </c:if>

        <c:if test="${sessionScope.acc == null}">
          <li class="nav-item">
            <a class="nav-link" href="/Login.jsp">Đăng Nhập</a>
          </li>
        </c:if>
      </ul>

      <form action="search" method="post" class="form-inline my-2 my-lg-0">
        <div class="input-group input-group-sm">
          <input name="txt" type="text" class="form-control" aria-label="Small"
                 aria-describedby="inputGroup-sizing-sm" placeholder="Tìm kiếm...">
          <div class="input-group-append">
            <button type="submit" class="btn btn-secondary btn-number">
              <i class="fa fa-search"></i>
            </button>
          </div>
        </div>
        <a class="btn btn-success btn-sm ml-3" href="/Cart.jsp">
          <i class="fa fa-shopping-cart"></i> Giỏ hàng
          <span class="badge badge-light"></span>
        </a>
      </form>
    </div>
  </div>
</nav>
<section class="jumbotron text-center ">
  <div class="container">
    <h1 class="jumbotron-heading">Nhà Hàng 3 Miền</h1>
    <p class="lead text-muted mb-0">Uy tín tạo nên thương hiệu với hơn 10 năm cung cấp các sản phầm đồ ăn</p>
  </div>
</section>


