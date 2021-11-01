<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Login</title>
  <link rel="shortcut icon" type="image/x-icon"
    href="https://res.cloudinary.com/practicaldev/image/fetch/s--E8ak4Hr1--/c_limit,f_auto,fl_progressive,q_auto,w_32/https://dev-to.s3.us-east-2.amazonaws.com/favicon.ico">
  <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet" />
  <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css" />
</head>

<body class="bg-gray-900">
  <div class="mobile">
    <div class="box shadow-md">
      <div class="flex flex-col">
        <div class="logo__container mt-40">
          <a href="./">
            <div class="logo cursor-pointer">
              <div class="logo__moon"></div>
              <div class="logo__sun"></div>
            </div>
          </a>
        </div>
        <section class="form mt-10">
          <form action="" method="post">
            <span style="display: block; text-align: center; color: red">${message}</span>

            <div class="form__container container-m">
              <div class="input">
                <input type="text" placeholder="Username" name="username" />
              </div>
              <div class="input">
                <input type="password" placeholder="Password" name="password" />
              </div>
              <div class="form__control mt-6 flex justify-between">
                <div class="control__left">
                  <input id="checkpass" name="checkpass" type="checkbox" />
                  <label for="checkpass">Save Password</label>
                </div>
                <div class="cursor-pointer control__right font-bold text-md">
                  <a href="./signup">Sign up</a>
                </div>
              </div>
              <button type="submit" class="form__btn">Login</button>
            </div>
          </form>
        </section>
      </div>
    </div>
  </div>
</body>

</html>