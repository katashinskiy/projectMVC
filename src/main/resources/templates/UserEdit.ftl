<#import "parts/common.ftl" as S>
<@S.page>
User Editor

<form action="/Users" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Edit user name: </label>
        <div class="col-sm-5">
            <input class="form-control" type="text" name="userName" value="${User.userName}"/>
        </div>
    </div>
    <#list Role as role>
        <div>
            <label><input type="checkbox" name="${role}" ${User.role?seq_contains(role)?string("checked", "")}/>${role}
            </label>
        </div>
    </#list>
    <input type="hidden" value="${User.id}" name="userId"/>
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button class="btn btn-primary m-2" type="submit">Save</button>
</form>

</@S.page>