<#import "parts/common.ftl" as S>
<#import "parts/login.ftl" as L>
<@S.page>
<h5>${Username}</h5>
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
              <input class="form-control" type="password" name="password" placeholder="PassWord"/>
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