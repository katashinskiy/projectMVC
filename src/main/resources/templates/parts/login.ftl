<#include "security.ftl"/>

<#macro login path isRegistration>
  <form action="${path}" method="post">
      <div class="form-group row">
          <label class="col-sm-2 col-form-label"> User Name : </label>
          <div class="col-sm-5">
              <input type="text" name="username" class="form-control ${(usernameError??)?string("is-invalid","")}"
                     value="<#if user??>${user.username}</#if>" placeholder="UserName"/>
                   <#if usernameError??>
            <div class="invalid-feedback">
                ${usernameError}
            </div>
                   </#if>
          </div>
      </div>
      <#if isRegistration>
      <div class="form-group row">
          <label class="col-sm-2 col-form-label"> Email: </label>
          <div class="col-sm-5">
              <input class="form-control ${(emailError??)?string("is-invalid","")}" type="email" name="email"
                     value="<#if user??>${user.email}</#if>" placeholder="some@some.com"/>
                   <#if emailError??>
            <div class="invalid-feedback">
                ${emailError}
            </div>
                   </#if>
          </div>
      </div>
      </#if>

      <div class="form-group row">
          <label class="col-sm-2 col-form-label"> Password: </label>
          <div class="col-sm-5">
              <input class="form-control ${(passwordError??)?string("is-invalid","")}" type="password" name="password"
                     placeholder="Password"/>
                   <#if passwordError??>
            <div class="invalid-feedback">
                ${passwordError}
            </div>
                   </#if>
          </div>
      </div>

      <#if isRegistration>
           <div class="form-group row">
               <label class="col-sm-2 col-form-label"> Confirm password: </label>
               <div class="col-sm-5">
                   <input class="form-control ${(password2Error??)?string("is-invalid","")}" type="password"
                          name="password2" placeholder="Confirm password"/>
                   <#if password2Error??>
            <div class="invalid-feedback">
                ${password2Error}
            </div>
                   </#if>
               </div>
           </div>
      </#if>
      <input type="hidden" name="_csrf" value="${_csrf.token}"/>

      <div class="col-sm-6 m-2">
          <div class="g-recaptcha" data-sitekey="6Lf7EGcUAAAAADBOJFuYRZIujKywQoTvHXzXtsrU"></div>
        <#if captchaError??>
    <div class="alert alert-danger" role="alert">
        ${captchaError}
    </div>
        </#if>
      </div>

      <div class="form-group row">
          <div class="col-sm-10">
              <button type="submit" class="btn btn-primary">Sign in</button>
          </div>
      </div>
      <#if !isRegistration ><a href="/registration"> Add new user </a></#if>
  </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if user??>
        <button class="btn btn-primary mb-2" type="submit">Sing Out</button>
        <#else>
         <a class="btn btn-primary mb-2" href="/login" >Log in</a>
        </#if>
    </form>
</#macro>