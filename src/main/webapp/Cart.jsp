<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>

<body>
<jsp:include page="Menu.jsp"></jsp:include>
<div class="shopping-cart">
    <div class="px-4 px-lg-0">

        <div class="pb-5">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

                        <!-- Shopping cart table -->
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col" class="border-0 bg-light">
                                        <div class="p-2 px-3 text-uppercase">Sản Phẩm</div>
                                    </th>
                                    <th scope="col" class="border-0 bg-light">
                                        <div class="py-2 text-uppercase">Đơn Giá</div>
                                    </th>
                                    <th scope="col" class="border-0 bg-light">
                                        <div class="py-2 text-uppercase">Số Lượng</div>
                                    </th>
                                    <th scope="col" class="border-0 bg-light">
                                        <div class="py-2 text-uppercase">Xóa</div>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${sessionScope.cart}" var="p" varStatus="status">
                                    <tr>
                                        <th scope="row">
                                            <div class="p-2">
                                                <img src="${p.image}" alt="" width="70"
                                                     class="img-fluid rounded shadow-sm">
                                                <div class="ml-3 d-inline-block align-middle">
                                                    <h5 class="mb-0"><a href=""
                                                                        class="text-dark d-inline-block">${p.name}</a>
                                                    </h5><span class="text-muted font-weight-normal font-italic"></span>
                                                </div>
                                            </div>
                                        </th>
                                        <td class="align-middle"><strong>${p.price}</strong></td>
                                        <td class="align-middle">
                                            <a href="#">
                                                <button class="btnSub" data-index="${status.index}">-</button>
                                            </a>
                                            <strong>${p.quantity}</strong>
                                            <a href="#">
                                                <button class="btnAdd" data-index="${status.index}">+</button>
                                            </a>
                                        </td>
                                        <td class="align-middle">
                                            <form action="delete-product" method="post">
                                                <input type="hidden" name="index" value="${status.index}">
                                                <button type="submit" class="btn btn-danger">Xóa</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- End -->
                    </div>
                </div>

                <form action="create-order" method="post">
                    <div class="row py-5 p-4 bg-white rounded shadow-sm">
                        <div class="col-lg-6">
                            <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Mã giảm giá</div>
                            <div class="p-4">
                                <div class="input-group mb-4 border rounded-pill p-2">
                                    <input type="text" placeholder="Nhập Voucher" aria-describedby="button-addon3"
                                           class="form-control border-0">
                                    <div class="input-group-append border-0">
                                        <button id="button-addon3" type="button" class="btn btn-dark px-4 rounded-pill">
                                            <i class="fa fa-gift mr-2"></i>Sử dụng
                                        </button>
                                    </div>
                                </div>
                                <div class="input-group mb-4 border rounded-pill p-2">
                                    <input type="text" name="name" placeholder="Nhập tên của bạn"
                                           aria-describedby="button-addon3" class="form-control border-0" required>
                                    <div class="input-group-append border-0"></div>
                                </div>
                                <div class="input-group mb-4 border rounded-pill p-2">
                                    <input type="text" name="phone" placeholder="Nhập Số Điện Thoại Của Bạn"
                                           aria-describedby="button-addon3" class="form-control border-0" required>
                                    <div class="input-group-append border-0"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Thành tiền
                            </div>
                            <div class="p-4">
                                <ul class="list-unstyled mb-4">
                                    <li class="d-flex justify-content-between py-3 border-bottom"><strong
                                            class="text-muted">Tổng tiền hàng</strong><strong><input type="hidden" name="totalPrice" value="${totalPrice}">${totalPrice} VND</strong>
                                    </li>
                                    <li class="d-flex justify-content-between py-3 border-bottom"><strong
                                            class="text-muted">Phí vận chuyển</strong><strong>0 VND</strong></li>
                                    <li class="d-flex justify-content-between py-3 border-bottom"><strong
                                            class="text-muted">Thuế VAT</strong><strong><input type="hidden" name="vat" value="${vat}">${vat} VND</strong></li>
                                    <li class="d-flex justify-content-between py-3 border-bottom"><strong
                                            class="text-muted">Tổng thanh toán</strong>
                                        <h5 class="font-weight-bold"><input type="hidden" name="totalPayment" value="${totalPayment}">${totalPayment} VND</h5>
                                    </li>
                                </ul>
                                <input type="submit" class="btn btn-dark rounded-pill py-2 btn-block">
                            </div>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {
        // Xử lý sự kiện khi nhấn nút tăng số lượng
        $(".btnAdd").click(function() {
            var index = $(this).attr("data-index");
            updateCartQuantity(index, 1);
        });

        // Xử lý sự kiện khi nhấn nút giảm số lượng
        $(".btnSub").click(function() {
            var index = $(this).attr("data-index");
            updateCartQuantity(index, -1);
        });

        function updateCartQuantity(index, change) {
            $.ajax({
                type: "POST",
                url: "/update-cart-quantity",
                data: { index: index, change: change },
                success: function(response) {
                    // Cập nhật số lượng mới trên giao diện (nếu cần)
                    location.reload(); // Tải lại trang để cập nhật giỏ hàng và hiển thị số lượng mới
                },
                error: function() {
                    // Xử lý lỗi (nếu có)
                }
            });
        }
    });
</script>

</body>

</html>

