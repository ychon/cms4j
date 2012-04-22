<%--
  研究所.
  User: Deng Xiaolan (824688439@qq.com)
  Date: 12-4-21
  Time: 下午9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sitemesh-page" uri="http://www.opensymphony.com/sitemesh/page" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>金融信息安全研究所</title>
</head>
<body>
<img id="banner" src="${ctx}/static/images/banner.png"/>
<div id="index_left">
    <div id="left_l">
        <div class="container container_l">
            <p class="title_index title_l">
                <strong>发展定位</strong>
            </p>
            <p class="researchDesc">山东省金融信息工程技术研究中心依托山东财经大学的金融、财税、会计、管理、数学和信息技术等雄厚的学科资源，联合行业内相关优势企业，为推动我国金融信息化的发展及相关技术的研究而成立的一个开放的专业从事与金融信息化和金融风险管理相关的理论与应用技术研究、教育培训、战略规划与咨询服务的组织。中心于2010年12月经山东省科技厅批准成立，是省内在金融信息工程领域唯一的省级工程技术研究中心。中心目前主持国家级科研课题4项，省部级科研课题5项,取得软件著作权6项，申报国家发明专利3项。独立研究及与合作伙伴共同研发的多项成果已成功应用于交通银行、广发银行、恒丰银行、齐鲁银行及小额贷款公司等非银行金融机构。</p>
        </div>
    </div>
    <!---left_l end----->
    <div id="left_r">
        <fieldset class="content container_r">
            <legend><a href="#"><strong>咨询服务</strong></a></legend>
            <ul class="content twoRow">
                <li><a href="">风险管理组织架构</a></li>
                <li><a href="">风险管理组织架构</a></li>
                <li><a href="">信用风险内部评级体系</a></li>
            </ul>
            <ul class="content twoRow">
                <li><a href="">Basel新资本协议</a></li>
                <li><a href="">全面风险管理规划</a></li>
                <li><a href="">金融风险监控体系</a></li>
            </ul>
        </fieldset>
        <fieldset class="content container_r">
            <legend><a href="#"><strong>解决方案</strong></a></legend>
            <ul class="content left_r">
                <li><a href="#">农村金融机构全面风险管理方案</a></li>
                <li><a href="#">中小银行信用风险内部评级解决方案</a></li>
                <li><a href="#">战略规划落地实施解决方案</a></li>
            </ul>
        </fieldset>
    </div>
    <!---- left_r end---->
    <div id="left_b"> <%@ include file="/WEB-INF/layouts/teacher.jsp" %></div>
    <!---教师风采---->
</div>
<div id="index_right">
    <div class="container container_right">
        <p class="title_index title_rl"><strong>教育培训</strong><%--<a href="#" class="more">更多>></a>--%></p>
        <a class="institution peixun" href="#">证书培训</a>
        <a class="institution peixun" href="#">高管培训</a>
        <a class="institution peixun" href="#">学历培训</a>
        <a class="institution peixun" href="#">学历培训</a>
    </div>
    <div class="container container_right">
        <p class="title_index title_rl"><strong>IT支持系统</strong><a href="${ctx}/article/list/" class="more">更多>></a></p>
        <ul class="content r_content">
                <li><a href="#">风险及内控管理系统</a></li>
                <li><a href="#">流动性风险管理系统</a></li>

        </ul>
    </div>
    <div class="container container_right">
        <p class="title_index title_rl"><strong>合作伙伴</strong><%--<a href="#" class="more">更多>></a>--%></p>
        <ul class="content r_content">
            <c:forEach items="${companies}" var="company" begin="0" step="1">
                <li><a href="${ctx}/article/content/${company.id}">${fn:substring(company.title,0,14)}</a></li>
            </c:forEach>
        </ul>
    </div>

</div>
<ul id="friLnk">
    <li class="friLnkT cufon"><strong>友情链接</strong></li>
    <c:forEach items="${links}" var="link" begin="0" step="1">
        <li><a href="${link.url}">${fn:substring(link.title,0,13)}</a></li>
    </c:forEach>
</ul>
</body>
</html>