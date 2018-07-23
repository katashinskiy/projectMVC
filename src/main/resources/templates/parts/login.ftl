<#macro login path isRegistration>
  <form action="${path}" method="post">
      <div class="form-group row">
          <label class="col-sm-2 col-form-label"> User Name : </label>
          <div class="col-sm-5">
              <input type="text" name="username" class="form-control" placeholder="UserName"/>
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
              <button type="submit" class="btn btn-primary">Sign in</button>
          </div>
      </div>

      <#if !isRegistration ><a href="/registration"> Add new user </a></#if>
  </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary mb-2" type="submit">Sing Out</button>
    </form>
</#macro>