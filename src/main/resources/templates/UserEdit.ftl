<#import "parts/common.ftl" as S>
<@S.page>
User Editor

<form action="/Users" method="post">
    <input type="text" name="userName" value="${User.userName}"/>
    <#list Role as role>
        <div>
            <label><input type="checkbox" name = "${role}" ${User.role?seq_contains(role)?string("checked", "")}/>${role}</label>
        </div>
    </#list>
    <input type="hidden" value="${User.id}" name="userId"/>
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit">Save</button>
</form>

</@S.page>