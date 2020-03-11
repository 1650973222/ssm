<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script src="webjars/jquery/3.3.1-2/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>
	<script type="application/javascript">
		$(function(){
			var priv=$("#priv");
			var next=$("#next");
			var shang=parseInt(priv.attr("yema"));
			var xia=parseInt(next.attr("yema"));
			var pageCount=parseInt(next.attr("pageCount"));

			if(shang==1){
				priv.removeAttr("href");
			}
			if(xia==pageCount){
				next.removeAttr("href");
			}
			$("#OK").click(function(){
				//获取输入的页数
				var zd = $("#zd").val();
				//获取三个条件
				var book_type = $("#type option:selected").val();
				var book_name = $("#book_name").val();
				var is_borrow = $("#borrow option:selected").val();
				window.location.href = "list?book_type="+book_type+"&book_name="+book_name+"&is_borrow="+is_borrow+"&pageIndex="+zd;
			});
		});
		function logout(){
			window.location.href = "logout";
		}
	</script>
</head>
<body>
	<div class="container" align="center" style="width: 870px;">

		<form class="form-inline" action="${pageContext.request.contextPath}/list" method="post">
			图书分类：
			<select id="type" class="form-control" name="book_type">
				<option value="">——请选择——</option>
				<c:forEach items="${bookInfoList }" var="c">
					<option value="${c.book_type }"
					
						<c:if test="${c.book_type==book_type}">selected</c:if>
					>
					
						<c:if test="${c.book_type==1}">小说</c:if>
						<c:if test="${c.book_type==2}">文学</c:if>
						<c:if test="${c.book_type==3}">传记</c:if>
						<c:if test="${c.book_type==4}">艺术</c:if>
						<c:if test="${c.book_type==5}">少儿</c:if>
						<c:if test="${c.book_type==6}">经济</c:if>
						<c:if test="${c.book_type==7}">管理</c:if>
						<c:if test="${c.book_type==8}">科技</c:if>
					</option>
				</c:forEach>  
			</select>
			
			&emsp;&emsp;
			
			<div class="form-group">
			    <label for="book_name">图书名称：</label>
			    <input type="text" class="form-control" id="book_name" name="book_name" value="${book_name }"/>
			</div>
			&emsp;&emsp;
			是否借阅：
			<select id="borrow" class="form-control" name="is_borrow">
				<option value="">--请选择--</option>  
				<option value="0" <c:if test="${is_borrow==0}">selected</c:if>>未借阅</option>  
				<option value="1" <c:if test="${is_borrow==1}">selected</c:if>>已借阅</option>  
			</select>
			&emsp;&emsp;
			<button type="submit" class="btn btn-primary">查询</button>
		</form>
		<br><br>
		
		<span style="float: right;">当前用户：${sessionScope.users.user_code }&emsp;&emsp;<a href="javascript:;" onclick="logout()">退出</a></span>
		<br><br>
		<div class="table-responsive">
		  <table width="90%" border="1" style="text-align: center;">
		    <tr style="background: #319ACC">
			  <td>图书编号</td>
			  <td>图书分类</td>
			  <td>图书名称</td>
			  <td>作者</td>
			  <td>出版社</td>
			  <td>操作</td>
			</tr>
			
			<c:if test="${bookInfoList.size() == 0 }">
				<tr>
				  <td colspan="6"><h2>对不起，没有符合查询条件的结果</h2></td>
				</tr>
			</c:if>
			
			<c:if test="${bookInfoList.size() > 0 }">
			
			<c:forEach items="${bookInfoList}" var="b">
			    <tr>
				  <td>${b.book_id }</td>
				  <td>
				  	<c:if test="${b.book_type==1}">小说</c:if>
					<c:if test="${b.book_type==2}">文学</c:if>
					<c:if test="${b.book_type==3}">传记</c:if>
					<c:if test="${b.book_type==4}">艺术</c:if>
					<c:if test="${b.book_type==5}">少儿</c:if>
					<c:if test="${b.book_type==6}">经济</c:if>
					<c:if test="${b.book_type==7}">管理</c:if>
					<c:if test="${b.book_type==8}">科技</c:if>
				  </td>
				  <td>${b.book_name }</td>
				  <td>${b.book_atuthor }</td>
				  <td>${b.publish_press }</td>
				  <td>
				  	<c:if test="${b.is_borrow == 0}"><span><a href="${pageContext.request.contextPath}/update?id=${b.book_id }">申请借阅</a></span></c:if>
				  	<c:if test="${b.is_borrow == 1}"><span style="color: red">已借阅</span></c:if>
				  </td>
				</tr>
			</c:forEach>
			</c:if>
		  </table>
		</div>
		
		<c:if test="${bookInfoList.size() > 0 }">
		<nav>
			<ul class="pagination">
			    <li>
			      	<a id="priv" yema="${pages.getPageNum()}" href="${pageContext.request.contextPath}/list?pageIndex=${pages.getPageNum()-1}&book_type=${book_type}&book_name=${book_name}&is_borrow=${is_borrow}" aria-label="Previous">
			        	<span aria-hidden="true">&laquo;</span>
			      	</a>
			    </li>
   				<li><a href="${pageContext.request.contextPath}/list?pageIndex=1&book_type=${book_type}&book_name=${book_name}&is_borrow=${is_borrow}">1</a></li>
			    <li><a href="${pageContext.request.contextPath}/list?pageIndex=2&book_type=${book_type}&book_name=${book_name}&is_borrow=${is_borrow}">2</a></li>
			    <li><a href="${pageContext.request.contextPath}/list?pageIndex=3&book_type=${book_type}&book_name=${book_name}&is_borrow=${is_borrow}">3</a></li>
			    <li><a href="${pageContext.request.contextPath}/list?pageIndex=4&book_type=${book_type}&book_name=${book_name}&is_borrow=${is_borrow}">4</a></li>
			    <li><a href="${pageContext.request.contextPath}/list?pageIndex=5&book_type=${book_type}&book_name=${book_name}&is_borrow=${is_borrow}">5</a></li>
			    <li>
			      <a id="next" yema="${pages.getPageNum()}" pageCount="${pages.getPages() }" href="${pageContext.request.contextPath}/list?pageIndex=${pages.getPageNum()+1}&book_type=${book_type}&book_name=${book_name}&is_borrow=${is_borrow}" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
    			</li>
    			<li>
    				&emsp;第${pages.getPageNum()}页&emsp;共${pages.getPages() }页&emsp;
    				转到<input size="1" name="zd" id="zd"/>页&emsp;
    				<button type="button" class="btn btn-info" id="OK">确定</button>
    			</li>
  			</ul>
		</nav>
		</c:if>
	</div>
</body>
</html>