<#import "parts/common.ftl" as S>
<#import "parts/login.ftl" as L>
<@S.page>
<#--<h5>${Username}</h5>-->
<#--${message?if_exists}-->
  <form action="/Users/profile" method="post">

      <div class="form-group row">
          <label class="col-sm-2 col-form-label"> Email: </label>
          <div class="col-sm-5">
              <input class="form-control ${(emailError??)?string("is-invalid","")}" type="email" name="email"
                     placeholder="some@some.com" value="${email!''}"/>
    <#if emailError??>
            <div class="invalid-feedback">
                ${emailError}
            </div>
    </#if>
          </div>
      </div>

      <div class="form-group row">
          <label class="col-sm-2 col-form-label"> Password: </label>
          <div class="col-sm-5">
              <input class="form-control  ${(passwordError??)?string("is-invalid","")} " type="password" name="password"
                     value="<#if password??>${password}</#if>" placeholder="PassWord"/>
             <#if passwordError??>
            <div class="invalid-feedback">
                ${passwordError}
            </div>
             </#if>
          </div>
      </div>

      <div class="form-group row">
          <label class="col-sm-2 col-form-label">Confirm password: </label>
          <div class="col-sm-5">
              <input class="form-control  ${(password2Error??)?string("is-invalid","")}" type="password"
                     name="password2" value="<#if password2??>${password2}</#if>" placeholder="Confirm password"/>
             <#if password2Error??>
            <div class="invalid-feedback">
                ${password2Error}
            </div>
             </#if>
          </div>
      </div>
      <input type="hidden" name="_csrf" value="${_csrf.token}"/>

      <div class="form-group row">
          <div class="col-sm-10">
              <button type="submit" class="btn btn-primary">Save</button>
          </div>
      </div>

  </form>
</@S.page>