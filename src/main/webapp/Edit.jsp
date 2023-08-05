<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Sửa sản phẩm</title>
  <style>
    body {
      background-color: #f2f2f2;
      font-family: Arial, sans-serif;
    }

    .modal {
      /*display: none;*/
      position: fixed;
      z-index: 1;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
      overflow: auto;
      background-color: rgba(0, 0, 0, 0.4);
    }

    .modal-dialog {
      margin: auto;
      width: 50%;
      max-width: 700px;
      height: auto;
      background-color: #fff;
      padding: 20px;
      border-radius: 5px;
    }

    .modal-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    .modal-title {
      font-size: 1.5rem;
      font-weight: bold;
    }

    .close {
      font-size: 2rem;
      font-weight: bold;
      color: #000;
    }

    .form-group {
      margin-bottom: 20px;
    }

    label {
      /*display: block;*/
      margin-bottom: 5px;
      font-weight: bold;
      color: #333;
    }

    input[type="text"],
    textarea,
    select {
      width: 100%;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
      outline: none;
      font-size: 1rem;
      font-family: Arial, sans-serif;
    }

    select {
      -webkit-appearance: none;
      -moz-appearance: none;
      appearance: none;
      background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24'%3E%3Cpath d='M7.41 8.59L12 13.17l4.59-4.58L18 9l-6 6-6-6z'/%3E%3C/svg%3E");
      background-repeat: no-repeat;
      background-position: right 0.5rem center;
      background-size: 1.5rem;
      padding-right: 2.5rem;
    }

    .btn-default {
      background-color: #ccc;
      color: #333;
    }

    .btn-success {
      background-color: #28a745;
      color: #fff;
    }

    .btn {
      /*display: inline-block;*/
      padding: 10px 20px;
      margin-right: 10px;
      border: none;
      border-radius: 5px;
      font-size: 1rem;
      font-weight: bold;
      cursor: pointer;
      text-align: center;
      text-decoration: none;
      transition: background-color 0.3s ease;
    }

    .btn:hover {
      background-color: #007bff;
      color: #fff;
    }
  </style>
</head>
<body>
<div id="addEmployeeModal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <form action="edit" method="post">
        <div class="modal-header">
          <h4 class="modal-title">Sửa sản phẩm</h4>
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><a href="managerProduct">&times;</a></button>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label>Mã Sản phẩm</label>
            <input value="${detail.id}" name="id" type="text" class="form-control" readonly required>
          </div>
          <div class="form-group">
            <label>Tên sản phẩm</label>
            <input value="${detail.name}" name="name" type="text" class="form-control" required>
          </div>
          <div class="form-group">
            <label>Ảnh</label>
            <input value="${detail.image}" name="image" type="text" class="form-control" required>
          </div>
          <div class="form-group">
            <label>Giá tiền</label>
            <input value="${detail.price}" name="price" type="text" class="form-control" required>
          </div>
          <div class="form-group">
            <label>Tiêu đề</label>
            <textarea name="title" class="form-control" >${detail.title}</textarea>
          </div>
          <div class="form-group">
            <label>Mô tả sản phẩm</label>
            <textarea name="description" class="form-control" required>${detail.description}</textarea>
          </div>
          <div class="form-group">
            <label>Phân loại</label>
            <select name="category" class="form-select" aria-label="Default select example">
              <c:forEach items="${listC}" var="o">
                <option value="${o.cid}">${o.cname}</option>
              </c:forEach>
            </select>
          </div>
        </div>
        <div class="modal-footer">
          <a href="managerProduct"><input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel"></a>
          <input type="submit" class="btn btn-success" value="OK">
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>
