<%--
  Created by IntelliJ IDEA.
  User: kxp6223065
  Date: 2019/6/20
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<body>
<div class="container">
    <div class="row one-row">
        <div class="col one-fontsize">客服热线：010-594-78634</div>
        <div class="col">
            <div class="one-image">
                <div class="one-image1"></div>
                <div class="one-image2"></div>
                <div class="one-image3"></div>
            </div>
        </div>
    </div>
</div>
<div class="two">
    <div class="container">
        <div class="row">
            <div class="col">
                <div class="two-tree">技能树</div>
                <div class="two-website">www.jnshu.com</div>
            </div>
            <nav class="col navbar-expand-md navbar-dark">
                <button class="two-button navbar-toggler" type="button" data-toggle="collapse" data-target="#a">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="two-ul-li collapse navbar-collapse" id="a">
                    <ul class="two-ul navbar-nav">
                        <li class="two-li nav-item">
                            <a class="two-a" href="${pageContext.request.contextPath}/one">首页</a>
                            <div class="two-underline"></div>
                        </li>
                        <li class="two-li nav-item">
                            <a class="two-a" href="${pageContext.request.contextPath}/two">职业</a>
                            <div class="two-underline"></div>
                        </li>
                        <li class="two-li nav-item">
                            <a class="two-a" href="${pageContext.request.contextPath}/three">推荐</a>
                            <div class="two-underline"></div>
                        </li>
                        <li class="two-li nav-item">
                            <a class="two-a" href="#">关于</a>
                            <div class="two-underline"></div>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
</div>
</body>