<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-sm-3 " >
    <div class="card bg-light mb-3 ">
        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Thể Loại
        </div>
        <ul class="list-group category_block">
            <c:forEach items="${ListCategory}" var="o">
                <li class="list-group-item text-white ${tag == o.cid ? "active" : ""}">
                    <a href="category?categoryID=${o.cid}">${o.cname}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="card bg-light mb-3">
        <div class="card-header bg-success text-white text-uppercase">Sản Phẩm Mới Nhất</div>
        <div class="card-body">
            <img class="img-fluid" src="${LastProduct.image}"/>
            <h5 class="card-title">${LastProduct.name}</h5>
            <p class="card-text">${LastProduct.title}</p>
            <p class="bloc_left_price">${LastProduct.price} VND</p>
        </div>
    </div>
</div>
