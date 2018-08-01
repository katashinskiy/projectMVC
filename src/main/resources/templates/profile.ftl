<#import "parts/common.ftl" as S>
<#import "parts/login.ftl" as L>
<@S.page>
<#--<h5>${Username}</h5>-->
    <#--${message?if_exists}-->
  <form action="/Users/profile" method="post">

      <div class="form-group row">
          <label class="col-sm-2 col-form-label"> Email: </label>
          <div class="col-sm-5">
              <input class="form-control" type="email" name="email" placeholder="some@some.com" value="${email!''}"/>
          </div>
      </div>

      <div class="form-group row">
          <label class="col-sm-2 col-form-label"> Password: </label>
          <div class="col-sm-5">
              <input class="form-control ${(errorMessageP1??)?string("is-invalid","")}  ${(errorMessageD??)?string("is-invalid","")}" type="password" name="password" value="<#if password??>${password}</#if>" placeholder="PassWord"/>
             <#if errorMessageP1??>
            <div class="invalid-feedback">
                ${errorMessageP1}
            </div>
             <#elseif errorMessageD??>
                <div class="invalid-feedback">
                    ${errorMessageD}
                </div>
             </#if>
          </div>
      </div>

      <div class="form-group row">
          <label class="col-sm-2 col-form-label">Confirm password: </label>
          <div class="col-sm-5">
              <input class="form-control  ${(errorMessageP2??)?string("is-invalid","")}" type="password" name="password2" value="<#if password2??>${password2}</#if>" placeholder="Confirm password"/>
             <#if errorMessageP2??>
            <div class="invalid-feedback">
                ${errorMessageP2}
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