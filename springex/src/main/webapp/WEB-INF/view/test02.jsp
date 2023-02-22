<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-02-16
  Time: 오전 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
      Title : ${todoDTO.title}
    </div>
    <div>
      DueDate : ${todoDTO.dueDate}
    </div>
    <div>
      Writer : ${todoDTO.writer}
    </div>
    <div>
      Finished : ${todoDTO.finished}
    </div>
</body>
</html>
