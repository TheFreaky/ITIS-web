<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 08.11.2017
  Time: 3:36
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header class="header">
    <nav class="navbar navbar-static-top" role="navigation">
        <a href="" class="logo">
            <!-- Add the class icon to your logo image or logo icon to add the margining -->
            <img src="<c:url value="/resources/img/logo.png"/>" alt="logo"/>
        </a>
        <div class="navbar-left">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/trainings"/>"><i class="fa"></i>Тренировки</a></li>
            </ul>
        </div>
        <div class="navbar-right">
            <ul class="nav navbar-nav">

                <!-- User Account: style can be found in dropdown-->
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle padding-user" data-toggle="dropdown">
                        <img src="data:image/webp;base64,UklGRsgBAABXRUJQVlA4ILwBAABQCACdASojACMAPm0qk0WkIqGXHf2YQAbEsYBb7KXfgIOnWbtN5keeKFmwSy+mSZvSZ2RogH0NUQ02c2g6IDxSz/gKLSDvqaa2HMAA/v34pAFdHx5IiD/qwethEiIF+ThXwN173gvKpX+6ms1esv/w+xvBxNQk9II49JDYNv9rExf/bggcU+u2ToPVAeDD5kj9UzZkx8oOj/mqJavCd62h8qNApSva5RMbza6mJW5unGN0xNXSX615/DGdNurH/GY+hIpOBt0rL9aAXdJx7tEYfXgjMiqZk18j+fYDl+u3qrio98ivSZOW9dsi2PnshzQ58H9ZBfz29bENdOTqIzP2F2ilUrghKpd/pkAlDqEFxe3BpML1K+du5/7rVAg24n7kPjHeH6PSkol5RdvnrzOiNpuZX0pexiwpd6QhxjXbazyvTR4n/5YRDWMOknfKOs9xPsbClmf//qDzOlTp8a073fglvzpsaDKhEwhEcYUe5pWFRSWNOo49N5LqcjJ9pBwXXvP3dC8exgToGirogU4J7a/EGBFFJV5vCY3qZWgnvD7wmYwUSH00+fv8oSPZG6FTPxVdDeNMAIGuwAA="
                             class="img-circle img-responsive pull-left" alt="User Image">
                        <div class="riot">
                            <div>
                                ${sessionScope.user.name}
                                <span>
                                        <i class="caret"></i>
                                    </span>
                            </div>
                        </div>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <img src="data:image/webp;base64,UklGRsYIAABXRUJQVlA4ILoIAACwJQCdASqAAIAAPm0wlUckIqIhKBEMAIANiWVpiFg0jbQjvOvrO0NwyaNcX2b+5OcMDU5Ui14eU/3fNd9bGcmVIxuZIU1o92YrUFop16qNmvRkRj8kNbZAolz0iMvM7wURom2Qt4qIJ1h9EW3AK5ECB+grG//9TiV9nwdbbjVUZ/p/37cfdprQWQw2M2NcN0VvD+qnwnXVyg9X+O2uftDHuKxAeJZsvYPWXwD9/xsipG2AhjrNRQ0kvLZNw6ScNfvnipKx38Fbj0wR17KWLZCc7ivOjmzb8ukT+KM9CVyYEP7CBhZlIjUM7hRaYsaKouT4VUa7mMDT15lTUw6s1CfBGK/4w5qoIDqECBInBGz5VbeavaOkDq/kOAOQZRvqy1Wa6DOTLZrEi9DyUfc7OovYP316OU8AAP7668W6JsVERchFyESgpTEE9WSP+h9w+m4OmG6RFy7O1U5XwlODeOWZIo7n0Vk8kO1PGt1NTbblnnmqwziQirCM5Qj5OtuCP5UdUP1FztKpowpTH9JFoBv/sMrCXtqpI/CbkbwjSM/SL5Q4jxPSv8CYoLDhubkWI6jG8janDpuwdmXCMTTFYeFYb5943EeIU+WMi7vvuy4LG51ySFIoHkhnTwp9L0iu+JMIXAfc+EPsCxnBhE36WGUjJkmJQd9aQGvDFV6Es79ehH8EwlkAJ6+5V7rLjiMgcvD1k+PmrkLQCv6BlVu4d4Vp00OVAun4Xi8QdsKjzJUfD5hzrFdLVgXLL67Ciz9esoOrZ7491DIuM3yfwenIP4itG+BV/twFkZiLVd61IzV9AEflQwUJeGPOCFiRCHUZjI/KAM0+xMUfdyJPAgPIFROquArx/zkaT+m3lSI1o2FUoWePRL/gnuiAvKwXoWQnMY69clj8I1VfLquTvGlfIxb9GwYwKJIZ7Mt8qgljzngMMKRjSBcQQlBAjBGf+PL++uFhlYkZ1faxNFbi/oS+S515hUZ/3Q25PlU7GEDpcQo2QS39PA5miEtemg9Hcco1KIU0tQ1E/o8D+QOIHqdhHU/NC3UD68cZ9se3KbfldN4x0s7rCdQccTxOlK6qddB1PFU/kPSXeOXh2iv5C8bqnE1ADXazABHKggSnqm4zh28vNET7cdD7+xhARi18Ud5Beg5XcH2lhL9Z+uYhNXvjgmUL4IhhHzeXr+/jtl9HQ2A+l3Am5jNgMksHsgFe6SehSAUq8t6A3SnhIrlmpc2xnO2qsB971/YKFbW+1VCcwQn566U/3OT4zYZmZKRxskHHJVaO79wlHhFku5HnaAlcabJ+9E55ep5M/1GbGCRtW2PPG77wqE9DXkrxDCUXqslbFu9FbVWJSILFMHS2EXuHTduzKQjls4dWxXwuS7DgJ9QRvmiyx1TNfH4BSd9aTa1U5NeHhxA2nnziudQ2hMoQv02QEm4UmcaiX0m91rEQelBXJ4OSptKs0jEMBK00mbN5hDMW6zD8oXDRcNEoutp5IM3ev2y3bdKmxO54rJ9M95Tm0PAjPiTe6hLuqar31ba/Dz7k46XY7OAYz149J29aN+PCfanVL6Z0urjASmRV3knygByr+so3uh+4ZiuWJiuKn+02HuBJ44E0MbJDK0mi/FLlSJc5LnXaBSNakVpYR88uQYHJratJsr4XogCj2YZUH6jF3rTaZRFbpykAM+gWDrsWWfG/4gSwRE6oiW5L7s9/joPdr3IJfx/aXZExMWsG7cRlgQ6nrFiwfD8t/vgfQ/MW2JMfMNdFGdHNTVEXcp/oFRZ+UrzaXe1xBdAKrQDt7TKoeyXekkTfGlXJcR9r3JqfmgELqbSI8N6+1uquaXnavBcyjMYwLGDnDBjXm37xT/k+nB5HknCbw9Je8xd9JD1EYLvm5KGq15t3k7CMt15WoiMNts0zenzfHuPL1M4NzrMScCfwxQPPLriARpeqHSnVBOwydwteHhBGjAVNJXfigsLFVmwTX63iDwtaEARHC+bP9gQJ7JH418ZrAj1toFTIXxxszdaPrUwmglG7fuK3CLF0M9tkFJ+aj1BA/KjKUaGPgp8N+XvTPbKnH9G3IDdzbw3byXsqNIlxhFr/rbjSTkWdTDP/4T95Jgv8DJz8ZkP0oPll4Hne/37+KpfflfE4bGwjTEvaeyO57uTIC1tnQdkMBZxH4LmRsAmDtbtoM3SdTmrXp4x7TLfZbig0cjZaTS/knJCPPPT8FdsFT0fAaehFvXTNot+ifA9WcwN5mPChYvuMrlTtkwi6XYH0mtMgVzsb6nyb8yXjZX1oaNpt+pMTWw++g4nBN08hWdfRap706a38Pdf+2sRdaopw6gRSTTLj+ehwxdMk87LJGfcHZtaY6f3xn4EkZ6zAh23ysVXaf7ceQqwLY5/uhYKVQTlPBcyllkp1WrUhoJI7+ejkHM5E59oiETFYlU40ObVbtM/KtIqKRm+YHcccTGimHUWfPdZGmYkkGR+idEw2QmDNr8W9LScKR1jO1XofHCskiN5yJDGyIeHUAWsJVvLzgeJgTqWMJaftGcF06sv1qdmkFBDttS380r8X6v2woz83q60NMLqRd0/XHZHAGVRtpd4uOQFgZT/7WFYrs5HpcwnTu7x4sqImyxZUKnW929VBOBs+WnI3Crxj0Tq4fOp+tcqaOsAOXXt9u5LvIp/XGXDkKQUeipt0f008yzrFl7HqDjtySs/1PwDKS2ds8I7nFD39g9dSW+0Wa3K/559FHQzmILnyZydh70m04ahhNdPABUVhDxTw/IzTRa5GjEtX/S9ApY5xpIJ8ZcvB09kPbSbnvtw2pftzJJ6/EsZj9stBz4P/3CZXwNqRKgq2qF+6K7nDqDEwP7pmb38Yw2ppJCvadI6NejyCPelUGABrm8c+g8H46LkB6a77KikJxjmvaO60BRDY11I8wkypRvZtHzHIFXXvjq416ksHEvoCI7hYz9LvigFA7mkufkF9wuriu8S8tLtGEQH3X6p4AA=="
                                 class="img-circle" alt="User Image">
                            <p> ${sessionScope.user.name}</p>
                        </li>
                        <!-- Menu Body -->
                        <li class="p-t-3"><a href="<c:url value="/profile"/>"> <i class="fa fa-fw"></i> My Profile </a>
                        </li>
                        <li role="presentation"></li>
                        <li><a href="<c:url value="/setting"/>"> <i class="fa fa-fw"></i> Account Settings </a></li>
                        <li role="presentation" class="divider"></li>
                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <a href="<c:url value="/signout"/>">
                                <i class="fa fa-fw"></i>
                                Logout
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</header>