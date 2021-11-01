<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Wishlist</title>
  <link rel="shortcut icon" type="image/x-icon"
    href="https://res.cloudinary.com/practicaldev/image/fetch/s--E8ak4Hr1--/c_limit,f_auto,fl_progressive,q_auto,w_32/https://dev-to.s3.us-east-2.amazonaws.com/favicon.ico">
  <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
  <script src="https://unpkg.com/react@17/umd/react.development.js" crossorigin></script>
  <script src="https://unpkg.com/react-dom@17/umd/react-dom.development.js" crossorigin></script>
  <script src="https://unpkg.com/@babel/standalone/babel.min.js"></script>
  <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet" />
  <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/toastify.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/home.css" />
</head>

<body class="bg-gray-900">
  <div class="mobile">
    <div class="box shadow-md">
      <div class="flex flex-col h-full">
        <header class="container-m w-full flex justify-between items-center mt-4">
          <div class="box__left">
            <div class="logo__container">
              <a href="./">
                <div class="logo cursor-pointer">
                  <div class="logo__moon"></div>
                  <div class="logo__sun"></div>
                </div>
              </a>
            </div>
          </div>
          <div class="box__right flex items-center gap-6">
            <c:choose>
              <c:when test="${user == null}">
                <div>
                  <a href="./login">
                    <span class="uppercase font-bold">Login</span>
                  </a>
                </div>
              </c:when>
              <c:otherwise>
                <a href="./wishlist">
                  <div class="cart text-white text-2xl cursor-pointer">
                    <i class="ri-heart-fill"></i>
                    <%-- <span id="quantity" class="quantity">0</span> --%>
                  </div>
                </a>

                <div class="user cursor-pointer">
                  <span>${user.getFullName()}</span>
                  <input type="hidden" name="idUser" id="idUser" value="${user.getId()}">
                </div>
              </c:otherwise>
            </c:choose>

            <!-- <div class="cart text-white text-2xl cursor-pointer">
                  <i class="ri-shopping-cart-fill"></i>
                  <span class="quantity">10</span>
                </div>
                <div class="user cursor-pointer">
                  <div class="user__img">
                    <img src="./assets/image/user.jpg" alt="avatar" />
                  </div>
                </div> -->
          </div>
        </header>
        <main class="flex flex-col overflow-hidden flex-grow">
          <section class="mt-5">
            <div class="w-full container__search container-m">
              <div id="search" class="search"></div>
            </div>
          </section>
          <section class="mt-5 flex-grow flex overflow-hidden">
            <div class="w-full container-m flex flex-col">
              <h4 class="title uppercase">Các bản nhạc yêu thích</h4>
              <div id="top" class="album container__album mt-4 flex-grow">
                <ul class="album__list grid grid-cols-2 gap-x-6"></ul>
              </div>
            </div>
          </section>
          <section class="mt-10">
            <div class="container-m container__play">
              <div class="play">
                <span id="play-name" class="play__name">Xứng đôi cưới thôi</span>
                <div class="play__run mt-3" id="set-time">
                  <div class="play__run-circle" id="time-render"></div>
                  <audio class="hidden" id="audio" controls="controls" loop="loop" preload="none"
                    src="${pageContext.request.contextPath}/assets/music/music.mp3">
                    Trình duyệt của bạn không hỗ trợ nghe online !
                  </audio>
                </div>
                <div class="play__time flex justify-between font-light mt-1">
                  <span class="play__time-start" id="time-current">00:00</span>
                  <span class="play__time-end" id="time-duration">00:00</span>
                </div>
              </div>
            </div>
          </section>
          <section class="mt-4">
            <div class="container-m container-menu">
              <div class="menu flex justify-around items-center text-2xl">
                <div class="box__left">
                  <a href="./">
                    <i class="ri-home-fill"></i>
                  </a>
                  <a href="./wishlist">
                    <i class="ri-heart-fill"></i>
                  </a>
                </div>
                <div class="box__center">
                  <i id="play" class="ri-play-fill cursor-pointer"></i>
                </div>
                <div class="box__right">
                  <i class="ri-repeat-line"></i>
                  <a href="${pageContext.request.contextPath}/assets/music/music.mp3" id="download"
                    class="cursor-pointer">
                    <i class="ri-download-2-fill"></i>
                  </a>
                </div>
              </div>
            </div>
          </section>
        </main>
      </div>
    </div>
  </div>
  <script src="${pageContext.request.contextPath}/assets/js/toastify.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/common.js"></script>
  <script defer crossorigin src="${pageContext.request.contextPath}/assets/js/search.js" type="text/babel"></script>
  <script defer crossorigin src="${pageContext.request.contextPath}/assets/js/wishlist.js" type="text/babel"></script>
</body>

</html>